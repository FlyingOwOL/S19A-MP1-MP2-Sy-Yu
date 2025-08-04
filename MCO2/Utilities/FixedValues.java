package Utilities;

import java.awt.Font;

/**
 * FixedValues class contains constants used throughout the application.
 * These include font styles, time slots, and month names.
 */
public class FixedValues {
    public static final Font TITLE_FONT = new Font("Comic Sans MS", Font.PLAIN, 24);
    public static final Font LABEL_FONT = new Font("Comic Sans MS", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("Comic Sans MS", Font.PLAIN, 12);
    public static final Font JOURNAL_FONT = new Font("Comic Sans MS", Font.PLAIN, 10);
    public static String[] timeSlots = {
        "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00",
        "06:00:00", "07:00:00", "08:00:00", "09:00:00", "10:00:00",
        "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00",
        "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00",
        "21:00:00", "22:00:00", "23:00:00", "00:00:00"
    };
    public static String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
}
