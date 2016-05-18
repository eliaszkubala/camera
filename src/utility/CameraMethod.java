package utility;

import sun.net.www.http.HttpClient;
import utility.GlobalData;

import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by TheKing on 14.12.2015.
 */
public class CameraMethod {

    private static CameraMethod instance;

    public static CameraMethod getInstance() {
        if(instance == null){
            instance = new CameraMethod();
        }
        return instance;
    }

    private static String password = "admin";
    private static String username = "admin";
    Timer timer = new Timer();
    static String hostname = GlobalData.IP_ADDRESS;
    static int port = GlobalData.ONVIF_COMMAND_PORT;


    public void setDisplay(int brightness, int contrast, int saturation){
        String url = "http://192.168.1.88/vb.htm?language=ie&brightness="+brightness+"&contrast="+contrast+"&saturation=" + saturation;
        sendData(url);
    }

    public void zoomInAll() {
        zoom(1);
    }

    public void zoomOutAll() {
        zoom(-1);
    }

    public void zoomOut() {
        actionBeforeZoom();
        zoom(-1);
        actionAfterZoom();
    }

    public void zoomIn() {
        actionBeforeZoom();
        zoom(1);
        actionAfterZoom();
    }

    public void actionAfterZoom() {
        TimerTask action = new TimerTask() {
            public void run() {
                stopZoom();
            }
        };
        this.timer.schedule(action, 250);
    }

    public void actionBeforeZoom() {
        this.timer.cancel();
        this.timer = new Timer();
    }



    private void zoom(int i) {
        String xmldata;
        if(i<0)
            xmldata = "http://192.168.1.88/vb.htm?language=ie&ipncptz=zoomo00000%000.5";
        else
            xmldata = "http://192.168.1.88/vb.htm?language=ie&ipncptz=zoomi00000%000.5";

        sendData(xmldata);
    }

    public void stopZoom() {
        String xmldata = "http://192.168.1.88/vb.htm?language=ie&ipncptz=pstop00000";
        sendData(xmldata);
    }


    private static void httpRequest(String url) throws IOException {

        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        String userpass = username + ":" + password;
        String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
        connection.setRequestProperty("Authorization", basicAuth);
        InputStream response = connection.getInputStream();
        System.out.println(response);
    }

    public void sendData(String url) {
        System.out.println("Chce wyslać dane do kamery");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    httpRequest(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
