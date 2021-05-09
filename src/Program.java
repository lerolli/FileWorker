import java.security.NoSuchAlgorithmException;

public class Program {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        var fileWorker = new FileWorker("C:\\Users\\zahar\\Desktop\\test");
        fileWorker.setRecursive(true);
        var md5Executor = new Md5Executor();
        var consoleResulter = new ConsoleResulter(fileWorker, md5Executor);

        QueueTeams queue = QueueTeams.getInstance();
        queue.enqueue(CommandFactory.createInstance(fileWorker, consoleResulter));
        queue.dequeue();
    }
}
