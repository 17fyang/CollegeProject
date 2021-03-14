package fall.tencent;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName: Q1
 * Description:
 * date: 2020/9/6 19:51
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        long[] l1 = new long[n1];
        for (int i = 0; i < n1; i++) l1[i] = sc.nextLong();
        int n2 = sc.nextInt();
        long[] l2 = new long[n2];
        for (int i = 0; i < n2; i++) l2[i] = sc.nextLong();
        List<Long> list = new LinkedList<>();
        int i = 0;
        int j = 0;
        while (i < n1 && j < n2) {
            if (l1[i] > l2[j]) i++;
            else if (l1[i] < l2[j]) j++;
            else {
                list.add(l1[i]);
                i++;
                j++;
            }
        }
        for (long l : list) {
            System.out.print(l + " ");
        }
    }
}
