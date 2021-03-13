package fall.shunfeng;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ClassName: Q01
 * Description:
 * date: 2020/8/29 15:30
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

            int total = 1;
            int flag[] = new int[n];
            Arrays.fill(flag, -1);
            for (int i = 1; i < n; i++) {
                if (arr[i] == -1 || arr[i - 1] == -1) {
                    flag[i] = flag[i - 1];
                } else if (flag[i - 1] == -1 || arr[i] - arr[i - 1] == flag[i - 1]) {
                    flag[i] = arr[i] - arr[i - 1];
                } else {
                    total++;
                }
            }
            System.out.println(total);
        }
        sc.close();
    }
}
