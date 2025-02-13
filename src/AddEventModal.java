import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventModal extends JDialog {
    private JTextField nameField;
    private JSpinner dateSpinner;
    private JTextField locationField;
    private JButton addButton;
    private JComboBox<String> eventTypeComboBox;
    private EventListPanel eventListPanel;  // To update the list of events after adding

    public AddEventModal() {
        setTitle("Add New Event");
        setSize(350, 250);
        setLocationRelativeTo(null);  // Center the dialog
        setModal(true);  // Make this a modal dialog

        // Create panel for input fields
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));  // Grid layout for easy organization of fields

        // Event Type Dropdown (e.g., Meeting, Deadline)
        eventTypeComboBox = new JComboBox<>(new String[] {"Deadline", "Meeting"});
        panel.add(new JLabel("Event Type:"));
        panel.add(eventTypeComboBox);

        // Event Name
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        // Event Date and Time using JSpinner for better input control
        panel.add(new JLabel("Date and Time:"));
        dateSpinner = createDateTimeSpinner();
        panel.add(dateSpinner);

        // Location (only for meetings)
        panel.add(new JLabel("Location:"));
        locationField = new JTextField();
        panel.add(locationField);

        // Add button
        addButton = new JButton("Add Event");
        addButton.addActionListener(e -> addEvent());

        // Add the panel and button to the dialog
        add(panel, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
    }

    private JSpinner createDateTimeSpinner() {
        // Create a date-time model for the JSpinner
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd HH:mm:ss");
        spinner.setEditor(editor);
        return spinner;
    }

    private void addEvent() {
        String eventName = nameField.getText();

        // Validate the event name
        if (eventName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Event name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Extract the date-time value from the JSpinner
        LocalDateTime eventDate = LocalDateTime.ofInstant(((java.util.Date) dateSpinner.getValue()).toInstant(), java.time.ZoneId.systemDefault());

        // Event Type handling (Deadline or Meeting)
        String eventType = (String) eventTypeComboBox.getSelectedItem();
        Event event = null;

        if ("Deadline".equals(eventType)) {
            event = new Deadline(eventName, eventDate);
        } else if ("Meeting".equals(eventType)) {
            // If it's a meeting, get the location and add it to the event
            String location = locationField.getText().trim();
            if (location.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Location is required for meetings.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            event = new Meeting(eventName, eventDate, eventDate.plusHours(1), location);  // Assume 1-hour meeting duration
        }

        if (event != null) {
            // Add the event to the EventListPanel and update display
            eventListPanel.addEvent(event);
            dispose();  // Close the modal dialog
        }
    }

    // Set the EventListPanel from the parent frame
    public void setEventListPanel(EventListPanel eventListPanel) {
        this.eventListPanel = eventListPanel;
    }
}