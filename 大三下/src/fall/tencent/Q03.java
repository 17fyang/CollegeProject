package fall.tencent;

import java.util.Scanner;

/**
 * ClassName: Q03
 * Description:
 * date: 2020/8/23 20:42
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
//        int arr[] = new int[];
        while (t-- > 0) {
            long n = sc.nextLong();
            long temp = n;
            for (int i = 1; i < 12; i++) {
                long k = (n / (long) Math.pow(10, i)) - 1;
                if (k < 0) break;
                if (n / (long) Math.pow(10, i - 1) % 10 == 9) {
                    k++;
                }
                temp -= k * 9;
            }
            System.out.println(temp);

//            long max = 1;
//            for (long i = 0; i < (n / 2); i++) {
//                long temp = add(i) + add(n - i);
//                max = Math.max(temp, max);
//            }
//            System.out.println(max);
        }
        sc.close();
    }

    public static long add(long n) {
        String s = String.valueOf(n);
        char arr[] = s.toCharArray();
        long value = 0;
        for (char c : arr) {
            value += c - 48;
        }
        return value;
    }
}
