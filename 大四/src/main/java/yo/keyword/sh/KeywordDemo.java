package yo.keyword.sh;

import com.alibaba.excel.EasyExcel;
import other.RedisBoolRunnable;
import util.DownloadUtil;
import util.FileUtil;
import util.JedisUtil;
import util.ThreadUtil;
import yo.keyword.PdfToKeyWord;

import java.io.File;
import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 11:26
 * @Description:
 */
public class KeywordDemo {
    public static final String EXCEL_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\上证_问询函.xlsx";
    public static final String FILE_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\sh_file";
    public static final String PREFIX = "sh";
    public static final ThreadUtil async = ThreadUtil.init();

    public static void main(String[] args) {
        List<SHObject> objects = EasyExcel.read(EXCEL_LOCATION).head(SHObject.class).sheet("17-19年").doReadSync();

        //下载pdf文件
//        for (SHObject shObject : objects) {
//            async.run(new DealSH(shObject));
//        }


    }


}

class CalculateSH implements Runnable {
    private SHObject shObject;

    public CalculateSH(SHObject object) {
        shObject = object;
    }

    @Override
    public void run() {
        String okKey = RedisBoolRunnable.getOkKey(KeywordDemo.PREFIX, shObject.getHttp_url());

        if (JedisUtil.hasKey(okKey)) {
            String filePath = JedisUtil.get(okKey);
            shObject.setKeyword_times(String.valueOf(PdfToKeyWord.keywordTimes(filePath)));
        } else {
            new DealSH(shObject).run();
        }
    }
}

class DealSH extends RedisBoolRunnable {

    private SHObject shObject;

    public DealSH(SHObject object) {
        super(KeywordDemo.PREFIX, object.getHttp_url(), "");
        String fileName = shObject.getHttp_url().substring(shObject.getHttp_url().lastIndexOf("/") + 1);
        setValue(FileUtil.mergeDir(KeywordDemo.FILE_LOCATION, fileName));
        shObject = object;
    }

    @Override
    public void runTask() throws Exception {
        DownloadUtil.download(shObject.getHttp_url(), new File(getValue()));
    }

}

