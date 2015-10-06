package ch.vilalde.tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Tracker implements ActionListener {

    private JFrame mainFrame;
    private NewTaskWindow newTaskDialog;

    public Tracker() {
        prepareUi();
    }

    private void prepareUi() {
        mainFrame = new JFrame("Tracker - Your lightweight task tracker");
        mainFrame.setSize(1000, 700);
        mainFrame.setLayout(new BorderLayout());

        JMenuBar mainMenu = new TrackerMenu(mainFrame);
        mainFrame.setJMenuBar(mainMenu);

        newTaskDialog = new NewTaskWindow();

        JPanel buttonBar = new JPanel(new FlowLayout());
        JButton priorityButton = new JButton("Priority");
        priorityButton.addActionListener(this);
        buttonBar.add(priorityButton);
        JButton estEffortButton = new JButton("Estimated Effort");
        estEffortButton.addActionListener(this);
        buttonBar.add(estEffortButton);
        JButton spentEffortButton = new JButton("Spent Effort");
        spentEffortButton.addActionListener(this);
        buttonBar.add(spentEffortButton);

        // @Todo: Also add nice + img
        JButton addTaskButton = new JButton("Add new Task");
        addTaskButton.addActionListener(this);
        buttonBar.add(addTaskButton);

        mainFrame.add(buttonBar, BorderLayout.NORTH);

        JLabel message = new JLabel("There will a nice TreeMap here...");
        mainFrame.add(message, BorderLayout.CENTER);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public void showUi() {
        mainFrame.setVisible(true);
    }


    public static void main(String[] args) {
        Tracker ui = new Tracker();
        ui.showUi();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add new Task")) {

            newTaskDialog.setVisible(true);
        } else {
            System.out.println("This button is useless at the moment...");
        }
    }
}
