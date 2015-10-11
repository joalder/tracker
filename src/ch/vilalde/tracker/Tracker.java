package ch.vilalde.tracker;

import ch.vilalde.tracker.domain.Project;
import ch.vilalde.tracker.domain.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Tracker main class. Builds the UI for the main window.
 * Currently also responsible for the data storage (during runtime), and central window control
 */
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

    /**
     * Loads data (Projects, Tasks) from the XML file defied in DATA_FILE and stores it in a field.
     * If loading fails, an empty project list is set
     */
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

    /**
     * Prepares the UI for the main window, also initializes the other windows.
     * Windows are not visible by default (use showUi())
     */
    private void prepareUi() {
        mainFrame = new JFrame("Tracker - Your lightweight task tracker");
        mainFrame.setSize(1000, 700);
        mainFrame.setLayout(new BorderLayout());

        JMenuBar mainMenu = new TrackerMenu(mainFrame);
        mainFrame.setJMenuBar(mainMenu);

        newTaskWindow = new NewTaskWindow(this);
        newProjectWindow = new NewProjectWindow(this);

        JPanel leftButtonBar = new JPanel(new FlowLayout());

        JLabel sortingLabel = new JLabel("Sort by:");
        leftButtonBar.add(sortingLabel);
        JButton priorityButton = new JButton("Priority");
        priorityButton.addActionListener(this);
        leftButtonBar.add(priorityButton, BorderLayout.EAST);
        JButton estEffortButton = new JButton("Estimated Effort");
        estEffortButton.addActionListener(this);
        leftButtonBar.add(estEffortButton, BorderLayout.EAST);
        JButton spentEffortButton = new JButton("Spent Effort");
        spentEffortButton.addActionListener(this);
        leftButtonBar.add(spentEffortButton, BorderLayout.EAST);

        JPanel rightButtonBar = new JPanel(new FlowLayout());
        JButton addTaskButton = new JButton("Add new Task");
        addTaskButton.setIcon(addIcon);
        addTaskButton.addActionListener(this);
        rightButtonBar.add(addTaskButton);

        JPanel buttonBar = new JPanel(new BorderLayout());
        buttonBar.add(leftButtonBar, BorderLayout.WEST);
        buttonBar.add(rightButtonBar, BorderLayout.EAST);
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

    /**
     * Enable the display of the main window
     */
    public void showUi() {
        mainFrame.setVisible(true);
    }

    /**
     * Add a task to a specific project
     */
    public void addTask(Project refProject, Task task) {
        for (Project project : projects) {
            if (project.equals(refProject)) {
                project.addTask(task);
                return;
            }
        }
        System.out.println("Could not find project with name: " + refProject.getName());
    }

    /**
     * Adds a project to the global list of projects
     */
    public void addProject(Project project) {
        projects.add(project);
        newTaskWindow.updateProjects();
    }

    /**
     * Returns currently available projects
     */
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add new Task")) {

            showNewTaskWindow();
        } else {
            System.out.println("This button is useless at the moment...");
        }
    }

    /**
     * Preloads the used icons for further use in the app. If loading fails, icons will remain null.
     */
    private void loadIcons() {
        try {
            URL f = new File("src/resources/add.png").toURI().toURL();
            addIcon = new ImageIcon(f);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the {@link NewTaskWindow}. Has to be created beforehand.
     */
    public void showNewTaskWindow() {
        newTaskWindow.setVisible(true);
    }

    /**
     * Displays the {@link NewProjectWindow}. Has to be created beforehand.
     */
    public void showNewProjectWindow() {
        newProjectWindow.setVisible(true);
    }

    public static void main(String[] args) {
        Tracker ui = new Tracker();
        ui.showUi();
    }
}
