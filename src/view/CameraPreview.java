package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Mon Dec 14 17:36:28 CET 2015
 */



/**
 * @author fdfdsafds fdasfas
 */
public class CameraPreview extends JFrame {
    public CameraPreview() {
        initComponents();
    }

    private void createUIComponents() {
        // TODO: addOld custom component creation code here
    }

    public JTree getProjectsTree() {
        return projectsTree;
    }

    public JButton getAddProject() {
        return addProject;
    }

    public JMenuItem getOpenProjectList() {
        return openProjectList;
    }

    public JMenuItem getEditProject() {
        return editProject;
    }

    public JMenuItem getEditData() {
        return editData;
    }

    public JMenu getMenuProject() {
        return menuProject;
    }

    public JMenuItem getRefreshCameraPreview() {
        return refreshCameraPreview;
    }

    public JPanel getAddEventsPanel() {
        return addEventsPanel;
    }

    public JTextField getCurrentRecordTime() {
        return currentRecordTime;
    }

    public JButton getCallForward() {
        return callForward;
    }

    public JButton getCallBackward() {
        return callBackward;
    }

    public JButton getCallStop() {
        return callStop;
    }

    public JPanel getCameraParent() {
        return cameraParent;
    }

    private void layeredPane1ComponentResized(ComponentEvent e) {
        getCameraPanel().setSize(new Dimension(e.getComponent().getWidth(), e.getComponent().getHeight()));
        getCameraPanel().updateUI();
        getCameraPanel().setLocation(new Point(0,0));

        getAboveCamera2().setLocation(new Point( e.getComponent().getWidth() - getAboveCamera2().getWidth() - 10, 10));

        getCameraPanel().updateUI();

    }

    public JPanel getAboveCamera() {
        return aboveCamera;
    }

    public JTextPane getTextAboveCamera() {
        return textAboveCamera;
    }

    public JSlider getLightValue() {
        return lightValue;
    }

    public JButton getPreview() {
        return preview;
    }

    public JButton getCameraUp() {
        return cameraUp;
    }

    public JButton getCameraLeft() {
        return cameraLeft;
    }

    public JButton getCameraReset() {
        return cameraReset;
    }

    public JButton getCameraRight() {
        return cameraRight;
    }

    public JButton getCameraDown() {
        return cameraDown;
    }

    public JButton getClose() {
        return close;
    }

    public JMenuItem getCameraData() {
        return cameraData;
    }

    public JTextArea getEventsList() {
        return eventsList;
    }

    public JSlider getSpeed() {
        return speed;
    }

    public JPanel getAboveCamera2() {
        return aboveCamera2;
    }

