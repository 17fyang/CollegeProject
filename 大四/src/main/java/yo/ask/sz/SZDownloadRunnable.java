package yo.ask.sz;

import other.RedisBoolRunnable;
import util.DownloadUtil;
import util.FileUtil;

import java.io.File;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/27 11:09
 * @Description:
 */
public class SZDownloadRunnable extends RedisBoolRunnable {
    public static final String PREFIX = "dl_sz:middle";
    public static final String CONTENT_PREFIX = "http://reportdocs.static.szse.cn/UpFiles/fxklwxhj/";
    public static final String PDF_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\sz\\middlePdf";
    private SZObject szObject;

    public SZDownloadRunnable(SZObject szObject) {
        super(PREFIX, CONTENT_PREFIX + szObject.getContentPdf(), FileUtil.mergeDir(PDF_LOCATION, szObject.getContentPdf()));
        this.szObject = szObject;
    }

    @Override
    protected void runTask() throws Exception {
        DownloadUtil.download(getKey(), new File(getValue()));
    }
}
