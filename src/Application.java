import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import utility.GlobalData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by TheKing on 16.05.2016.
 */
public class Application {

    public static void main(final String[] args) {
        GlobalData.setLookAndFeel(GlobalData.NIMBUS);
        System.out.println("jdk version:  " + System.getProperty("sun.arch.data.model") + " bits.");
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CameraPreviewMain(args);
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new RobotSteeringKeyboardListener());

        LogsSender.send(LogsIntCode.URUCHOMIONO_SRODOWISKO, LogsString.URUCHOMIONO_SRODOWISKO);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                LogsSender.send(LogsIntCode.ZAMKNIETO_SRODOWISKO, LogsString.ZAMKNIETO_SRODOWISKO);
            }
        });
    }

}
