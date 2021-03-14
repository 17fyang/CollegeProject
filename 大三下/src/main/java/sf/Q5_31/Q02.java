package sf.Q5_31;

import java.util.Scanner;

/**
 * ClassName: Q02
 * Description:
 * date: 2020/5/31 19:11
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q02 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int width=sc.nextInt();
            int height=sc.nextInt();

            System.out.print('+');
            for(int i=0;i<width;i++) System.out.print('-');
            System.out.println('+');
            for(int i=0;i<height;i++){
                System.out.print('|');
                for(int j=0;j<width;j++)System.out.print(' ');
                System.out.println('|');
            }
            System.out.print('+');
            for(int i=0;i<width;i++) System.out.print('-');
            System.out.println('+');
            System.out.println();
        }
        sc.close();
    }
}
