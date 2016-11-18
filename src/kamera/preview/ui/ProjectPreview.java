package kamera.preview.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

public class ProjectPreview extends JFrame {
    public ProjectPreview() {
        initComponents();
    }

    private void layeredPane1ComponentResized(ComponentEvent e) {
        getPanel1().setSize(new Dimension(e.getComponent().getWidth(), e.getComponent().getHeight()));
        getPanel1().setLocation(new Point(0,0));


        getTextAboveCamera2().setSize(e.getComponent().getWidth(), getTextAboveCamera2().getHeight());
        getTextAboveCamera2().setLocation(new Point(0, e.getComponent().getHeight() - getTextAboveCamera2().getHeight()));
        StyledDocument doc = getTextAboveCamera2().getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        getAboveCamera2().setLocation(new Point( e.getComponent().getWidth() - getAboveCamera2().getWidth() - 10, 10));

        getPanel1().updateUI();
    }

    public JPanel getAboveCamera() {
        return aboveCamera;
    }

    public JTextPane getTextAboveCamera() {
        return textAboveCamera;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getShowChart() {
        return showChart;
    }

    public JButton getGenerateReports() {
        return generateReports;
    }

    public JTextPane getTextAboveCamera2() {
        return textAboveCamera2;
    }

    public JButton getClose() {
        return close;
    }

    public JComponent getEventSeparator() {
        return eventSeparator;
    }

    public JPanel getAboveCamera2() {
        return aboveCamera2;
    }

    public JTextPane getDistance() {
        return distance;
    }

    public JTable getTable1() {
        return table1;
    }

    public JScrollPane getEventScrollPane() {
        return eventScrollPane;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        splitPane1 = new JSplitPane();
        tabbedPane1 = new JTabbedPane();
        scrollPane5 = new JScrollPane();
        panel4 = new JPanel();
        separator2 = compFactory.createSeparator("Nagrania");
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        eventSeparator = compFactory.createSeparator("Zdarzenia");
        eventScrollPane = new JScrollPane();
        table1 = new JTable();
        showChart = new JButton();
        generateReports = new JButton();
        close = new JButton();
        scrollPane4 = new JScrollPane();
        info = new JPanel();
        separator7 = compFactory.createSeparator("Informacje og\u00f3lne");
        label5 = new JLabel();
        nazwaFirmy = new JTextField();
        label6 = new JLabel();
        numerOdcinka = new JTextField();
        label8 = new JLabel();
        miasto = new JTextField();
        separator3 = compFactory.createSeparator("Studnia pocz\u0105tkowa");
        label7 = new JLabel();
        symbolStudni = new JTextField();
        label88 = new JLabel();
        textField3 = new JTextField();
        label23 = new JLabel();
        spinner4 = new JSpinner();
        label24 = new JLabel();
        spinner5 = new JSpinner();
        label25 = new JLabel();
        spinner6 = new JSpinner();
        separator4 = compFactory.createSeparator("Studnia ko\u0144cowa");
        label9 = new JLabel();
        textField6 = new JTextField();
        label10 = new JLabel();
        textField5 = new JTextField();
        label155 = new JLabel();
        spinner1 = new JSpinner();
        label21 = new JLabel();
        spinner2 = new JSpinner();
        label22 = new JLabel();
        spinner3 = new JSpinner();
        separator5 = compFactory.createSeparator("Opis kana\u0142u");
        label17 = new JLabel();
        inspekcja = new JComboBox<>();
        label111 = new JLabel();
        material = new JComboBox<>();
        label12 = new JLabel();
        rozmiarRury = new JComboBox<>();
        label13 = new JLabel();
        przrekroj = new JComboBox<>();
        label14 = new JLabel();
        kierunekJazdy = new JComboBox<>();
        label15 = new JLabel();
        typStudni = new JComboBox<>();
        label16 = new JLabel();
        typInstalacji = new JComboBox<>();
        separator6 = compFactory.createSeparator("Zarz\u0105dzaj");
        saveInfo = new JButton();
        panel2 = new JPanel();
        splitPane2 = new JSplitPane();
        panel3 = new JPanel();
        startButton = new JButton();
        pauseButton = new JButton();
        slider1 = new JSlider();
        time = new JLabel();
        stopButton = new JButton();
        layeredPane1 = new JLayeredPane();
        aboveCamera = new JPanel();
        textAboveCamera = new JTextPane();
        aboveCamera2 = new JPanel();
        distance = new JTextPane();
        textAboveCamera2 = new JTextPane();
        panel1 = new JPanel();

        //======== this ========
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());

        //======== splitPane1 ========
        {
            splitPane1.setOneTouchExpandable(true);
            splitPane1.setDividerLocation(300);
            splitPane1.setDividerSize(6);

            //======== tabbedPane1 ========
            {

                //======== scrollPane5 ========
                {
                    scrollPane5.setPreferredSize(new Dimension(300, 0));
                    scrollPane5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPane5.setBorder(new EmptyBorder(5, 5, 5, 5));

                    //======== panel4 ========
                    {

                        // JFormDesigner evaluation mark
                        panel4.setBorder(new javax.swing.border.CompoundBorder(
                            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                                java.awt.Color.red), panel4.getBorder())); panel4.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

                        panel4.setLayout(new FormLayout(
                            "42dlu:grow, left:33dlu:grow",
                            "3*(default, $lgap), top:106dlu:grow, $lgap, 200px:grow, $lgap, default, $lgap, bottom:default"));
                        panel4.add(separator2, CC.xywh(1, 1, 2, 1));

                        //======== scrollPane1 ========
                        {

                            //---- list1 ----
                            list1.setBackground(Color.white);
                            list1.setVisibleRowCount(5);
                            scrollPane1.setViewportView(list1);
                        }
                        panel4.add(scrollPane1, CC.xywh(1, 3, 2, 1, CC.FILL, CC.DEFAULT));
                        panel4.add(eventSeparator, CC.xywh(1, 5, 2, 1));

                        //======== eventScrollPane ========
                        {
                            eventScrollPane.setPreferredSize(new Dimension(100, 100));

                            //---- table1 ----
                            table1.setModel(new DefaultTableModel());
                            table1.setCellSelectionEnabled(true);
                            table1.setRequestFocusEnabled(false);
                            eventScrollPane.setViewportView(table1);
                        }
                        panel4.add(eventScrollPane, CC.xywh(1, 7, 2, 2));

                        //---- showChart ----
                        showChart.setText("Poka\u017c wykres");
                        panel4.add(showChart, CC.xy(1, 11, CC.FILL, CC.DEFAULT));

                        //---- generateReports ----
                        generateReports.setText("Generuj raport w PDF");
                        panel4.add(generateReports, CC.xy(2, 11, CC.FILL, CC.DEFAULT));

                        //---- close ----
                        close.setText("Zamknij podgl\u0105d");
                        panel4.add(close, CC.xywh(1, 13, 2, 1, CC.FILL, CC.DEFAULT));
                    }
                    scrollPane5.setViewportView(panel4);
                }
                tabbedPane1.addTab("Nagranie", scrollPane5);

                //======== scrollPane4 ========
                {
                    scrollPane4.setPreferredSize(new Dimension(300, 0));
                    scrollPane4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPane4.setBorder(new EmptyBorder(5, 5, 5, 5));

                    //======== info ========
                    {
                        info.setLayout(new FormLayout(
                            "2*(default:grow)",
                            "14dlu, 3*(min), 14dlu, 2*(min), 3*(default), 14dlu, 2*(min), 3*(default), 14dlu, 7*(min), 2*($lgap, default)"));
                        info.add(separator7, CC.xywh(1, 1, 2, 1));

                        //---- label5 ----
                        label5.setText("Operator");
                        info.add(label5, CC.xy(1, 2));

                        //---- nazwaFirmy ----
                        nazwaFirmy.setToolTipText("nazwaFirmy");
                        nazwaFirmy.setName("nazwaFirmy");
                        info.add(nazwaFirmy, CC.xy(2, 2));

                        //---- label6 ----
                        label6.setText("Numer odcinka");
                        info.add(label6, CC.xy(1, 3));

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
                        info.add(separator3, CC.xywh(1, 5, 2, 1));

                        //---- label7 ----
                        label7.setText("Symbol studni");
                        info.add(label7, CC.xy(1, 6));

                        //---- symbolStudni ----
                        symbolStudni.setToolTipText("poczatkowaSymbolStudni");
                        symbolStudni.setName("poczatkowaSymbolStudni");
                        info.add(symbolStudni, CC.xy(2, 6));

                        //---- label88 ----
                        label88.setText("Ulica poczatkowa");
                        info.add(label88, CC.xy(1, 7));

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
                        info.add(separator4, CC.xywh(1, 11, 2, 1));

                        //---- label9 ----
                        label9.setText("Symbol studni");
                        info.add(label9, CC.xy(1, 12));

                        //---- textField6 ----
                        textField6.setName("koncowaSymbolStudni");
                        info.add(textField6, CC.xy(2, 12));

                        //---- label10 ----
                        label10.setText("Ulica ko\u0144cowa");
                        info.add(label10, CC.xy(1, 13));

                        //---- textField5 ----
                        textField5.setName("koncowaUlica");
                        info.add(textField5, CC.xy(2, 13));

                        //---- label155 ----
                        label155.setText("Rz\u0119dna kinety (m)");
                        info.add(label155, CC.xy(1, 14));

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
                        info.add(separator5, CC.xywh(1, 17, 2, 1));

                        //---- label17 ----
                        label17.setText("Rodzaj inspekcji");
                        info.add(label17, CC.xy(1, 18));

                        //---- inspekcja ----
                        inspekcja.setModel(new DefaultComboBoxModel<>(new String[] {
                            "Inspekcja"
                        }));
                        inspekcja.setName("inspekcja");
                        info.add(inspekcja, CC.xy(2, 18));

                        //---- label111 ----
                        label111.setText("Materia\u0142 ");
                        info.add(label111, CC.xy(1, 19));

                        //---- material ----
                        material.setModel(new DefaultComboBoxModel<>(new String[] {
                            "Beton",
                            "Plastik",
                            "Stal"
                        }));
                        material.setName("material");
                        info.add(material, CC.xy(2, 19));

                        //---- label12 ----
                        label12.setText("Rozmiar rury");
                        info.add(label12, CC.xy(1, 20));

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

                        //---- label13 ----
                        label13.setText("Przekroj");
                        info.add(label13, CC.xy(1, 21));

                        //---- przrekroj ----
                        przrekroj.setModel(new DefaultComboBoxModel<>(new String[] {
                            "Ko\u0142owy",
                            "Tr\u00f3jk\u0105tny",
                            "Kwadratowy"
                        }));
                        przrekroj.setName("przekroj");
                        info.add(przrekroj, CC.xy(2, 21));

                        //---- label14 ----
                        label14.setText("Kierunek jazdy");
                        info.add(label14, CC.xy(1, 22));

                        //---- kierunekJazdy ----
                        kierunekJazdy.setModel(new DefaultComboBoxModel<>(new String[] {
                            "Pod pr\u0105d",
                            "Z pr\u0105dem"
                        }));
                        kierunekJazdy.setName("kierunekJazdy");
                        info.add(kierunekJazdy, CC.xy(2, 22));

                        //---- label15 ----
                        label15.setText("Typ studni");
                        info.add(label15, CC.xy(1, 23));

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
                        info.add(separator6, CC.xywh(1, 26, 2, 1));

                        //---- saveInfo ----
                        saveInfo.setText("zapisz");
                        info.add(saveInfo, CC.xy(2, 28));
                    }
                    scrollPane4.setViewportView(info);
                }
                tabbedPane1.addTab("Informacje", scrollPane4);
            }
            splitPane1.setLeftComponent(tabbedPane1);

            //======== panel2 ========
            {
                panel2.setMinimumSize(new Dimension(200, 200));
                panel2.setOpaque(false);
                panel2.setPreferredSize(new Dimension(400, 33));
                panel2.setLayout(new GridLayout());

                //======== splitPane2 ========
                {
                    splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
                    splitPane2.setDividerLocation(35);
                    splitPane2.setDoubleBuffered(true);
                    splitPane2.setEnabled(false);

                    //======== panel3 ========
                    {
                        panel3.setMinimumSize(new Dimension(20, 21));
                        panel3.setPreferredSize(new Dimension(200, 21));
                        panel3.setMaximumSize(new Dimension(0, 0));

                        //---- startButton ----
                        startButton.setIcon(new ImageIcon(getClass().getResource("/Play-52.png")));

                        //---- pauseButton ----
                        pauseButton.setIcon(new ImageIcon(getClass().getResource("/Pause-52.png")));

                        //---- slider1 ----
                        slider1.setValue(0);

                        //---- time ----
                        time.setText("0/0");
                        time.setHorizontalAlignment(SwingConstants.CENTER);

                        //---- stopButton ----
                        stopButton.setIcon(new ImageIcon(getClass().getResource("/stop.png")));

                        GroupLayout panel3Layout = new GroupLayout(panel3);
                        panel3.setLayout(panel3Layout);
                        panel3Layout.setHorizontalGroup(
                            panel3Layout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(startButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pauseButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(stopButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(slider1, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(time, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                        );
                        panel3Layout.setVerticalGroup(
                            panel3Layout.createParallelGroup()
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addGroup(panel3Layout.createParallelGroup()
                                        .addComponent(startButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pauseButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panel3Layout.createSequentialGroup()
                                            .addComponent(stopButton)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(time, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(slider1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
                        );
                    }
                    splitPane2.setTopComponent(panel3);

                    //======== layeredPane1 ========
                    {
                        layeredPane1.setBackground(Color.black);
                        layeredPane1.setDoubleBuffered(true);
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
                            textAboveCamera.setBackground(Color.white);
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
                            aboveCamera2.add(distance);
                        }
                        layeredPane1.add(aboveCamera2, JLayeredPane.DEFAULT_LAYER);
                        aboveCamera2.setBounds(675, 10, 80, 40);

                        //---- textAboveCamera2 ----
                        textAboveCamera2.setText("Informacje nt. inspekcji");
                        textAboveCamera2.setFont(new Font("Tahoma", Font.PLAIN, 20));
                        textAboveCamera2.setForeground(Color.black);
                        textAboveCamera2.setBackground(Color.white);
                        textAboveCamera2.setDoubleBuffered(true);
                        textAboveCamera2.setFocusable(false);
                        textAboveCamera2.setFocusCycleRoot(false);
                        textAboveCamera2.setRequestFocusEnabled(false);
                        textAboveCamera2.setOpaque(false);
                        textAboveCamera2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        layeredPane1.add(textAboveCamera2, JLayeredPane.DEFAULT_LAYER);
                        textAboveCamera2.setBounds(0, 60, 445, 110);

                        //======== panel1 ========
                        {
                            panel1.setMinimumSize(new Dimension(300, 300));
                            panel1.setPreferredSize(new Dimension(0, 450));
                            panel1.setOpaque(false);
                            panel1.setBackground(Color.black);
                            panel1.setLayout(new GridLayout());
                        }
                        layeredPane1.add(panel1, JLayeredPane.DEFAULT_LAYER);
                        panel1.setBounds(0, 0, 480, 470);
                    }
                    splitPane2.setBottomComponent(layeredPane1);
                }
                panel2.add(splitPane2);
            }
            splitPane1.setRightComponent(panel2);
        }
        contentPane.add(splitPane1);
        setSize(1090, 755);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JList getList1() {
        return list1;
    }


    public JSlider getSlider1() {
        return slider1;
    }

    public JLabel getTime() {
        return time;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
    private JSplitPane splitPane1;
    private JTabbedPane tabbedPane1;
    private JScrollPane scrollPane5;
    private JPanel panel4;
    private JComponent separator2;
    private JScrollPane scrollPane1;
    private JList list1;
    private JComponent eventSeparator;
    private JScrollPane eventScrollPane;
    private JTable table1;
    private JButton showChart;
    private JButton generateReports;
    private JButton close;
    private JScrollPane scrollPane4;
    private JPanel info;
    private JComponent separator7;
    private JLabel label5;
    private JTextField nazwaFirmy;
    private JLabel label6;
    private JTextField numerOdcinka;
    private JLabel label8;
    private JTextField miasto;
    private JComponent separator3;
    private JLabel label7;
    private JTextField symbolStudni;
    private JLabel label88;
    private JTextField textField3;
    private JLabel label23;
    private JSpinner spinner4;
    private JLabel label24;
    private JSpinner spinner5;
    private JLabel label25;
    private JSpinner spinner6;
    private JComponent separator4;
    private JLabel label9;
    private JTextField textField6;
    private JLabel label10;
    private JTextField textField5;
    private JLabel label155;
    private JSpinner spinner1;
    private JLabel label21;
    private JSpinner spinner2;
    private JLabel label22;
    private JSpinner spinner3;
    private JComponent separator5;
    private JLabel label17;
    private JComboBox<String> inspekcja;
    private JLabel label111;
    private JComboBox<String> material;
    private JLabel label12;
    private JComboBox<String> rozmiarRury;
    private JLabel label13;
    private JComboBox<String> przrekroj;
    private JLabel label14;
    private JComboBox<String> kierunekJazdy;
    private JLabel label15;
    private JComboBox<String> typStudni;
    private JLabel label16;
    private JComboBox<String> typInstalacji;
    private JComponent separator6;
    private JButton saveInfo;
    private JPanel panel2;
    private JSplitPane splitPane2;
    private JPanel panel3;
    private JButton startButton;
    private JButton pauseButton;
    private JSlider slider1;
    private JLabel time;
    private JButton stopButton;
    private JLayeredPane layeredPane1;
    private JPanel aboveCamera;
    private JTextPane textAboveCamera;
    private JPanel aboveCamera2;
    private JTextPane distance;
    private JTextPane textAboveCamera2;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables}


    public JPanel getInfo() {
        return info;
    }

    public JButton getSaveInfo() {
        return saveInfo;
    }
}
