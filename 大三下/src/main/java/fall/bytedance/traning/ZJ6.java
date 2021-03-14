package fall.bytedance.traning;

import java.util.Scanner;

/**
 * ClassName: ZJ6
 * Description:
 * date: 2020/8/9 16:16
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class ZJ6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < arr.length; i++) arr[i] = sc.nextInt();
        long[] newArr = solution(n, x, arr);
        System.out.print(newArr[0]);
        for (int i = 1; i < newArr.length; i++) System.out.print(" " + newArr[i]);
    }

    public static long[] solution(int n, int x, long[] arr) {
        long min = Long.MAX_VALUE;
        int minLocation = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < min) {
                min = arr[i];
                minLocation = i;
            }

        long[] newArr = new long[arr.length];
        long total = min * arr.length;
        for (int k = 0; k < arr.length; k++) {
            newArr[k] = arr[k] - min;
        }
        int location = minLocation;
        while (location != x - 1) {
            location++;
            if (location >= arr.length) location = 0;
            total++;
            newArr[location] -= 1;
        }
        newArr[minLocation] = total;
        return newArr;

    }
}
