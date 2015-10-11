package ch.vilalde.tracker;

import ch.vilalde.tracker.domain.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Form window to for adding a new project
 */
public class NewProjectWindow extends JFrame implements ActionListener {

    /**
     * Reference to the main tracker
     */
    Tracker main;
    /**
     * Project name label
     */
    JLabel nameLabel;
    /**
     * Color chooser label
     */
    JLabel colorLabel;
    /**
     * JColorChooser for selecting the project color
     */
    JColorChooser colorChooser;
    /**
     * Actual field for the name of a new project
     */
    JTextField nameField;
    /**
     * Button for saving the project
     */
    JButton saveButton;

    /**
     * Std. constructor for the window
     * @param tracker Reference to the main tracker, used for data and window control
     */
    public NewProjectWindow(Tracker tracker) {
        super("Tracker - Add new Project");

        this.setSize(550, 400);

        main = tracker;

        prepareUi();

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("Add new Project window closed!");
                clean();
            }
        });
    }

    /**
     * Prepares all the necessary UI components for this window, but does not set it visible
     */
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
        saveButton.addActionListener(this);
        this.add(saveButton, constraints);

        this.pack();
    }

    /**
     * Cleans the necessary fields for reuse
     */
    public void clean(){
        nameField.setText("");
    }

    /**
     * @return true if all the required fields have been filled
     */
    public Boolean isFilled(){
        return !nameField.getText().equals("") && colorChooser.getColor() != null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")){
            if (isFilled()){
                // All good, create and save the new project
                Project project = new Project();
                project.setName(nameField.getText());
                project.setColor(colorChooser.getColor());
                main.addProject(project);
                clean();
                this.setVisible(false);
            }
        }
    }
}
