package yo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * ClassName: FileUtil
 * Description:
 * date: 2020/10/5 23:00
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class FileUtil {
    private static FileUtil instance = new FileUtil();

    public static FileUtil getInstance() {
        return instance;
    }

    public int getPageByName(String name) {
        int right = name.lastIndexOf(".txt");
        int left = name.lastIndexOf("page");
        return Integer.parseInt(name.substring(left + 4, right));
    }

    public String readTxt(File f) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(f));
        StringBuilder sb = new StringBuilder();
        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\r\n");
        }
        return sb.toString();
    }
}
