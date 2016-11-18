package kamera.common.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TheKing on 27.02.2016.
 */
public class OpaqueJFrame extends JFrame {
    public OpaqueJFrame(String title) throws HeadlessException {
        super(title);
        setUndecorated(true);
        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        setOpacity((float) 0.8);
    }
}
