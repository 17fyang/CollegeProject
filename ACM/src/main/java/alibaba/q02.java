package alibaba;

import java.util.Scanner;

/*
 * 阿里笔试第二题
 * date:2020.3.25
 * author:yf
 *
 */
public class q02 {
    //为什么你们题目中示例的2 3 6不是等差数列
    public static void main(String[] args) {
        solution();
    }

    public static void solution() {
        Scanner sc = new Scanner(System.in);
        int line = sc.nextInt();
        int row = sc.nextInt();
        int questionTimes = sc.nextInt();

        int[][] arr = new int[line][row];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                if (arr[i][j] == 0) {
                    int result = check(arr, i, j);
                    arr[i][j] = result;
                }
            }
        }

        for (int i = 0; i < questionTimes; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (arr[x][y] != 0) System.out.println(arr[x][y]);
            else System.out.println("Unknown");
        }
    }

    private static int check(int[][] arr, int i, int j) {
        boolean done = false;
        boolean temp = false;
        int d = 0;
        int startLocateX = 0;
        int startLocateY = 0;
        for (int k = 0; k < arr[i].length; k++) {
            if (arr[i][k] != 0 && !temp) {
                temp = true;
                startLocateX = i;
                startLocateY = k;
            } else if (arr[i][k] != 0) {
                d = (arr[i][k] - arr[i][startLocateY]) / (k - startLocateX);
                done = true;
                break;
            }
        }
        if (!done) {
            for (int k = 0; k < arr.length; k++) {
                if (arr[k][j] != 0 && !temp) {
                    temp = true;
                    startLocateX = k;
                    startLocateY = j;
                } else if (arr[i][k] != 0) {
                    d = (arr[k][j] - arr[startLocateX][j]) / (k - startLocateY);
                    done = true;
                    break;
                }
            }
        }
        if (startLocateX == i) arr[i][j] = arr[startLocateX][startLocateY] + d * (startLocateY - j);
        if (startLocateY == j) arr[i][j] = arr[startLocateX][startLocateY] + d * (startLocateX - i);
        return arr[i][j];
    }
}
