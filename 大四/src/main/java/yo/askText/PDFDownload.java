package yo.askText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName: PDFDownload
 * Description:
 * date: 2020/10/5 16:59
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class PDFDownload {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 1; i < 17952; i++) {
            executor.execute(new PDFDownloadTask(i));
        }
    }
}

