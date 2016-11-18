package kamera.common;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


public class SimpleServer {

    public SimpleServer() throws IOException {
        System.out.println("Server nasłuchuje na porcie 8080");
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        String command[] = {"left", "right", "up", "down",
                "forward", "backward", "reset", "stop",
                "setLight(0)", "setLight(1)", "setLight(2)", "setLight(3)"};

        for (int i = 0; i < command.length; i++) {
            server.createContext("/" + command[i], new MyHandler(command[i]));
        }

        server.setExecutor(null);
        server.start();
    }

    public static void main(final String[] args) throws IOException {
        new SimpleServer();
    }

    static class MyHandler implements HttpHandler {
        private final String data;

        public MyHandler(String data) {
            this.data = data;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Wywolałeś " + data;
            System.out.println(response);
            switch (data) {
                case "left":
                    RobotSteering.cameraLeft();
                    break;
                case "up":
                    RobotSteering.cameraUp();
                    break;
                case "down":
                    RobotSteering.cameraDown();
                    break;
                case "right":
                    RobotSteering.cameraRight();
                    break;
                case "reset":
                    RobotSteering.cameraReset();
                    break;
                case "forward":
                    RobotSteering.engineForward();
                    break;
                case "backward":
                    RobotSteering.engineBackward();
                    break;
                case "stop":
                    RobotSteering.engineStop();
                    break;
                case "setLight(0)":
                    RobotSteering.cameraSetLight(0);
                    break;
                case "setLight(1)":
                    RobotSteering.cameraSetLight(1);
                    break;
                case "setLight(2)":
                    RobotSteering.cameraSetLight(2);
                    break;
                case "setLight(3)":
                    RobotSteering.cameraSetLight(3);
                    break;
            }

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
