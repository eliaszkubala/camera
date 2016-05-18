package utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by TheKing on 05.02.2016.
 */
public class FileUtils {

    private String path;

    public FileUtils(String path) {
        this.path = path;
    }

    public String getNameWithouExt(){
        String filename = new File(path).getName();
        if(filename.contains(".")) {
            String name = filename.substring(0, filename.lastIndexOf('.')).toLowerCase();
            return name;
        }else{
            return filename;
        }
    };

    public void openExplorerByPath(){
        try {
            Runtime.getRuntime().exec("explorer " + path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public boolean isDirectory() {
        return new File(path).isDirectory();
    }
}
