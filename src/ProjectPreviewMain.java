import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.itextpdf.text.DocumentException;
import view.ProjectPreview;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import utility.FrameUtility;
import utility.GlobalData;
import utility.ListWrapper;
import utility.RenderInfoFile;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjectPreviewMain {

    private static String projectPath;
    private static Chart2d chart2d;
    private Canvas canvas;
    private MediaPlayerFactory factory;
    private EmbeddedMediaPlayer mediaPlayer;
    private CanvasVideoSurface videoSurface;
    private ProjectPreview preview;
    private String currentFileName;
    private RenderSubtitlesFile events;
    private RenderSubtitlesFile gyroData;
    private RenderSubtitlesFile distance;

    public static void main(final String[] args) {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                chart2d = new Chart2d();
                new ProjectPreviewMain(projectPath);
            }
        });
    }

    public ProjectPreviewMain(String projectPath) {
        this.projectPath = projectPath;
        chart2d = new Chart2d();
        createVideo();
        createGUI();
        addListeners();
        addScheduleTasks();

        try {
            addItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FrameUtility.maximize(getGUI());
    }

    private void addScheduleTasks() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                callEverySecond();
            }
        }, 0, 500);
}

    private void connectedDataFiles() throws IOException {
        String path = GlobalData.CURRENT_DIR_FILE.getAbsolutePath();
        if (projectPath != null) {
            path = path + "\\" + projectPath;
        }

        events = null;
        gyroData = null;

        Files.walk(Paths.get(path)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                String fileName = filePath.getFileName().toString();
                if (fileName.contains(currentFileName))
                    if (fileName.contains(".txt")) {
                        String childPath = filePath.toString();

                        if (fileName.contains("events")) {
                            events = new RenderSubtitlesFile(childPath);
                        }

                        if (fileName.contains("distanceInMm")) {
                            distance = new RenderSubtitlesFile(childPath);
                        }

                        if (fileName.contains("gyroData")) {
                            gyroData = new RenderSubtitlesFile(childPath);
                            chart2d.setTrace(childPath);
                            chart2d.saveImage(Project.getDirOfProject(projectPath) + "\\" + currentFileName);
                        }

                        if (fileName.contains("info")) {
                            String info = new RenderInfoFile(childPath).getString();
                            new CustomSerialize(getGUI().getInfo()).set(info);
                        }
                    }
            }
        });

        if (events != null) {

            getGUI().getEventScrollPane().setVisible(true);
            getGUI().getEventSeparator().setVisible(true);

            getGUI().getTable1().setModel(events.getEventsTableModel(currentFileName));
            getGUI().getTable1().invalidate();

            getGUI().getTable1().removeMouseListener(mouseAdapter);
            getGUI().getTable1().addMouseListener(mouseAdapter);


        } else {
            getGUI().getEventScrollPane().setVisible(false);
            getGUI().getEventSeparator().setVisible(false);
        }
    }

    MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent event) {
            super.mousePressed(event);
            JTable jTable = (JTable) event.getSource();
            System.out.println(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
            jTable.setColumnSelectionInterval(0, jTable.getModel().getColumnCount() - 1);
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            super.mouseReleased(event);
            JTable jTable = (JTable) event.getSource();
            System.out.println(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
            jTable.setColumnSelectionInterval(0, jTable.getModel().getColumnCount() - 1);
        }

        @Override
        public void mouseClicked(MouseEvent event) {
            super.mouseClicked(event);
            JTable jTable = (JTable) event.getSource();
            System.out.println(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
            jTable.setColumnSelectionInterval(0, jTable.getModel().getColumnCount() - 1);

            mediaPlayer.start();
            int index = jTable.getSelectedRow();
            int second = Integer.parseInt((String) jTable.getValueAt(index, 1));
            float position = (float) second / (float) getLength();


            if (event.getClickCount() == 1) {
                mediaPlayer.start();
                mediaPlayer.setPosition(position);
                refreshProgressBar();
            }

            if (event.getClickCount() == 2) {
                if (mediaPlayer.isPlaying()) mediaPlayer.pause();

                mediaPlayer.saveSnapshot();
                BufferedImage bi = mediaPlayer.getSnapshot();
                File outputfile = new File(GlobalData.getCurrentProjectFile() + "\\" + EventPreviewMain.getSnapshotName(currentFileName, second));
                try {
                    ImageIO.write(bi, "png", outputfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                EventPreviewMain.openWindow(currentFileName, second);

            }

        }
    };

    private void addItems() throws IOException {
        final DefaultListModel model = new DefaultListModel();
        String path = GlobalData.CURRENT_DIR_FILE.getAbsolutePath();
        if (projectPath != null) {
            path = path + "\\" + projectPath;
        }

        Files.walk(Paths.get(path)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                if (filePath.getFileName().toString().contains("mp4")) {
                    String childPath = filePath.toString();
                    String strPath = childPath.substring(childPath.lastIndexOf("\\") + 1, childPath.length());
                    ListWrapper listWrapper = new ListWrapper(strPath, childPath);
                    model.addElement(listWrapper);
                }
            }
        });

        getGUI().getList1().setCellRenderer(new ListWrapperRenderer());
        getGUI().getList1().setModel(model);
        getGUI().getList1().invalidate();

        if (getGUI().getList1().getModel().getSize() > 0) {
            getGUI().getList1().setSelectedIndex(0);
        }

    }

    private void addListeners() {

        getGUI().getClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGUI().setVisible(false);
                chart2d.setVisible(false);
            }
        });

        getGUI().getGenerateReports().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new PDFCreator(projectPath, projectPath);
                } catch (DocumentException e1) {
                    JOptionPane.showMessageDialog(null, e.toString(), "Error " + e1,
                            JOptionPane.ERROR_MESSAGE);
                } catch (IOException e2) {
                    JOptionPane.showMessageDialog(null, e.toString(), "Error " + e2,
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        getGUI().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("okno podglad projektu zamkniete");
                chart2d.setVisible(false);
                chart2d.dispose();
            }
        });

        getGUI().getShowChart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chart2d.setVisible(!chart2d.isVisible());
            }
        });

        getGUI().getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        });
        getGUI().getPauseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.pause();
            }
        });

        final boolean[] setPosition = {false};
        getGUI().getSlider1().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (setPosition[0]) {
                    float newPos = (float) getGUI().getSlider1().getValue() / 100;
                    mediaPlayer.setPosition((float) newPos);

                    System.out.println(newPos);
                }
            }
        });

        getGUI().getSlider1().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setPosition[0] = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setPosition[0] = false;
            }
        });

        getGUI().getStopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.stop();
                getGUI().getSlider1().setValue(0);
            }
        });

        getGUI().getList1().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListWrapper listWrapper = (ListWrapper) getGUI().getList1().getSelectedValue();
                playPreview(listWrapper.getData().toString());
            }
        });

        getGUI().getList1().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (SwingUtilities.isRightMouseButton(e)) {
                    JList list = (JList) e.getSource();
                    int row = list.locationToIndex(e.getPoint());
                    list.setSelectedIndex(row);
                    ListWrapper listWrapper = (ListWrapper) list.getSelectedValue();

                    String path = listWrapper.getData().toString();
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem delete = new JMenuItem("Usuń");
                    delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mediaPlayer.stop();
                            Project.getInstance().removeProjectByPath(path);
                            try {
                                addItems();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    menu.add(delete);
                    menu.show(list, e.getX(), e.getY());

                }
            }
        });

        getGUI().getSaveInfo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String info = new CustomSerialize(getGUI().getInfo()).get();
                FileMaker fileMaker = new FileMaker(currentFileName, GlobalData.CURRENT_DIR_FILE.toString() + "\\" + projectPath + "\\");
                fileMaker.createInfoFile(info);
                fileMaker.release();
            }
        });

    }

    private void playPreview(String path) {
        File movieFile = new File(path);

        currentFileName = movieFile.getName().replace(".mp4", "");

        getGUI().getTextAboveCamera2().setVisible(false);

        String[] options = {
                ":file-caching=0",
                ":network-caching=500",
                ":no-sout-rtp-sap",
                ":no-sout-standard-sap",
                ":sout-all",
                ":rtsp-caching=200",
                ":sout-keep"};

        mediaPlayer.playMedia(path, options);
        try {
            connectedDataFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getCurrentSecond() {
        return (int) (mediaPlayer.getTime() / 1000);
    }

    private int getLength() {
        return (int) mediaPlayer.getLength() / 1000;
    }

    int textAbveSecondsLength = 7;
    int currentTextAboveSecondsLength = 0;

    private void callEverySecond() {
        if (!mediaPlayer.isPlaying()) return;

        getGUI().getSlider1().setMaximum(100);
        getGUI().getSlider1().setMinimum(0);
        refreshProgressBar();
        getGUI().getTime().setText(String.valueOf(getCurrentSecond()) + "/" + getLength());


        chart2d.setMarker(getCurrentSecond());
        chart2d.setPositionAs(getGUI().getPanel1());

        JTable jTable = getGUI().getTable1();
        int localSecond = 0;


        if (events != null) {
            String eventText = "";
            for (int i = 0; i < jTable.getModel().getRowCount(); i++) {
                //ListWrapper item = (ListWrapper) jTable.getModel().getElementAt(i);
                String second2 = (String) jTable.getValueAt(i, 1);
                int second = Integer.valueOf(second2);
                if (getCurrentSecond() == second) {
                    localSecond = second;
                    getGUI().getTable1().clearSelection();
                    getGUI().getTable1().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    getGUI().getTable1().setColumnSelectionInterval(0, getGUI().getTable1().getModel().getColumnCount() - 1);
                    getGUI().getTable1().setRowSelectionInterval(i, i);
                    eventText = events.getSubtitle(getCurrentSecond());
                    getGUI().getTextAboveCamera2().setText(eventText + System.lineSeparator() + new RenderSubtitlesFile(currentFileName).getLongDescription(localSecond, currentFileName));
                }
            }

            if (!eventText.equals("")) {
                getGUI().getTextAboveCamera2().setVisible(true);
                currentTextAboveSecondsLength = 0;
            } else {
                currentTextAboveSecondsLength++;
                if (currentTextAboveSecondsLength > textAbveSecondsLength) {
                    getGUI().getTextAboveCamera2().setVisible(false);
                }
            }

        }

        if (gyroData != null) {
            String gyroDataText = gyroData.getSubtitle(getCurrentSecond());
            if (!gyroDataText.equals(""))
                getGUI().getTextAboveCamera().setText(gyroDataText);
        }

        if (distance != null) {
            String distanceS = distance.getSubtitle(getCurrentSecond());
            if (!distanceS.equals("")) {
                float distanceF = Float.parseFloat(distanceS) / 10 / 100;
                DecimalFormat df = new DecimalFormat("#.000");
                getGUI().getDistance().setText(new BigDecimal(distanceF).setScale(2, RoundingMode.HALF_EVEN).floatValue() + "m");
            }
        }

    }

    private void refreshProgressBar() {
        getGUI().getSlider1().setValue((int) (mediaPlayer.getPosition() * 100));
    }

    private void createGUI() {
        getGUI();
        getGUI().getPanel1().add(canvas);
        getGUI().setVisible(true);
        getGUI().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createVideo() {
        canvas = new Canvas();
        factory = new MediaPlayerFactory();
        mediaPlayer = factory.newEmbeddedMediaPlayer();
        videoSurface = factory.newVideoSurface(canvas);
        mediaPlayer.setVideoSurface(videoSurface);
    }

    public void refreshData() {
        try {
            connectedDataFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ProjectPreview getGUI() {
        if (preview== null)
            preview = new ProjectPreview();
        return preview;
    }
}
