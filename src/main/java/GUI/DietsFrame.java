package GUI;

import javax.swing.*;
import java.awt.*;

public class DietsFrame extends JFrame {

    public DietsFrame() {
        setTitle("Diets");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 5);

        JTextArea proteinTextArea = new JTextArea(10, 30);
        proteinTextArea.setText("asjndbausodbnasudbasuoidbausoidbasbdasiuodb" +
                "asbdasoibdaoisudbaiskjdbaskjdbaksdjbaksjdbaksjdbaksjbdaksj");
        proteinTextArea.setEditable(false);
        JTextArea carboTextArea = new JTextArea(10, 30);
        carboTextArea.setEditable(false);
        JTextArea fruitTextArea = new JTextArea(10, 30);
        fruitTextArea.setEditable(false);
        JTextArea veganTextArea = new JTextArea(10, 30);
        veganTextArea.setEditable(false);

        proteinTextArea.setLineWrap(true);
        carboTextArea.setLineWrap(true);
        fruitTextArea.setLineWrap(true);
        veganTextArea.setLineWrap(true);

        // Set the preferred size of the JTextAreas
        Dimension preferredSize = new Dimension(250, 80);
        proteinTextArea.setPreferredSize(preferredSize);
        carboTextArea.setPreferredSize(preferredSize);
        fruitTextArea.setPreferredSize(preferredSize);
        veganTextArea.setPreferredSize(preferredSize);

        // Set up scroll panes for each text area
        JScrollPane proteinScrollPane = new JScrollPane(proteinTextArea);
        JScrollPane carboScrollPane = new JScrollPane(carboTextArea);
        JScrollPane fruitScrollPane = new JScrollPane(fruitTextArea);
        JScrollPane veganScrollPane = new JScrollPane(veganTextArea);

        // Set the size of the scroll panes
        Dimension scrollPaneSize = new Dimension(300, 100);
        proteinScrollPane.setPreferredSize(scrollPaneSize);
        carboScrollPane.setPreferredSize(scrollPaneSize);
        fruitScrollPane.setPreferredSize(scrollPaneSize);
        veganScrollPane.setPreferredSize(scrollPaneSize);

        // Add labels and scroll panes to the panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(new JLabel("Protein Diet:"), gbc);

        gbc.gridx = 1;
        contentPanel.add(proteinScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("Carbo Diet:"), gbc);

        gbc.gridx = 1;
        contentPanel.add(carboScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("Fruit Diet:"), gbc);

        gbc.gridx = 1;
        contentPanel.add(fruitScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(new JLabel("Vegan Diet:"), gbc);

        gbc.gridx = 1;
        contentPanel.add(veganScrollPane, gbc);

        JButton closeButton = new JButton("Close");
        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        setContentPane(panel);
        setVisible(true);
    }

}

