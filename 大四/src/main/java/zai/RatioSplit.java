package zai;

import com.alibaba.excel.EasyExcel;
import zai.domain.Electricity;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/4/9 17:03
 * @Description:
 */
public class RatioSplit {
    public static final String FILE_LOCATION = "C:\\Users\\Administrator\\Desktop\\zai\\stationpower-3037.xlsx";
    public static final String OUTPUT_EXCEL = "C:\\Users\\Administrator\\Desktop\\zai\\with_split_0.1.xlsx";

    public static void main(String[] args) {
        List<Electricity> list = EasyExcel.read(FILE_LOCATION).head(Electricity.class).sheet("stationpower-3037").doReadSync();

        List<Electricity> result = new LinkedList<>();
        for (Electricity electricity : list) {
            if (electricity.getLight() != null && electricity.getPower() != null && electricity.getTemperature() != null)
                result.add(electricity);
        }

        for (Electricity electricity : result) {
            BigDecimal light = new BigDecimal(electricity.getLight());
            BigDecimal power = new BigDecimal(electricity.getPower());
            BigDecimal tem = new BigDecimal(electricity.getTemperature()).add(new BigDecimal(30));

            BigDecimal level = light.multiply(BigDecimal.valueOf(10));
            BigDecimal value = electricity.getLight().equals("0") ? BigDecimal.ZERO :
                    power.divide(light, 16, BigDecimal.ROUND_HALF_UP).divide(tem, 8, BigDecimal.ROUND_HALF_UP);
            value = value.multiply(new BigDecimal(1000));

            electricity.setLevel(level.intValue());
            electricity.setLevelValue(value.toPlainString());
        }
        EasyExcel.write(OUTPUT_EXCEL).head(Electricity.class).sheet().doWrite(result);
    }
}
