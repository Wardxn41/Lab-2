import java.time.LocalDateTime;

public class EventTester {
    public static void main(String[] args) {
        // Create some sample events
        Event meeting1 = new Meeting("Team Meeting", LocalDateTime.of(2025, 2, 12, 9, 0), LocalDateTime.of(2025, 2, 12, 10, 0), "Room 1");
        Event deadline1 = new Deadline("Project Deadline", LocalDateTime.of(2025, 2, 14, 17, 0));

        // Print event details
        System.out.println(meeting1.getName() + " at " + meeting1.getDateTime());
        System.out.println(deadline1.getName() + " at " + deadline1.getDateTime());

        // Test comparison
        System.out.println("Comparison of events: " + meeting1.compareTo(deadline1));
    }
}
