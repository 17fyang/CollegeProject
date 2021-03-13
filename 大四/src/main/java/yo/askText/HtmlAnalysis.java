package yo.askText;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import yo.domain.ask_text;
import yo.utils.FileUtil;

import java.io.File;

/**
 * ClassName: HtmlAnalysis
 * Description:
 * date: 2020/10/3 21:45
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class HtmlAnalysis {
    public static final String htmlFolder = "E:\\python\\pycharm\\workSpace\\yoGraduation\\askText\\file";

    public static void main(String[] args) throws Exception {
        File folder = new File(htmlFolder);
        File[] fileList = folder.listFiles();
//        analysisOnePage(fileList[0], 1);
        for (File f : fileList) {
            int page = FileUtil.getInstance().getPageByName(f.getName());
            analysisOnePage(f, page);
        }
    }


    public static void analysisOneData(Element element, int page) {
        try {
            Elements spanElem = element.getElementsByTag("span");
            int id = Integer.parseInt(spanElem.first().text());
            String time = spanElem.last().text();
            Elements aElem = element.getElementsByTag("a");
            String http_url = aElem.first().attr("href");
            String title = aElem.first().text();
            String company = title.split("：")[0];
            ORM.getInstance().insert(new ask_text(id, page, company, title, http_url, time, 0));
        } catch (Exception e) {
            System.out.println("warn!! there is a data fail to analysis in page " + page);
            e.printStackTrace();
        }

    }

    public static void analysisOnePage(File f, int page) throws Exception {
        String content = FileUtil.getInstance().readTxt(f);
        Document document = Jsoup.parse(content);
        Elements dataListElem = document.body().getElementsByClass("item-title clearfix");
        for (Element e : dataListElem) {
            analysisOneData(e, page);
        }
    }
}
