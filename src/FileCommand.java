public class FileCommand implements ICommand
{
    private final FileWorker fileWorker;
    private final IResulter resulter;

    public FileCommand(FileWorker fileWorker, IResulter resulter) {
        this.fileWorker = fileWorker;
        this.resulter = resulter;
    }
    @Override
    public void start() {
        resulter.showResult();
    }
}