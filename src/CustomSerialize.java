import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.omg.CORBA.Object;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by TheKing on 04.02.2016.
 */
public class CustomSerialize {
    private JDialog objectJDialog = null;
    private JPanel objectJPanel = null;

    public CustomSerialize(JPanel object) {
        this.objectJPanel = object;
    }

    public String get() {
        JSONObject jsonObject = new JSONObject();

        for (Component obj : getComponents()) {
            getDeep(jsonObject, obj);
        }

        return jsonObject.toJSONString();
    }

    public void getDeep(JSONObject jsonObject, Component object) {
        String value = null, id = null;

        if (object instanceof JTextField) {
            value = ((JTextField) object).getText();

        } else if (object instanceof JComboBox) {
            value = ((JComboBox) object).getSelectedItem().toString();

        } else if (object instanceof JSpinner) {
            int valueInt = (int) ((JSpinner) object).getValue();
            value = String.valueOf(valueInt);

        } else if (object instanceof JSlider) {
            value = String.valueOf(((JSlider) object).getValue());

        }else if (object instanceof JRadioButton){
            value = Boolean.toString(((JRadioButton) object).isSelected());

        } else if (object instanceof JLabel) {
            value = ((JLabel) object).getToolTipText();

        } else if (object instanceof JTextArea) {
            value = ((JTextArea) object).getText();
        }

        id = object.getName();
        if (id != null) {
            System.out.println(id + " " + value);
            jsonObject.put(id, value);
        }

        if (object instanceof JScrollPane) {
            for (Component obj : ((JScrollPane) object).getViewport().getComponents())
                getDeep(jsonObject, obj);
        }

        if (object instanceof JPanel)
            for (Component obj : ((JPanel) object).getComponents())
                getDeep(jsonObject, obj);
    }

    public void set(String string) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(string);

            for (java.lang.Object key : jsonObject.keySet()) {
                String value = (String) jsonObject.get(key);
                String id = (String) key;

                for (Component object : getComponents()) {
                    setDeep(value, id, object);

                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setDeep(String value, String id, Component obj) {

        if (obj instanceof JScrollPane) {
            for (Component object : ((JScrollPane) obj).getViewport().getComponents())
                setDeep(value, id, object);
        }

        if (obj instanceof JPanel)
            for (Component object : ((JPanel) obj).getComponents())
                setDeep(value, id, object);


        if (obj.getName() == null) return;

        if (obj.getName().equals(id)) {
            System.out.println(obj.getName() +  " " + id + " " + value);

            if (obj instanceof JTextField) {
                ((JTextField) obj).setText(value);
            }
            if(obj instanceof JLabel){
                ((JLabel) obj).setToolTipText(value);
                ImageIcon imageIcon = new ImageIcon(value);
                ((JLabel) obj).setIcon(imageIcon);
            }

            if (obj instanceof JRadioButton){
                ((JRadioButton) obj).setSelected(Boolean.valueOf(value));
            }

            if (obj instanceof JTextArea) {
                ((JTextArea) obj).setText(value);
            }
            if (obj instanceof JComboBox) {
                ((JComboBox) obj).setSelectedItem(value);
            }
            if (obj instanceof JSpinner) {
                int localValue = Integer.parseInt(value);
                ((JSpinner) obj).setValue(localValue);
            }
            if (obj instanceof JSlider) {
                int localValue = Integer.parseInt(value);
                ((JSlider) obj).setValue(localValue);
            }

        }
    }

    public Component[] getComponents() {
        if (objectJPanel != null) {
            return objectJPanel.getComponents();
        }
        if (objectJDialog != null) {
            return objectJDialog.getComponents();
        }
        return null;
    }
}
