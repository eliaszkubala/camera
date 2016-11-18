package kamera.common;

import kamera.common.RS485Reader;

/**
 * Created by TheKing on 24.02.2016.
 */
public class RobotSteering {
    public static void cameraRight() {
        RS485Reader.getInstance().write("cameraRight()");
    }

    public static void cameraLeft() {
        RS485Reader.getInstance().write("cameraLeft()");
    }

    public static void cameraReset() {
        RS485Reader.getInstance().write("cameraReset()");
    }

    public static void cameraDown() {
        RS485Reader.getInstance().write("cameraUp()");
    }

    public static void cameraUp() {
        RS485Reader.getInstance().write("cameraDown()");
    }

    public static void engineStop() {
        RS485Reader.getInstance().write("stopEngine()");
    }

    public static void engineBackward() {
        RS485Reader.getInstance().write("forward()");
    }

    public static void engineForward() {
        RS485Reader.getInstance().write("backward()");
    }

    public static void engineSpeed(int speed) {
        RS485Reader.getInstance().write("setSpeed(" + speed + ")");
    }

    public static void cameraSetLight(int value) {
        RS485Reader.getInstance().write("setLight(" + value + ")");
    }
}
