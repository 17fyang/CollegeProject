package didi;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * ClassName: Q02
 * Description:
 * date: 2020/9/13 18:55
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int arr[][] = new int[m][3];
        for (int i = 0; i < m; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        int home = sc.nextInt();
        int pari = sc.nextInt();
        String start = sc.next();
        String temp[] = start.split("\\.");
        int month = Integer.parseInt(temp[0]);
        temp = temp[1].split("/");
        int day = Integer.parseInt(temp[0]);
        int hour = Integer.parseInt(temp[1]);

        LocalDateTime startTime = LocalDateTime.of(2020, month, day, hour, 0);

        int cost = getMin(arr, n, pari, home);
        LocalDateTime endTime = startTime.plusHours(cost);
        System.out.println(endTime.getMonthValue() + "." + endTime.getDayOfMonth() + "/" + endTime.getHour());
        sc.close();
    }

    public static int getMin(int[][] arr, int n, int pari, int home) {
        int[] cost = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(home);

        while (!queue.isEmpty()) {
            int city = queue.poll();
            for (int j = 0; j < arr.length; j++) {
                int aim = arr[j][0] == city ? arr[j][1] : -1;
                if (aim == -1) aim = ((aim == -1) && (arr[j][1] == city)) ? arr[j][0] : -1;
                if (aim != -1) {
                    int temp = cost[city] + arr[j][2];
                    if (cost[aim] == 0 || temp < cost[aim]) {
                        queue.add(aim);
                        cost[aim] = temp;
                    }
                }
            }

        }
        return cost[pari];
    }
}
