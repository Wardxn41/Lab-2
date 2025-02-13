import java.time.Duration;
import java.time.LocalDateTime;

enum Urgency {
    DISTANT, IMMINENT, OVERDUE;

    private static Duration thresholdOfImminence = Duration.ofDays(1);

    public static void setThresholdOfImminence(Duration d) {
        thresholdOfImminence = d;
    }

    public static Urgency getUrgency(LocalDateTime time) {
        Duration remainingTime = Duration.between(LocalDateTime.now(), time);
        if (remainingTime.isNegative()) {
            return OVERDUE;
        } else if (remainingTime.compareTo(thresholdOfImminence) < 0) {
            return IMMINENT;
        } else {
            return DISTANT;
        }
    }
}
