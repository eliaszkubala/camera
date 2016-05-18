import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by TheKing on 24.02.2016.
 */
public class RobotSteeringKeyboardListener implements KeyEventDispatcher {
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        //System.out.println(e.getKeyChar() + " " + e.getKeyCode());
        int keyCode = e.getKeyCode();

        // głowica
        if(e.isControlDown()) {
            if (keyCode == KeyEvent.VK_NUMPAD8 || keyCode == KeyEvent.VK_UP) {
                RobotSteering.cameraUp();
                return true;
            }

            if (keyCode == KeyEvent.VK_NUMPAD2|| keyCode == KeyEvent.VK_DOWN) {
                RobotSteering.cameraDown();
                return true;
            }
            if (keyCode == KeyEvent.VK_NUMPAD5 || keyCode == KeyEvent.VK_ESCAPE) {
                RobotSteering.cameraReset();
                return true;
            }
            if (keyCode == KeyEvent.VK_NUMPAD4|| keyCode == KeyEvent.VK_LEFT) {
                RobotSteering.cameraLeft();
                return true;
            }
            if (keyCode == KeyEvent.VK_NUMPAD6 || keyCode == KeyEvent.VK_RIGHT) {
                RobotSteering.cameraRight();
                return true;
            }
        }

        // silnik
        // z altem
        if (e.isShiftDown()) {
            if (keyCode == KeyEvent.VK_NUMPAD8 || keyCode == KeyEvent.VK_UP) {
                RobotSteering.engineForward();
                return true;
            }
            if (keyCode == KeyEvent.VK_NUMPAD2 || keyCode == KeyEvent.VK_DOWN) {
                RobotSteering.engineBackward();
                return true;
            }
            if (keyCode == KeyEvent.VK_NUMPAD5 || keyCode == KeyEvent.VK_ESCAPE) {
                RobotSteering.engineStop();
                return true;
            }
        }

        // światło
        if(e.isAltDown()) {
            if (keyCode == KeyEvent.VK_NUMPAD0) {
                RobotSteering.cameraSetLight(0);
                return true;
            }
            if (keyCode == KeyEvent.VK_NUMPAD1) {
                RobotSteering.cameraSetLight(1);
                return true;
            }
            if (keyCode == KeyEvent.VK_NUMPAD2) {
                RobotSteering.cameraSetLight(2);
                return true;
            }
            if (keyCode == KeyEvent.VK_NUMPAD3) {
                RobotSteering.cameraSetLight(3);
                return true;
            }
        }

        return false;
    }
}
