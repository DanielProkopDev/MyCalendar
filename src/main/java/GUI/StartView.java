package GUI;
/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartView extends JPanel {

    StartView startView
    public StartView() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components

        JButton createAccountButton = new JButton("Create an Account");
        JButton logInButton = new JButton("Log In");
        JButton userDetailsButton = new JButton("User Details");
        JButton dietsButton = new JButton("Diets");
        JButton ingredientsButton = new JButton("Ingredients");
        JButton exitButton = new JButton("Exit");


        dietsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DietsFrame dietsFrame = new DietsFrame(); // Opens DietsFrame
            }
        });


        userDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDetailsFrame userDetailsFrame = new UserDetailsFrame(); // Opens UserDetailsFrame
            }
        });



        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccountFrame createAccountFrame = new CreateAccountFrame(); // Open CreateAccountFrame
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame loginFrame = new LoginFrame();

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createAccountButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(logInButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(userDetailsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(dietsButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(ingredientsButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(exitButton, gbc);
    }
}*/