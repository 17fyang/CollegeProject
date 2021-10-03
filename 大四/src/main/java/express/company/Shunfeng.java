package express.company;

import express.Province;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/6/22 13:31
 * @Description:
 */
public class Shunfeng implements ICompany {
    @Override
    public String getName() {
        return "顺丰";
    }

    /**
     * 顺丰的价格有波动，取最大值
     *
     * @param province
     * @param weight
     * @return
     */
    @Override
    public double calculate(Province province, double weight) {
        if (province == Province.GUANGDONG) {
            if (weight <= 20) return 37;
            else if (weight <= 60) return (Math.ceil(weight) - 20) * 1.9 + 37;
            else return (Math.ceil(weight) - 60) * 1.7 + 113;
        } else if (province == Province.FUJIAN) {
            if (weight <= 20) return 55;
            else if (weight <= 60) return (Math.ceil(weight) - 20) * 3.4 + 55;
            else return (Math.ceil(weight) - 60) * 3.1 + 191;
        }
        throw new RuntimeException("不支持该计算方式");
    }
}
