package kamera.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*

    Projekt -> Inspekcja

    absolutePath
    dirName



 */



public class GlobalData {
    public static final File CURRENT_DIR_FILE = new File(System.getProperty("user.home"), "Videos");
    private static String mCurrentProjectDirectory = null;
    public static float GYROSCOPE_OFFSET = 0;

    private static String lastRecordFileName;

    public static String getLastRecordFileName() {
        return lastRecordFileName;
    }

    public static String getPathForFileMaker() {
        return getCurrentProjectFile().getAbsolutePath() + "\\";
    }

    public static String getPathForFileMaker(String fileName) {
        return getPathForFileMaker() + fileName;
    }

    public static void setLastRecordFileName(String lastRecordFileName) {
        GlobalData.lastRecordFileName = lastRecordFileName;
    }

    public String getCurrentProjectDirectory() {
        return mCurrentProjectDirectory;
    }

    public static void setCurrentProjectDir(String currentProjectName) {
        mCurrentProjectDirectory = currentProjectName;
    }

    public static File getCurrentProjectFile() {
        if (mCurrentProjectDirectory == null) {
            return null;
        }
        return new File(System.getProperty("user.home"), "Videos" + "\\" + mCurrentProjectDirectory);
    }

    public static String getCurrentProjectName() {
        return mCurrentProjectDirectory;
    }


    private static String projectPath;
    private static String inspectionPath;
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

    public static String getInspectionPath() {
        return inspectionPath;
    }

    ;

    public static String getInspectionName() {
        return inspectionName;
    }

    public static String getProjectPath() {
        return projectPath;
    }


}
