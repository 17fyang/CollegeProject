package duxiaoman;

import java.util.Scanner;

public class Q02 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int a=sc.nextInt();
		int b=sc.nextInt();
		int result=maxPool(n,m,a,b);
		System.out.println(result);
		sc.close();
	}

	private static int maxPool(int n, int m, int a, int b) {
		int arr[][]=new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j]=(i+1)*(j+1)%10;
			}
		}
		int maxValue[][]=new int[n-a+1][m-b+1];
		for(int i=0;i<m-b+1;i++) {
			maxValue[0][i]=getMax(arr,0,i,a,b);
		}
		for(int i=1;i<n-a+1;i++) {
			for(int j=0;j<m-b+1;j++) {
				maxValue[i][j]=getMax2(arr,maxValue,i,j,a,b);
			}
		}
		int sum=0;
		for(int i=0;i<maxValue.length;i++) {
			for(int j=0;j<maxValue[0].length;j++) {
				sum+=maxValue[i][j];
			}
		}
		return sum;
	}
	private static int getMax2(int[][] arr,int money[][], int n, int m, int a, int b) {
		int case1=0;
		int case2=0;
		for(int j=m;j<m+b;j++) {
			if(arr[n-1][j]>case1)	case1=arr[n-1][j];
			if(arr[n+a-1][j]>case2)	case2=arr[n+a-1][j];
		}
		return Math.max(money[n-1][m], Math.max(case2, case1));
	}

	private static int getMax(int arr[][],int n,int m,int a,int b) {
		int max=0;
		for(int i=n;i<n+a;i++) {
			for(int j=m;j<m+b;j++) {
				if(arr[i][j]>max)	max=arr[i][j];
			}
		}
		return max;
	}
}
