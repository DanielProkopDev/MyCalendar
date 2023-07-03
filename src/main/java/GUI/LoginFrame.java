package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField emailTextField;
    private JTextField passwordTextField;

    private StartView1 startView1;

    public LoginFrame(StartView1 startView1) {
        this.startView1 = startView1;
        setTitle("Log In");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel loginLabel = new JLabel("Log In");
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(loginLabel, gbc);

        gbc.gridwidth = 1; // Reset gridwidth to default

        JLabel emailLabel = new JLabel("E-mail:");
        emailTextField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordTextField = new JPasswordField();

        JButton submit = new JButton("Submit");

        submit.addMouseListener(new LoginSubmitListener( this,startView1));

        emailTextField.setPreferredSize(new Dimension(300, 25)); // Adjust the width and height of the text fields
        passwordTextField.setPreferredSize(new Dimension(300, 25));

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(emailTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordTextField, gbc);

        getContentPane().add(loginPanel);
        getContentPane().add(submit,BorderLayout.SOUTH);
        setVisible(true);
    }

    public String getEmail() {
        return emailTextField.getText();
    }

    public String getPassword() {
        return passwordTextField.getText();
    }
}
