package sf.Q6_7;

import java.util.Scanner;

/**
 * ClassName: Q05
 * Description:
 * date: 2020/6/7 22:11
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q05 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        while(n-->0){
            String line=sc.nextLine();
            String arr[]=line.split(" ");
            for (int i=0;i<arr.length;i++){
                System.out.print(arr[i].substring(0,1).toUpperCase());
            }
            System.out.println();
        }
        sc.close();
    }
}
