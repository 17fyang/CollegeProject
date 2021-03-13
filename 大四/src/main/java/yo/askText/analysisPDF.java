package yo.askText;

import yo.utils.PathUtil;

import java.io.File;

/**
 * ClassName: analysisPDF
 * Description:解析并填充pdf
 * date: 2020/10/6 12:29
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class analysisPDF {
    public static void main(String[] args) throws Exception {
        File folder = new File(PathUtil.getInstance().getDownloadPDF(""));
        File[] pageList = folder.listFiles();
        for (File f : pageList) {
            File[] pdfs = f.listFiles();
            if (pdfs.length != 10) System.out.println(f.getAbsolutePath());
        }
    }

}