    public JTextPane getDistance() {
        return distance;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        menuBar1 = new JMenuBar();
        menuProject = new JMenu();
        openProjectList = new JMenuItem();
        menu3 = new JMenu();
        editProject = new JMenuItem();
        editData = new JMenuItem();
        menu2 = new JMenu();
        menu1 = new JMenu();
        refreshCameraPreview = new JMenuItem();
        cameraData = new JMenuItem();
        mainSplitPane = new JSplitPane();
        leftSplitPane = new JPanel();
        panel5 = new JPanel();
        label1 = compFactory.createLabel("Wybierz projekt");
        scrollPane3 = new JScrollPane();
        projectsTree = new JTree();
        addProject = new JButton();
        rightSplitPane = new JSplitPane();
        nagrano = new JTabbedPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        button4 = new JButton();
        button6 = new JButton();
        zoomInAll = new JButton();
        zoomOutAll = new JButton();
        stopZoom = new JButton();
        label26 = new JLabel();
        lightValue = new JSlider();
        panel3 = new JPanel();
        label27 = new JLabel();
        speed = new JSlider();
        callBackward = new JButton();
        callStop = new JButton();
        callForward = new JButton();
        panel4 = new JPanel();
        cameraUp = new JButton();
        cameraLeft = new JButton();
        cameraReset = new JButton();
        cameraRight = new JButton();
        cameraDown = new JButton();
        panel6 = new JPanel();
        startRecord = new JButton();
        stopRecord = new JButton();
        currentRecordTime = new JTextField();
        addEventsPanel = new JPanel();
        textEvent = new JTextPane();
        addEvent = new JButton();
        scrollPane2 = new JScrollPane();
        eventsList = new JTextArea();
        close = new JButton();
        preview = new JButton();
        scrollPane1 = new JScrollPane();
        info = new JPanel();
        label19 = new JLabel();
        label2 = new JLabel();
        nazwaFirmy = new JTextField();
        label3 = new JLabel();
        numerOdcinka = new JTextField();
        label8 = new JLabel();
        miasto = new JTextField();
        label18 = new JLabel();
        label5 = new JLabel();
        symbolStudni = new JTextField();
        label4 = new JLabel();
        textField3 = new JTextField();
        label23 = new JLabel();
        spinner4 = new JSpinner();
        label24 = new JLabel();
        spinner5 = new JSpinner();
        label25 = new JLabel();
        spinner6 = new JSpinner();
        label20 = new JLabel();
        label7 = new JLabel();
        textField6 = new JTextField();
        label6 = new JLabel();
        textField5 = new JTextField();
        label15 = new JLabel();
        spinner1 = new JSpinner();
        label21 = new JLabel();
        spinner2 = new JSpinner();
        label22 = new JLabel();
        spinner3 = new JSpinner();
        label11 = new JLabel();
        label17 = new JLabel();
        inspekcja = new JComboBox<>();
        label9 = new JLabel();
        material = new JComboBox<>();
        label10 = new JLabel();
        rozmiarRury = new JComboBox<>();
        label12 = new JLabel();
        przrekroj = new JComboBox<>();
        label13 = new JLabel();
        kierunekJazdy = new JComboBox<>();
        label14 = new JLabel();
        typStudni = new JComboBox<>();
        label16 = new JLabel();
        typInstalacji = new JComboBox<>();
        cameraParent = new JPanel();
        layeredPane1 = new JLayeredPane();
        aboveCamera = new JPanel();
        textAboveCamera = new JTextPane();
        aboveCamera2 = new JPanel();
        distance = new JTextPane();
        camera = new JPanel();

        //======== this ========
        setMinimumSize(new Dimension(800, 600));
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());

        //======== menuBar1 ========
        {

            //======== menuProject ========
            {
                menuProject.setText("Projekt");

                //---- openProjectList ----
                openProjectList.setText("Zamknij");
                menuProject.add(openProjectList);

                //======== menu3 ========
                {
                    menu3.setText("Edytuj");

                    //---- editProject ----
                    editProject.setText("Projekt");
                    menu3.add(editProject);

                    //---- editData ----
                    editData.setText("Dane");
                    menu3.add(editData);
                }
                menuProject.add(menu3);
            }
            menuBar1.add(menuProject);

            //======== menu2 ========
            {
                menu2.setText("Opcje");

                //======== menu1 ========
                {
                    menu1.setText("Kamera");

                    //---- refreshCameraPreview ----
                    refreshCameraPreview.setText("Od\u015bwie\u017c po\u0142\u0105czenie");
                    menu1.add(refreshCameraPreview);
                }
                menu2.add(menu1);

                //---- cameraData ----
                cameraData.setText("Dane \u015brodowiskowe");
                menu2.add(cameraData);
            }
            menuBar1.add(menu2);
        }
        setJMenuBar(menuBar1);

