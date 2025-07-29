package Views;

import Models.Calendar.CalendarParentModel;
import Models.Entry.*;
import Utilities.FixedValues;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * The EntriesDisplayView class provides a graphical interface to display calendar entries.
 * It shows a table of entries including tasks, meetings, events, and journals with relevant details.
 */
public class EntriesDisplayView extends JFrame {
    private JPanel headerPanel;           // Panel for header    
    private JPanel contentPanel;          // Panel for content
    private JTable entriesTable;          // Table to display entries
    private DefaultTableModel tableModel; // Model for the table data  
    private JScrollPane scrollPane;       // Scroll panel for the table
    private JLabel titleLabel;            // Label for the title of the view
    private JButton closeButton;          // Button to close the view
    
    private String[] columnNames = {"Type", "Title", "Date", "Details", "Status/Priority", "Organizer/Creator"};

    //TODO entries should be sorted from Task >> Meeting >> Event. Task is High to Low the others is any. 

    //TODO Since the entries should be editable they should be changeable here in the entries display view

    /**
     * Constructor for the EntriesDisplayView class.
     * Initializes the components and sets up the layout for displaying calendar entries.
     *
     * @param calendar The CalendarParentModel containing the entries to be displayed.
     */
    public EntriesDisplayView(CalendarParentModel calendar) {
        initializeComponents();
        loadEntries(calendar);
        setupLayout();
    }

    /**
     * Initializes the components of the EntriesDisplayView.
     * Sets up the header, content panel, table model, and scroll pane.
     */
    private void initializeComponents() {
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

    /**
     * Sets up the layout of the EntriesDisplayView.
     * Adds components to the header and content panels, and configures the frame.
     */
    private void setupLayout() {
        // Header layout
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(titleLabel);
        
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
     * Loads entries from the provided calendar model into the table.
     * Iterates through the entries and adds them to the table model.
     *
     * @param calendar The CalendarParentModel containing the entries to be displayed.
     */
    private void loadEntries(CalendarParentModel calendar) {
        ArrayList<EntryModel> entries = calendar.getEntries();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Loop through entries and add them to the table model
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
        
        // Sort entries by date (newest first)
        // You can implement custom sorting here if needed
    }

    /**
     * Refreshes the entries displayed in the view.
     * Clears the current entries and reloads them from the provided calendar model.
     *
     * @param calendar The CalendarParentModel containing the updated entries to be displayed.
     */
    public void refreshEntries(CalendarParentModel calendar) {
        loadEntries(calendar);
    }
}