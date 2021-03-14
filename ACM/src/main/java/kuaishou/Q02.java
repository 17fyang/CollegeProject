package kuaishou;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Q02 {
    public static void main(String[] args) {
        int arr[] = GetPowerFactor(27, 3);

        for (int i = 0; i < arr.length; i++) System.out.println(arr[i]);
    }

    public static int[] GetPowerFactor(int R, int N) {
        if (N == 1 && R != 1) return new int[0];
        else if (N == 1) return new int[1];
        int max = 0;
        while (Math.pow(N, max) <= R) max++;
        int num = R;
        List<Integer> list = new LinkedList<Integer>();
        for (int i = max - 1; i >= 0; i--) {
            int value = (int) Math.pow(N, i);
            if (num >= value) {
                num -= value;
                list.add(i);
            }
        }
        if (num != 0) return new int[0];
        Collections.sort(list);
        int arr[] = new int[list.size()];
        for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
        return arr;
    }
}
