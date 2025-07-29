package Views;      // We use this package to organize all GUI-related classes

import javax.swing.JFrame;

/**
 * This class provides a standard format for all pop-up windows in our application.
 * 
 * It sets the default size, layout, and close operation for pop-ups.
 * 
 * Other pop-up windows can extend this class to keep a consistent style.
 */
public class PopUpFormat extends JFrame {

    /**
     * This constructor sets the default format of the pop-up window.
     * 
     * It sets the size to 400x400, disables resizing, uses no layout manager,
     * and disposes the window when closed.
     */
    public PopUpFormat() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 500);
        this.setResizable(false);
        this.setLayout(null);
    }

    // Additional methods for setting up the pop-up format can be added here
    
}