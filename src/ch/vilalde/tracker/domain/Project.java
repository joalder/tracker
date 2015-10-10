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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (!name.equals(project.name)) return false;
        if (color != null ? !color.equals(project.color) : project.color != null) return false;
        return !(tasks != null ? !tasks.equals(project.tasks) : project.tasks != null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return name;
    }

    private ArrayList<Task> tasks = new ArrayList<>();

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
