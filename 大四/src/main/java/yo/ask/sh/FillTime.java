package yo.ask.sh;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import util.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/27 17:46
 * @Description:
 */
public class FillTime {
    public static final String KEYWORD_EXCEL_LOCATION = "C:\\Users\\Administrator\\Desktop\\yo\\excel_output\\sh_with_keyword.xlsx";
    public static final String FILE_TEMPLATE = "C:\\Users\\Administrator\\Desktop\\yo\\sh\\json_file\\page%d.json";

    public static void main(String[] args) throws Exception {
        List<AskSHDo> objects = EasyExcel.read(KEYWORD_EXCEL_LOCATION).head(AskSHDo.class).sheet("0").doReadSync();

        Map<String, String> map = new HashMap<>();
        for (int i = 2; i < 96; i++) {
            String jsonText = FileUtil.readTxt(new File(String.format(FILE_TEMPLATE, i)));
            JSONObject json = (JSONObject) JSONObject.parse(jsonText);
            JSONArray dataArr = json.getJSONObject("pageHelp").getJSONArray("data");
            for (Iterator<Object> it = dataArr.stream().iterator(); it.hasNext(); ) {
                JSONObject data = (JSONObject) it.next();
                String newTime = data.getString("createTime");
                map.put("http://" + data.getString("docURL"), newTime);
            }
        }

        for (AskSHDo askSHDo : objects) {
            askSHDo.setDate(map.get(askSHDo.getPdf()));
        }

        EasyExcel.write(KEYWORD_EXCEL_LOCATION).head(AskSHDo.class).sheet().doWrite(objects);

    }
}
