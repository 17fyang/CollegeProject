package util;

import kotlin.text.Charsets;

import java.io.*;

/**
 * ClassName: FileUtil
 * Description:
 * date: 2020/10/5 23:00
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class FileUtil {

    /**
     * 合并目录和文件路径
     *
     * @param dir
     * @param son
     * @return
     */
    public static String mergeDir(String dir, String son) {
        return dir + "\\" + son;
    }

    public static int getPageByName(String name) {
        int right = name.lastIndexOf(".txt");
        int left = name.lastIndexOf("page");
        return Integer.parseInt(name.substring(left + 4, right));
    }

    /**
     * 读取文本内容
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static String readTxt(File f) throws Exception {
        FileReader reader = new FileReader(f);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\r\n");
        }
        reader.close();
        br.close();
        return sb.toString();
    }

    /**
     * 从流中读出数据，返回字符串
     *
     * @param in
     * @return
     */
    public static String readFromStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 写入一个新文件
     *
     * @param text
     * @param path
     */
    public static void writeText(String text, String path) {
        cn.hutool.core.io.FileUtil.writeString(text, new File(path), Charsets.UTF_8);
    }
}
