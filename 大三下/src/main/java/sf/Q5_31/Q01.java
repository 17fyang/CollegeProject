package sf.Q5_31;

import java.util.Scanner;

/**
 * ClassName: Q01
 * Description:
 * date: 2020/5/31 19:06
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q01 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String s=sc.next();
            char c[]=s.toCharArray();
            char max=0;
            for(int i=0;i<c.length;i++){
                if(c[i]>max)    max=c[i];
            }
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<c.length;i++){
                sb.append(c[i]);
                if(c[i]==max)   sb.append("(max)");
            }
            System.out.println(sb.toString());
        }
        sc.close();
    }
}