        //======== mainSplitPane ========
        {
            mainSplitPane.setDividerSize(0);
            mainSplitPane.setDividerLocation(250);

            //======== leftSplitPane ========
            {
                leftSplitPane.setBackground(new Color(214, 217, 223));

                // JFormDesigner evaluation mark
                leftSplitPane.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), leftSplitPane.getBorder())); leftSplitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

                leftSplitPane.setLayout(new BoxLayout(leftSplitPane, BoxLayout.X_AXIS));

                //======== panel5 ========
                {
                    panel5.setBackground(new Color(214, 217, 223));
                    panel5.setLayout(new BorderLayout());
                    panel5.add(label1, BorderLayout.PAGE_START);

                    //======== scrollPane3 ========
                    {

                        //---- projectsTree ----
                        projectsTree.setModel(new DefaultTreeModel(
                            new DefaultMutableTreeNode("(Projekty)") {
                                {
                                    DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("projekt 1");
                                        node1.add(new DefaultMutableTreeNode("nagranie 1"));
                                        node1.add(new DefaultMutableTreeNode("nagranie 2"));
                                        node1.add(new DefaultMutableTreeNode("nagranie 3"));
                                    add(node1);
                                    node1 = new DefaultMutableTreeNode("projekt 2");
                                        node1.add(new DefaultMutableTreeNode("nagranie 1"));
                                        node1.add(new DefaultMutableTreeNode("nagranie 2"));
                                    add(node1);
                                    node1 = new DefaultMutableTreeNode("projekt 3");
                                        node1.add(new DefaultMutableTreeNode("nagranie 1"));
                                        node1.add(new DefaultMutableTreeNode("nagranie 2"));
                                        node1.add(new DefaultMutableTreeNode("nagranie 3"));
                                    add(node1);
                                }
                            }));
                        projectsTree.setRootVisible(false);
                        scrollPane3.setViewportView(projectsTree);
                    }
                    panel5.add(scrollPane3, BorderLayout.CENTER);

                    //---- addProject ----
                    addProject.setText("Dodaj projekt");
                    panel5.add(addProject, BorderLayout.PAGE_END);
                }
                leftSplitPane.add(panel5);
            }
            mainSplitPane.setLeftComponent(leftSplitPane);

            //======== rightSplitPane ========
            {
                rightSplitPane.setDividerSize(5);
                rightSplitPane.setDividerLocation(300);

                //======== nagrano ========
                {

                    //======== panel1 ========
                    {
                        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
                        panel1.setLayout(new FormLayout(
                            "${growing-button}, $lcgap, ${growing-button}",
                            "3*(default), $lgap, default, $lgap, bottom:default:grow"));

                        //======== panel2 ========
                        {
                            panel2.setBorder(new TitledBorder("Kamera"));
                            panel2.setLayout(new GridBagLayout());
                            ((GridBagLayout)panel2.getLayout()).columnWidths = new int[] {0, 0, 0};
                            ((GridBagLayout)panel2.getLayout()).rowHeights = new int[] {0, 0, 7, 0, 0};
                            ((GridBagLayout)panel2.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0E-4};
                            ((GridBagLayout)panel2.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

                            //---- button4 ----
                            button4.setText("przybliz");
                            panel2.add(button4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- button6 ----
                            button6.setText("oddal");
                            panel2.add(button6, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- zoomInAll ----
                            zoomInAll.setText("przybliz ca\u0142kiem");
                            panel2.add(zoomInAll, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- zoomOutAll ----
                            zoomOutAll.setText("oddal ca\u0142kiem");
                            panel2.add(zoomOutAll, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- stopZoom ----
                            stopZoom.setText("zatrzymaj");
                            panel2.add(stopZoom, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- label26 ----
                            label26.setText("Nat\u0119\u017cenie \u015bwiat\u0142a");
                            panel2.add(label26, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- lightValue ----
                            lightValue.setMaximum(3);
                            lightValue.setValue(0);
                            panel2.add(lightValue, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));
                        }
                        panel1.add(panel2, CC.xywh(1, 1, 3, 1, CC.DEFAULT, CC.FILL));

                        //======== panel3 ========
                        {
                            panel3.setBorder(new TitledBorder("Silnik"));
                            panel3.setLayout(new GridBagLayout());
                            ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
                            ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0};
                            ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {1.0, 0.0, 1.0, 1.0E-4};
                            ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

                            //---- label27 ----
                            label27.setText("Pr\u0119dko\u015b\u0107");
                            panel3.add(label27, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- speed ----
                            speed.setMaximum(20);
                            speed.setValue(10);
                            panel3.add(speed, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- callBackward ----
                            callBackward.setText("Wstecz");
                            panel3.add(callBackward, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- callStop ----
                            callStop.setText("zatrzymaj");
                            panel3.add(callStop, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- callForward ----
                            callForward.setText("Naprz\u00f3d");
                            panel3.add(callForward, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));
                        }
                        panel1.add(panel3, CC.xywh(1, 2, 3, 1, CC.DEFAULT, CC.CENTER));

                        //======== panel4 ========
                        {
                            panel4.setBorder(new TitledBorder("Oko kamery"));
                            panel4.setLayout(new GridBagLayout());
                            ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
                            ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                            ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0, 1.0E-4};
                            ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0E-4};

                            //---- cameraUp ----
                            cameraUp.setText("G\u00f3ra");
                            panel4.add(cameraUp, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- cameraLeft ----
                            cameraLeft.setText("Lewo");
                            panel4.add(cameraLeft, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- cameraReset ----
                            cameraReset.setText("Reset");
                            panel4.add(cameraReset, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- cameraRight ----
                            cameraRight.setText("Prawo");
                            panel4.add(cameraRight, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                            //---- cameraDown ----
                            cameraDown.setText("D\u00f3\u0142");
                            panel4.add(cameraDown, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));
                        }
                        panel1.add(panel4, CC.xywh(1, 3, 3, 1, CC.DEFAULT, CC.CENTER));

                        //======== panel6 ========
                        {
                            panel6.setBorder(new TitledBorder("Zarz\u0105dzaj nagrywaniem"));
                            panel6.setLayout(new FormLayout(
                                "2*(default:grow, $lcgap), default",
                                "default, $lgap, default"));

                            //---- startRecord ----
                            startRecord.setText("nagrywaj");
                            panel6.add(startRecord, CC.xy(1, 1));

                            //---- stopRecord ----
                            stopRecord.setText("zatrzymaj");
                            panel6.add(stopRecord, CC.xy(3, 1));

                            //---- currentRecordTime ----
                            currentRecordTime.setHorizontalAlignment(SwingConstants.LEFT);
                            currentRecordTime.setText("1");
                            currentRecordTime.setEnabled(false);
                            currentRecordTime.setBackground(Color.white);
                            panel6.add(currentRecordTime, CC.xy(5, 1));

                            //======== addEventsPanel ========
                            {
                                addEventsPanel.setBorder(new TitledBorder(null, "Dodaj zdarzenie", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION));
                                addEventsPanel.setLayout(new FormLayout(
                                    "default:grow, $lcgap, 95dlu:grow, $lcgap, default:grow",
                                    "default, $lgap, default"));

                                //---- textEvent ----
                                textEvent.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                                addEventsPanel.add(textEvent, CC.xywh(1, 1, 4, 1, CC.DEFAULT, CC.FILL));

                                //---- addEvent ----
                                addEvent.setText("Dodaj");
                                addEventsPanel.add(addEvent, CC.xy(5, 1, CC.DEFAULT, CC.TOP));

                                //======== scrollPane2 ========
                                {

                                    //---- eventsList ----
                                    eventsList.setEnabled(false);
                                    eventsList.setLineWrap(true);
                                    scrollPane2.setViewportView(eventsList);
                                }
                                addEventsPanel.add(scrollPane2, CC.xywh(1, 3, 5, 1));
                            }
                            panel6.add(addEventsPanel, CC.xywh(1, 3, 5, 1));
                        }
                        panel1.add(panel6, CC.xywh(1, 5, 3, 1));

                        //---- close ----
                        close.setText("Zamknij");
                        panel1.add(close, CC.xy(1, 7));

                        //---- preview ----
                        preview.setText("Podgl\u0105g projektu");
                        panel1.add(preview, CC.xy(3, 7));
                    }
                    nagrano.addTab("Sterowanie i Nagrywanie", panel1);

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setBorder(new EmptyBorder(5, 5, 5, 5));

                        //======== info ========
                        {
                            info.setLayout(new FormLayout(
                                "2*(default:grow)",
                                "14dlu, 3*(min), 14dlu, 2*(min), 3*(default), 14dlu, 2*(min), 3*(default), 14dlu, 8*(min), $lgap, default"));

                            //---- label19 ----
                            label19.setText("Og\u00f3lne");
                            info.add(label19, CC.xywh(1, 1, 2, 1));

                            //---- label2 ----
                            label2.setText("Nazwa firmy");
                            info.add(label2, CC.xy(1, 2));

                            //---- nazwaFirmy ----
                            nazwaFirmy.setToolTipText("nazwaFirmy");
                            nazwaFirmy.setName("nazwaFirmy");
                            info.add(nazwaFirmy, CC.xy(2, 2));

                            //---- label3 ----
                            label3.setText("Numer odcinka");
                            info.add(label3, CC.xy(1, 3));

                            //---- numerOdcinka ----
                            numerOdcinka.setToolTipText("numerOdcinka");
                            numerOdcinka.setName("numerOdcinka");
                            info.add(numerOdcinka, CC.xy(2, 3));

                            //---- label8 ----
                            label8.setText("Miasto");
                            info.add(label8, CC.xy(1, 4));

                            //---- miasto ----
                            miasto.setToolTipText("miasto");
                            miasto.setName("miasto");
                            info.add(miasto, CC.xy(2, 4));

                            //---- label18 ----
                            label18.setText("Studnia pocz\u0105tkowa");
                            info.add(label18, CC.xywh(1, 5, 2, 1));

                            //---- label5 ----
                            label5.setText("Symbol studni");
                            info.add(label5, CC.xy(1, 6));

                            //---- symbolStudni ----
                            symbolStudni.setToolTipText("poczatkowaSymbolStudni");
                            symbolStudni.setName("poczatkowaSymbolStudni");
                            info.add(symbolStudni, CC.xy(2, 6));

                            //---- label4 ----
                            label4.setText("Ulica poczatkowa");
                            info.add(label4, CC.xy(1, 7));

                            //---- textField3 ----
                            textField3.setToolTipText("poczatkowaUlica");
                            textField3.setName("poczatkowaUlica");
                            info.add(textField3, CC.xy(2, 7));

                            //---- label23 ----
                            label23.setText("Rz\u0119dna kinety (m)");
                            info.add(label23, CC.xy(1, 8));

                            //---- spinner4 ----
                            spinner4.setToolTipText("poczatkowaRzednaKinety");
                            spinner4.setModel(new SpinnerNumberModel(0, 0, null, 1));
                            spinner4.setName("poczatkowaRzednaKinety");
                            info.add(spinner4, CC.xy(2, 8));

                            //---- label24 ----
                            label24.setText("Rozmiar (mm)");
                            info.add(label24, CC.xy(1, 9));

                            //---- spinner5 ----
                            spinner5.setToolTipText("mm");
                            spinner5.setModel(new SpinnerNumberModel(0, 0, null, 1));
                            spinner5.setName("poczatkowaRozmiar");
                            info.add(spinner5, CC.xy(2, 9));

                            //---- label25 ----
                            label25.setText("Zg\u0142\u0119bienie (m)");
                            info.add(label25, CC.xy(1, 10));

                            //---- spinner6 ----
                            spinner6.setToolTipText("m");
                            spinner6.setModel(new SpinnerNumberModel(0, 0, null, 1));
                            spinner6.setName("poczatkowaZaglebienie");
                            info.add(spinner6, CC.xy(2, 10));

                            //---- label20 ----
                            label20.setText("Studnia ko\u0144cowa");
                            info.add(label20, CC.xywh(1, 11, 2, 1));

                            //---- label7 ----
                            label7.setText("Symbol studni");
                            info.add(label7, CC.xy(1, 12));

                            //---- textField6 ----
                            textField6.setName("koncowaSymbolStudni");
                            info.add(textField6, CC.xy(2, 12));

                            //---- label6 ----
                            label6.setText("Ulica ko\u0144cowa");
                            info.add(label6, CC.xy(1, 13));

                            //---- textField5 ----
                            textField5.setName("koncowaUlica");
                            info.add(textField5, CC.xy(2, 13));

                            //---- label15 ----
                            label15.setText("Rz\u0119dna kinety (m)");
                            info.add(label15, CC.xy(1, 14));

                            //---- spinner1 ----
                            spinner1.setToolTipText("m");
                            spinner1.setModel(new SpinnerNumberModel(0, 0, null, 1));
                            spinner1.setName("koncowaRzednaKinety");
                            info.add(spinner1, CC.xy(2, 14));

                            //---- label21 ----
                            label21.setText("Rozmiar (mm)");
                            info.add(label21, CC.xy(1, 15));

                            //---- spinner2 ----
                            spinner2.setToolTipText("mm");
                            spinner2.setModel(new SpinnerNumberModel(0, 0, null, 1));
                            spinner2.setName("koncowaRozmiar");
                            info.add(spinner2, CC.xy(2, 15));

                            //---- label22 ----
                            label22.setText("Zag\u0142\u0119bienie (m)");
                            info.add(label22, CC.xy(1, 16));

                            //---- spinner3 ----
                            spinner3.setToolTipText("m");
                            spinner3.setModel(new SpinnerNumberModel(0, 0, null, 1));
                            spinner3.setName("koncowaZaglebienie");
                            info.add(spinner3, CC.xy(2, 16));

                            //---- label11 ----
                            label11.setText("Opis kana\u0142u");
                            info.add(label11, CC.xy(1, 17));

                            //---- label17 ----
                            label17.setText("Rodzaj inspekcji");
                            info.add(label17, CC.xy(1, 18));

                            //---- inspekcja ----
                            inspekcja.setModel(new DefaultComboBoxModel<>(new String[] {
                                "Inspekcja"
                            }));
                            inspekcja.setName("inspekcja");
                            info.add(inspekcja, CC.xy(2, 18));

                            //---- label9 ----
                            label9.setText("Materia\u0142 ");
                            info.add(label9, CC.xy(1, 19));

                            //---- material ----
                            material.setModel(new DefaultComboBoxModel<>(new String[] {
                                "Beton",
                                "Plastik",
                                "Stal"
                            }));
                            material.setName("material");
                            info.add(material, CC.xy(2, 19));

                            //---- label10 ----
                            label10.setText("Rozmiar rury");
                            info.add(label10, CC.xy(1, 20));

                            //---- rozmiarRury ----
                            rozmiarRury.setModel(new DefaultComboBoxModel<>(new String[] {
                                "100",
                                "200",
                                "300",
                                "400",
                                "500",
                                "600",
                                "700",
                                "800",
                                "900",
                                "1000",
                                "1100"
                            }));
                            rozmiarRury.setName("rozmiarRury");
                            info.add(rozmiarRury, CC.xy(2, 20));

                            //---- label12 ----
                            label12.setText("Przekroj");
                            info.add(label12, CC.xy(1, 21));

                            //---- przrekroj ----
                            przrekroj.setModel(new DefaultComboBoxModel<>(new String[] {
                                "Ko\u0142owy",
                                "Tr\u00f3jk\u0105tny",
                                "Kwadratowy"
                            }));
                            przrekroj.setName("przekroj");
                            info.add(przrekroj, CC.xy(2, 21));

                            //---- label13 ----
                            label13.setText("Kierunek jazdy");
                            info.add(label13, CC.xy(1, 22));

                            //---- kierunekJazdy ----
                            kierunekJazdy.setModel(new DefaultComboBoxModel<>(new String[] {
                                "Pod pr\u0105d",
                                "Z pr\u0105dem"
                            }));
                            kierunekJazdy.setName("kierunekJazdy");
                            info.add(kierunekJazdy, CC.xy(2, 22));

                            //---- label14 ----
                            label14.setText("Typ studni");
                            info.add(label14, CC.xy(1, 23));

                            //---- typStudni ----
                            typStudni.setModel(new DefaultComboBoxModel<>(new String[] {
                                "Pocz\u0105tkowa",
                                "Ko\u0144cowa"
                            }));
                            typStudni.setName("typStudni");
                            info.add(typStudni, CC.xy(2, 23));

                            //---- label16 ----
                            label16.setText("Typ instalacji");
                            info.add(label16, CC.xy(1, 24));

                            //---- typInstalacji ----
                            typInstalacji.setModel(new DefaultComboBoxModel<>(new String[] {
                                "Deszczowa",
                                "Sanitarna"
                            }));
                            typInstalacji.setName("typInstalacji");
                            info.add(typInstalacji, CC.xy(2, 24));
                        }
                        scrollPane1.setViewportView(info);
                    }
                    nagrano.addTab("Informacje", scrollPane1);
                }
                rightSplitPane.setLeftComponent(nagrano);

                //======== cameraParent ========
                {
                    cameraParent.setOpaque(false);
                    cameraParent.setLayout(new CardLayout());

                    //======== layeredPane1 ========
                    {
                        layeredPane1.setBackground(Color.black);
                        layeredPane1.addComponentListener(new ComponentAdapter() {
                            @Override
                            public void componentResized(ComponentEvent e) {
                                layeredPane1ComponentResized(e);
                            }
                        });

                        //======== aboveCamera ========
                        {
                            aboveCamera.setBackground(Color.white);
                            aboveCamera.setLayout(new FlowLayout());

                            //---- textAboveCamera ----
                            textAboveCamera.setText("0.00%");
                            textAboveCamera.setFont(new Font("Tahoma", Font.PLAIN, 20));
                            textAboveCamera.setForeground(Color.black);
                            textAboveCamera.setBackground(Color.white);
                            textAboveCamera.setEditable(false);
                            aboveCamera.add(textAboveCamera);
                        }
                        layeredPane1.add(aboveCamera, JLayeredPane.DEFAULT_LAYER);
                        aboveCamera.setBounds(10, 10, 80, 40);

                        //======== aboveCamera2 ========
                        {
                            aboveCamera2.setBackground(Color.white);
                            aboveCamera2.setLayout(new FlowLayout());

                            //---- distance ----
                            distance.setText("0.00 m");
                            distance.setFont(new Font("Tahoma", Font.PLAIN, 20));
                            distance.setBackground(Color.white);
                            distance.setEditable(false);
                            aboveCamera2.add(distance);
                        }
                        layeredPane1.add(aboveCamera2, JLayeredPane.DEFAULT_LAYER);
                        aboveCamera2.setBounds(200, 10, 80, 40);

                        //======== camera ========
                        {
                            camera.setMinimumSize(new Dimension(16, 9));
                            camera.setMaximumSize(new Dimension(1600, 900));
                            camera.setPreferredSize(new Dimension(520, 495));
                            camera.setBackground(Color.black);
                            camera.setLayout(new GridLayout());
                        }
                        layeredPane1.add(camera, JLayeredPane.DEFAULT_LAYER);
                        camera.setBounds(5, 0, 525, 645);
                    }
                    cameraParent.add(layeredPane1, "card4");
                }
                rightSplitPane.setRightComponent(cameraParent);
            }
            mainSplitPane.setRightComponent(rightSplitPane);
        }
        contentPane.add(mainSplitPane);
        setSize(1090, 710);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
    private JMenuBar menuBar1;
    private JMenu menuProject;
    private JMenuItem openProjectList;
    private JMenu menu3;
    private JMenuItem editProject;
    private JMenuItem editData;
    private JMenu menu2;
    private JMenu menu1;
    private JMenuItem refreshCameraPreview;
    private JMenuItem cameraData;
    private JSplitPane mainSplitPane;
    private JPanel leftSplitPane;
    private JPanel panel5;
    private JLabel label1;
    private JScrollPane scrollPane3;
    private JTree projectsTree;
    private JButton addProject;
    private JSplitPane rightSplitPane;
    private JTabbedPane nagrano;
    private JPanel panel1;
    private JPanel panel2;
    private JButton button4;
    private JButton button6;
    private JButton zoomInAll;
    private JButton zoomOutAll;
    private JButton stopZoom;
    private JLabel label26;
    private JSlider lightValue;
    private JPanel panel3;
    private JLabel label27;
    private JSlider speed;
    private JButton callBackward;
    private JButton callStop;
    private JButton callForward;
    private JPanel panel4;
    private JButton cameraUp;
    private JButton cameraLeft;
    private JButton cameraReset;
    private JButton cameraRight;
    private JButton cameraDown;
    private JPanel panel6;
    private JButton startRecord;
    private JButton stopRecord;
    private JTextField currentRecordTime;
    private JPanel addEventsPanel;
    private JTextPane textEvent;
    private JButton addEvent;
    private JScrollPane scrollPane2;
    private JTextArea eventsList;
    private JButton close;
    private JButton preview;
    private JScrollPane scrollPane1;
    private JPanel info;
    private JLabel label19;
    private JLabel label2;
    private JTextField nazwaFirmy;
    private JLabel label3;
    private JTextField numerOdcinka;
    private JLabel label8;
    private JTextField miasto;
    private JLabel label18;
    private JLabel label5;
    private JTextField symbolStudni;
    private JLabel label4;
    private JTextField textField3;
    private JLabel label23;
    private JSpinner spinner4;
    private JLabel label24;
    private JSpinner spinner5;
    private JLabel label25;
    private JSpinner spinner6;
    private JLabel label20;
    private JLabel label7;
    private JTextField textField6;
    private JLabel label6;
    private JTextField textField5;
    private JLabel label15;
    private JSpinner spinner1;
    private JLabel label21;
    private JSpinner spinner2;
    private JLabel label22;
    private JSpinner spinner3;
    private JLabel label11;
    private JLabel label17;
    private JComboBox<String> inspekcja;
    private JLabel label9;
    private JComboBox<String> material;
    private JLabel label10;
    private JComboBox<String> rozmiarRury;
    private JLabel label12;
    private JComboBox<String> przrekroj;
    private JLabel label13;
    private JComboBox<String> kierunekJazdy;
    private JLabel label14;
    private JComboBox<String> typStudni;
    private JLabel label16;
    private JComboBox<String> typInstalacji;
    private JPanel cameraParent;
    private JLayeredPane layeredPane1;
    private JPanel aboveCamera;
    private JTextPane textAboveCamera;
    private JPanel aboveCamera2;
    private JTextPane distance;
    private JPanel camera;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JPanel getCameraPanel() {
        return camera;
    }

    public JButton getButton4() {
        return button4;
    }
    public JButton getButton6() {
        return button6;
    }

    public JButton getZoomInAll() {
        return zoomInAll;
    }

    public JButton getZoomOutAll() {
        return zoomOutAll;
    }

    public JButton getStopZoom() {
        return stopZoom;
    }

    public JButton getStartRecord() {
        return startRecord;
    }

    public JButton getStopRecord() {
        return stopRecord;
    }

    public JTextPane getTextEvent() {
        return textEvent;
    }

    public JButton getAddEvent() {
        return addEvent;
    }

    public JPanel getInfo() {
        return info;
    }

    public JSplitPane getMainSplitPane() {
        return mainSplitPane;
    }

    public JPanel getLeftSplitPane() {
        return leftSplitPane;
    }

    public JSplitPane getRightSplitPane() {
        return rightSplitPane;
    }

    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
    }

    private class poczatkowaSymbolStudni extends JTextField {
        private poczatkowaSymbolStudni() {
            initComponents();
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }

    private class poczatkowaUlica extends JTextField {
        private poczatkowaUlica() {
            initComponents();
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }

    private class poczatkowaRzednaKinety extends JSpinner {
        private poczatkowaRzednaKinety() {
            initComponents();
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
        // JFormDesigner - End of variables declaration  //GEN-END:variables
    }
}
