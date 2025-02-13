import java.time.LocalDateTime;

class Deadline extends Event implements Completable {
    private boolean complete = false;

    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void complete() {
        complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }
}
