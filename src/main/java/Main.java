import GUI.MainFrame;

import javax.swing.*;

public class Main implements Runnable {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame();
            }
        });
    }
}


