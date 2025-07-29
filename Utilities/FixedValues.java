package Utilities; 

import java.awt.Font; 

/**
 * Contains fixed values used for the font sizes throughout the application.
 * 
 * All fonts use the "Comic Sans MS" typeface with varying sizes and weights.
 * The time slots are predefined for use in calendar entries.
 */
public class FixedValues {
    public static final Font TITLE_FONT = new Font("Comic Sans MS", Font.PLAIN, 24);
    public static final Font LABEL_FONT = new Font("Comic Sans MS", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Comic Sans MS", Font.PLAIN, 12);
    public static final Font JOURNAL_FONT = new Font("Comic Sans MS", Font.PLAIN, 10);
    public static String[] timeSlots = {"1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM",
                                        "6:00 AM", "7:00 AM", "8:00 AM", "9:00 AM", "10:00 AM",
                                        "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM",
                                        "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM",
                                        "9:00 PM", "10:00 PM", "11:00 PM", "12:00 AM"};
}