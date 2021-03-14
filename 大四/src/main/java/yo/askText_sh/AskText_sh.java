package yo.askText_sh;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import yo.askText.ORM;
import yo.domain.ask_text;
import yo.utils.FileUtil;

import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * ClassName: AskText_sh
 * Description:瑙ｆ瀽html
 * date: 2020/10/5 22:58
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class AskText_sh {
    public static final String htmlFolder = "E:\\python\\pycharm\\workSpace\\yoGraduation\\askText_sh\\file";
    public static final String HTTP_URL_PREFIX = "http://www.sse.com.cn";
    public static Set<Integer> set = new HashSet<>(Arrays.asList(855, 860));

    public static void main(String[] args) throws Exception {
        File folder = new File(htmlFolder);
        File[] fileList = folder.listFiles();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (File f : fileList) {
            int page = FileUtil.getInstance().getPageByName(f.getName());
            if (!set.contains(page)) continue;
            System.out.println(page);
            executor.execute(() -> {
                try {
//                    int page = FileUtil.getInstance().getPageByName(f.getName());
                    analysisOnePage(f, page);
                } catch (Exception e) {
                    System.out.println("error!!! fail to analysis this page ");
                    e.printStackTrace();
                }
            });
        }
    }

    private static void analysisOnePage(File f, int page) throws Exception {
        String content = FileUtil.getInstance().readTxt(f);
        Document document = Jsoup.parse(content);
        Elements dataListElem = document.body().getElementsByTag("dl");
        for (Element e : dataListElem) {
            analysisOneData(e, page);
        }
    }

    private static void analysisOneData(Element element, int page) {
        try {
            int id = Integer.parseInt(element.getElementsByTag("dt").first().text());
            String time = element.getElementsByTag("span").first().text();
            Elements aElem = element.getElementsByTag("a");
            String http_url = HTTP_URL_PREFIX + aElem.first().attr("href");
            String title = aElem.attr("title");
            String[] arr = title.split("[锛�,鍏充簬,鐨�,鍏徃,闂鍑絔");
            String company = arr[0];
            int i = 0;
            while (arr[i++].equals("")) company = arr[i];
            ORM.getInstance().insert(new ask_text(id, page, company, title, http_url, time, 0));
        } catch (SQLException e) {
            System.out.println("warn!! there is a data fail to analysis in page " + page);
            e.printStackTrace();
        }
    }
}
