import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
public class EventPlanner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        EventListPanel eventListPanel = new EventListPanel();
        addDefaultEvents(eventListPanel);

        frame.add(eventListPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    static void addDefaultEvents(EventListPanel events) {
        // Add some default events (Deadline and Meeting)
        events.addEvent(new Deadline("Sample Deadline", LocalDateTime.of(2025, 3, 1, 12, 0)));
        events.addEvent(new Meeting("Sample Meeting", LocalDateTime.of(2025, 2, 20, 10, 0),
               LocalDateTime.of(2000, 2, 20, 11, 0), "Zoom"));
    }
}
