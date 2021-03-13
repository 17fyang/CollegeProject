package fall.tencent;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ClassName: Q14
 * Description:
 * date: 2020/9/6 19:51
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) arr1[i] = arr2[i] = sc.nextInt();

        Arrays.sort(arr2);

        int n1 = arr2[(n / 2) - 1];
        int n2 = arr2[n / 2];

        for (int i : arr1) {
            if (i <= n1) System.out.println(n2);
            if (i >= n2) System.out.println(n1);
        }
        sc.close();
    }
}
