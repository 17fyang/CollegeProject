package sf.Q5_24;

import java.math.BigDecimal;
import java.util.Scanner;

public class Q05 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        while(n-->0){
            int value=sc.nextInt();
            long result=solution(value);
            System.out.println(result);
        }
    }

    private static int solution(long value) {
        double tmp = value * Math.log10((double) (value));
        double res = tmp - Math.floor(tmp);
        return (int)Math.pow(10,res);
    }
}
