package util;

/**
 * ClassName: PathUtil
 * Description:
 * date: 2020/10/5 16:35
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class PathUtil {
    public static PathUtil instance = new PathUtil();
    private static String resource = "E://鏉ㄥ竼/Y/graduation/";

//    static {
//        try {
//            resource = PathUtil.class.getResource("/").getPath();
//            resource = URLDecoder.decode(resource, "utf-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static PathUtil getInstance() {
        return instance;
    }

    public String getResource() {
        return resource;
    }

    public String getDownloadPDF(String sonUrl) {
        return resource + "downloadPDF_sh/" + sonUrl;
    }

    public String getFileName(String fileUrl) {
        return fileUrl.substring(fileUrl.lastIndexOf("."));
    }
}
