package express.company;

import express.Province;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/6/22 13:38
 * @Description:
 */
public class Debang implements ICompany {
    @Override
    public String getName() {
        return "德邦";
    }

    /**
     * 德邦小件快递不准，3.6kg以上的大件快递较准
     *
     * @param province
     * @param weight
     * @return
     */
    @Override
    public double calculate(Province province, double weight) {
        return normal(province, weight) * 0.85;
    }

    private double normal(Province province, double weight) {
        if (province == Province.GUANGDONG) {
            if (weight <= 1) return 10;
            else if (weight <= 2) return 12;
            else if (weight <= 3) return 14;
            return 14 + (Math.ceil(weight) - 3) * 1.5;
        } else if (province == Province.FUJIAN) {
            if (weight <= 1) return 13;
            else if (weight <= 2) return 16;
            else if (weight <= 3) return 13;
            return 14 + (Math.ceil(weight) - 3) * 3;
        }
        throw new RuntimeException("不支持该计算方式");
    }
}
