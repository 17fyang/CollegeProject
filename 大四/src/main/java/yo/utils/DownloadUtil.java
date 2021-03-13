package yo.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: DownloadUtil
 * Description:
 * date: 2020/10/5 16:58
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class DownloadUtil {
    public static DownloadUtil instance = new DownloadUtil();

    public static DownloadUtil getInstance() {
        return instance;
    }

    public void download(String fileUrl, File outFile) throws Exception {
        if (!outFile.getParentFile().exists()) outFile.getParentFile().mkdirs();
        while (outFile.exists()) outFile = this.rename(outFile);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(fileUrl).build();
        Response response = client.newCall(request).execute();

        FileOutputStream fout = new FileOutputStream(outFile);
        InputStream in = response.body().byteStream();

        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            fout.write(buf, 0, len);
        }
        fout.flush();
        fout.close();
    }

    /**
     * 重复文件重命名
     *
     * @param f
     * @return
     */
    public File rename(File f) {
        String absPath = f.getParentFile().getAbsolutePath();
        absPath = absPath + "\\_" + f.getName();
        return new File(absPath);
    }
}
