package sf.Q5_24;

import java.util.Scanner;

public class Q04 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        while(sc.hasNext()){
            long n=sc.nextLong();
            String result=solution(n);
            System.out.println(result);
        }
    }

    private static String solution(long n) {
        n=n%10000;
        long sum=(1+n)*n/2;
        sum=sum%10000;
        sum=(sum*sum)%10000;

        StringBuilder  sb=new StringBuilder();
        sb.append(sum/1000);
        sum=sum%1000;
        sb.append(sum/100);
        sum=sum%100;
        sb.append(sum/10);
        sum=sum%10;
        sb.append(sum);
        return sb.toString();
    }
}
