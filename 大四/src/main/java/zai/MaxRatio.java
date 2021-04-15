package zai;

import com.alibaba.excel.EasyExcel;
import zai.domain.Electricity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static zai.RatioSplit.OUTPUT_EXCEL;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/4/9 17:50
 * @Description:找出每个段的最大值
 */
public class MaxRatio {
    public static final String OUTPUT_FILE = "C:\\Users\\Administrator\\Desktop\\zai\\max_ratio_0.1.xlsx";

    public static void main(String[] args) {


        List<Electricity> list = EasyExcel.read(OUTPUT_EXCEL).head(Electricity.class).sheet().doReadSync();

        Map<Electricity, BigDecimal> map = new HashMap<>();

        for (Electricity ele : list) {
            if (!map.containsKey(ele)) {
                map.put(ele, new BigDecimal(ele.getLevelValue()));
            } else {
                BigDecimal before = map.get(ele);
                if (before.compareTo(new BigDecimal(ele.getLevelValue())) < 0) {
                    map.remove(ele);
                    map.put(ele, new BigDecimal(ele.getLevelValue()));
                }
            }
        }
        EasyExcel.write(OUTPUT_FILE).head(Electricity.class).sheet().doWrite(new LinkedList<>(map.keySet()));
    }
}
