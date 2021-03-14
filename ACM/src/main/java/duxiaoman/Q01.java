package duxiaoman;

import java.util.Scanner;

public class Q01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        int[] money = new int[n + 1];
		
        int result = cost(arr, money, n, a, b, c);
        System.out.println(result);
        sc.close();
    }

    private static int cost(int[] arr, int money[], int n, int a, int b, int c) {
        for (int i = arr.length - 2; i > 0; i--) {
            int min = getMin(arr, money, i, a, b, c);
            money[i] = min;
        }
        for (int i = arr.length - 2; i > 0; i--) {
            int min = getMin2(arr, money, i, a, b, c);
            money[i] = min;
        }
        for (int i = arr.length - 2; i > 0; i--) {
            int min = getMin2(arr, money, i, a, b, c);
            money[i] = min;
        }
        return money[1];
    }

    private static int getMin2(int[] arr, int[] money, int i, int a, int b, int c) {
        int case1 = a + c * (arr.length - 1 - arr[i]);
        int case2 = Integer.MAX_VALUE;
        for (int j = 1; j < arr.length; j++) {
            int jump = 0;
            if (arr[i] >= j) jump = b * (arr[i] - j) + a;
            else jump = c * (j - arr[i]) + a;
            if (jump + money[j] < case2) case2 = jump + money[j];
        }
        return Math.min(case1, case2);
    }


    private static int getMin(int[] arr, int[] money, int i, int a, int b, int c) {
        int case1 = a + c * (arr.length - 1 - arr[i]);
        int case2 = Integer.MAX_VALUE;
        for (int j = i + 1; j < arr.length; j++) {
            int jump = 0;
            if (arr[i] >= j) jump = b * (arr[i] - j) + a;
            else jump = c * (j - arr[i]) + a;
            if (jump + money[j] < case2) case2 = jump + money[j];
        }
        return Math.min(case1, case2);
    }
}
