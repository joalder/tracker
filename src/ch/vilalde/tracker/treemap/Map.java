package ch.vilalde.tracker.treemap;

import ch.vilalde.tracker.domain.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by avi on 11.10.15.
 */
public class Map extends JPanel {

    private ArrayList<Project> projects;

    public Map(ArrayList<Project> projects) {
        super(new FlowLayout());

        FlowLayout layout = (FlowLayout) getLayout();
        layout.setAlignOnBaseline(true);

        this.projects = projects;

        for (Project project : projects) {
            add(new Column(project));

            System.out.println("Project: " + project.getName());
        }
    }
}
