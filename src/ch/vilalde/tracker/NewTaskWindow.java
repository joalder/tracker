package ch.vilalde.tracker;

import ch.vilalde.tracker.domain.Project;
import ch.vilalde.tracker.domain.Task;

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

    public static final String SAVE_AND_ADD_ANOTHER = "Save and add another";
    public static final String SAVE = "Save";
    public static final String ADD_NEW_PROJECT = "Add new Project";

    private Tracker main;
    private JPanel mainPanel;
    private JComboBox<Project> fieldProject;
    private JTextField titleField;
    private JSpinner effortSpinner;
    private JComboBox<String> priorityField;
    private JTextArea descriptionArea;

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

        titleField = new JTextField(30);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.ipady = 8;
        constraints.ipadx = 5;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(titleField, constraints);

        JLabel labelTitle = new JLabel("Title:");
        labelTitle.setLabelFor(titleField);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        mainPanel.add(labelTitle, constraints);

        fieldProject = new JComboBox<>();
        updateProjects();
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(fieldProject, constraints);

        JLabel labelProject = new JLabel("Project:");
        labelProject.setLabelFor(fieldProject);
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(labelProject, constraints);

        JButton buttonProject = new JButton(ADD_NEW_PROJECT, main.addIcon);
        buttonProject.addActionListener(this);
        constraints.gridx = 2;
        constraints.gridy = 1;
        mainPanel.add(buttonProject, constraints);

        effortSpinner = new JSpinner();
        constraints.gridx = 1;
        constraints.gridy = 2;
        mainPanel.add(effortSpinner, constraints);

        JLabel labelEstEffort = new JLabel("Estimated Effort:");
        labelEstEffort.setLabelFor(effortSpinner);
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(labelEstEffort, constraints);

        Vector<String> prioChoice = new Vector<>();
        prioChoice.add("High");
        prioChoice.add("Medium");
        prioChoice.add("Low");
        priorityField = new JComboBox<>(prioChoice);
        constraints.gridx = 1;
        constraints.gridy = 3;
        mainPanel.add(priorityField, constraints);

        JLabel labelPriority = new JLabel("Priority:");
        labelPriority.setLabelFor(priorityField);
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPanel.add(labelPriority, constraints);

        descriptionArea = new JTextArea(6, 30);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        mainPanel.add(descriptionArea, constraints);

        JLabel labelDetailDescription = new JLabel("Detailed Description:");
        labelPriority.setLabelFor(descriptionArea);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        mainPanel.add(labelDetailDescription, constraints);

        JButton buttonSave = new JButton(SAVE);
        constraints.gridx = 1;
        constraints.gridy = 5;
        buttonSave.addActionListener(this);
        mainPanel.add(buttonSave, constraints);

        JButton buttonSaveAndAdd = new JButton(SAVE_AND_ADD_ANOTHER);
        constraints.gridx = 2;
        constraints.gridy = 5;
        buttonSaveAndAdd.addActionListener(this);
        mainPanel.add(buttonSaveAndAdd, constraints);

        this.add(mainPanel);
        this.pack();
    }

    public void updateProjects(){
        fieldProject.removeAllItems();
        for (Project project : main.getProjects()){
            fieldProject.addItem(project);
        }
    }

    private Boolean isFilled(){
        return !(titleField.getText().equals("") || descriptionArea.getText().equals(""));
    }

    private void saveTask(){
        Task task = new Task(titleField.getText(), (int)effortSpinner.getValue(), (String)priorityField.getSelectedItem(), descriptionArea.getText());
        main.addTask((Project)fieldProject.getSelectedItem(), task);
    }

    private void clean(){
        titleField.setText("");
        descriptionArea.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getActionCommand().equals(ADD_NEW_PROJECT)){
            main.showNewProjectWindow();
        } else if (e.getActionCommand().equals(SAVE)){
            if (isFilled()){
                saveTask();
                clean();
                this.setVisible(false);
            }
        } else if (e.getActionCommand().equals(SAVE_AND_ADD_ANOTHER)){
            if (isFilled()){
                saveTask();
                clean();
            }
        }
    }
}
