/**
 * Created by TheKing on 24.02.2016.
 */
public class RobotSteering {
    public static void cameraRight() {
        RS485_Reader.getInstance().write("cameraRight()");
    }

    public static void cameraLeft() {
        RS485_Reader.getInstance().write("cameraLeft()");
    }

    public static void cameraReset() {
        RS485_Reader.getInstance().write("cameraReset()");
    }

    public static void cameraDown() {
        RS485_Reader.getInstance().write("cameraUp()");
    }

    public static void cameraUp() {
        RS485_Reader.getInstance().write("cameraDown()");
    }

    public static void engineStop() {
        RS485_Reader.getInstance().write("stopEngine()");
    }

    public static void engineBackward() {
        RS485_Reader.getInstance().write("forward()");
    }

    public static void engineForward() {
        RS485_Reader.getInstance().write("backward()");
    }

    public static void engineSpeed(int speed){
        RS485_Reader.getInstance().write("setSpeed("+speed+")");
    }

    public static void cameraSetLight(int value) {
        RS485_Reader.getInstance().write("setLight(" + value + ")");
    }
}
