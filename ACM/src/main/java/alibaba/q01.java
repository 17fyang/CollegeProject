package alibaba;

import java.util.Scanner;

/*
 * 锟斤拷锟斤拷锟斤拷缘锟揭伙拷锟�
 * date:2020.3.25
 * author:yf
 */
public class q01 {
    private static long min = -1;

    public static void main(String[] args) {
        solution2();
    }

    public static void solution2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double average = 0;
        int sum = 0;
        int[][] arr = new int[3][n];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
                average += (arr[i][j] - average) / (sum + 1);
                sum++;
            }
        }
        func(arr, 0, 1, arr[0][0], 0);
        func(arr, 1, 1, arr[1][0], 0);
        func(arr, 2, 1, arr[2][0], 0);
        System.out.println(min);
    }

    public static void func(int[][] arr, int i, int j, int before, long total) {
        total += Math.abs(arr[i][j] - before);

        if (j == arr[0].length - 1) {
            if (min == -1 || total < min) min = total;
            return;
        }
        func(arr, 0, j + 1, arr[i][j], total);
        func(arr, 1, j + 1, arr[i][j], total);
        func(arr, 2, j + 1, arr[i][j], total);
    }

    //锟斤拷锟斤拷
    public static void solution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double average = 0;
        int sum = 0;
        int arr[][] = new int[3][n];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
                average += (arr[i][j] - average) / (sum + 1);
                sum++;
            }
        }

        int newArr[] = new int[n];
        for (int i = 0; i < n; i++) {
            double num0 = Math.abs(arr[0][i] - average);
            double num1 = Math.abs(arr[1][i] - average);
            double num2 = Math.abs(arr[2][i] - average);
            int locate = 0;
            if (num0 > num1) locate = 1;
            if (num2 > num1 && num2 > num1) locate = 2;
            newArr[i] = arr[locate][i];
        }

        long totalSum = 0;
        for (int i = 1; i < n; i++) {
            totalSum += Math.abs(newArr[i] - newArr[i - 1]);
        }
        System.out.println(totalSum);
        sc.close();
    }
}
