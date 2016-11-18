package kamera.common;

import com.sun.deploy.util.StringUtils;
import kamera.project.ProjectWindow;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import kamera.utility.GlobalData;
import kamera.utility.Arduino;

import java.io.*;
import java.math.BigDecimal;
import java.util.Enumeration;

public class RS485Reader implements SerialPortEventListener {

    private static RS485Reader instance;
    public FileMaker listener;
    public ProjectWindow mainListener;
    private String DISTANCE_DATA;
    private boolean CONNECTED_STATUS;

    public float getGYROSCOPE_DEGREE_DATA() {
        return GYROSCOPE_DEGREE_DATA;
    }

    private float GYROSCOPE_DEGREE_DATA;

    public String getPRESSURE_DATA() {
        return PRESSURE_DATA;
    }

    private String PRESSURE_DATA;

    public boolean isCONNECTED_STATUS() {
        return CONNECTED_STATUS;
    }

    public String getHUMI_DATA() {
        return HUMI_DATA;
    }

    public String getTEMP_DATA() {
        return TEMP_DATA;
    }

    public String getGYROSCOPE_DATA() {
        return GYROSCOPE_DATA;
    }

    public String getDISTANCE_DATA() {
        return DISTANCE_DATA;
    }

    private String TEMP_DATA;
    private String HUMI_DATA;
    private String GYROSCOPE_DATA;

    public static RS485Reader getInstance() {
        if (instance == null) {
            instance = new RS485Reader();
            instance.initialize();
        }

        return instance;
    }

    SerialPort serialPort;
    /**
     * The port we're normally going to use.
     */
    private static final String PORT_NAMES[] = {
            "/dev/tty.usbserial-A9007UX1", // Mac OS X
            "/dev/ttyACM0", // Raspberry Pi
            "/dev/ttyUSB0", // Linux
            Arduino.getPort(), // Windows
    };
    private PrintStream printStream;
    /**
     * A BufferedReader which will be fed by a InputStreamReader
     * converting the bytes into characters
     * making the displayed results codepage independent
     */
    private BufferedReader input;
    /**
     * The output stream to the port
     */
    private OutputStream output;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 38400;

    public void initialize() {

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();


        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }

        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            serialPort.disableReceiveTimeout();
            serialPort.enableReceiveThreshold(1);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();
            printStream = new PrintStream(output);

            // addOld kamera.event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);


            //write("forward()");

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }


    public void write(String s) {
        // TODO Auto-generated method stub
        System.out.println("RS 485 > " + s);
        try {
            output.write(s.getBytes());
            CONNECTED_STATUS = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            CONNECTED_STATUS = false;
        }
    }

    /**
     * This should be called when you stop using the port.
     * This will prevent port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an kamera.event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {

        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {


            try {
                String inputLine = input.readLine();
                System.out.println("test " + inputLine);

                if (RS485Reader.getInstance().mainListener != null) {
                    RS485Reader.getInstance().mainListener.serialEvents(inputLine);
                }

                if (RS485Reader.getInstance().listener != null) {
                    RS485Reader.getInstance().listener.serialEvent(inputLine);
                }
                CONNECTED_STATUS = true;

                String[] data = StringUtils.splitString(inputLine, "|");
                if (data[0].equals("temp")) {
                    String temp = data[1];
                    TEMP_DATA = temp;
                }

                if (data[0].equals("hum")) {
                    String temp = data[1];
                    HUMI_DATA = temp;
                }

                if (data[0].equals("dis")) {
                    String temp = data[1];
                    DISTANCE_DATA = temp;
                }

                if (data[0].equals("pre")) {
                    String temp = data[1];
                    PRESSURE_DATA = temp;
                }

                if (data[0].equals("gyro")) {
                    GYROSCOPE_DEGREE_DATA = Float.parseFloat(data[2]);
                    float stopnie = (Float.parseFloat(data[2]) + GlobalData.GYROSCOPE_OFFSET);
                    System.out.println("Żyroskop " + stopnie);
                    stopnie = Math.round(stopnie * 1000f) / 1000f;
                    stopnie = (float) (Math.tan(Math.toRadians(stopnie)) * 100f);

                    System.out.println("Procent " + stopnie);
                    System.out.println(stopnie);
                    stopnie = new BigDecimal(stopnie).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();

                    /*
                    x - 0%
                    45 - 100%
                     */

                    String y = String.valueOf(stopnie);
                    GYROSCOPE_DATA = y;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                System.err.println(e.toString());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        RS485Reader main = new RS485Reader();
        main.initialize();
        Thread t = new Thread() {
            public void run() {
                //the following line will keep this app alive for 1000 seconds,
                //waiting for events to occur and responding to them (printing incoming messages to console).
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException ie) {
                }
            }
        };
        t.start();
        System.out.println("Started");
    }

    public void setListener(FileMaker listener) {
        this.listener = listener;
    }

    public void setMainListener(ProjectWindow listner) {
        this.mainListener = listner;
    }
}