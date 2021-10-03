package express.company;

import express.Province;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/6/22 13:53
 * @Description:
 */
public class Yuantong implements ICompany {
    @Override
    public String getName() {
        return "圆通";
    }

    @Override
    public double calculate(Province province, double weight) {
        if (province == Province.GUANGDONG) {
            if (weight <= 1) return 7.5;
            return (Math.ceil(weight) - 1) * 2 + 7.5;
        } else if (province == Province.FUJIAN) {
            if (weight <= 1) return 8;
            return (Math.ceil(weight) - 1) * 4 + 7.5;
        } else if (province == Province.BEIJING) {
            if (weight <= 1) return 10;
            return (Math.ceil(weight) - 1) * 2 + 6.5;
        }
        throw new RuntimeException("不支持该计算方式");
    }
}
