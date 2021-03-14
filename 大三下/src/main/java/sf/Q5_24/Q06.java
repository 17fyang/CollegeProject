package sf.Q5_24;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q06 {
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
        List<Integer> list=new ArrayList<Integer>();
        int i=Integer.parseInt(String.valueOf(value%10));
        int intValue=i;
        int startLocation=0;

        while(true){
            if(list.contains(intValue)){
                startLocation=list.indexOf(intValue);
                break;
            }
            list.add(intValue);
            intValue=intValue*i%10;
        }
        value-=startLocation;
        int length=list.size()-startLocation;
        int location=Integer.parseInt(String.valueOf((value-1)%length));
        return list.get(startLocation+location);
    }
}
