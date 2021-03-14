package fall.shenxinfu;

import java.util.Arrays;

/**
 * ClassName: Q03
 * Description:
 * date: 2020/10/11 19:30
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q03 {
    public static void main(String[] args) {


    }

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int total = 0;
        for (int i = piles.length / 3; i < piles.length; i += 2) total += piles[i];
        return total;
    }
}
