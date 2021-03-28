package yo.ask.sh;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import util.FileUtil;
import util.ThreadUtil;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 17:01
 * @Description:
 */
public class HtmlGet {
    public static final String FILE_TEMPLATE = "C:\\Users\\Administrator\\Desktop\\yo\\sh\\json_file\\page%d.json";
    public static final String SH_EXCEL_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\excel_output\\sh.xlsx";
    public static final String KEYWORD_EXCEL_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\excel_output\\withKeyword.xlsx";

    public static void main(String[] args) {
        calculatePdf();
    }

    private static void calculatePdf() {
        List<AskSHDo> objects = EasyExcel.read(SH_EXCEL_LOCATION).head(AskSHDo.class).sheet("0").doReadSync();
        ThreadUtil async = ThreadUtil.init();

        for (AskSHDo askSHDo : objects) {
            async.run(new AskCalculateRunnable(askSHDo));
        }

        async.getExecutorService().shutdown();

        while (!async.getExecutorService().isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sleep 1000");
        }
        System.out.println("finish");


        EasyExcel.write(KEYWORD_EXCEL_LOCATION).sheet().doWrite(objects);
    }

    private static void downloadPdf() {
        List<AskSHDo> objects = EasyExcel.read(SH_EXCEL_LOCATION).head(AskSHDo.class).sheet("0").doReadSync();
        ThreadUtil async = ThreadUtil.init();

        for (AskSHDo askSHDo : objects) {
            async.run(new AshSHRunnable(askSHDo));
        }

    }

    private static void JsonToExcel() {
        try {
            List<AskSHDo> list = new LinkedList<>();

            for (int i = 2; i < 96; i++) {
                String jsonText = FileUtil.readTxt(new File(String.format(FILE_TEMPLATE, i)));
                JSONObject json = (JSONObject) JSONObject.parse(jsonText);

                JSONArray dataArr = json.getJSONObject("pageHelp").getJSONArray("data");

                for (Iterator<Object> it = dataArr.stream().iterator(); it.hasNext(); ) {
                    JSONObject data = (JSONObject) it.next();
                    AskSHDo askSHDo = new AskSHDo();
                    askSHDo.setTitle(data.getString("docTitle"));
                    askSHDo.setStockCode(data.getString("stockcode"));
                    askSHDo.setType(data.getString("extWTFL"));
                    askSHDo.setPdf("http://" + data.getString("docURL"));
                    askSHDo.setCompanyName(data.getString("extGSJC"));
                    askSHDo.setDate(data.getString("cmsOpDate"));
                    list.add(askSHDo);
                }
            }

            EasyExcel.write(SH_EXCEL_LOCATION).sheet().doWrite(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void runServerProxy() {
        AtomicInteger page = new AtomicInteger(-1);
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.addResponseFilter((resp, content, msg) -> {
            String respText = content.getTextContents();
            if (!respText.startsWith("jsonpCallback")) return;
            int intPage = page.incrementAndGet();

            String jsonText = respText.substring(respText.indexOf("(") + 1, respText.length() - 1);

            FileUtil.writeText(jsonText, String.format(FILE_TEMPLATE, intPage));
        });

        proxy.start(0);
        int port = proxy.getPort();

        System.out.println(port);
    }
}
