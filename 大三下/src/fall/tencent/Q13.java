package fall.tencent;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * ClassName: Q13
 * Description:
 * date: 2020/9/6 19:51
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            Integer num = map.get(s);
            num = num == null ? 0 : num;
            map.put(s, num + 1);
        }


        TreeSet<Node> set = new TreeSet<Node>((n1, n2) -> {
            if (n1.num != n2.num) return n2.num - n1.num;
            else return n1.s.compareTo(n2.s);
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            set.add(new Node(entry.getKey(), entry.getValue()));
        }

        int i = 0;
        for (Node node : set) {
            if (i++ < k)
                System.out.println(node.s + " " + node.num);
            else
                break;
        }


        i = 0;
        int num = 0;
        for (Node node : set) {
            if (i++ == set.size() - k) {
                num = node.num;
                break;
            }
        }

        i = 0;
        for (Node node : set) {
            if (node.num <= num && i++ < k) {
                System.out.println(node.s + " " + node.num);
            }
        }


        sc.close();
    }
}

class Node {
    String s;
    int num;

    public Node(String s, int num) {
        this.s = s;
        this.num = num;
    }
}
