package GUI;

import javax.swing.*;
import java.awt.*;

public class StatusIndicator extends JLabel {
    private Color color;

    public StatusIndicator() {
        setPreferredSize(new Dimension(20, 20));
        color = Color.RED;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(color);
        g2d.fillOval(0, 0, getWidth(), getHeight());
    }

    public void setColor1(Color color) {
        this.color = color;
        repaint();
    }
}

