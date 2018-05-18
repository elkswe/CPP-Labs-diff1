package by.bsuir.road;

import by.bsuir.road.model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 *  The main class that creates a GUI and manages the application.
 */
public class Main {
    /**
     *  Flag of exit.
     */
    private static boolean fExit = false;

    /**  **/
    private static final int STARTING_WIDTH = 300;

    /**  **/
    private static final int STARTING_HEIGHT = 200;

    /**  **/
    private static final int CAR_COUNT = 50;

    /**  **/
    private static final int DEFAULT_SLEEP_TIME = 110;

    /**
     * Creates GUI and manages the application.
     * @param args Command line.
     */
    public static void main(final String[] args) {
        var manager = new Manager();

        var frame = new JFrame("Road");
        var textArea = new JTextArea();
        var scrollPane = new JScrollPane(textArea);
        var mainPanel = new JPanel();
        var button = new JButton("Exit");
        var southPanel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(STARTING_WIDTH, STARTING_HEIGHT);
        textArea.setCaretPosition(0);
        southPanel.setLayout(new FlowLayout());
        southPanel.add(button);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        button.addActionListener(e -> Main.fExit = true);

        frame.setContentPane(mainPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while (!fExit) {
            int count = 0;
            while (count++ < CAR_COUNT) {
                manager.addCar();
            }
            textArea.setText(manager.getViolations());
            manager.removeAll();
            try {
                Thread.sleep(DEFAULT_SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
