package yo.ask.fillCode;

import com.alibaba.excel.EasyExcel;
import util.ThreadUtil;

import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/27 14:50
 * @Description:
 */
public class FillCodeDemo {
    public static final String EXCEL_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\待补充.xlsx";
    public static final String OUTPUT_EXCEL = "C:\\Users\\Administrator\\Desktop\\yo\\excel_output\\with_code.xlsx";

    public static void main(String[] args) throws Exception {
        listCompany();
    }

    private static void listCompany() throws InterruptedException {
        List<FillObject> list = EasyExcel.read(EXCEL_LOCATION).head(FillObject.class).sheet("待补充股票代码").doReadSync();
        ThreadUtil async = ThreadUtil.init();


        for (FillObject line : list) {
            async.run(() -> {
                try {
                    String[] arr = line.getTitle().split("[：]");
                    String name = arr[0].replaceAll(" ", "");
                    String stockCode = BaikeSearch.search(name);
                    if (stockCode != null) line.setCode(stockCode);
                    else System.out.println(name + " " + line.getTitle());
                } catch (Exception e) {

                }
            });
        }

        async.getExecutorService().shutdown();

        while (!async.getExecutorService().isTerminated()) {
            Thread.sleep(1000);
        }
        Thread.sleep(1000);

        EasyExcel.write(OUTPUT_EXCEL).head(FillObject.class).sheet().doWrite(list);
    }
}
