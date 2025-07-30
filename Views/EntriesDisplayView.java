package Views;

import Models.Calendar.CalendarParentModel;
import Models.Entry.*;
import Utilities.FixedValues;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Displays a view for calendar entries, allowing users to view and edit entries.
 * This class extends JFrame and provides a table to display various types of calendar entries.
 */
public class EntriesDisplayView extends JFrame {
    private JPanel headerPanel;             
    private JPanel contentPanel;
    private JTable entriesTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JLabel titleLabel;
    private JButton closeButton;
    private JComboBox<EntryModel> entriesBox;
    private JButton editButton;
    
    private String[] columnNames = {"Type", "Title", "Date", "Details", "Status/Priority", "Organizer/Creator"};

    public EntriesDisplayView(CalendarParentModel calendar) {
        initializeComponents(calendar);
        loadEntries(calendar);
        setupLayout();
    }

    private void initializeComponents(CalendarParentModel calendar) {
        this.setTitle("Calendar Entries");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(true);
        this.setLayout(new BorderLayout());

        // Header panel
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setPreferredSize(new Dimension(800, 60));
        
        titleLabel = new JLabel("Calendar Entries");
        titleLabel.setFont(FixedValues.TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Initialize entriesBox
        entriesBox = new JComboBox<>(calendar.getEntries().toArray(new EntryModel[0]));
        entriesBox.setPreferredSize(new Dimension(200, 25)); // Set preferred size for the combo box

        // Initialize Edit button
        editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(200, 25));

        // Content panel
        contentPanel = new JPanel(new BorderLayout());
        
        // Table setup
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        entriesTable = new JTable(tableModel);
        entriesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        entriesTable.getTableHeader().setFont(FixedValues.LABEL_FONT);
        entriesTable.setFont(FixedValues.BUTTON_FONT);
        entriesTable.setRowHeight(25);
        
        scrollPane = new JScrollPane(entriesTable);
        
        // Close button
        closeButton = new JButton("Close");
        closeButton.setFont(FixedValues.BUTTON_FONT);
        closeButton.setFocusable(false);
        closeButton.addActionListener(e -> dispose());
    }

    private void setupLayout() {
        // Header layout
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(titleLabel);
        headerPanel.add(entriesBox); // Add the JComboBox to the headerPanel
        headerPanel.add(editButton);  // Add the JButton to the headerPanel
        
        // Content layout
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(closeButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add to frame
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Loads entries from the calendar model into the table.
     * 
     * @param calendar
     */
    private void loadEntries(CalendarParentModel calendar) {
        ArrayList<EntryModel> entries = calendar.getEntries();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        // Clear existing data
        tableModel.setRowCount(0);
        
        // Sort entries with a single return comparator
        entries.sort((e1, e2) -> {
            int result = 0;

            // 1. Separate Tasks from other types (Tasks come first)
            boolean isTask1 = e1 instanceof Task;
            boolean isTask2 = e2 instanceof Task;

            if (isTask1 && !isTask2) {
                result = -1;  // e1 comes first
            } else if (!isTask1 && isTask2) {
                result = 1;   // e2 comes first
            } else if (isTask1) { // Both are Tasks
                Task task1 = (Task) e1;
                Task task2 = (Task) e2;

                // 2. "Done" tasks go to bottom
                boolean isDone1 = "Done".equals(task1.getStatus());
                boolean isDone2 = "Done".equals(task2.getStatus());

                if (isDone1 && !isDone2) {
                    result = 1;  // Done comes after Pending
                } else if (!isDone1 && isDone2) {
                    result = -1; // Pending comes before Done
                } else { // Same status - compare priority
                    // Using enum-like ordering for priorities
                    Map<String, Integer> priorityOrder = Map.of(
                        "High", 0,
                        "Medium", 1,
                        "Low", 2
                    );
                    int priority1 = priorityOrder.getOrDefault(task1.getPriority(), 3);
                    int priority2 = priorityOrder.getOrDefault(task2.getPriority(), 3);
                    result = Integer.compare(priority1, priority2);
                }
            }
            // The result variable will be returned at the end of the lambda
            return result;
        });

        // Populate the table with sorted entries
        for (EntryModel entry : entries) {
            String[] rowData = new String[6];
            
            // Determine entry type and populate data accordingly
            if (entry instanceof Task) {
                Task task = (Task) entry;
                rowData[0] = "Task";
                rowData[1] = task.getTitle();
                rowData[2] = task.getDate().format(dateFormatter);
                rowData[3] = task.getDetails();
                rowData[4] = task.getPriority() + " (" + task.getStatus() + ")";
                rowData[5] = task.getCreatedBy();
                
            } else if (entry instanceof EventEntry) {
                EventEntry event = (EventEntry) entry;
                rowData[0] = "Event";
                rowData[1] = event.getTitle();
                rowData[2] = event.getDate().format(dateFormatter);
                rowData[3] = event.getDetails();
                rowData[4] = "Venue: " + event.getVenue();
                rowData[5] = event.getOrganizer();
                
            } else if (entry instanceof Meeting) {
                Meeting meeting = (Meeting) entry;
                rowData[0] = "Meeting";
                rowData[1] = meeting.getTitle();
                rowData[2] = meeting.getDate().format(dateFormatter);
                rowData[3] = meeting.getDetails();
                rowData[4] = "Modality: " + meeting.getModality();
                rowData[5] = "N/A";
                
            } else if (entry instanceof Journal) {
                Journal journal = (Journal) entry;
                rowData[0] = "Journal";
                rowData[1] = journal.getTitle();
                rowData[2] = journal.getDate().format(dateFormatter);
                rowData[3] = journal.getDetails().length() > 50 ? 
                           journal.getDetails().substring(0, 50) + "..." : 
                           journal.getDetails();
                rowData[4] = "Personal";
                rowData[5] = "Self";
            }
            
            tableModel.addRow(rowData);
        }
    }

    /**
     * Refreshes the entries displayed in this view.
     * 
     * @param calendar
     */
    public void refreshEntries(CalendarParentModel calendar) {
        loadEntries(calendar);
    }

    /**
     * Gets the edit button for this view.
     * 
     * @return the edit button
     */
    public JButton getEditButton(){
        return this.editButton;
    }

    public JComboBox<EntryModel> getEntriesBox() {
        return this.entriesBox;
    }

    /**
     * Sets the action listener for the edit button.
     * 
     * @param actionListener
     */
    public void setButtonActionListener (ActionListener actionListener){
        this.editButton.addActionListener(actionListener);
    }

    /**
     * 
     * 
     */
    public void updateGUI(){
        this.revalidate();
        this.repaint();
    }
}
