import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by TheKing on 29.12.2015.
 */
public class FileMaker {

    private int startOffset = 5; // liczba sekund pominietych przez startowanie nagrywania
    private static final String newLine = System.getProperty("line.separator");
    private final String path;
    private final String file;
    private Timer timer;
    private int seconds = 0 - startOffset;
    private JTextField textField = null;
    private ArrayList<String> events;


    public FileMaker(String projectName, String projectPath) {
        events = new ArrayList<>();
        this.file = projectName;
        this.path = projectPath;
    }

    public void startTimer() {
        timer = new Timer();
        timer.schedule(new SayHello(), 0, 1000);
    }

    public void setCurrentTimeControl(JTextField textField) {
        this.textField = textField;
    }

    public void release() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public void addEvents(String string) {
        events.add(seconds + " - " + string);
        appendToFile("events", string);
    }

    public void createInfoFile(String string) {
        createFile("info", string);
    }

    public void createEventFile(int eventID, String string) {
        deleIfExist(path);
        createFile("event_" + eventID, string);
    }

    public void addGyroData(String string) {

        appendToFile("gyroData", string);
    }

    public void addDistanceInCentimeter(String string) {
        appendToFile("distanceInMm", string);
    }

    private void createFile(String fileName, String string) {
        String name = path + file + "-" + fileName + ".txt";
        createFile(name);
        setText(name, string);
        System.out.println("tworze info file " + string + " tutaj: " + name);
    }


    private void deleIfExist(String path) {
        File f = new File(path);
        f.delete();
    }

    private void appendToFile(String fileName, String string) {
        String name = path + file + "-" + fileName + ".txt";
        createFile(name);
        appendText(name, seconds + "|" + string + newLine);
    }

    private void setText(String name, String string) {
        try {
            Files.write(Paths.get(name), string.getBytes());
        } catch (IOException e) {
        }
    }

    private void appendText(String name, String string) {
        try {
            Files.write(Paths.get(name), string.getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
        }
    }

    private void createFile(String s) {
        try {
            File f = new File(s);
            f.createNewFile();
        } catch (java.io.IOException e) {
            System.err.println("already exists: " + e.getMessage());
        }
    }

    public void serialEvent(String inputLine) {

        if (timer == null) return;

        String[] data = StringUtils.splitString(inputLine, "|");

        if (data[0].equals("gyro")) {
            String y = RS485_Reader.getInstance().getGYROSCOPE_DATA();
            System.out.println("y: " + y);
            try {
                NumberFormat.getInstance().parse(y);
                addGyroData(y + "");
            } catch (ParseException e) {

            }
        }

        if (data[0].equals("dis")) {
            String distance = RS485_Reader.getInstance().getDISTANCE_DATA();
            addDistanceInCentimeter(
                    distance
            );
        }
    }

    class SayHello extends TimerTask {
        public void run() {
            seconds += 1;
            System.out.println("seconds: " + seconds);
            if (textField != null)
                textField.setText(seconds + "");

            if (seconds % 1 == 0) {
                // co %X sekundy pobieram sobie gyro
                //RS485_Reader.getInstance().write("getGyro()");
                RS485_Reader.getInstance().setListner(FileMaker.this);
            }
        }
    }
}
