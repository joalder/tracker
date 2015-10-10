package ch.vilalde.tracker.domain;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Domain class for storing a project and its data
 */
public class Project implements Serializable {
    private String name;
    private Color color;
    private ArrayList<Task> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }
}
