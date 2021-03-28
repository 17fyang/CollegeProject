package yo.askText;

import util.DownloadUtil;
import util.PathUtil;
import yo.domain.ask_text;

import java.io.File;

/**
 * ClassName: PDFDownloadTask
 * Description:
 * date: 2020/10/6 12:04
 *
 * @author :涔岄甫鍧愰鏈轰籂
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
                DownloadUtil.download(domain.getHttp_url(), new File(filePath));
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
