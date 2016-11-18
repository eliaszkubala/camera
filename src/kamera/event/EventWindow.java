package kamera.event;

import kamera.common.FileMaker;
import kamera.common.LogsSender;
import kamera.common.RenderInfoFile;
import kamera.common.ui.CustomSerialize;
import kamera.event.request.CodesRequest;
import kamera.event.ui.EditEvent;
import kamera.event.ui.JArc;
import kamera.project.request.ProjectRequest;
import kamera.utility.GlobalData;
import kamera.utility.ImageUtility;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Created by TheKing on 03.04.2016.
 */
public class EventWindow implements ActionListener {

    private static FileMaker fileMaker;
    private static int eventFileID = 0;
    private static String eventProjectName;
    private EditEvent eventPreview;
    private JPopupMenu defaultMenu;
    private JArc jArc;

    public static void openWindow(String projectName, int eventID) {
        eventProjectName = projectName;
        eventFileID = eventID;

        LogsSender.send(LogsSender.LogsIntCode.EDYTOWANO_ZDARZENIE, LogsSender.LogsString.EDYTOWANO_ZDARZENIE + " " + projectName + ":" + eventFileID);

        fileMaker = new FileMaker(projectName, GlobalData.getPathForFileMaker());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EventWindow(null);
            }
        });
    }

    public EditEvent getGUI() {
        if (eventPreview == null) {
            eventPreview = new EditEvent();
            refreshJArc();
        }
        return eventPreview;
    }

    private void createJArc(int startAngle, int arcAngle) {
        jArc = new JArc(startAngle, arcAngle);
        getGUI().getPanel6().removeAll();
        jArc.setPreferredSize(new Dimension(100, 100));
        getGUI().getPanel6().add(jArc, BorderLayout.CENTER);
        getGUI().getPanel6().updateUI();
    }

    public EventWindow(String[] args) {
        getGUI();

        String info = new RenderInfoFile(GlobalData.getPathForFileMaker(getFileName(eventProjectName, eventFileID))).getString();
        new CustomSerialize(getGUI().getPanel()).set(info);
        refreshJArc();

        addListeners();
        initialize();
    }

    public static String getFileName(String eventProjectName, int eventFileID) {
        return eventProjectName + "-event_" + eventFileID + ".txt";
    }

    private void initialize() {
        String path = GlobalData.getCurrentProjectFile() + "\\" + getSnapshotName(eventProjectName, eventFileID);
        getGUI().getZdjecie().setIcon(ImageUtility.rescaleImage(new File(path), 800, 600));

        defaultRadioButtonInit();

        JTextField[] fields = new JTextField[3];
        fields[0] = getGUI().getOpis1();
        fields[1] = getGUI().getOpis2();
        fields[2] = getGUI().getOpis3();

        for (int i = 0; i < fields.length; i++) {
            fields[i].setVisible(!fields[i].getText().equals(""));
        }
    }

    private void defaultRadioButtonInit() {
        JRadioButton[] radio = new JRadioButton[4];
        radio[0] = getGUI().getRadioButton1();
        radio[1] = getGUI().getRadioButton2();
        radio[2] = getGUI().getRadioButton3();
        radio[3] = getGUI().getRadioButton4();

        Boolean selected = false;
        for (int i = 0; i < radio.length; i++) {
            selected = radio[i].isSelected();
            if (selected == true) {
                initDefaultMenu(radio[i].getName());
                break;
            }
        }
        if (!selected) {
            defaultMenu = CodesRequest.getmPipeline().getMenu(this);
            getGUI().getRadioButton1().setSelected(true);
        }
    }

    private void addListeners() {
        getGUI().getCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getGUI().setVisible(false);
            }
        });

        getGUI().getSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = new CustomSerialize(getGUI().getPanel()).get();
                fileMaker.createEventFile(eventFileID, string);
                getGUI().setVisible(false);
                ProjectRequest.getInstance().currentProjectPreview.refreshData();
            }
        });

        getGUI().getRadioButton1().addActionListener(this);
        getGUI().getRadioButton2().addActionListener(this);
        getGUI().getRadioButton3().addActionListener(this);
        getGUI().getRadioButton4().addActionListener(this);
        getGUI().getSelectEvent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                defaultMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        ChangeListener sliderListener = e -> refreshJArc();

        getGUI().getSzerokosc().addChangeListener(sliderListener);
        getGUI().getPrzesuniecie().addChangeListener(sliderListener);

    }

    private void refreshJArc() {
        createJArc(getGUI().getPrzesuniecie().getValue(), getGUI().getSzerokosc().getValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton o = (JRadioButton) e.getSource();
        String name = o.getName();
        System.out.println(name);

        initDefaultMenu(name);
    }

    public void initDefaultMenu(String name) {
        if (name.equals("rurociag")) {
            defaultMenu = CodesRequest.getmPipeline().getMenu(this);
        } else if (name.equals("inne")) {
            defaultMenu = CodesRequest.getmOthers().getMenu(this);
        }
        if (name.equals("studzienka")) {
            defaultMenu = CodesRequest.getmManhole().getMenu(this);
        } else if (name.equals("inwentaryzacja")) {
            defaultMenu = CodesRequest.getmStocktaking().getMenu(this);
        }
    }

    public void passActionPerformed(String... args) {

        JTextField[] fields = new JTextField[3];
        fields[0] = getGUI().getOpis1();
        fields[1] = getGUI().getOpis2();
        fields[2] = getGUI().getOpis3();

        for (int i = 0; i < fields.length; i++) fields[i].setText("");

        for (int i = 0; i < fields.length; i++) {
            if (args.length >= i + 1) {
                fields[i].setVisible(true);
                fields[i].setText(args[i]);
            } else {
                fields[i].setVisible(false);
            }
        }
        getGUI().getPanel3().updateUI();

    }

    public static String getSnapshotName(String currentFileName, int second) {
        return currentFileName + "event_" + second + "-snapshot.png";
    }
}
