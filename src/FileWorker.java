import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class FileWorker {
    Path path;
    File directory;
    ArrayList<String> executeFiles;
    private boolean isRecursive;

    public FileWorker(String path){
        directory = new File(path);
        this.path = Paths.get(directory.toURI());
        isRecursive = false;
        executeFiles = new ArrayList<>();
    }


    public boolean getRecursive(){
        return isRecursive;
    }

    public void setRecursive(boolean recursive){
        isRecursive = recursive;
    }

    public ArrayList execute(IExecutable executor) throws NoSuchAlgorithmException {
        reExecute(executor,directory);
        return executeFiles;
    }

    public void reExecute(IExecutable executor, File directory) throws NoSuchAlgorithmException {
        File[] filesInDirectory = directory.listFiles();
        for (File file : filesInDirectory) {
            if (file.isDirectory() && isRecursive)
                reExecute(executor, file);

            String result = executor.process(file);
            if (result != null) {
                Path path1 = Paths.get(file.toURI());
                Path path2 = path.relativize(path1);
                executeFiles.add(path2 + " "+ result);
            }
        }
    }
}
