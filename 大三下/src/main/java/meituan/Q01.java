package meituan;

import java.util.Scanner;

/**
 * ClassName: Q01
 * Description:
 * date: 2020/9/13 10:10
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(new Q01().solution(n, m, k, arr));


        sc.close();
    }

    public int solution(int n, int m, int k, int[] arr) {
        int left = 0;
        int right = m - 1;
        if (right > arr.length) return 0;

        int sum = 0;
        int no = 0;
        for (int i = 0; i <= right; i++) {
            if (arr[i] < k) no++;
        }
        if (no == 0) sum++;

        while (right + 1 < arr.length) {
            if (arr[right + 1] < k) no++;
            if (arr[left] < k) no--;
            if (no == 0) sum++;
            right++;
            left++;
        }
        return sum;

    }
}
