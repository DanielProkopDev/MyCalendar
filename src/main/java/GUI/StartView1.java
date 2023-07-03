package GUI;

import GUI.util.LoginStatusObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartView1 extends JPanel implements LoginStatusObserver {
    private StatusIndicator statusIndicator;

    StartView1 startView1;

    public StartView1() {
        this.startView1=this;
        setLayout(new BorderLayout()); // Use BorderLayout as the main layout manager

        JButton createAccountButton = new JButton("Create an Account");
        JButton logInButton = new JButton("Log In");
        JButton userDetailsButton = new JButton("User Details");
        JButton dietsButton = new JButton("Diets");
        JButton ingredientsButton = new JButton("Ingredients");
        JButton exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel(new GridBagLayout()); // Create a panel for buttons using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components

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
                JFrame loginFrame = new LoginFrame(startView1);

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
        buttonPanel.add(createAccountButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(logInButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        buttonPanel.add(userDetailsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(dietsButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(ingredientsButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonPanel.add(exitButton, gbc);

        add(buttonPanel, BorderLayout.CENTER); // Add the button panel to the center of the main panel

        // Create the status indicator and add it to the bottom right corner using FlowLayout
        statusIndicator = new StatusIndicator();
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        statusPanel.add(statusIndicator);
        add(statusPanel, BorderLayout.SOUTH);
    }

    // Method to update the status indicator color
    @Override
    public void updateStatusIndicator(boolean loginSuccess) {
        Color indicatorColor = loginSuccess ? Color.GREEN : Color.RED;
        statusIndicator.setColor1(indicatorColor);
    }
}

