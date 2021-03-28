package yo.ask.sz;

import com.alibaba.excel.EasyExcel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/27 18:01
 * @Description:
 */
public class FillType {
    public static final String TYPE_EXCEL = "C:\\Users\\Administrator\\Desktop\\yo\\sz\\主板.xlsx";
    public static final String BEFORE_EXCEL = "C:\\Users\\Administrator\\Desktop\\yo\\excel_output\\sz_with_keyword.xlsx";
    public static final String OUT_EXCEL = "C:\\Users\\Administrator\\Desktop\\yo\\excel_output\\sz_main_with_keyword.xlsx";

    public static void main(String[] args) {
        List<SZObject> objects = EasyExcel.read(BEFORE_EXCEL).head(SZObject.class).sheet("主板").doReadSync();
        List<SZObject> types = EasyExcel.read(TYPE_EXCEL).head(SZObject.class).sheet("主板").doReadSync();

        Map<String, String> map = new HashMap<>();
        for (SZObject szObject : types) {
            if (szObject.getContentPdf() == null) continue;
            map.put(szObject.getContentPdf(), szObject.getType());
        }

        for (SZObject o : objects) {
            o.setType(map.get(o.getContentPdf()));
        }

        EasyExcel.write(OUT_EXCEL).head(SZObject.class).sheet().doWrite(objects);

    }
}
