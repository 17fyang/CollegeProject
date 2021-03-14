package sf.Q5_31;

import java.util.Scanner;

/**
 * ClassName: Q3
 * Description:
 * date: 2020/5/31 19:30
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q03 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long ans[]=new long[21];
        ans[1] = 0;
        ans[2] = 1;
        for (int i = 3; i < 21; i++)
            ans[i] = ans[i - 1] * (i - 1) + ans[i - 2] * (i - 1);
        while(sc.hasNext()){
            int n=sc.nextInt();
            System.out.println(ans[n]);
        }
        sc.close();
    }
}
