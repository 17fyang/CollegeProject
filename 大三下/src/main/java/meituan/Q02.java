package meituan;

import java.util.Scanner;

/**
 * ClassName: Q02
 * Description:
 * date: 2020/9/13 10:27
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLine();
        }

        int length = solution(arr);
        for (int i = 0; i < length; i++)
            System.out.println(arr[i]);

        sc.close();
    }

    public static int solution(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            boolean flag = true;
            if (arr.length % i == 0) {
                for (int j = i; j < arr.length; j += i) {
                    if (!dengyu(arr, i, j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return i;
            }

        }
        return arr.length;
    }

    public static boolean dengyu(String[] arr, int length, int left) {
        int right = left + length;
        boolean flag = true;
        for (int i = 0; i < length; i++) {
            if (!arr[i].equals(arr[left + i])) {
                flag = false;
                break;
            }
        }
        if (flag) return true;
        flag = true;
        for (int i = 0; i < length; i++) {
            if (!arr[i].equals(arr[right - i - 1])) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
