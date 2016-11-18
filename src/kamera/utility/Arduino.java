package kamera.utility;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

public class Arduino {
    public static String getPort() {
        CommPortIdentifier serialPortId;
        Enumeration enumComm;
        String PORT = null;

        enumComm = CommPortIdentifier.getPortIdentifiers();
        while (enumComm.hasMoreElements()) {
            serialPortId = (CommPortIdentifier) enumComm.nextElement();
            if (serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(serialPortId.getName());
                PORT = serialPortId.getName();
            }
        }
        return PORT;

    }
}