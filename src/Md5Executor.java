import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

public class Md5Executor implements IExecutable{
    HashMap<String, BigInteger> hashMap = new HashMap<>();
    BigInteger integer;

    public String process(File f) {

        if (!f.canExecute() || !f.canRead())
            return null;

        if (f.isDirectory()) {
            if (hashMap.containsKey(f.getAbsolutePath()))
                return hashMap.get(f.getAbsolutePath()).toString(16);

            integer = BigInteger.ZERO;
            SubProcess(f);
            hashMap.put(f.getAbsolutePath(), integer);
            int i = integer.hashCode();
            integer = BigInteger.ZERO;
            String s = new BigInteger(Integer.toString(i)).toString(16);
            return s;
        }
        else {
            var bytesArray = GetBytesArray(f);
            return getHashMD5(bytesArray).toString(16);
        }

    }

    private byte[] GetBytesArray(File file) {
        var bytes = new ArrayList<>();
        byte[] b;
        try {
            var fileInputStream = new FileInputStream(file);
            try {
                int i;
                while ((i = fileInputStream.read()) != -1) {
                    bytes.add((byte) i);
                }
                b = new byte[bytes.size()];
                for (int j = 0; j < bytes.size(); j++) {
                    b[j] = (byte) bytes.get(j);
                }
            } finally {
                fileInputStream.close();
            }
        } catch (Exception e) {
            b = null;
        }
        return b;
    }

    private BigInteger getHashMD5(byte[] b) {
        BigInteger bigInt;
        try {
            var md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            if (b != null)
                md5.update(b);
            byte[] digest = md5.digest();
            bigInt = new BigInteger(1, digest);

        }
        catch (Exception e) {
            bigInt = null;
        }
        return bigInt;
    }

    private void SubProcess(File file) {
        File[] listFiles = file.listFiles();
        BigInteger big = BigInteger.ZERO;
        for (File f : listFiles) {
            if (f.isDirectory()) {
                if (hashMap.containsKey(f.getAbsolutePath()))
                    integer = integer.add(hashMap.get(f.getAbsolutePath()));
                else
                    SubProcess(f);
            } else {
                byte[] b = GetBytesArray(f);
                BigInteger bigInteger = getHashMD5(b);
                if (bigInteger != null && b != null)
                    integer = integer.add(bigInteger);
            }
        }
    }
}
