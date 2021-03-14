package sf.finalExam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName: icpcA
 * Description:
 * date: 2020/6/21 17:34
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class icpcA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        //读取数据
        List<Plate> Joao = getPlates(sc);
        List<Plate> Maria = getPlates(sc);
        //排序
        Joao.sort(new myComparator(false));
        Maria.sort(new myComparator(true));
        //处理可变位置重合的情况
        boolean result = solution(Joao, Maria);
        //输出结果
        if (result) out(Joao, Maria);
        else out(null, null);
        sc.close();
    }

    //输出结果
    private static void out(List<Plate> joao, List<Plate> maria) {
    }

    //调整盘子位置
    private static boolean solution(List<Plate> joao, List<Plate> maria) {
        for (int i = 0; i < joao.size(); i++) {
            //找到重合区间
            if (getSameArr(joao, maria, i) != null) {
                //利用贪心原理调整位置，调整失败返回false
                if (!adjustList(joao, maria, i)) return false;
            }
        }
        return false;
    }

    //从某个位置开始调整盘子位置
    private static boolean adjustList(List<Plate> joao, List<Plate> maria, int i) {
        return 1 == 1;
    }

    //获取重合的区间
    private static Plate[] getSameArr(List<Plate> joao, List<Plate> maria, int i) {
        return null;
    }

    //读取盘子数据
    private static List<Plate> getPlates(Scanner sc) {
        String price[] = sc.nextLine().split(" ");
        String weight[] = sc.nextLine().split(" ");
        List<Plate> arr = new ArrayList<Plate>(price.length);
        for (int i = 0; i < price.length; i++) {
            arr.add(new Plate(Integer.parseInt(weight[i]), Integer.parseInt(price[i])));
        }
        return arr;
    }
}

//比较器
class myComparator implements Comparator {
    public boolean flag = false;

    public myComparator(boolean flag) {
        this.flag = flag;
    }

    @Override
    public int compare(Object o, Object t1) {
        Plate p1 = (Plate) o;
        Plate p2 = (Plate) t1;
        if (p1.price != p2.price) {
            return p1.price - p2.price;
        } else {
            if (flag) {
                return p1.height - p2.height;
            } else {
                return p2.height - p1.height;
            }
        }
    }
}

//盘子类
class Plate {
    int height;
    int price;

    public Plate(int height, int price) {
        this.price = price;
        this.height = height;
    }
}