package leetCode.match1208;

import java.util.Scanner;

public class Q10 {
	public static void main(String[] args) {
		int n=5;
		int m=3;
		Scanner sc=new  Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		int value[]=new int[n];
		for(int i=0;i<n;i++) 		value[i]=sc.nextInt();
		int lastLocate=-1;
		for(int i=0;i<m;i++) {
			if(i!=0)	System.out.print(" ");
			lastLocate=max(value,lastLocate+1,m-i-1);
			System.out.print(value[lastLocate]);
		}
		
	}
	
	public static int max(int[] value,int startPoint,int number) {
		int maxLocate=startPoint;
		for(int i=startPoint;i<value.length-number;i++) {
			if(value[i]>value[maxLocate])	maxLocate=i;
		}
		return maxLocate;
	}
}
