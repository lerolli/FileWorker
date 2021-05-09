public class CommandFactory {
    public static ICommand createInstance(FileWorker fw, IResulter r) {
        return new FileCommand(fw, r);
    }
}
