package Models.Entry;

/**
 * Represents a task entry in the calendar system.
 * 
 * Inherits from EntryModel and adds task-specific fields such as priority, 
 * status, creator, and the user who finished the task.
 */
public class Task extends EntryModel {

    private String priority;    // Task priority (e.g., high, medium, low)
    private String status;      // Task status (e.g., pending, done, etc.)
    private String createdBy;   // User who created the task
    private String finishedBy;  // User who marked the task as finished

    /**
     * Constructs a Task entry with a title, priority, status, and creator.
     * The finishedBy field is initialized to an empty string.
     *
     * @param title      the task title
     * @param priority   the priority level of the task
     * @param status     the current status of the task
     * @param createdBy  the user who created the task
     */
    public Task(String title, String priority, String status, String createdBy) {
        super(title);
        this.priority = priority;
        this.status = status;
        this.createdBy = createdBy;
        this.finishedBy = "";
    }

    /**
     * Returns the priority of the task.
     *
     * @return the priority
     */
    public String getPriority() {
        return this.priority;
    }

    /**
     * Returns the status of the task.
     *
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Returns the name of the user who finished the task.
     *
     * @return the user who finished the task
     */
    public String getFinishedBy() {
        return this.finishedBy;
    }

    /**
     * Returns the name of the user who created the task.
     *
     * @return the creator of the task
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority the new priority level
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Sets the status of the task.
     *
     * @param status the new task status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Marks the task as finished by setting its status to "done"
     * and recording the name of the user who completed it.
     *
     * @param finishedBy the user who finished the task
     */
    public void finishTask(String finishedBy) {
        this.status = "done";
        this.finishedBy = finishedBy;
    }

    /**
     * Validates the given status string as a priority level.
     *
     * @param status the status/priority string to validate
     * @return true if the status is valid; false otherwise
     */
    public boolean isValidStatus(String status) {
        boolean isValid = false;
        switch (status) {
            case "high":
            case "medium":
            case "low":
                isValid = true;
                break;
        }
        return isValid;
    }
}