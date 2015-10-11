package ch.vilalde.tracker;

import ch.vilalde.tracker.domain.Project;
import ch.vilalde.tracker.domain.Task;
import ch.vilalde.tracker.treemap.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Tracker main class. Builds the UI for the main window.
 * Currently also responsible for the data storage (during runtime), and central window control
 */
public class Tracker implements ActionListener {

    final String CAPTION_ADD_NEW_TASK = "Add new task";
    final String CAPTION_PRIORITY = "Priority";
    final String CAPTION_ESTIMATED_EFFORT = "Estimated Effort";
    final String CAPTION_SPENT_EFFORT = "Spent Effort";

    public static final String DATA_FILE = "tracker_data.xml";
    private JFrame mainFrame;
    private ArrayList<Project> projects;
    private Map treeMap;
    private NewTaskWindow newTaskWindow;
    private NewProjectWindow newProjectWindow;
    ImageIcon addIcon;

    public class PriorityComparator implements Comparator<Task> {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.getPriorityAsInt().compareTo(t2.getPriorityAsInt()) * -1;
        }
    }

    public class EstimatedEffortComparator implements Comparator<Task> {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.getEstimatedEffort().compareTo(t2.getEstimatedEffort()) * -1;
        }
    }

    public class SpentEffortComparator implements Comparator<Task> {
        @Override
        public int compare(Task t1, Task t2) {
            return t1.getSpentEffort().compareTo(t2.getSpentEffort()) * -1;
        }
    }

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

        JMenuBar mainMenu = new TrackerMenu();
        mainFrame.setJMenuBar(mainMenu);

        newTaskWindow = new NewTaskWindow(this);
        newProjectWindow = new NewProjectWindow(this);

        JPanel leftButtonBar = new JPanel(new FlowLayout());

        JLabel sortingLabel = new JLabel("Sort by:");
        leftButtonBar.add(sortingLabel);
        JButton priorityButton = new JButton(CAPTION_PRIORITY);
        priorityButton.addActionListener(this);
        leftButtonBar.add(priorityButton, BorderLayout.EAST);
        JButton estEffortButton = new JButton(CAPTION_ESTIMATED_EFFORT);
        estEffortButton.addActionListener(this);
        leftButtonBar.add(estEffortButton, BorderLayout.EAST);
        JButton spentEffortButton = new JButton(CAPTION_SPENT_EFFORT);
        spentEffortButton.addActionListener(this);
        leftButtonBar.add(spentEffortButton, BorderLayout.EAST);

        JPanel rightButtonBar = new JPanel(new FlowLayout());
        JButton addTaskButton = new JButton(CAPTION_ADD_NEW_TASK);
        addTaskButton.setIcon(addIcon);
        addTaskButton.addActionListener(this);
        rightButtonBar.add(addTaskButton);

        JPanel buttonBar = new JPanel(new BorderLayout());
        buttonBar.add(leftButtonBar, BorderLayout.WEST);
        buttonBar.add(rightButtonBar, BorderLayout.EAST);
        mainFrame.add(buttonBar, BorderLayout.NORTH);

        buildTreeMap();

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                Util.saveData(projects, DATA_FILE);
                System.exit(0);
            }
        });
    }

    private void buildTreeMap() {
        if (treeMap != null) {
            mainFrame.remove(treeMap);
        }

        treeMap = new Map(projects);

        mainFrame.add(treeMap, BorderLayout.WEST);
    }

    /**
     * Enable the display of the main window
     */
    public void showUi() {
        mainFrame.setVisible(true);
    }

    /**
     * Add a task to a specific project
     *
     * @param refProject Project the task belongs to
     * @param task Task to add
     */
    public void addTask(Project refProject, Task task) {
        for (Project project : projects) {
            if (project.equals(refProject)) {
                project.addTask(task);
                buildTreeMap();
                return;
            }
        }
        System.out.println("Could not find project with name: " + refProject.getName());
    }

    /**
     * Adds a project to the global list of projects
     * @param project Project to add
     */
    public void addProject(Project project) {
        projects.add(project);
        newTaskWindow.updateProjects();
        buildTreeMap();
    }

    /**
     * @return currently (runtime) available projects
     */
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(CAPTION_ADD_NEW_TASK)) {
            showNewTaskWindow();
        } else if (e.getActionCommand().equals(CAPTION_PRIORITY)) {
            for (Project project : projects) {
                project.getTasks().sort(new PriorityComparator());
            }
            buildTreeMap();
        } else if (e.getActionCommand().equals(CAPTION_ESTIMATED_EFFORT)) {
            for (Project project : projects) {
                project.getTasks().sort(new EstimatedEffortComparator());
            }
            buildTreeMap();
        } else if (e.getActionCommand().equals(CAPTION_SPENT_EFFORT)) {
            for (Project project : projects) {
                project.getTasks().sort(new SpentEffortComparator());
            }
            buildTreeMap();
        } else {
            System.out.println("This button is useless at the moment...");
        }
    }

    /**
     * Preloads the used icons for further use in the app. If loading fails, icons will remain null.
     */
    private void loadIcons() {
        URL f = getClass().getClassLoader().getResource("resources/add.png");
        addIcon = new ImageIcon(f);
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
