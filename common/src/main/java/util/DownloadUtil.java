package util;

import okhttp3.Response;

import java.io.File;
import java.io.InputStream;

/**
 * ClassName: DownloadUtil
 * Description:
 * date: 2020/10/5 16:58
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class DownloadUtil {

    /**
     * 从http地址下载文件
     *
     * @param fileUrl http 地址
     * @param outFile 输出文件对象
     * @throws Exception
     */
    public static void download(String fileUrl, File outFile) throws Exception {
        if (!outFile.getParentFile().exists()) outFile.getParentFile().mkdirs();
        while (outFile.exists()) outFile = rename(outFile);

        String text = downloadText(fileUrl);

        FileUtil.writeText(text, outFile.getAbsolutePath());
    }

    /**
     * 下载资源文件，返回String类型
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String downloadText(String url) throws Exception {
        Response response = HttpUtil.syncGet(url);
        if (!response.isSuccessful()) throw new RuntimeException("no successful request");
        InputStream in = response.body().byteStream();
        return FileUtil.readFromStream(in);
    }

    /**
     * 给文件重命名
     *
     * @param f
     * @return
     */
    public static File rename(File f) {
        String absPath = f.getParentFile().getAbsolutePath();
        absPath = absPath + "\\_" + f.getName();
        return new File(absPath);
    }
}
