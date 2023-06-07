package GUI;

import Utils.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton switchButton;

    int yearNow= LocalDate.now().getYear();

    HibernateUtil hibernateUtil = new HibernateUtil();

    public MainFrame() {
        createUI();
        HibernateUtil.getSessionFactory();

    }

    private void createUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the initial view
        JPanel startView = createStartView();
        cardPanel.add(startView, "Start");



        // Create a button to switch views
        switchButton = new JButton("Switch to Calendar");
        switchButton.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent e) {
                                               CalendarApp1 calendarApp = new CalendarApp1(yearNow);
                                               Thread calendarThread = new Thread(calendarApp);
                                               calendarThread.start();
                                           }
                                       });

                // Add the cardPanel and button to the frame
                getContentPane().setLayout(new BorderLayout());
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(switchButton, BorderLayout.SOUTH);

        setSize(800, 600);
        setVisible(true);
    }

    private JPanel createStartView() {
        StartView startView = new StartView();
       /* JPanel startView = new JPanel();
        JLabel startLabel = new JLabel("Welcome to the Start View");
        startLabel.setFont(new Font("Arial", Font.BOLD, 24));
        startView.add(startLabel);
        return startView;*/
        return startView;
    }


}

