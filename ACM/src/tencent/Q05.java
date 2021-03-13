package tencent;

import java.math.BigDecimal;
import java.util.Scanner;

public class Q05 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for(int t=0;t<n;t++) {
			long son=sc.nextLong();
			int deep=sc.nextInt();
			System.out.println(question(son,deep));
		}
		sc.close();
	}
	private static long question(long son,int deep) {
		if(son<Math.pow(2, deep))	return -1;
		int sonDeep=(int)(Math.log(son)/Math.log(2));
		BigDecimal two=new BigDecimal(2);
		BigDecimal tempBig=new BigDecimal(son).subtract(two.pow(sonDeep));
		BigDecimal zi=two.pow(deep-1).multiply(tempBig);
		BigDecimal fatherLocation=zi.divide(two.pow(sonDeep));
		BigDecimal result=fatherLocation.add(two.pow(deep-1));
		return result.longValue();
	}
}
