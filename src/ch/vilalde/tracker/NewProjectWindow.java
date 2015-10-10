package ch.vilalde.tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Form window to for adding a new project
 */
public class NewProjectWindow extends JFrame {

    Tracker main;
    JLabel nameLabel;
    JLabel colorLabel;
    JColorChooser colorChooser;
    JTextField nameField;
    JButton saveButton;

    public NewProjectWindow(Tracker tracker) {
        super("Tracker - Add new Project");

        this.setSize(550, 400);

        main = tracker;

        prepareUi();

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("Add new Project window closed!");
            }
        });
    }

    private void prepareUi(){
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        nameLabel = new JLabel("Project name:");
        this.add(nameLabel, constraints);

        constraints.gridx = 1;
        nameField = new JTextField(30);
        this.add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        colorLabel = new JLabel("Choose the project color:");
        this.add(colorLabel, constraints);

        constraints.gridy = 2;
        constraints.gridwidth = 2;
        colorChooser = new JColorChooser();
        this.add(colorChooser, constraints);

        constraints.gridy = 3;
        constraints.gridwidth = 2;
        saveButton = new JButton("Save");
        this.add(saveButton, constraints);

        this.pack();
    }
}
