import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTeams {
    private static QueueTeams instance;
    private static Queue<ICommand> queue;

    private QueueTeams() {
        queue = new LinkedList<>();
    }

    public static QueueTeams getInstance() {
        if (instance == null) {
            instance = new QueueTeams();
        }
        return instance;
    }

    public void enqueue(ICommand commands) {
        queue.addAll(Arrays.asList(commands));
    }

    public void dequeue() {
        queue.poll().start();
    }
}
