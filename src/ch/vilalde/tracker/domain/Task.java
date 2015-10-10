package ch.vilalde.tracker.domain;

import java.io.Serializable;

/**
 * Domain class for a task and its content
 */
public class Task implements Serializable{
    private String title;
    private int estimatedEffort;
    private String priority;
    private String detailedDescription;

    public Task(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (estimatedEffort != task.estimatedEffort) return false;
        if (!title.equals(task.title)) return false;
        if (priority != null ? !priority.equals(task.priority) : task.priority != null) return false;
        return !(detailedDescription != null ? !detailedDescription.equals(task.detailedDescription) : task.detailedDescription != null);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + estimatedEffort;
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (detailedDescription != null ? detailedDescription.hashCode() : 0);
        return result;
    }

    public Task(String title, int estimatedEffort, String priority, String detailedDescription){
        this.title = title;
        this.estimatedEffort = estimatedEffort;
        this.priority = priority;
        this.detailedDescription = detailedDescription;

    }

    public int getEstimatedEffort() {
        return estimatedEffort;
    }

    public void setEstimatedEffort(int estimatedEffort) {
        this.estimatedEffort = estimatedEffort;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
