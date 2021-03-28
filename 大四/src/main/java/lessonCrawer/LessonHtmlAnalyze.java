package lessonCrawer;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.JedisUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/17 13:53
 * @Description:
 */
public class LessonHtmlAnalyze {
    public static final String FILE_LOCATION = "E:\\python\\pycharm\\workSpace\\graduation2021\\lessonCrawler\\file\\lesson\\%d";
    public static final String REDIS_KEY = "lesson:%d";
    public static final String FAIL_KEY = "lesson:fail:%d";

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 1200; i < 1300; i++) {
            String redisKey = String.format(REDIS_KEY, i);
            if (JedisUtil.hasKey(redisKey)) continue;

            String filePath = String.format(FILE_LOCATION, i);
            File f = new File(filePath);
            if (!f.exists()) {
                System.out.println("file not exit :" + i);
                continue;
            }

            final int finalI = i;
            executorService.execute(() -> {
                try {
                    dealFile(f);
                    System.out.println(finalI + ":ok");
                    JedisUtil.set(String.format(REDIS_KEY, finalI), "");
                } catch (Exception e) {
                    JedisUtil.set(String.format(FAIL_KEY, finalI), "");
                    System.out.println("fail to deal file:" + finalI);
                    e.printStackTrace();
                }
            });

        }

    }


    public static void dealFile(File f) throws IOException {
        String s = FileUtils.readFileToString(f, StandardCharsets.UTF_8);
        Document doc = Jsoup.parse(s);

        MoocLesson lesson = new MoocLesson();

        Element lessonInfoElement = doc.body().getElementsByClass("w pr").first();
        parseLesson(lessonInfoElement, lesson);

        Element lessonDirElement = doc.body().getElementsByClass("course-info-main clearfix w").first();
        parseLessonDir(lessonDirElement, lesson);

        Element reComElement = doc.body().getElementsByClass("aside-course-img").first();
        String lessonImage = reComElement.getElementsByTag("img").attr("src");
        lesson.setLessonImage(lessonImage);

        if (!HttpSender.insertLesson(lesson)) throw new RuntimeException();
    }

    private static void parseLessonDir(Element lessonDirElement, MoocLesson lesson) {
        Element introElement = lessonDirElement.getElementsByClass("course-description course-wrap").first();
        lesson.setIntroduce(introElement.text().replace("简介：", ""));

        Elements captureElements = lessonDirElement.getElementsByClass("chapter course-wrap ");

        List<MoocCapture> list = new ArrayList<>();
        for (Element e : captureElements) {
            list.add(MoocCapture.valueOf(e));
        }
        lesson.setCaptures(list);

    }

    private static void parseLesson(Element element, MoocLesson lesson) {
        Element lessonTitleElement = element.getElementsByClass("hd clearfix").first();
        lesson.setLessonTitle(lessonTitleElement.text());

        Element authorInfoElement = element.getElementsByClass("teacher-info l").first();
        Element imageElement = authorInfoElement.getElementsByTag("img").first();
        lesson.setHeadImageUrl(imageElement.attr("src"));

        Element authorNameElement = authorInfoElement.getElementsByClass("tit").first();
        String authorName = authorNameElement.getElementsByTag("a").text();
        lesson.setAuthorName(authorName);

        Element jobElement = authorInfoElement.getElementsByClass("job").first();
        lesson.setAuthorJob(jobElement.text());

    }
}
