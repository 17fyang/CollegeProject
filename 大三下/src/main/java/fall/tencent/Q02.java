package fall.tencent;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * ClassName: Q02
 * Description:
 * date: 2020/8/23 19:59
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int k = sc.nextInt();
        if (str.length() == 1) System.out.println(str);
        Set<String> treeSet = new TreeSet<>();

        char arr[] = str.toCharArray();
        int start = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                add(treeSet, new String(str.substring(start, i)));
                start = i;
            }
        }
        add(treeSet, str.substring(start, arr.length));

        for (String s : treeSet) {
            if (--k == 0) {
                System.out.println(s);
                break;
            }
        }
        sc.close();
    }

    public static void add(Set<String> set, String s) {
        if ("".equals(s)) return;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                set.add(s.substring(i, j));
            }
        }

//        int n = 5;
//        if (set.size() > 5) {
//            Iterator<String> it = set.iterator();
//            while (it.hasNext()) {
//                if (n-- < 0) {
//                    it.remove();
//                }
//            }
//        }
    }
}
