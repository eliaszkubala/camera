import javax.swing.*;
import java.awt.*;

/**
 * Created by TheKing on 06.04.2016.
 */
class JArc extends JComponent {

    private int startAngle;
    private int arcAngle;
    private int offsetX = 10;
    private int offsetY = 10;

    public JArc(int startAngle, int arcAngle) {
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        setPreferredSize(new Dimension(100, 100));

        if(getParent() instanceof JPanel) {
            offsetX = (int) ((getParent().getWidth() - getPreferredSize().getWidth()) / 2);
            offsetY = (int) ((getParent().getHeight() - getPreferredSize().getHeight()) / 2);
        }

    }

    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.GRAY);
        g2.fillArc(offsetX, offsetY, (int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight(), 0, 360);
        g2.setColor(Color.BLACK);
        g2.fillArc(offsetX, offsetY, (int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight(),
                startAngle - arcAngle / 2 + 90, arcAngle
        );

        g2.setColor(Color.WHITE);
        g2.fillArc(offsetX + 20, offsetY + 20, (int) getPreferredSize().getWidth() - 40, (int) getPreferredSize().getHeight() - 40, 0, 360);

    }


    public void setParameter(int startAngle, int arcAngle) {
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
    }
}