package express.company;

import express.Province;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/6/22 13:00
 * @Description:
 */
public interface ICompany {
    /**
     * 公司名
     *
     * @return
     */
    String getName();

    /**
     * 快递计算公式
     *
     * @param province
     * @param weight
     * @return
     */
    double calculate(Province province, double weight);
}
