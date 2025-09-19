package Models.Entry;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Meeting class represents a meeting-type calendar entry.
 * It extends EntryModel and includes additional fields such as modality, venue, link,
 * start/end date, and start/end time.
 */
public class Meeting extends EntryModel {
    private String modality;        // The modality of the meeting (e.g., Online, Face-to-face)
    private String venue;           // The venue where the meeting will take place
    private String link;            // The link for online meetings
    private LocalDate startDate;    // The start date of the meeting
    private LocalDate endDate;      // The end date of the meeting
    private LocalTime startTime;    // The start time of the meeting    
    private LocalTime endTime;      // The end time of the meeting

    /**
     * Constructs a Meeting with the specified title and modality.
     * Initializes start and end dates to the current date.
     *
     * @param title the title of the meeting
     * @param modality the meeting modality (e.g., Online, Face-to-face)
     */
    public Meeting(String title, String modality) {
        super(title);
        this.modality = modality;
        this.venue = "";
        this.link = "";
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
    }

    /**
     * Gets the modality of the meeting.
     *
     * @return the modality
     */
    public String getModality() {
        return this.modality;
    }

    /**
     * Gets the venue of the meeting.
     *
     * @return the venue
     */
    public String getVenue() {
        return this.venue;
    }

    /**
     * Gets the meeting link (if applicable).
     *
     * @return the link
     */
    public String getLink() {
        return this.link;
    }

    /**
     * Gets the start date of the meeting.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the end date of the meeting.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Gets the start time of the meeting.
     *
     * @return the start time
     */
    public LocalTime getStartDateTime() {
        return this.startTime;
    }

    /**
     * Gets the end time of the meeting.
     *
     * @return the end time
     */
    public LocalTime getEndDateTime() {
        return this.endTime;
    }

    /**
     * Sets the modality of the meeting.
     *
     * @param modality the modality to set
     */
    public void setModality(String modality) {
        this.modality = modality;
    }

    /**
     * Sets the venue of the meeting.
     *
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * Sets the link of the meeting.
     *
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Sets the start date of the meeting by parsing a String.
     *
     * @param startDate the start date as a String in ISO format (yyyy-MM-dd)
     */
    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
        System.out.println("parsed Start:" + this.startDate);
    }

    /**
     * Sets the end date of the meeting by parsing a String.
     *
     * @param endDate the end date as a String in ISO format (yyyy-MM-dd)
     */
    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
        System.out.println("parsed Start:" + this.endDate);
    }

    /**
     * Sets the start time of the meeting by parsing a String.
     *
     * @param startTime the start time as a String in ISO format (HH:mm)
     */
    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime);
    }

    /**
     * Sets the end time of the meeting by parsing a String.
     *
     * @param endTime the end time as a String in ISO format (HH:mm)
     */
    public void setEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime);
    }
}
