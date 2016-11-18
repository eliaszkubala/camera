/*
 * Created by JFormDesigner on Tue Apr 05 11:40:29 CEST 2016
 */

package kamera.event.ui;

import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author dsfsdfggdsf fdsfs
 */
public class EditEvent extends JFrame {
    public EditEvent() {
        initComponents();
    }

    public JButton getSelectEvent() {
        return selectEvent;
    }

    public JRadioButton getRadioButton1() {
        return radioButton1;
    }

    public JRadioButton getRadioButton2() {
        return radioButton2;
    }

    public JRadioButton getRadioButton3() {
        return radioButton3;
    }

    public JRadioButton getRadioButton4() {
        return radioButton4;
    }

    public JTextField getOpis1() {
        return opis1;
    }

    public JTextField getOpis2() {
        return opis2;
    }

    public JTextField getOpis3() {
        return opis3;
    }

    public JPanel getPanel3() {
        return panel3;
    }

    public JPanel getPanel6() {
        return panel6;
    }

    public JSlider getSzerokosc() {
        return szerokosc;
    }

    public JSlider getPrzesuniecie() {
        return przesuniecie;
    }

    public JButton getCancel() {
        return cancel;
    }

    public JButton getSave() {
        return save;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getZdjecie() {
        return zdjecie;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - dsfsdfggdsf fdsfs
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        separator4 = new JSeparator();
        scrollPane2 = new JScrollPane();
        panel = new JPanel();
        panel3 = new JPanel();
        separator1 = compFactory.createSeparator("Wybierz typ zdarzenia");
        panel1 = new JPanel();
        hSpacer6 = new JPanel(null);
        radioButton1 = new JRadioButton();
        hSpacer1 = new JPanel(null);
        radioButton2 = new JRadioButton();
        hSpacer3 = new JPanel(null);
        radioButton3 = new JRadioButton();
        hSpacer4 = new JPanel(null);
        radioButton4 = new JRadioButton();
        hSpacer5 = new JPanel(null);
        selectEvent = new JButton();
        opis1 = new JTextField();
        opis2 = new JTextField();
        opis3 = new JTextField();
        separator2 = compFactory.createSeparator("Opisz zdarzenie");
        label1 = new JLabel();
        ilosc = new JTextField();
        label2 = new JLabel();
        panel6 = new JPanel();
        scrollPane1 = new JScrollPane();
        uwagi = new JTextArea();
        separator3 = compFactory.createSeparator("Lokalizacja zdarzenia");
        panel7 = new JPanel();
        label3 = new JLabel();
        szerokosc = new JSlider();
        label4 = new JLabel();
        przesuniecie = new JSlider();
        zdjecie = new JLabel();
        panel4 = new JPanel();
        cancel = new JButton();
        hSpacer2 = new JPanel(null);
        save = new JButton();

        //======== this ========
        setVisible(true);
        setAlwaysOnTop(true);
        setTitle("Edytuj zdarzenie");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(separator4);

        //======== scrollPane2 ========
        {

            //======== panel ========
            {
                panel.setName("test");

                // JFormDesigner evaluation mark
                panel.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), panel.getBorder())); panel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                //======== panel3 ========
                {
                    panel3.setBorder(new EmptyBorder(5, 5, 5, 5));
                    panel3.setName("test");
                    panel3.setLayout(new FormLayout(
                        "93dlu, $lcgap, default:grow",
                        "9*(default, $lgap), 68dlu, $lgap, default:grow"));
                    panel3.add(separator1, CC.xy(1, 1));

                    //======== panel1 ========
                    {
                        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
                        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
                        panel1.add(hSpacer6);

                        //---- radioButton1 ----
                        radioButton1.setText("Ruroci\u0105g");
                        radioButton1.setName("rurociag");
                        panel1.add(radioButton1);
                        panel1.add(hSpacer1);

                        //---- radioButton2 ----
                        radioButton2.setText("Studzienka");
                        radioButton2.setName("studzienka");
                        panel1.add(radioButton2);
                        panel1.add(hSpacer3);

                        //---- radioButton3 ----
                        radioButton3.setText("Inwentaryzacja");
                        radioButton3.setName("inwentaryzacja");
                        panel1.add(radioButton3);
                        panel1.add(hSpacer4);

                        //---- radioButton4 ----
                        radioButton4.setText("Inne");
                        radioButton4.setName("inne");
                        panel1.add(radioButton4);
                        panel1.add(hSpacer5);
                    }
                    panel3.add(panel1, CC.xy(3, 1));

                    //---- selectEvent ----
                    selectEvent.setText("Wybierz opis zdarzenia");
                    panel3.add(selectEvent, CC.xywh(1, 3, 3, 1));

                    //---- opis1 ----
                    opis1.setHorizontalAlignment(SwingConstants.CENTER);
                    opis1.setPreferredSize(new Dimension(139, 26));
                    opis1.setVisible(false);
                    opis1.setName("opis1");
                    panel3.add(opis1, CC.xywh(1, 5, 3, 1, CC.DEFAULT, CC.CENTER));

                    //---- opis2 ----
                    opis2.setHorizontalAlignment(SwingConstants.CENTER);
                    opis2.setPreferredSize(new Dimension(139, 26));
                    opis2.setVisible(false);
                    opis2.setName("opis2");
                    panel3.add(opis2, CC.xywh(1, 7, 3, 1, CC.DEFAULT, CC.CENTER));

                    //---- opis3 ----
                    opis3.setHorizontalAlignment(SwingConstants.CENTER);
                    opis3.setPreferredSize(new Dimension(139, 26));
                    opis3.setVisible(false);
                    opis3.setName("opis3");
                    panel3.add(opis3, CC.xywh(1, 9, 3, 1, CC.DEFAULT, CC.CENTER));
                    panel3.add(separator2, CC.xywh(1, 11, 3, 1));

                    //---- label1 ----
                    label1.setText("Uj\u0119cie ilo\u015bciowe");
                    panel3.add(label1, CC.xy(1, 13));

                    //---- ilosc ----
                    ilosc.setName("ilosc");
                    panel3.add(ilosc, CC.xy(3, 13));

                    //---- label2 ----
                    label2.setText("Dodatkowe uwagi");
                    panel3.add(label2, CC.xy(1, 15));

                    //======== panel6 ========
                    {
                        panel6.setPreferredSize(new Dimension(120, 120));
                        panel6.setMaximumSize(new Dimension(120, 120));
                        panel6.setMinimumSize(new Dimension(120, 120));
                        panel6.setLayout(new BorderLayout());
                    }
                    panel3.add(panel6, CC.xy(1, 19, CC.CENTER, CC.CENTER));

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setPreferredSize(new Dimension(250, 48));

                        //---- uwagi ----
                        uwagi.setMinimumSize(new Dimension(250, 16));
                        uwagi.setName("uwagi");
                        uwagi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                        scrollPane1.setViewportView(uwagi);
                    }
                    panel3.add(scrollPane1, CC.xy(3, 15));
                    panel3.add(separator3, CC.xywh(1, 17, 3, 1));

                    //======== panel7 ========
                    {
                        panel7.setAlignmentX(0.0F);
                        panel7.setName("test2");
                        panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));

