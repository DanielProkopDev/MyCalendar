package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

public class CalendarApp1  implements Runnable {
    private int year;
    private JPanel yearPanel;
    private JTextArea rectangleBoxTextArea;

    Border border = BorderFactory.createLineBorder(Color.BLACK);

    // Get the current date to determine the past days
    LocalDate currentDate = LocalDate.now();
    JPanel rectangleBoxPanel;


    public CalendarApp1(int year) {
        this.year = year;
        System.out.println(year);
        System.out.println(currentDate);

    }


    public void run() {
        createUI();

        try {
            Thread.sleep(3000); // Simulating some long-running task
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void createUI() {
        JFrame frame = new JFrame("Calendar App");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(1400, 1000)); // Set the preferred frame size

        // Create the rectangle box panel on the right side
        rectangleBoxPanel = new RectangleBoxPanel();
        frame.add(rectangleBoxPanel, BorderLayout.EAST);

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the "File" menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Create the "New" menu item under "File"
        JMenuItem newMenuItem = new JMenuItem("New");
        fileMenu.add(newMenuItem);

        // Create the "Options" menu item under "File"
        JMenuItem optionsMenuItem = new JMenuItem("Options");
        fileMenu.add(optionsMenuItem);

        // Create the "Delete" menu item under "File"
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        fileMenu.add(deleteMenuItem);


        // Create the "Home" menu
        JMenu homeMenu = new JMenu("Home");
        menuBar.add(homeMenu);

        // Create the "Diet" menu
        JMenu dietMenuItem = new JMenu("Diet");
        homeMenu.add(dietMenuItem);

        // Create the "User" menu
        JMenu userMenuItem = new JMenu("User");
        homeMenu.add(userMenuItem);

        // Create the "Exit" menu item with custom UI
        JMenuItem exitMenuItem = new JMenuItem("Exit") {
            @Override
            public Dimension getPreferredSize() {
                Dimension preferredSize = super.getPreferredSize();
                preferredSize.width = 100;  // Set the desired width
                return preferredSize;
            }
        };
        exitMenuItem.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon,
                                         Color background, Color foreground, int defaultTextIconGap) {
                // Custom rendering code for the menu item
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(background);
                g2.fillRect(0, 0, c.getWidth(), c.getHeight());
                g2.setColor(foreground);
                g2.setFont(c.getFont());
                g2.drawString(((JMenuItem) c).getText(), 10, c.getHeight() - defaultTextIconGap);
            }
        });
        menuBar.add(exitMenuItem);



        // Add action listeners to the menu items
        newMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle "New" action
                JOptionPane.showMessageDialog(frame, "New action clicked");
            }
        });

        optionsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle "Options" action
                JOptionPane.showMessageDialog(frame, "Options action clicked");
            }
        });

        deleteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle "Delete" action
                JOptionPane.showMessageDialog(frame, "Delete action clicked");
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle "Exit" action
                JFrame calendarAppFrame = (JFrame) SwingUtilities.getWindowAncestor(rectangleBoxPanel);
                calendarAppFrame.dispose(); // Dispose the CalendarApp frame
            }
        });

        // Set the menu bar to the frame
        frame.setJMenuBar(menuBar);

        // Add a window listener to the frame
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle the window closing event
                JFrame calendarAppFrame = (JFrame) e.getWindow();
                calendarAppFrame.dispose(); // Dispose the CalendarApp frame
            }
        });


        yearPanel = new JPanel(new GridLayout(3, 4));
        frame.add(yearPanel);

        for (Month month : Month.values()) {
            YearMonth yearMonth = YearMonth.of(year, month);
            int daysInMonth = yearMonth.lengthOfMonth();
            String monthName = month.toString();

            JPanel monthPanel = createMonthPanel(monthName, daysInMonth);
            yearPanel.add(monthPanel);
        }




        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createMonthPanel(String monthName, int daysInMonth) {
        JPanel monthPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                int arc = 20; // Radius of the rounded corners
                int x = 0;
                int y = 0;
                int width = getWidth();
                int height = getHeight();
                Shape roundedRect = new RoundRectangle2D.Double(x, y, width - 1, height - 1, arc, arc);

                // Set clip to the rounded rectangle
                g2d.setClip(roundedRect);

                // Fill the clipped area with ocean blue color
                g2d.setColor(new Color(0, 105, 148));
                g2d.fillRect(x, y, width, height);

                // Set stroke for the border
                Stroke borderStroke = new BasicStroke(5f); // 5 pixels thickness
                g2d.setStroke(borderStroke);

                // Draw the rounded rectangle border
                g2d.setColor(Color.WHITE);
                g2d.draw(roundedRect);

                g2d.dispose();
            }
        };
        monthPanel.setOpaque(false);
        monthPanel.setLayout(new BorderLayout());

        /// Create a label for the month name
        JLabel monthLabel = new JLabel(monthName, JLabel.CENTER);
        monthLabel.setFont(monthLabel.getFont().deriveFont(Font.BOLD, 20f));
        monthLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); // Add padding to the top
        monthPanel.add(monthLabel, BorderLayout.NORTH);

        // Create a panel for the day names
        JPanel dayNamesPanel = new JPanel(new GridLayout(1, 7));
        dayNamesPanel.setBorder(BorderFactory.createEmptyBorder(1, 5, 3, 5)); // Add padding to the left and right
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String dayName : dayNames) {
            JLabel dayNameLabel = new JLabel(dayName, JLabel.CENTER);
            dayNameLabel.setFont(dayNameLabel.getFont().deriveFont(Font.BOLD, 16f));
            dayNamesPanel.add(dayNameLabel);
        }
        monthPanel.add(dayNamesPanel, BorderLayout.CENTER);

        // Create a panel for the day numbers
        JPanel dayNumbersPanel = new JPanel(new GridLayout(0, 7));
        dayNumbersPanel.setBorder(BorderFactory.createEmptyBorder(1, 5, 70, 5)); // Add padding to the left, right, and bottom



        // Calculate the starting day of the month
        YearMonth yearMonth = YearMonth.of(year, Month.valueOf(monthName.toUpperCase()));
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int startingDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue() % 7;

        // Add empty labels for the days before the starting day
        for (int i = 0; i < startingDayOfWeek; i++) {
            dayNumbersPanel.add(new JLabel());
        }

        // Add day number labels for each day of the month
        for (int day = 1; day <= daysInMonth; day++) {
            JLabel dayNumberLabel = new JLabel(String.valueOf(day), JLabel.CENTER);
            dayNumberLabel.setFont(dayNumberLabel.getFont().deriveFont(Font.BOLD, 18f)); // Adjust the font size as desired
            dayNumberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border around the day number label

            // Check if the day is within the valid range for the month
            if (day <= yearMonth.lengthOfMonth()) {
                LocalDate date = yearMonth.atDay(day);
                if (date.isBefore(currentDate)) {
                    dayNumberLabel.setForeground(Color.RED);
                } else if (date.isEqual(currentDate)) {
                    dayNumberLabel.setForeground(Color.GREEN);
                } else {
                    dayNumberLabel.setForeground(Color.BLACK);
                }
            } else {
                dayNumberLabel.setForeground(Color.BLACK);
            }

            // Add mouse listener to the day number label

            dayNumberLabel.addMouseListener(new DayMouseListener(dayNumberLabel,yearMonth,rectangleBoxPanel,border));

            dayNumbersPanel.add(dayNumberLabel);
        }

        monthPanel.add(dayNumbersPanel, BorderLayout.SOUTH);

        return monthPanel;
    }

}
