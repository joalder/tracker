package ch.vilalde.tracker.treemap;

import ch.vilalde.tracker.domain.Project;
import ch.vilalde.tracker.domain.Task;

import javax.swing.*;
import java.awt.*;

/**
 * Created by avi on 11.10.15.
 */
public class Tile extends JPanel {

    final int PIXEL = 50;

    private Task task;
    private Project project;
    private int size;
    private Color color;

    public Tile(Task task, Project project, int size) {
        this.task = task;
        this.project = project;
        this.size = size;
        this.color = project.getColor();

        Font font = new Font("Helvetica-Neue", Font.PLAIN, 10 + size * 3);

        MultilineLabel label = new MultilineLabel(task.getTitle());
        label.setForeground(Color.WHITE);
        label.setSize(getPreferredSize());
        label.setMargin(new Insets(5, 5, 10, 10));
        label.setFont(font);

        add(label);
    }

    public Dimension getPreferredSize() {
        int pixelsize = size * PIXEL;
        return new Dimension(pixelsize, pixelsize);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int pixelsize = size * PIXEL;
        Color c = new Color(color.getRed(), color.getGreen(), color.getBlue(), 55 + size * 50);

        // draw background
        g.setColor(c);
        g.fillRect(0, 0, pixelsize - 5, pixelsize - 5);
    }
}
