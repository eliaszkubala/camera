package utility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by TheKing on 04.02.2016.
 */
public class RenderInfoFile {
    private String path = null;
    private static final String newLine = System.getProperty("line.separator");

    public RenderInfoFile(String path) {
        this.path = path;
    }

    public String get() {
        JSONParser parser = new JSONParser();
        String infoString = "";
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(path));

            for (java.lang.Object key : jsonObject.keySet()) {
                String value = (String) jsonObject.get(key);
                String id = (String) key;

                infoString += id + " " + value + newLine;
            }
        } catch (Exception e) {

        }
        return infoString;
    }

    public String getString() {
        System.out.println(path);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            jsonObject = (JSONObject) parser.parse(fr);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            if (fr != null)
                fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null)
            return jsonObject.toString();
        else {
            return "";
        }
    }

    public JSONObject getJSON() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return jsonObject;
    }
}
