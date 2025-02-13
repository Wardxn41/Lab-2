import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {
    private Event event;
    private JButton completeButton;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(event.getName());
        JLabel dateLabel = new JLabel(event.getDateTime().toString());

        add(nameLabel, BorderLayout.NORTH);
        add(dateLabel, BorderLayout.CENTER);

        if (event instanceof Completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                ((Completable) event).complete();
                JOptionPane.showMessageDialog(this, "Event completed!");
            });
            add(completeButton, BorderLayout.SOUTH);
        }
    }
}
