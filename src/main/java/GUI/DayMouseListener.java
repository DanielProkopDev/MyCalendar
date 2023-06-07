package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;



class DayMouseListener extends MouseAdapter {


    JLabel dayNumberLabel;
    YearMonth yearMonth;

    JPanel rectangleBoxPanel;

    Border border;

    private Color originalColorBackground;

    private Color originalColorForeground;

    private final Color highlightColor = new Color(220, 220, 220);
    public DayMouseListener(JLabel dayNumberLabel, YearMonth yearMonth, JPanel rectangleBoxPanel, Border border){
        super();
        this.dayNumberLabel = dayNumberLabel;
        this.yearMonth = yearMonth;
        this.rectangleBoxPanel = rectangleBoxPanel;
        this.border = border;

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int clickedDay = Integer.parseInt(dayNumberLabel.getText());
        LocalDate clickedDate = yearMonth.atDay(clickedDay);
        LocalDate currentDate = LocalDate.now();
        long daysDifference = ChronoUnit.DAYS.between(currentDate, clickedDate);

        // Update the rectangleBoxPanel with the clicked date information

        rectangleBoxPanel.removeAll();
        rectangleBoxPanel.setLayout(new GridLayout(5, 2));

        // Add the information labels to the rectangleBoxPanel
        JLabel yearLabel = new JLabel("Year: " + clickedDate.getYear());
        JLabel monthLabel = new JLabel("Month: " + clickedDate.getMonth());
        JLabel dayLabel = new JLabel("Day: " + clickedDay);
        JLabel daysDifferenceLabel = new JLabel("How far: " + daysDifference + " days");
        JLabel dietLabel = new JLabel("Diet:");
        JLabel mealLabel = new JLabel("<html>Your Meal<br>of The Day:</html>");

        JTextArea dietTextArea = new JTextArea();
        dietTextArea.setBorder(border);
        JTextArea mealTextArea = new JTextArea();
        mealTextArea.setBorder(border);

        // Create the buttons
        JButton generateMealButton = new JButton("<html>Generate<br>Your Meal</html>");

        JButton saveButton = new JButton("Save");


        rectangleBoxPanel.add(yearLabel);
        rectangleBoxPanel.add(monthLabel);
        rectangleBoxPanel.add(dayLabel);
        rectangleBoxPanel.add(daysDifferenceLabel);
        rectangleBoxPanel.add(dietLabel);
        rectangleBoxPanel.add(dietTextArea);
        rectangleBoxPanel.add(mealLabel);
        rectangleBoxPanel.add(mealTextArea);
        rectangleBoxPanel.add(generateMealButton);
        rectangleBoxPanel.add(saveButton);

        // Refresh the rectangleBoxPanel
        rectangleBoxPanel.revalidate();
        rectangleBoxPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        originalColorBackground = dayNumberLabel.getBackground();
        originalColorForeground = dayNumberLabel.getForeground();
        dayNumberLabel.setOpaque(true);
        dayNumberLabel.setBackground(highlightColor);
        dayNumberLabel.setForeground(Color.YELLOW);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        dayNumberLabel.setOpaque(false);
        dayNumberLabel.setBackground(originalColorBackground);
        dayNumberLabel.setForeground(originalColorForeground);
    }
}
