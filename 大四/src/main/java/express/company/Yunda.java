package express.company;

import express.Province;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/6/22 13:26
 * @Description:
 */
public class Yunda implements ICompany {
    @Override
    public String getName() {
        return "韵达";
    }

    @Override
    public double calculate(Province province, double weight) {
        if (province == Province.GUANGDONG) {
            if (weight <= 1) return 6;
            else return (weight - 1) * 2 + 6;
        } else {
            if (weight <= 1) return 7.5;
            else return (weight - 1) * 3.5 + 7.5;
        }
    }


}
