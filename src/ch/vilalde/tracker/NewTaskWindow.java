package ch.vilalde.tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

/**
 * Form for entering new tasks and projects
 */
public class NewTaskWindow extends JFrame implements ActionListener {

    private Tracker main;
    private JPanel mainPanel;

    public NewTaskWindow(Tracker tracker) {
        super("Tracker - Add new Task");

        this.setSize(550, 400);
        main = tracker;

        prepareUi();

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("Add new Task window closed!");
            }
        });
    }

    private void prepareUi() {
        mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JTextField fieldTitle = new JTextField(30);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.ipady = 8;
        constraints.ipadx = 5;
        constraints.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(fieldTitle, constraints);

        JLabel labelTitle = new JLabel("Title:");
        labelTitle.setLabelFor(fieldTitle);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        mainPanel.add(labelTitle, constraints);

        // @Todo: Actually fill in the current projects
        JComboBox<String> fieldProject = new JComboBox<>();
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(fieldProject, constraints);

        JLabel labelProject = new JLabel("Project:");
        labelProject.setLabelFor(fieldProject);
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(labelProject, constraints);

        JButton buttonProject = new JButton("Add new Project");
        buttonProject.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 1;
        mainPanel.add(buttonProject, constraints);

        JSpinner spinnerEffort = new JSpinner();
        constraints.gridx = 1;
        constraints.gridy = 2;
        mainPanel.add(spinnerEffort, constraints);

        JLabel labelEstEffort = new JLabel("Estimated Effort:");
        labelEstEffort.setLabelFor(spinnerEffort);
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(labelEstEffort, constraints);

        Vector<String> prioChoice = new Vector<>();
        prioChoice.add("High");
        prioChoice.add("Medium");
        prioChoice.add("Low");
        JComboBox<String> fieldPriority = new JComboBox<>(prioChoice);
        constraints.gridx = 1;
        constraints.gridy = 3;
        mainPanel.add(fieldPriority, constraints);

        JLabel labelPriority = new JLabel("Priority:");
        labelPriority.setLabelFor(fieldPriority);
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPanel.add(labelPriority, constraints);

        JTextArea textArea = new JTextArea(6, 30);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        mainPanel.add(textArea, constraints);

        JLabel labelDetailDescription = new JLabel("Detailed Description:");
        labelPriority.setLabelFor(textArea);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        mainPanel.add(labelDetailDescription, constraints);

        JButton buttonSave = new JButton("Save");
        constraints.gridx = 1;
        constraints.gridy = 5;
        mainPanel.add(buttonSave, constraints);

        JButton buttonSaveAndAdd = new JButton("Save and add another");
        constraints.gridx = 2;
        constraints.gridy = 5;
        mainPanel.add(buttonSaveAndAdd, constraints);

        this.add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getActionCommand().equals("Add new Project")){
            main.showNewProjectWindow();
        }
    }
}
