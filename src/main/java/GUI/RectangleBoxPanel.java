package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RectangleBoxPanel extends JPanel {
    private JTextArea rectangleBoxTextArea;

    public RectangleBoxPanel() {
        createUI();
    }

    private void createUI() {
        setPreferredSize(new Dimension(200, 600));
        setBackground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        rectangleBoxTextArea = new JTextArea();
        rectangleBoxTextArea.setPreferredSize(new Dimension(150, 100));
        rectangleBoxTextArea.setLineWrap(true);
        rectangleBoxTextArea.setWrapStyleWord(true);
        add(rectangleBoxTextArea);
    }

    public JTextArea getRectangleBoxTextArea() {
        return rectangleBoxTextArea;
    }
}

