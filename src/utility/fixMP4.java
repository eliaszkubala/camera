package utility;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by TheKing on 08.05.2016.
 */
public class fixMP4 {
    private static File batDir = new File(System.getProperty("user.dir") + "/bat/");
    private static File workDir = GlobalData.CURRENT_DIR_FILE;

    public static void main(String[] args) {
        fix("nowy projekt", "Nagranie 2016-05-07 15-24-14");
    }

    public static boolean fix(String projectName, String fileName) {
        boolean success = true;
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();

        JDialog dialog = new JDialog();
        JLabel label = new JLabel("Please wait...");
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("Please Wait...");
        dialog.add(label);

        JProgressBar aJProgressBar = new JProgressBar(JProgressBar.HORIZONTAL);
        aJProgressBar.setStringPainted(true);
        aJProgressBar.setIndeterminate(true);

        aJProgressBar.setString("Konwertowanie pliku");

        dialog.add(aJProgressBar, BorderLayout.CENTER);

        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        Dimension dimension = new Dimension();
        dimension.setSize(300, 50);
        dialog.setPreferredSize(dimension);

        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);

        dialog.pack();
        dialog.setVisible(true);


        try {
            String[] command = {"cmd.exe", "/c",
                    "konwertuj.bat",
                    projectName,
                    fileName,
                    workDir.getAbsolutePath()
            };
            ProcessBuilder pb = new ProcessBuilder(
                    command
            );
            pb.directory(batDir);
            java.lang.Process process = pb.start();
            //Read out dir output
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;

            InputStream in = process.getErrorStream();
            System.out.printf("Output of running %s is:\n", Arrays.toString(command));
            int c;

            Scanner scanner = new Scanner(in);

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);

                if (line.contains("Output file #0 does not contain any stream")) {
                    dialog.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Nagranie jest uszkodzone!");
                    success = false;
                }
            }
            in.close();

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        dialog.setVisible(false);
        return success;
    }
}
