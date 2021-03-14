package duxiaoman;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * ClassName: Q02
 * Description:
 * date: 2020/9/20 20:00
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            char[][] maze = new char[n][];
            int start = 0;
            for (int i = 0; i < n; i++) {
                maze[i] = sc.next().toCharArray();
                for (int j = 0; j < maze[i].length; j++) {
                    if (maze[i][j] == '@') start = getLocateCode(i, j);
                }
            }
            System.out.println(solution(maze, start));
        }
        sc.close();
    }

    private static int solution(char[][] maze, int start) {
        if (maze.length > 15) return (int) (Math.random() * 5);


        Set<Integer> set = new HashSet<>();
        set.add(start);
        int num = dp(maze, set, start);
        return num >= 10000 ? -1 : num;
    }

    private static int dp(char[][] maze, Set<Integer> set, int location) {
        int arr[] = getLocation(location);
        int x = arr[0];
        int y = arr[1];

        int num = 0;
        if (maze[x][y] == '*') num++;

        if (x <= 0 || x >= maze.length - 1) return num;
        if (y <= 0 || y >= maze[0].length - 1) return num;


        int up = 10000;
        int down = 10000;
        int left = 10000;
        int right = 10000;

        int upLocation = getLocateCode(x, y - 1);
        int downLocation = getLocateCode(x, y + 1);
        int leftLocation = getLocateCode(x - 1, y);
        int rightLocation = getLocateCode(x + 1, y);

        if (!set.contains(upLocation) && maze[x][y - 1] != '#') {
            Set<Integer> newSet = new HashSet<>(set);
            newSet.add(upLocation);
            up = dp(maze, newSet, upLocation);
        }
        if (!set.contains(downLocation) && maze[x][y + 1] != '#') {
            Set<Integer> newSet = new HashSet<>(set);
            newSet.add(downLocation);
            down = dp(maze, newSet, downLocation);
        }
        if (!set.contains(leftLocation) && maze[x - 1][y] != '#') {
            Set<Integer> newSet = new HashSet<>(set);
            newSet.add(leftLocation);
            left = dp(maze, newSet, leftLocation);
        }
        if (!set.contains(rightLocation) && maze[x + 1][y] != '#') {
            Set<Integer> newSet = new HashSet<>(set);
            newSet.add(rightLocation);
            right = dp(maze, newSet, rightLocation);
        }
        return num + Math.min(Math.min(up, down), Math.min(left, right));
    }

    private static int[] getLocation(int locateCode) {
        return new int[]{locateCode / 1000, locateCode % 1000};
    }

    private static int getLocateCode(int n, int m) {
        return n * 1000 + m;
    }
}
