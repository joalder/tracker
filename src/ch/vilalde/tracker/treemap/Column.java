package ch.vilalde.tracker.treemap;

import ch.vilalde.tracker.domain.Project;
import ch.vilalde.tracker.domain.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by avi on 11.10.15.
 */
public class Column extends JPanel {

    private Project project;

    public Column(Project project) {
        super(new GridBagLayout());

        this.project = project;

        ArrayList<Task> tasks = project.getTasks();

        for (int tindex = 0; tindex < tasks.size(); tindex++) {
            Task task = tasks.get(tindex);

            GridBagConstraints constraints = new GridBagConstraints();

            if (tindex < 2) {
                constraints.gridx = (tindex % 2) * 4;
                constraints.gridy = 0;
                constraints.gridwidth = constraints.gridheight = 4;
            } else if (tindex < 10) {
                constraints.gridx = ((tindex - 2) % 4) * 2;
                constraints.gridy = ((tindex - 2) / 4) * 2 + 4;
                constraints.gridwidth = constraints.gridheight = 2;
            } else {
                constraints.gridx = (tindex - 10) % 8;
                constraints.gridy = (tindex - 10) / 10 + 8;
                constraints.gridwidth = constraints.gridheight = 1;
            }

            Tile tile = new Tile(task, project, constraints.gridwidth);

            add(tile, constraints);

            System.out.println(" - " + task.getTitle());
        }
    }

    @Override
    public Component.BaselineResizeBehavior getBaselineResizeBehavior() {
        return Component.BaselineResizeBehavior.CONSTANT_ASCENT;
    }

    @Override
    public int getBaseline(int width, int height) {
        return 0;
    }
}
