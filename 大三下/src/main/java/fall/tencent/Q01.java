package fall.tencent;

import java.util.Scanner;

/**
 * ClassName: Q01
 * Description:
 * date: 2020/8/23 19:59
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine();
        String arr[] = line.split(" ");
        for (String s : arr) {
            if (--k != 0) {
                System.out.print(s + " ");
            }
        }
        sc.close();
    }
}