                        //---- label3 ----
                        label3.setText("Szeroko\u015b\u0107");
                        panel7.add(label3);

                        //---- szerokosc ----
                        szerokosc.setMaximum(360);
                        szerokosc.setValue(90);
                        szerokosc.setName("szerokosc");
                        panel7.add(szerokosc);

                        //---- label4 ----
                        label4.setText("Przesuni\u0119cie");
                        panel7.add(label4);

                        //---- przesuniecie ----
                        przesuniecie.setMaximum(360);
                        przesuniecie.setValue(0);
                        przesuniecie.setName("przesuniecie");
                        panel7.add(przesuniecie);
                    }
                    panel3.add(panel7, CC.xy(1, 21, CC.DEFAULT, CC.TOP));

                    //---- zdjecie ----
                    zdjecie.setIcon(new ImageIcon("C:\\Users\\TheKing\\Desktop\\tozadizalacmusi\\2016-04-06 12_05_44-Wiadomo\u015bci.png"));
                    zdjecie.setHorizontalAlignment(SwingConstants.CENTER);
                    zdjecie.setName("zdjecie");
                    zdjecie.setToolTipText("C:\\Users\\TheKing\\Videos\\Projekt testowy2\\Nagranie 2016-03-06 17-33-chart.jpg");
                    panel3.add(zdjecie, CC.xywh(3, 19, 1, 3));
                }
                panel.add(panel3);
            }
            scrollPane2.setViewportView(panel);
        }
        contentPane.add(scrollPane2);

        //======== panel4 ========
        {
            panel4.setBorder(new EmptyBorder(5, 5, 5, 5));
            panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

            //---- cancel ----
            cancel.setText("Anuluj");
            panel4.add(cancel);
            panel4.add(hSpacer2);

            //---- save ----
            save.setText("Zapisz");
            panel4.add(save);
        }
        contentPane.add(panel4);
        setSize(865, 710);
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButton1);
        buttonGroup1.add(radioButton2);
        buttonGroup1.add(radioButton3);
        buttonGroup1.add(radioButton4);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - dsfsdfggdsf fdsfs
    private JSeparator separator4;
    private JScrollPane scrollPane2;
    private JPanel panel;
    private JPanel panel3;
    private JComponent separator1;
    private JPanel panel1;
    private JPanel hSpacer6;
    private JRadioButton radioButton1;
    private JPanel hSpacer1;
    private JRadioButton radioButton2;
    private JPanel hSpacer3;
    private JRadioButton radioButton3;
    private JPanel hSpacer4;
    private JRadioButton radioButton4;
    private JPanel hSpacer5;
    private JButton selectEvent;
    private JTextField opis1;
    private JTextField opis2;
    private JTextField opis3;
    private JComponent separator2;
    private JLabel label1;
    private JTextField ilosc;
    private JLabel label2;
    private JPanel panel6;
    private JScrollPane scrollPane1;
    private JTextArea uwagi;
    private JComponent separator3;
    private JPanel panel7;
    private JLabel label3;
    private JSlider szerokosc;
    private JLabel label4;
    private JSlider przesuniecie;
    private JLabel zdjecie;
    private JPanel panel4;
    private JButton cancel;
    private JPanel hSpacer2;
    private JButton save;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
