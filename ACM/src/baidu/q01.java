package baidu;

import java.util.Scanner;

public class q01 {
	public static void main(String[] args) {
		solution();
	}
	private static void solution() {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int a[]=new int[n];
		int b[]=new int[n];
		
//		for(int i=0;i<n;i++) 		a[i]=sc.nextInt();
//		for(int i=0;i<n;i++) 		b[i]=sc.nextInt();
		
		n=5;
		m=5;
		int newA[]= {10,20,30,40,50};
		int newB[]= {4,5,6,7,8};
		a=newA;
		b=newB;
		
		int next[][]=new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				next[i][j]=a[i]-b[i]*j;
			}
		}
		
		int total=1;
		for(int i=0;i<m;i++) {
			total=total*(n-i);
		}
		int result[]=new int[total];
		
		int index=0;
		while(index<m) {
			index++;
			for(int j=0;j<index;j++) {
				for(int i=0;i<n;i++) {
					result[index*i]=next[index][i];
				}
			}
			
			System.out.println("sd");
		}
	
		int max=result[0];
		for(int i=0;i<result.length;i++) {
			if(result[i]>max)	max=result[i];
		}
		System.out.println(max);
	}
}
