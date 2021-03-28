package lessonCrawer;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.JdbcUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/17 19:54
 * @Description:
 */
public class GetImage {
    public static void main(String[] args) throws IOException, SQLException {
        Set<String> imageSet = new HashSet<>();
        for (int i = 1100; i < 1300; i++) {
            String filePath = String.format(LessonHtmlAnalyze.FILE_LOCATION, i);
            File f = new File(filePath);
            if (!f.exists()) continue;

            String s = FileUtils.readFileToString(f, StandardCharsets.UTF_8);
            Document doc = Jsoup.parse(s);
            try {
                parseHtml(doc, imageSet);
            } catch (Exception ignored) {
            }
        }

        List<Integer> idList = getResourceList();

        Iterator<Integer> idIt = idList.listIterator();
        Iterator<String> urlIt = imageSet.iterator();

        while (idIt.hasNext() && urlIt.hasNext()) {
            updateUrl(idIt.next(), urlIt.next());
        }


    }

    public static void parseHtml(Document doc, Set<String> imageSet) {
        Elements reComElement = doc.body().getElementsByClass("aside-course-img");
        for (Element e : reComElement) {
            String lessonImage = e.getElementsByTag("img").attr("src");
            imageSet.add("https:" + lessonImage);
        }
    }

    public static List<Integer> getResourceList() throws SQLException {
        String sql = "select * from resource";
        Connection connection = JdbcUtil.getConnection();
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(sql);

        List<Integer> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getInt(1));
        }

        JdbcUtil.close(rs, stat, connection);
        return list;
    }

    public static void updateUrl(int id, String url) throws SQLException {
        String sql = "update resource set resource_path = \"" + url + "\" where resource_id = " + id;
        Connection connection = JdbcUtil.getConnection();
        Statement stat = connection.createStatement();
        stat.execute(sql);

        JdbcUtil.close(null, stat, connection);
    }

}
