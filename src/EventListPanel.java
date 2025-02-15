import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;
    private JButton addEventButton;
    private JCheckBox meetingFilterCheckBox;
    private JCheckBox deadlineFilterCheckBox;
    private JCheckBox sortByDateCheckBox;
    private JCheckBox sortByTypeCheckBox;
    private JCheckBox sortByNameCheckBox;

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());

        // Control Panel for the Add Event Button, Filters, and Sorting Options
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // FlowLayout to align components horizontally

        // Create and add the "Add Event" button
        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> openAddEventModal()); // Add action listener
        controlPanel.add(addEventButton);

        // Create and add checkboxes for filtering events
        meetingFilterCheckBox = new JCheckBox("Show Meetings", true);  // Default to true (show all)
        deadlineFilterCheckBox = new JCheckBox("Show Deadlines", true);  // Default to true (show all)

        // Add listeners for filtering
        meetingFilterCheckBox.addActionListener(e -> updateEventDisplay());
        deadlineFilterCheckBox.addActionListener(e -> updateEventDisplay());

        // Add filters to the control panel (this will place them next to the "Add Event" button)
        controlPanel.add(meetingFilterCheckBox);
        controlPanel.add(deadlineFilterCheckBox);

        // Create and add checkboxes for sorting options
        sortByDateCheckBox = new JCheckBox("Sort by Date-Time");
        sortByTypeCheckBox = new JCheckBox("Sort by Event Type");
        sortByNameCheckBox = new JCheckBox("Sort by Name");

        // Add listeners for sorting
        sortByDateCheckBox.addActionListener(e -> updateEventDisplay());
        sortByTypeCheckBox.addActionListener(e -> updateEventDisplay());
        sortByNameCheckBox.addActionListener(e -> updateEventDisplay());

        // Add sorting options to the control panel
        controlPanel.add(sortByDateCheckBox);
        controlPanel.add(sortByTypeCheckBox);
        controlPanel.add(sortByNameCheckBox);

        add(controlPanel, BorderLayout.NORTH);

        // Panel to display event panels
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(displayPanel, BorderLayout.CENTER);
    }

    // Method to open the Add Event Modal
    private void openAddEventModal() {
        AddEventModal modal = new AddEventModal();
        modal.setEventListPanel(this);  // Pass the reference of the EventListPanel
        modal.setVisible(true);  // Show the modal
    }

    public void addEvent(Event event) {
        events.add(event);
        updateEventDisplay();  // Update the display after adding a new event
    }

    public void updateEventDisplay() {
        // Filter the events based on selected checkboxes
        List<Event> filteredEvents = new ArrayList<>();

        for (Event event : events) {
            if ((event instanceof Meeting && meetingFilterCheckBox.isSelected()) ||
                    (event instanceof Deadline && deadlineFilterCheckBox.isSelected())) {
                filteredEvents.add(event);
            }
        }

        // Apply sorting based on selected criteria
        if (sortByDateCheckBox.isSelected()) {
            Collections.sort(filteredEvents, Comparator.comparing(Event::getDateTime));
        } else if (sortByTypeCheckBox.isSelected()) {
            Collections.sort(filteredEvents, new Comparator<Event>() {
                @Override
                public int compare(Event e1, Event e2) {
                    return e1.getClass().getSimpleName().compareTo(e2.getClass().getSimpleName());
                }
            });
        } else if (sortByNameCheckBox.isSelected()) {
            Collections.sort(filteredEvents, Comparator.comparing(Event::getName));
        }

        // Now update the display with the filtered and sorted events
        displayPanel.removeAll();
        for (Event event : filteredEvents) {
            EventPanel eventPanel = new EventPanel(event);
            displayPanel.add(eventPanel);
        }

        revalidate();
        repaint();
    }
}


