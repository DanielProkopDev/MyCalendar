package GUI;

import Service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateAccountFrame extends JFrame {

   UserService userService = new UserService();
    public CreateAccountFrame() {
        setTitle("Create an Account");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
       // JPanel submitPanel = new JPanel(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        JLabel usernameLabel = new JLabel("Your Username:");
        JTextField usernameTextField = new JTextField(20);

        JLabel emailLabel = new JLabel("E-mail:");
        JTextField emailTextField = new JTextField(20);

        JLabel confirmEmailLabel = new JLabel("Confirm E-mail:");
        JTextField confirmEmailTextField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordTextField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JTextField confirmPasswordTextField = new JPasswordField(20);

        JLabel dobLabel = new JLabel("Date of Birth(yyyy-mm-dd):");
        JTextField dobTextField = new JTextField(20);

        JButton submitButton = new JButton("Submit");

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (userService.createUser(usernameTextField.getText(),emailTextField.getText(),confirmEmailTextField.getText(),passwordTextField.getText().toCharArray(),confirmPasswordTextField.getText().toCharArray(),dobTextField.getText())) {
                    JOptionPane.showMessageDialog(CreateAccountFrame.this,"Account Created");
                }else {
                    JOptionPane.showMessageDialog(CreateAccountFrame.this,"Password or Email isn't confirmed");
                }
            }
        });

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
        panel.add(confirmEmailLabel, gbc);

        gbc.gridx = 1;
        panel.add(confirmEmailTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        panel.add(confirmPasswordTextField, gbc);

        gbc.gridy=5;
        gbc.gridx=0;
        panel.add(dobLabel,gbc);

        gbc.gridx=1;
        panel.add(dobTextField,gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        setContentPane(panel);
        setVisible(true);
    }

}

