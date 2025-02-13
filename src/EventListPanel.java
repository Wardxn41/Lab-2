import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events;
    private JPanel displayPanel;
    private JButton addEventButton;

    public EventListPanel() {
        events = new ArrayList<>();
        setLayout(new BorderLayout());

        // Control Panel for the Add Event Button
        JPanel controlPanel = new JPanel();

        // Create and add the "Add Event" button
        addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> openAddEventModal()); // Add action listener
        controlPanel.add(addEventButton);

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
        updateEventDisplay();
    }

    public void updateEventDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            EventPanel eventPanel = new EventPanel(event);
            displayPanel.add(eventPanel);
        }
        revalidate();
        repaint();
    }

}

