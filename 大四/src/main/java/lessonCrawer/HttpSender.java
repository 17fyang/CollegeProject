package lessonCrawer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mysql.cj.util.StringUtils;
import okhttp3.*;
import util.JsonUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/17 15:47
 * @Description:
 */
public class HttpSender {
    public static final String REGISTER = "http://localhost:80/otsea/user/insertUser";
    public static final String INSERT_LESSON = "http://localhost:80/otsea/content/lesson/insertLesson";


    public static boolean insertLesson(MoocLesson lesson) {
        if (StringUtils.isNullOrEmpty(lesson.getAuthorName())) return false;
        if (StringUtils.isNullOrEmpty(lesson.getAuthorJob())) return false;
        if (StringUtils.isNullOrEmpty(lesson.getHeadImageUrl())) return false;
        if (StringUtils.isNullOrEmpty(lesson.getIntroduce())) return false;
        if (StringUtils.isNullOrEmpty(lesson.getLessonImage())) return false;
        if (StringUtils.isNullOrEmpty(lesson.getLessonTitle())) return false;
        if (lesson.getCaptures() == null || lesson.getCaptures().size() == 0) return false;

        RequestBody formBody = new FormBody.Builder()
                .add("name", lesson.getAuthorName())
                .add("intro", lesson.getAuthorJob())
                .add("imageUrl", lesson.getHeadImageUrl()).build();
        Rest registerResult = syncPost(REGISTER, formBody);
        if (registerResult.getCode() != 200) {
            System.out.println("warning!! " + registerResult);
        }

        String userId = registerResult.getData().asText();
        formBody = new FormBody.Builder()
                .add("userId", userId)
                .add("lessonName", lesson.getLessonTitle())
                .add("lessonIntro", lesson.getIntroduce())
                .add("lessonImage", lesson.getLessonImage())
                .add("lessonDir", MoocCapture.listToJson(lesson.getCaptures()).toString()).build();

        Rest insertResult = syncPost(INSERT_LESSON, formBody);
        if (insertResult.getCode() != 200) {
            System.out.println("warning two !! " + insertResult);
        }

        return true;

    }

    /**
     * 同步发送post请求
     *
     * @param url
     * @param body
     * @return
     */
    public static Rest syncPost(String url, RequestBody body) {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new RuntimeException("fail to get server result!!");

            String responseStr = response.body().string();

            ObjectNode restNode = (ObjectNode) JsonUtil.getObjectMapper().readTree(responseStr);
            return packFromJson(restNode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Rest packFromJson(ObjectNode json) {
        int code = 0;

        code = json.get("code").asInt();
        String msg = json.get("msg").asText();
        JsonNode data = json.get("data");

        return new Rest(code, msg, data);
    }

}
