package fall.bytedance.traning;

import java.util.Scanner;

/**
 * ClassName: Q01
 * Description:
 * date: 2020/8/9 12:33
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) arr[i] = sc.nextInt() - 1;
        System.out.println(solution(arr));
    }

    public static long solution(int[] arr) {
        long[] dp = new long[arr.length + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] * 2 % 1000000007 + 2 - dp[arr[i - 1]];
            dp[i] %= 1000000007;
        }
        return dp[dp.length - 1];
    }
}
