package yo.askText;

import yo.domain.ask_text;
import yo.utils.DownloadUtil;
import yo.utils.PathUtil;

import java.io.File;

/**
 * ClassName: PDFDownloadTask
 * Description:
 * date: 2020/10/6 12:04
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class PDFDownloadTask extends Thread {
    private int id;

    public PDFDownloadTask(int id) {
        this.id = id;
    }

    public void run() {
        try {
            ask_text domain = ORM.getInstance().selectOne(this.id);
            if (domain.getDownloadFlag() != 1) {
                String filePath = PDFDownloadTask.newFilePath(domain);
                DownloadUtil.getInstance().download(domain.getHttp_url(), new File(filePath));
                ORM.getInstance().flagDownload(domain.getId());
                System.out.println("success download id : " + id);
            }
        } catch (Exception e) {
            System.out.println("error!!! fail to download by id : " + this.id);
            e.printStackTrace();
        }
    }

    public static String newFilePath(ask_text domain) {
        String filePath = domain.getPage() + "/" + domain.getTitle().replace("*", "") + ".pdf";
        return PathUtil.getInstance().getDownloadPDF(filePath);
    }
}
