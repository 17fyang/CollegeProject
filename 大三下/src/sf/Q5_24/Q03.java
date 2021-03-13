package sf.Q5_24;

import java.math.BigDecimal;
import java.util.Scanner;

public class Q03 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            String result = caculate(n);
            System.out.println(result);
        }
    }
    private static String caculate(int n){
        if(n==0)    return "1";
        BigDecimal sum=new BigDecimal(1);
        for(int i=1;i<=n;i++){
            sum=sum.multiply(new BigDecimal(i));
        }
        return sum.toString();
    }
}
