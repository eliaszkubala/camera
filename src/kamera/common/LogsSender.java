package kamera.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by TheKing on 05.05.2016.
 */

public class LogsSender {
    private static void httpRequest(int code2, String message) throws IOException {
        String url = "http://iptak.pl/mobirobi/add.php";
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
        String code = String.valueOf(code2);
        String name = message;

        String query = String.format("code=%s&name=%s",
        URLEncoder.encode(code, charset),
                URLEncoder.encode(name, charset));

        URLConnection connection = new URL(url + "?" + query).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();

        System.out.println(response);
    }


    public static void send(final int code, final String message){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    httpRequest(code, message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static class LogsIntCode {
        public static int URUCHOMIONO_SRODOWISKO = 1;
        public static int ROZPOCZETO_INSPEKCJE = 2;
        public static int ZAKONCZONO_INSPEKCJE = 3;
        public static int DODANO_ZDARZENIE = 4;
        public static int DODANO_PROJEKT = 5;
        public static int EDYTOWANO_PROJEKT = 6;
        public static int WYGENEROWANO_RAPORT = 7;
        public static int EDYTOWANO_ZDARZENIE = 8;
        public static int ZAMKNIETO_SRODOWISKO = 9;
        public static int USUNIETO_PROJEKT = 10;
    }

    public static class LogsString {
        public static String URUCHOMIONO_SRODOWISKO = "Uruchomiono środowisko";
        public static String ROZPOCZETO_INSPEKCJE = "Rozpoczęto inspekcje";
        public static String ZAKONCZONO_INSPEKCJE = "Zakończono inspekcje";
        public static String DODANO_ZDARZENIE = "Dodano zdarzenie";
        public static String DODANO_PROJEKT = "Dodano projekt";
        public static String EDYTOWANO_PROJEKT = "Edytowano projekt";
        public static String WYGENEROWANO_RAPORT = "Wygenerowano raport";
        public static String EDYTOWANO_ZDARZENIE = "Edytowano zdarzenie";
        public static String ZAMKNIETO_SRODOWISKO = "Zamknięto środowisko";
        public static String USUNIETO_PROJEKT = "Usunięto projekt";
    }

}
