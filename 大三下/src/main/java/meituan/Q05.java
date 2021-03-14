package meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ClassName: Q05
 * Description:
 * date: 2020/9/13 11:27
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Thing arr[] = new Thing[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = new Thing(i, sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(arr, (t1, t2) -> {
            if (t1.Necessary != t2.Necessary) return t2.Necessary - t1.Necessary;
            else if (t1.first != t2.first) return t2.first - t1.first;
            else return t1.id - t2.id;
        });
        for (int i = 0; i < n; i++) {
            if (i == 0) System.out.print(arr[0].id);
            else System.out.print(" " + arr[i].id);
        }
        sc.close();
    }
}

class Thing {
    int id = 0;
    int first = 0;
    int Necessary = 0;

    public Thing(int id, int first, int necessary) {
        this.id = id;
        this.first = first;
        Necessary = necessary;
    }
}
