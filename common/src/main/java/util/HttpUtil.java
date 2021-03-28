package util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/27 15:17
 * @Description:
 */
public class HttpUtil {
    /**
     * 同步发送get请求
     *
     * @param url
     * @return
     */
    public static Response syncGet(String url) throws IOException {
        OkHttpClient client = buildClient();
        Request request = buildRequest(url);
        return client.newCall(request).execute();
    }

    public static Request buildRequest(String url) {
        return new Request.Builder()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36")
                .url(url).build();
    }

    public static OkHttpClient buildClient() {
        return new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();
    }
}
