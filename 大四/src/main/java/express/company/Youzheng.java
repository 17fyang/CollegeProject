package express.company;

import express.Province;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/6/22 13:59
 * @Description:
 */
public class Youzheng implements ICompany {
    @Override
    public String getName() {
        return "邮政";
    }

    @Override
    public double calculate(Province province, double weight) {
        if (province == Province.GUANGDONG) {
            if (weight <= 1) return 6;
            else return (Math.ceil(weight) - 1) * 2 + 6;
        } else if (province == Province.FUJIAN) {
            if (weight <= 1) return 10;
            else return (Math.ceil(weight) - 1) * 4 + 10;
        } else if (province == Province.BEIJING) {
            if (weight <= 1) return 14;
            else return (Math.ceil(weight) - 1) * 5 + 14;
        }
        throw new RuntimeException("不支持该计算方式");
    }
}
