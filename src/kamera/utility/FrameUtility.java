package kamera.utility;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TheKing on 10.04.2016.
 */
public class FrameUtility {
    public static void centerWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public static void maximize(JFrame testowy) {
        testowy.setExtendedState(testowy.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
}
