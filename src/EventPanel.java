import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;
    private JLabel statusLabel;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        // Create and style labels for event name and formatted date-time
        JLabel nameLabel = new JLabel(event.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Styling the name label
        add(nameLabel, BorderLayout.NORTH);

        // Format the date-time to a more user-friendly format
        JLabel dateLabel = new JLabel(formatEventDateTime(event.getDateTime()));
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Styling the date label
        add(dateLabel, BorderLayout.CENTER);

        // Check if the event is a Meeting and display its location
        if (event instanceof Meeting) {
            Meeting meeting = (Meeting) event;
            JLabel locationLabel = new JLabel("Location: " + meeting.getLocation());
            locationLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Styling the location label
            add(locationLabel, BorderLayout.SOUTH); // Add to the panel below the date

            // Adjust the layout and positioning if necessary
            locationLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Optional: Center the label
        }

        // Status label to show completion status (if any)
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12)); // Styling status label
        add(statusLabel, BorderLayout.SOUTH);

        // If the event is of type Completable (i.e., it can be completed), add the complete button
        if (event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                // Mark event as completed
                ((Completable) event).complete();

                // Show a message dialog
                JOptionPane.showMessageDialog(this, "Event completed!");

                // Update the status label to show "Completed"
                completeButton.setEnabled(false);
                completeButton.setText("Completed");  // Change the button text
            });
            add(completeButton, BorderLayout.SOUTH);
        }
    }

    // Format the LocalDateTime to a user-friendly string format
    private String formatEventDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }
}


