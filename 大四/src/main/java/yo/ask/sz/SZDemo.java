package yo.ask.sz;

import com.alibaba.excel.EasyExcel;
import util.JedisUtil;
import util.ThreadUtil;

import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/27 10:56
 * @Description:
 */
public class SZDemo {
    public static final String EXCEL_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\sz\\主板.xlsx";
    public static final String OUTPUT_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\sz\\sz_chuang_withKeyword.xlsx";

    static {
        JedisUtil.setDB(6);
    }

    public static void main(String[] args) throws InterruptedException {
        calculatePdf();
    }

    private static void downloadPdf() {
        List<SZObject> list = EasyExcel.read(EXCEL_LOCATION).head(SZObject.class).sheet("中小企业板").doReadSync();
        ThreadUtil async = ThreadUtil.init();

        for (SZObject szObject : list) {
            async.run(new SZDownloadRunnable(szObject));
        }
        async.getExecutorService().shutdown();
    }

    private static void calculatePdf() throws InterruptedException {
        List<SZObject> list = EasyExcel.read(EXCEL_LOCATION).head(SZObject.class).sheet("创业板").doReadSync();
        ThreadUtil async = ThreadUtil.init();

        for (SZObject szObject : list) {
            async.run(new SZCalculateRunnable(szObject));
        }

        async.getExecutorService().shutdown();

        while (!async.getExecutorService().isTerminated()) {
            Thread.sleep(1000);
        }
        Thread.sleep(1000);

        EasyExcel.write(OUTPUT_LOCATION).head(SZObject.class).sheet("创业板").doWrite(list);
    }
}
