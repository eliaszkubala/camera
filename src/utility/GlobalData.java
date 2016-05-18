package utility;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GlobalData {
    public static final String IP_ADDRESS = "192.168.1.88";
    public static final int ONVIF_COMMAND_PORT = 8899;
    public static final String RTSP_CAMERA_ADDRESS = "rtsp://192.168.1.88:8557/Onvif/Streaming/2?videoCodecType=H.264";

    private static String currentProjektDir = null;
    public static final File CURRENT_DIR_FILE = new File(System.getProperty("user.home"), "Videos");
    private static final String CURRENT_DIR_PATH = CURRENT_DIR_FILE.getAbsolutePath();
    public static float GYROSCOPE_OFFSET =0;// 41.74f;

    public static String getLastRecordFileName() {
        return lastRecordFileName;
    }

    private static String lastRecordFileName;

    public static String getPathForFileMaker() {
       return GlobalData.getCurrentProjectFile().getAbsolutePath() + "\\";
    }
    public static String getPathForFileMaker(String fileName) {
        return getPathForFileMaker() + fileName;
    }

    public static void setLastRecordFileName(String lastRecordFileName) {
        GlobalData.lastRecordFileName = lastRecordFileName;
    }

    public String getCurrentProjektDir() {
        return currentProjektDir;
    }

    public static void setCurrentProjectDir(String currentProjectName) {
        currentProjektDir = currentProjectName;
    }

    public static File getCurrentProjectFile(){
        if(currentProjektDir == null){
            return null;
        }
        return new File(System.getProperty("user.home"), "Videos" + "\\" + currentProjektDir);
    }
    public static String getCurrentProjectName(){
        return currentProjektDir;
    }


    public final  static int WINDOWS = 0;
    public final static int NIMBUS = 1;
    public static void setLookAndFeel(int TYPE) {
        try {
            switch (TYPE) {
                case NIMBUS:
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
                    break;
                case WINDOWS:
                    UIManager.setLookAndFeel(new WindowsLookAndFeel());
                    break;
            }
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }


    private static String inspectionPath;
    private static String projectPath;
    private static String inspectionName;

    public static void createNewInspection() {
        File dir = GlobalData.getCurrentProjectFile();
        dir.mkdirs();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        inspectionName = "Nagranie " + df.format(new Date());
        projectPath = dir.getAbsolutePath() + "\\";
        inspectionPath = projectPath + inspectionName + ".mp4";
        setLastRecordFileName(inspectionName);
    }
    public static String getInspectionPath(){
        return inspectionPath;
    };
    public static String getInspectionName() {
        return inspectionName;
    }

    public static String getProjectPath() {
        return projectPath;
    }


}
