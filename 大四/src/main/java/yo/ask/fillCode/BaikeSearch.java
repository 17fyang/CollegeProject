package yo.ask.fillCode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.DownloadUtil;
import util.FileUtil;
import util.JedisUtil;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/27 15:16
 * @Description:
 */
public class BaikeSearch {
    public static final String BAI_KE_URL = "https://baike.baidu.com/item/%s&rsv_spt=1&rsv_iqid=0xcb097cf7000026a3&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&rsv_btype=t&inputT=312&rsv_t=7206ruX%2FrYlSI75UkUwqMvF9%2BTb1w3azShktGILJ18LFkP33fuArD5OOKBfkDGHpaxxb&rsv_n=2&rsv_pq=eed0977800084a6f&rsv_sug3=2&rsv_sug1=2&rsv_sug7=100&rsv_sug2=0&rsv_sug4=312";
    public static final String BAIDU_URL = "http://www.baidu.com/s?wd=%s";
    public static final Pattern pattern = Pattern.compile("[0-9]{6}");
    public static final String REDIS_KEY = "fill:%s";
    public static final Set<String> attributeSet = new HashSet<>();

    static {
        attributeSet.add("t");
        attributeSet.add("c-title");
        attributeSet.add("t c-gap-bottom-small");
    }

    public static void main(String[] args) throws Exception {
        search("绵石投资");
    }

    public static String search(String name) throws Exception {
        String result = checkCache(name);
        if (result != null) return result;

        result = searchBySelenium(name);
        if (result != null) {
            putCache(name, result);
            return result;
        }

//        result = searchByBaike(name);
//        if (result != null) {
//            putCache(name, result);
//            return result;
//        }
        return null;
    }

    private static String searchBySelenium(String name) throws Exception {
        String text = FileUtil.readTxt(new File("C:\\Users\\Administrator\\Desktop\\namelist.txt"));
        String[] arr = text.split("\r\n");
        for (String s : arr) {
            if (name.equals(s.split(" ")[0])) return s.split(" ")[1];
        }
        return null;
    }

    private static String searchByTitle(String name) throws Exception {
        String text = DownloadUtil.downloadText(String.format(BAIDU_URL, name));
        Document doc = Jsoup.parse(text);
        System.out.println(text);
        Elements elements = doc.body().getElementsByTag("h3");
        for (Element element : elements) {
            String content = element.text();
//            System.out.println(content);

            String attr = element.attr("class");
            if (attr == null || !attributeSet.contains(attr)) continue;
            if (findStockCode(content) != null) return findStockCode(content);
        }
        return null;
    }

    private static String searchByBaike(String name) throws Exception {
        String text = DownloadUtil.downloadText(String.format(BAI_KE_URL, name));
        Document doc = Jsoup.parse(text);
        Elements elements = doc.body().getElementsByClass("content-wrapper");

        String content = elements.text();

        String[] keywords = content.split("代码");

        for (int i = 1; i < keywords.length; i++) {
            String result = findStockCode(keywords[i].substring(0, 12));
            if (result != null) return result;
        }

        return null;
    }


    private static String checkCache(String name) {
        String key = String.format(REDIS_KEY, name);
        if (JedisUtil.hasKey(key)) return JedisUtil.get(key);
        return null;
    }

    private static void putCache(String name, String code) {
        String key = String.format(REDIS_KEY, name);
        JedisUtil.set(key, code);
    }


    public static String findStockCode(String s) {
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) return matcher.group();
        return null;
    }
}
