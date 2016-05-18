package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Fri Feb 05 18:58:20 CET 2016
 */



/**
 * @author eliasz kubala
 */
public class NewProject extends JDialog {
    public NewProject(Frame owner) {
        super(owner);
        initComponents();
    }

    public NewProject(Dialog owner) {
        super(owner);
        initComponents();
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JTextField getNazwaProjektu() {
        return nazwaProjektu;
    }

    public JTextField getWykonawcaProjektu() {
        return wykonawcaProjektu;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - eliasz kubala kubala
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        separator1 = compFactory.createSeparator("Dane og\u00f3lne");
        label2 = new JLabel();
        nazwaProjektu = new JTextField();
        label4 = new JLabel();
        wykonawcaProjektu = new JTextField();
        separator2 = compFactory.createSeparator("Zleceniodawca");
        label3 = new JLabel();
        zleceniodawcaProjektu = new JTextField();
        label7 = compFactory.createLabel("Adres");
        textField1 = new JTextField();
        label5 = compFactory.createLabel("Kontakt");
        textField2 = new JTextField();
        label6 = compFactory.createLabel("Nip");
        textField3 = new JTextField();
        label8 = compFactory.createLabel("Email");
        textField4 = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setAlwaysOnTop(true);
        setType(Window.Type.POPUP);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                contentPanel.setLayout(new FormLayout(
                    "default, $lcgap, default:grow",
                    "8*(default, $lgap), default"));

                //---- separator1 ----
                separator1.setBorder(new EmptyBorder(5, 5, 5, 5));
                contentPanel.add(separator1, CC.xywh(1, 1, 3, 1));

                //---- label2 ----
                label2.setText("Nazwa projektu");
                contentPanel.add(label2, CC.xy(1, 3));

                //---- nazwaProjektu ----
                nazwaProjektu.setName("nazwaProjektu");
                contentPanel.add(nazwaProjektu, CC.xy(3, 3));

                //---- label4 ----
                label4.setText("Wykonawca projektu");
                contentPanel.add(label4, CC.xy(1, 5));

                //---- wykonawcaProjektu ----
                wykonawcaProjektu.setName("wykonawcaProjektu");
                contentPanel.add(wykonawcaProjektu, CC.xy(3, 5));

                //---- separator2 ----
                separator2.setBorder(new EmptyBorder(5, 5, 5, 5));
                contentPanel.add(separator2, CC.xywh(1, 7, 3, 1));

                //---- label3 ----
                label3.setText("Nazwa");
                contentPanel.add(label3, CC.xy(1, 9));

                //---- zleceniodawcaProjektu ----
                zleceniodawcaProjektu.setName("zleceniodawcaProjektu");
                contentPanel.add(zleceniodawcaProjektu, CC.xy(3, 9));
                contentPanel.add(label7, CC.xy(1, 11));

                //---- textField1 ----
                textField1.setName("adres");
                contentPanel.add(textField1, CC.xy(3, 11));
                contentPanel.add(label5, CC.xy(1, 13));

                //---- textField2 ----
                textField2.setName("kontakt");
                contentPanel.add(textField2, CC.xy(3, 13));
                contentPanel.add(label6, CC.xy(1, 15));

                //---- textField3 ----
                textField3.setName("nip");
                contentPanel.add(textField3, CC.xy(3, 15));
                contentPanel.add(label8, CC.xy(1, 17));

                //---- textField4 ----
                textField4.setName("email");
                contentPanel.add(textField4, CC.xy(3, 17));
            }
            dialogPane.add(contentPanel, BorderLayout.NORTH);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(5, 5, 5, 5));
                buttonBar.setLayout(new FormLayout(
                    "$glue, $button, $rgap, $button",
                    "pref"));

                //---- okButton ----
                okButton.setText("OK");
                buttonBar.add(okButton, CC.xy(2, 1));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, CC.xy(4, 1));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(610, 510);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - eliasz kubala kubala
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JComponent separator1;
    private JLabel label2;
    private JTextField nazwaProjektu;
    private JLabel label4;
    private JTextField wykonawcaProjektu;
    private JComponent separator2;
    private JLabel label3;
    private JTextField zleceniodawcaProjektu;
    private JLabel label7;
    private JTextField textField1;
    private JLabel label5;
    private JTextField textField2;
    private JLabel label6;
    private JTextField textField3;
    private JLabel label8;
    private JTextField textField4;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
