package yo.askText_sh;

import yo.askText.ORM;
import yo.askText.PDFDownloadTask;
import yo.domain.ask_text;
import yo.utils.FileUtil;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClassName: fillDownloadDemo
 * Description:
 * date: 2020/10/6 11:44
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class fillDownloadDemo {
    public static final String logPath = "C:\\Users\\Administrator\\Desktop\\log.txt";

    public static void main(String[] args) throws Exception {
        getLeftId();
    }

    private static void getLeftId() {
        List<ask_text> list = ORM.getInstance().selectAll();
        Set<String> set = list.stream().map(ask_text::getTitle).collect(Collectors.toSet());
        System.out.println(set.size());
//        System.out.println(list.size());
//        for (ask_text domain : list) {
//            String filePath = PDFDownloadTask.newFilePath(domain);
//            File f = new File(filePath);
//            if (!f.exists()) System.out.println(domain.getId());
//        }
    }

    private static void fillPDF() {
        Set<Integer> set = new HashSet<>(Arrays.asList(598, 1438, 2622, 5853, 6438, 9818, 10077, 10729));
        for (int id : set) {
            new PDFDownloadTask(id).start();
        }
    }

    private static void analysisLog() throws Exception {
        String log = FileUtil.getInstance().readTxt(new File(logPath));
        String[] arr = log.split("\r\n");
        boolean[] flag = new boolean[17952];
        for (String s : arr) {
            if (s.startsWith("success")) {
                int index = Integer.parseInt(s.split(": ")[1]);
                flag[index] = true;
            }
        }
        for (int i = 0; i < flag.length; i++) {
            if (!flag[i]) System.out.print(i + ",");
        }
    }
}
