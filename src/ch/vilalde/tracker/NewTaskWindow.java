package ch.vilalde.tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Form for entering new tasks and projects
 */
public class NewTaskWindow extends JFrame {


    private JPanel mainPanel;

    public NewTaskWindow() {
        super("Tracker - Add new Task");

        this.setSize(550, 400);

        prepareUi();

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("Add new Task window closed!");
            }
        });
    }

    private void prepareUi() {
        // A proper SpringLayout would probably be appropriate here
        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 10));

        JLabel label = new JLabel("Even here will be some content");

        mainPanel.add(label);
        this.add(mainPanel);
    }

}
