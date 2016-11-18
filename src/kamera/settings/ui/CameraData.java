/*
 * Created by JFormDesigner on Sat Apr 09 20:56:27 CEST 2016
 */

package kamera.settings.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author fasfdfd safaf
 */
public class CameraData extends JFrame {
    public CameraData() {
        initComponents();
    }

    public JTextField getStatus() {
        return status;
    }

    public JTextField getTemp() {
        return temp;
    }

    public JTextField getHumi() {
        return humi;
    }

    public JTextField getPres() {
        return pres;
    }

    public JTextField getGyro() {
        return gyro;
    }

    public JButton getCalibration() {
        return calibration;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        panel1 = new JPanel();
        label5 = compFactory.createLabel("");
        label6 = compFactory.createLabel("Po\u0142\u0105czenie");
        status = new JTextField();
        label2 = new JLabel();
        temp = new JTextField();
        label3 = new JLabel();
        humi = new JTextField();
        label4 = new JLabel();
        pres = new JTextField();
        label1 = new JLabel();
        gyro = new JTextField();
        calibration = new JButton();

        //======== this ========
        setTitle("Dane \u015brodowiskowe kamery");
        setResizable(false);
        setAlwaysOnTop(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //======== panel1 ========
        {
            panel1.setBorder(new EmptyBorder(5, 5, 5, 5));

            // JFormDesigner evaluation mark
            panel1.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            panel1.setLayout(new FormLayout(
                "2*(${growing-button})",
                "2*(default), 3*(fill:default), default, $lgap, default"));

            //---- label5 ----
            label5.setIcon(new ImageIcon(getClass().getResource("/Obraz2.png")));
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            label5.setBorder(new EmptyBorder(5, 5, 5, 5));
            panel1.add(label5, CC.xywh(1, 1, 2, 1));
            panel1.add(label6, CC.xy(1, 2));

            //---- status ----
            status.setEnabled(false);
            panel1.add(status, CC.xy(2, 2));

            //---- label2 ----
            label2.setText("Temperatura");
            panel1.add(label2, CC.xy(1, 3));

            //---- temp ----
            temp.setEnabled(false);
            panel1.add(temp, CC.xy(2, 3));

            //---- label3 ----
            label3.setText("Wilgotno\u015b\u0107");
            panel1.add(label3, CC.xy(1, 4));

            //---- humi ----
            humi.setEnabled(false);
            panel1.add(humi, CC.xy(2, 4));

            //---- label4 ----
            label4.setText("Ci\u015bnienie");
            panel1.add(label4, CC.xy(1, 5));

            //---- pres ----
            pres.setEnabled(false);
            pres.setText("NaN");
            panel1.add(pres, CC.xy(2, 5));

            //---- label1 ----
            label1.setText("\u017byroskop");
            panel1.add(label1, CC.xy(1, 6));

            //---- gyro ----
            gyro.setEnabled(false);
            panel1.add(gyro, CC.xy(2, 6));

            //---- calibration ----
            calibration.setText("kalibruj");
            panel1.add(calibration, CC.xy(2, 8));
        }
        contentPane.add(panel1);
        setSize(420, 425);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - fdsafsd fdsafdsaf
    private JPanel panel1;
    private JLabel label5;
    private JLabel label6;
    private JTextField status;
    private JLabel label2;
    private JTextField temp;
    private JLabel label3;
    private JTextField humi;
    private JLabel label4;
    private JTextField pres;
    private JLabel label1;
    private JTextField gyro;
    private JButton calibration;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
