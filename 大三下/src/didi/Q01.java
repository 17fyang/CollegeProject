package didi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * ClassName: Q01
 * Description:
 * date: 2020/9/13 18:55
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            int arr[][] = new int[m][3];
            for (int i = 0; i < m; i++) {
                arr[i][0] = sc.nextInt();
                arr[i][1] = sc.nextInt();
                arr[i][2] = sc.nextInt();
            }
            Queue<Integer> queue = new LinkedList<>();
            boolean flag[] = new boolean[n + 1];
            queue.add(1);
            flag[0] = true;
            while (!queue.isEmpty()) {
                int length = queue.size();
                for (int i = 0; i < length; i++) {
                    int dao = queue.poll();
                    flag[dao] = true;
                    for (int j = 0; j < arr.length; j++) {
                        if (arr[j][2] <= k && arr[j][0] == dao && !flag[arr[j][1]]) {
                            queue.add(arr[j][1]);
                        } else if (arr[j][2] <= k && arr[j][1] == dao && !flag[arr[j][0]]) {
                            queue.add(arr[j][0]);
                        }
                    }
                }
            }
            boolean result = true;
            for (int i = 1; i < flag.length; i++) {
                if (!flag[i]) result = false;
            }
            if (result) System.out.println("Yes");
            else System.out.println("No");
        }

        sc.close();
    }
}
