package ch.vilalde.tracker.test;

import ch.vilalde.tracker.Util;
import ch.vilalde.tracker.domain.Project;
import ch.vilalde.tracker.domain.Task;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Jonas on 10.10.2015.
 */
public class TestDataPersistence {

    private ArrayList<Project> projectList = new ArrayList<>();
    public static final String FILE_NAME = "test.xml";

    @Before
    public void setUp() {
        Task task = new Task("Test Task", 10, "High", "Moar Text");
        Task task2 = new Task("Test Task 2", 7, "Medium", "Moar Text");
        Project project = new Project();

        project.setName("Project 1");
        project.setColor(new Color(220, 0, 0));
        project.addTask(task);
        project.addTask(task2);
        projectList.add(project);
    }

    @Test
    public void saveData() {
        try {
            Util.saveData(projectList, FILE_NAME);
        } catch (FileNotFoundException e) {
            assertTrue("Exception occured!" + e.getMessage(), false);
        }

        File f = new File(FILE_NAME);
        assertTrue("Checking file existence", f.exists() && !f.isDirectory());
    }

    @Test
    public void readData() {
        ArrayList<Project> projects = null;
        try {
            projects = Util.loadData(FILE_NAME);
        } catch (FileNotFoundException e) {
            assertTrue("Exception occured!" + e.getMessage(), false);
        } catch (ClassCastException e){
            assertTrue("Invalid format! " + e.getMessage(), false);
        }

        assertArrayEquals("Comparing project lists", projectList.toArray(), projects.toArray());
    }

}
