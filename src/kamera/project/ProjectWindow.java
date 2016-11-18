package kamera.project;

import kamera.project.request.ProjectRequest;
import kamera.common.*;
import kamera.common.ui.CustomSerialize;
import kamera.settings.EnviromentDataWindow;
import kamera.common.request.CameraRequest;
import kamera.utility.GlobalData;
import kamera.utility.fixMP4;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ProjectWindow extends CameraRecord {
    public ProjectWindow() {
        getGUI();
        configuareGUIListeners();
        RS485Reader.getInstance().setMainListener(this);
        new ProjectRequest(getGUI());

        ProjectRequest.getInstance().reloadProjectTree();
        ProjectRequest.getInstance().imitateCloseProject();
        try {
            new SimpleServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startCameraPreview();
        RS485Reader.getInstance().setMainListener(this);
    }


    private void configuareGUIListeners() {

        getGUI().getCameraData().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EnviromentDataWindow();
            }
        });

        getGUI().getCallForward().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RobotSteering.engineForward();
            }
        });

        getGUI().getCallBackward().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RobotSteering.engineBackward();
            }
        });

        getGUI().getCallStop().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RobotSteering.engineStop();
            }
        });

        getGUI().getCameraDown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RobotSteering.cameraDown();
            }
        });
        getGUI().getCameraUp().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RobotSteering.cameraUp();
            }
        });
        getGUI().getCameraLeft().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RobotSteering.cameraLeft();
            }
        });
        getGUI().getCameraRight().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RobotSteering.cameraRight();
            }
        });
        getGUI().getCameraReset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RobotSteering.cameraReset();
            }
        });

        getGUI().getButton4().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CameraRequest.getInstance().zoomIn();
            }
        });
        getGUI().getButton6().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CameraRequest.getInstance().zoomOut();
            }
        });
        getGUI().getZoomInAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CameraRequest.getInstance().zoomInAll();
            }
        });
        getGUI().getZoomOutAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CameraRequest.getInstance().zoomOutAll();
            }
        });
        getGUI().getStopZoom().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CameraRequest.getInstance().stopZoom();
            }
        });
        getGUI().getStartRecord().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startRecord();
            }
        });
        getGUI().getStopRecord().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopRecord();
                startCameraPreview();
                new Thread(() -> {
                    Boolean success = fixVideoFile();
                    if (success)
                        showPreviewDialog();
                }).start();
            }
        });

        getGUI().getLightValue().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                RobotSteering.cameraSetLight(value);
            }
        });

        getGUI().getSpeed().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                RobotSteering.engineSpeed(value);
            }
        });


        getGUI().getAddEvent().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewEvent();
            }
        });

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        String actionKey = "none";
        InputMap map = getGUI().getTextEvent().getInputMap();
        map.put(enterKey, actionKey);

        getGUI().getTextEvent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addNewEvent();
                }
            }
        });


        getGUI().getClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectRequest.getInstance().imitateCloseProject();
            }
        });

        getGUI().getOpenProjectList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectRequest.getInstance().imitateCloseProject();
            }
        });
        getGUI().getEditData().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectRequest.getInstance().editProjectDataByName(GlobalData.getCurrentProjectName());
            }
        });
        getGUI().getEditProject().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProject();
            }
        });

        getGUI().getPreview().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProject();
            }
        });

        getGUI().getRefreshCameraPreview().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startCameraPreview();
            }
        });
    }

    private boolean fixVideoFile() {
        return fixMP4.
                fix(GlobalData.getCurrentProjectName(),
                        GlobalData.getLastRecordFileName()
                );
    }

    private void showPreviewDialog() {
        int n = JOptionPane.showConfirmDialog(
                getGUI(), "Nowa inspekcja została utworzona. Czy chcesz teraz dodać opisy zdarzeń?",
                "Zakończono Inspekcje",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            editProject();
        } else if (n == JOptionPane.NO_OPTION) {

        }
    }

    private void clearEventsList() {
        getGUI().getEventsList().setText("");
    }

    private void addNewEvent() {
        String string = getGUI().getTextEvent().getText();

        if (string.length() == 0) return;

        if (getFileMaker() != null) {
            getFileMaker().addEvents(string);

            LogsSender.send(LogsSender.LogsIntCode.DODANO_ZDARZENIE, LogsSender.LogsString.DODANO_ZDARZENIE + " " + string);

            getGUI().getTextEvent().setText("");
            getGUI().getTextEvent().requestFocus();


            StringBuffer text = new StringBuffer();
            String separator = "";
            for (String line : getFileMaker().getEvents()) {
                text.append(separator).append(line);
                separator = System.lineSeparator();
            }
            getGUI().getEventsList().setText(text.toString());
        }
    }

    public void editProject() {
        ProjectRequest.getInstance().editProjectByName(GlobalData.getCurrentProjectName());
    }

    private void stopRecord() {
        clearEventsList();
        String string = new CustomSerialize(getGUI().getInfo()).get();
        getFileMaker().createInfoFile(string);
        ProjectRequest.getInstance().reloadProjectTree();
        LogsSender.send(LogsSender.LogsIntCode.ZAKONCZONO_INSPEKCJE, LogsSender.LogsString.ZAKONCZONO_INSPEKCJE);
    }

    void startCameraPreview() {
        releaseFilesMaker();
        reloadCamera();
        startPreview();
    }

    private void startRecord() {
        RS485Reader.getInstance().write("clearDis()");
        reloadCamera();
        startNewRecord();

        getGUI().getAddEventsPanel().setVisible(true);
        getGUI().getAboveCamera().setVisible(true);
        getGUI().getStartRecord().setEnabled(false);
        getGUI().getStopRecord().setEnabled(true);
        getGUI().getCurrentRecordTime().setVisible(true);
    }

    public void serialEvents(String inputLine) {
        System.out.println("dane dotarły do głównego widoku");
        getGUI().getTextAboveCamera().setText(RS485Reader.getInstance().getGYROSCOPE_DATA() + "%");
        getGUI().getDistance().setText(RS485Reader.getInstance().getDISTANCE_DATA() + "mm");
    }


    @Override
    public void beforeCameraCreate() {

    }

    @Override
    public void afterCreateGUI() {
        getGUI().getAddEventsPanel().setVisible(false);
        getGUI().getStartRecord().setEnabled(true);
        getGUI().getStopRecord().setEnabled(false);
        getGUI().getCurrentRecordTime().setVisible(false);
        getGUI().setVisible(true);
        getGUI().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void onReloadCamera() {

    }
}