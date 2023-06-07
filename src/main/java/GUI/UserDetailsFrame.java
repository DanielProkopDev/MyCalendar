package GUI;

import javax.swing.*;
import java.awt.*;

public class UserDetailsFrame extends JFrame {

    public UserDetailsFrame() {
        setTitle("User Details");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Your Username:");
        JTextField usernameTextField = new JTextField(20);
        usernameTextField.setEditable(false);

        JLabel emailLabel = new JLabel("Your E-mail:");
        JTextField emailTextField = new JTextField(20);
        emailTextField.setEditable(false);

        JLabel newEmailLabel = new JLabel("New E-mail:");
        JTextField newEmailTextField = new JTextField(20);

        JLabel confirmNewEmailLabel = new JLabel("Confirm New E-mail:");
        JTextField confirmNewEmailTextField = new JTextField(20);

        JLabel newPasswordLabel = new JLabel("New Password:");
        JTextField newPasswordTextField = new JTextField(20);

        JLabel confirmNewPasswordLabel = new JLabel("Confirm New Password:");
        JTextField confirmNewPasswordTextField = new JTextField(20);

        JLabel setDietsLabel = new JLabel("Set Your Diets:");
        JRadioButton proteinRadioButton = new JRadioButton("Protein");
        JRadioButton carboRadioButton = new JRadioButton("Carbo");
        JRadioButton fruitRadioButton = new JRadioButton("Fruit");
        JRadioButton veganRadioButton = new JRadioButton("Vegan");

        JButton saveButton = new JButton("Save");
        JButton discardButton = new JButton("Discard");

        ButtonGroup dietButtonGroup = new ButtonGroup();
        dietButtonGroup.add(proteinRadioButton);
        dietButtonGroup.add(carboRadioButton);
        dietButtonGroup.add(fruitRadioButton);
        dietButtonGroup.add(veganRadioButton);

        // Add components to the panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        panel.add(emailTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(newEmailLabel, gbc);

        gbc.gridx = 1;
        panel.add(newEmailTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(confirmNewEmailLabel, gbc);

        gbc.gridx = 1;
        panel.add(confirmNewEmailTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(newPasswordLabel, gbc);

        gbc.gridx = 1;
        panel.add(newPasswordTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(confirmNewPasswordLabel, gbc);

        gbc.gridx = 1;
        panel.add(confirmNewPasswordTextField, gbc);

        gbc.gridy = 6;
        panel.add(setDietsLabel, gbc);

        gbc.gridy = 7;
        panel.add(proteinRadioButton, gbc);

        gbc.gridy = 8;
        panel.add(carboRadioButton, gbc);

        gbc.gridy = 9;
        panel.add(fruitRadioButton, gbc);

        gbc.gridy = 10;
        panel.add(veganRadioButton, gbc);

        gbc.gridy = 11;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveButton, gbc);

        gbc.gridx = 1;
        panel.add(discardButton, gbc);

        setContentPane(panel);
        setVisible(true);
    }


}
