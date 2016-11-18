package kamera.common;

import kamera.event.EventWindow;
import kamera.utility.GlobalData;
import kamera.common.model.ListWrapper;
import kamera.common.model.PointFloat;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RenderSubtitlesFile {
    private String path;

    public RenderSubtitlesFile(String path) {
        this.path = path;
    }

    public String getSubtitle(int second) {
        try {
            for (String line : Files.readAllLines(Paths.get(path))) {
                String[] cell = line.split("\\|");
                int localSecond = Integer.parseInt(cell[0]);
                if (localSecond == second) {
                    return cell[1];
                }
                if (localSecond > second) {
                    return "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    static String[] opis1 = {"opis1", "opis2", "opis3"};

    public static String getShortDescription(int second, String currentFileName) {
        String evetFilePath = GlobalData.getPathForFileMaker(EventWindow.getFileName(currentFileName, second));
        JSONObject json = new RenderInfoFile(evetFilePath).getJSON();
        String opis = "";
        if (json != null) {
            String separator = "";
            for (int i = 0; i < opis1.length; i++) {
                String name = getCodeFromEventString(json, opis1[i]);
                if (name.equals("")) continue;
                opis += separator + name;
                separator = "-";
            }
        }
        return opis;
    }

    public static String getLongDescription(int second, String currentFileName) {
        String evetFilePath = GlobalData.getPathForFileMaker(EventWindow.getFileName(currentFileName, second));
        JSONObject json = new RenderInfoFile(evetFilePath).getJSON();
        String opis = "";
        if (json != null) {
            String separator = "";
            for (int i = 0; i < opis1.length; i++) {
                String name = getDescFromEventString(json, opis1[i]);
                if (name.equals("")) continue;
                opis += separator + name;
                separator = System.lineSeparator();
            }
            if (!opis.equals("")) opis += System.lineSeparator();
        }
        return opis;
    }

    public DefaultListModel getDefaultListModel() {
        DefaultListModel model = new DefaultListModel();
        if (path == null) return model;
        try {
            for (String line : Files.readAllLines(Paths.get(path))) {
                String[] cell = line.split("\\|");
                int localSecond = Integer.parseInt(cell[0]);
                ListWrapper listWrapper = new ListWrapper(cell[1], cell[0]);
                model.addElement(listWrapper);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }


    public DefaultListModel getEventsListModel(String currentFileName) {
        DefaultListModel model = new DefaultListModel();
        if (path == null) return model;
        try {
            for (String line : Files.readAllLines(Paths.get(path))) {
                String[] cell = line.split("\\|");
                int localSecond = Integer.parseInt(cell[0]);

                String opis = getShortDescription(localSecond, currentFileName);

                ListWrapper listWrapper = new ListWrapper(opis + " " + cell[0] + " " + cell[1], cell[0]);
                model.addElement(listWrapper);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }


    public DefaultTableModel getEventsTableModel(String currentFileName) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        model.addColumn("Kod");
        model.addColumn("Sekunda");
        model.addColumn("Opis");

        if (path == null) return model;
        try {
            for (String line : Files.readAllLines(Paths.get(path))) {
                String[] cell = line.split("\\|");
                int localSecond = Integer.parseInt(cell[0]);

                String evetFilePath = GlobalData.getPathForFileMaker(EventWindow.getFileName(currentFileName, localSecond));
                JSONObject json = new RenderInfoFile(evetFilePath).getJSON();
                String[] opis1 = {"opis1", "opis2", "opis3"};
                String opis = getShortDescription(localSecond, currentFileName);

                model.addRow(new String[]{opis, cell[0], cell[1]});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    private static String getCodeFromEventString(JSONObject json, String opis1) {
        opis1 = (String) json.get(opis1);
        if (!opis1.equals("")) {
            String[] opis = opis1.split(" - ");
            return opis[0];
        }
        return "";
    }

    private static String getDescFromEventString(JSONObject json, String opis1) {
        opis1 = (String) json.get(opis1);
        if (!opis1.equals("")) {
            return opis1;
        }
        return "";
    }

    public ArrayList<PointFloat> getFloatArrayList() {
        ArrayList<PointFloat> arrayList = new ArrayList<>();
        if (path == null) return arrayList;
        try {
            for (String line : Files.readAllLines(Paths.get(path))) {
                String[] cell = line.split("\\|");
                int localSecond = Integer.parseInt(cell[0]);
                float variable = Float.parseFloat(cell[1]);
                if (localSecond >= 0)
                    arrayList.add(new PointFloat(localSecond, variable));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}
