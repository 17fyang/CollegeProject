package express.company;

import express.Province;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/6/22 13:56
 * @Description:
 */
public class Jingdong implements ICompany {
    @Override
    public String getName() {
        return "京东";
    }

    @Override
    public double calculate(Province province, double weight) {
        if (province == Province.GUANGDONG) {
            if (weight <= 10) return 18;
            else if (weight <= 20) return 30;
        } else if (province == Province.FUJIAN) {
            if (weight <= 10) return 38;
            else if (weight <= 20) return 78;
        } else if (province == Province.BEIJING) {
            if (weight <= 10) return 58;
            else if (weight <= 20) return 98;
        }
        throw new RuntimeException("不支持该计算方式");
    }
}
