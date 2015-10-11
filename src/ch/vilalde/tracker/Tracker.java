package ch.vilalde.tracker;

import ch.vilalde.tracker.domain.Project;
import ch.vilalde.tracker.domain.Task;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Tracker implements ActionListener {

    public static final String DATA_FILE = "tracker_data.xml";
    private JFrame mainFrame;
    private ArrayList<Project> projects;
    private NewTaskWindow newTaskWindow;
    private NewProjectWindow newProjectWindow;
    ImageIcon addIcon;

    public Tracker() {
        loadData();
        loadIcons();
        prepareUi();
    }

    private void loadData() {
        try {
            projects = Util.loadData(DATA_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find Tracker data in " + DATA_FILE);
            System.out.println(e.getStackTrace());
        }
        if (projects == null) {
            projects = new ArrayList<>();
        }
    }

    private void prepareUi() {
        mainFrame = new JFrame("Tracker - Your lightweight task tracker");
        mainFrame.setSize(1000, 700);
        mainFrame.setLayout(new BorderLayout());

        JMenuBar mainMenu = new TrackerMenu(mainFrame);
        mainFrame.setJMenuBar(mainMenu);

        newTaskWindow = new NewTaskWindow(this);
        newProjectWindow = new NewProjectWindow(this);

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

        JButton addTaskButton = new JButton("Add new Task");
        addTaskButton.setIcon(addIcon);
        addTaskButton.addActionListener(this);
        buttonBar.add(addTaskButton);

        mainFrame.add(buttonBar, BorderLayout.NORTH);

        JLabel message = new JLabel("There will a nice TreeMap here...");
        mainFrame.add(message, BorderLayout.CENTER);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                Util.saveData(projects, DATA_FILE);
                System.exit(0);
            }
        });
    }


    public void showUi() {
        mainFrame.setVisible(true);
    }

    public void addTask(Project refProject, Task task) {
        for (Project project : projects) {
            if (project.equals(refProject)) {
                project.addTask(task);
                return;
            }
        }
        System.out.println("Could not find project with name: " + refProject.getName());
    }

    public void addProject(Project project) {
        projects.add(project);
        newTaskWindow.updateProjects();
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public static void main(String[] args) {
        Tracker ui = new Tracker();
        ui.showUi();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add new Task")) {

            showNewTaskWindow();
        } else {
            System.out.println("This button is useless at the moment...");
        }
    }

    private void loadIcons() {
        try {
            URL f = new File("src/resources/add.png").toURI().toURL();
            addIcon = new ImageIcon(f);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void showNewTaskWindow() {
        newTaskWindow.setVisible(true);
    }

    public void showNewProjectWindow() {
        newProjectWindow.setVisible(true);
    }
}
