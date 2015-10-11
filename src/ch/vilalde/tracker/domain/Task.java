package ch.vilalde.tracker.domain;

import java.io.Serializable;

/**
 * Domain class for a task and its content
 */
public class Task implements Serializable{
    /**
     * Title of the task, main identification for display
     */
    private String title;
    /**
     * Estimated effort of the task in hours
     */
    private int estimatedEffort;
    /**
     * Total spent effort in hours for this task
     */
    private int spentEffort;
    /**
     * Priority of the task. Either "High", "Medium" or "Low"
     * @ Todo: Convert to/use enum!?
     */
    private String priority;
    /**
     * (Non formatted) Detailed description of the task
     */
    private String detailedDescription;

    public Task(){}

    public Task(String title, int estimatedEffort, String priority, String detailedDescription){
        this.title = title;
        this.estimatedEffort = estimatedEffort;
        this.priority = priority;
        this.detailedDescription = detailedDescription;

    }

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
