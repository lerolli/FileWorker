import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class ConsoleResulter implements IResulter{
    ArrayList<String> result;

    public ConsoleResulter(FileWorker fileWorker, IExecutable executable){
        try {
            result = fileWorker.execute(executable);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void showResult() {
        for (String o : result) System.out.println(o);
    }
}
