package meituan;

import java.util.Scanner;

/**
 * ClassName: Q03
 * Description:
 * date: 2020/9/13 11:05
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int d = sc.nextInt();
        System.out.println(solution(n, k, d));
        sc.close();
    }

    private static long solution(int n, int k, int d) {
        long[] arr = new long[n + 1];
        arr[d] = 1;
        arr[d + 1] = 1;
        for (int i = d + 2; i <= n; i++) {
            for (int j = 1; j < k; j++) {
                arr[i] += arr[i - j];
                arr[i] %= 998244353;
            }
        }
        return 0;
    }
}
