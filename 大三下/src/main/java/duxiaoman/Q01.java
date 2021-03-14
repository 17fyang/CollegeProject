package duxiaoman;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ClassName: Q01
 * Description:
 * date: 2020/9/20 20:00
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ming = sc.next();
        String color = sc.next();
        char[] mings = ming.toCharArray();
        char[] colors = color.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : colors) {
            if (!map.containsKey(c)) map.put(c, 0);
            map.put(c, map.get(c) + 1);
        }

        int total = 0;
        for (char m : mings) {
            Integer num = map.get(m);
            if (num == null) continue;
            total++;
            if (num == 1) map.remove(m);
            else map.put(m, num - 1);
        }
        System.out.println(total);


        sc.close();
    }
}
