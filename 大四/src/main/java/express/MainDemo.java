package express;

import express.company.ICompany;
import org.reflections.Reflections;

import java.util.Scanner;
import java.util.Set;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/6/22 12:58
 * @Description:大四毕业了要往打包行李寄快递，所以写了个程序来对比快递公司的开销
 */
public class MainDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            for (Province p : Province.values()) {
                System.out.println(p.getId() + ":" + p.getName());
            }

            System.out.print("请输入目的地：");
            int id = sc.nextInt();
            System.out.print("请输入快递重量（单位：公斤）：");
            double weight = sc.nextDouble();

            System.out.println("--------------------花费清单-----------------");
            Reflections reflections = new Reflections(ICompany.class.getPackage().getName());
            Set<Class<? extends ICompany>> monitorClasses = reflections.getSubTypesOf(ICompany.class);
            for (Class<? extends ICompany> monitor : monitorClasses) {
                ICompany company = monitor.newInstance();

                String msg = null;
                try {
                    double cost = company.calculate(Province.getProvinceById(id), weight);
                    msg = String.valueOf(cost);
                } catch (RuntimeException e) {
                    msg = e.getMessage();
                }


                System.out.println(company.getName() + ":" + msg);
            }
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        System.out.println("按任意键退出...");
        sc.next();
        sc.close();
    }
}
