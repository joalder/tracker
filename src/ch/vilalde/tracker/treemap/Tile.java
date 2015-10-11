package ch.vilalde.tracker.treemap;

import ch.vilalde.tracker.domain.Project;
import ch.vilalde.tracker.domain.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by avi on 11.10.15.
 */
public class Tile extends JPanel implements ActionListener {

    final int PIXEL = 50;

    private Task task;
    private Project project;
    private int size;
    private Color color;

    private JPanel controlPanel;

    final String CAPTION_ADD_HOUR = "+1h";

    public Tile(Task task, Project project, int size) {
        super(new BorderLayout());

        this.task = task;
        this.project = project;
        this.size = size;
        this.color = project.getColor();

        Font font = new Font("Helvetica-Neue", Font.PLAIN, 6 + size * 3);

        MultilineLabel titleLabel = new MultilineLabel(task.getTitle());
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setSize(getPreferredSize());
        titleLabel.setMargin(new Insets(5, 5, 5, 5));
        titleLabel.setFont(font);
        add(titleLabel, BorderLayout.NORTH);

        controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setOpaque(false);

        GridBagConstraints constraints = new GridBagConstraints();

        JButton addButton = new JButton(CAPTION_ADD_HOUR);
        addButton.addActionListener(this);
        constraints.gridx = 0;
        controlPanel.add(addButton, constraints);

        JLabel effortLabel = new JLabel(task.getSpentEffort() + "/" + task.getEstimatedEffort());
        effortLabel.setForeground(Color.WHITE);
        constraints.gridx = 1;
        controlPanel.add(effortLabel, constraints);

        add(controlPanel, BorderLayout.SOUTH);
    }

    public Dimension getPreferredSize() {
        int pixelsize = size * PIXEL;
        return new Dimension(pixelsize, pixelsize);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int pixelsize = size * PIXEL;
        Color c = getColor();

        // draw background
        g.setColor(c);
        g.fillRect(1, 1, pixelsize - 2, pixelsize - 2);
    }

    private Color getColor() {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), 55 + size * 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(CAPTION_ADD_HOUR)) {
            task.setSpentEffort(task.getSpentEffort() + 1);
        }
    }
}
