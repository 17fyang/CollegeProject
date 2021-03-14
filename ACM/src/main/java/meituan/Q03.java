package meituan;

import java.util.Scanner;

public class Q03 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		System.out.println(solution(n,k));
		sc.close();
	}
	public static int solution(int total,int k) {
		if(k==1)	return 1;
		int i=total/2;
		int j=total;
		while(i<j) {
			int mid=(j+i)/2+1;
			boolean flag=isPass(mid,total,k);
			if(flag)		j=mid-1;
			else i=mid+1;
		}
		return i;
	}
	private static boolean isPass(int min,int total,int k) {
		int j=0;
		int t=total;
		boolean flag=false;
		while(t>0) {
			int downNumber=(int)Math.pow(k, j++);
			if(downNumber>min)	{
				flag=true;
				break;
			}
			t-=min/downNumber;
		}
		return !flag;
	}
}
