package util;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 13:21
 * @Description:
 */
public class PDFUtil {
    /**
     * 从PDF文件中读取文字
     *
     * @param path
     * @return
     */
    public static String readText(String path) {
        PdfDocument doc = new PdfDocument(path);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < doc.getPages().getCount(); i++) {
            PdfPageBase page = doc.getPages().get(i);
            sb.append(page.extractText(true));
        }
        return sb.toString();
    }

}
