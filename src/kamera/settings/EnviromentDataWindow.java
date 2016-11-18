package kamera.settings;

import kamera.common.RS485Reader;
import kamera.settings.ui.CameraData;
import kamera.utility.GlobalData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class EnviromentDataWindow {
    private final Timer timer;
    private CameraData cameraData;

    public EnviromentDataWindow() {
        getGUI().setVisible(true);

        timer = new Timer();
        timer.schedule(new SayHello(), 0, 1000);

        getGUI().getCalibration().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalData.GYROSCOPE_OFFSET = -RS485Reader.getInstance().getGYROSCOPE_DEGREE_DATA();
            }
        });
    }

    public CameraData getGUI() {
        if (cameraData == null)
            cameraData = new CameraData();
        return cameraData;
    }

    private class SayHello extends TimerTask {
        @Override
        public void run() {
            if(getGUI().isVisible()) {
                System.out.println("pobieram dane");
                getGUI().getTemp().setText(RS485Reader.getInstance().getTEMP_DATA());
                getGUI().getGyro().setText(RS485Reader.getInstance().getGYROSCOPE_DATA());
                getGUI().getHumi().setText(RS485Reader.getInstance().getHUMI_DATA());
                getGUI().getPres().setText(RS485Reader.getInstance().getPRESSURE_DATA());


                if (RS485Reader.getInstance().isCONNECTED_STATUS()) {
                    getGUI().getStatus().setText("Połączono");
                } else {
                    getGUI().getStatus().setText("Brak połączenia z kamerą");
                }
            }
        }
    }
}
