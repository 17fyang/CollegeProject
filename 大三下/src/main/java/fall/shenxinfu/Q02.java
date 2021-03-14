package fall.shenxinfu;

/**
 * ClassName: Q02
 * Description:
 * date: 2020/10/11 19:14
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q02 {
    public static void main(String[] args) {
        System.out.println(new Q02().minDistance("horse", "rose"));
    }

    public int minDistance(String a, String b) {
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();

        int[][] dp = new int[aa.length + 1][bb.length + 1];
        for (int i = 1; i < dp[0].length; i++) dp[0][i] = i;
        for (int i = 1; i < dp.length; i++) dp[i][0] = i;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (aa[i - 1] == bb[j - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]) + 1;
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[0].length; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
