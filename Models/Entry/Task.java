package Models.Entry;

public class Task extends EntryModel {
    private String priority;        // The priority of the task (e.g., High, Medium, Low)
    private String status;          // The status of the task (e.g., Pending, Completed)
    private String createdBy;       // The name of the person who created the task
    private String finishedBy;      // The name of the person who finished the task

    /**
     * Constructs a new Task with the given title, priority, status, and creator.
     *
     * @param title the title of the task
     * @param priority the priority level of the task
     * @param status the current status of the task
     * @param createdBy the name of the task creator
     */
    public Task(String title, String priority, String status, String createdBy) {
        super(title);
        this.priority = priority;
        this.status = status;
        this.createdBy = createdBy;
        this.finishedBy = "";
    }

    /**
     * Gets the priority level of the task.
     *
     * @return the task's priority
     */
    public String getPriority() {
        return this.priority;
    }

    /**
     * Gets the current status of the task.
     *
     * @return the task's status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Gets the name of the person who finished the task.
     *
     * @return the name of the finisher
     */
    public String getFinishedBy() {
        return this.finishedBy;
    }

    /**
     * Gets the name of the person who created the task.
     *
     * @return the name of the creator
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority the new priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Sets the status of the task.
     *
     * @param status the new status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets the name of the person who finished the task.
     *
     * @param finishedBy the name of the finisher
     */
    public void setFinishedBy(String finishedBy) {
        this.finishedBy = finishedBy;
    }

    /**
     * Sets the name of the person who created the task.
     *
     * @param createdBy the name of the creator
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
