package kamera;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import kamera.common.GlobalKeyboardListener;
import kamera.common.LogsSender;
import kamera.common.RS485Reader;
import kamera.project.ProjectWindow;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import kamera.utility.GlobalData;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class Application {

    public static void main(final String[] args) {
        setLookAndFeel(NIMBUS);
        System.out.println("jdk version:  " + System.getProperty("sun.arch.data.model") + " bits.");
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        RS485Reader.getInstance();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProjectWindow();
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new GlobalKeyboardListener());

        LogsSender.send(LogsSender.LogsIntCode.URUCHOMIONO_SRODOWISKO, LogsSender.LogsString.URUCHOMIONO_SRODOWISKO);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                LogsSender.send(LogsSender.LogsIntCode.ZAMKNIETO_SRODOWISKO, LogsSender.LogsString.ZAMKNIETO_SRODOWISKO);
            }
        });
    }

    public final  static int WINDOWS = 0;
    public final static int NIMBUS = 1;
    public static void setLookAndFeel(int TYPE) {
        try {
            switch (TYPE) {
                case NIMBUS:
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
                    break;
                case WINDOWS:
                    UIManager.setLookAndFeel(new WindowsLookAndFeel());
                    break;
            }
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

}
