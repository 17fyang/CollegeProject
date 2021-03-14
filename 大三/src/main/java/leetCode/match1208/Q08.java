package leetCode.match1208;

import java.util.Scanner;

public class Q08 {
	public static int sum=0;
	public static void main(String[] args) {
		int n=8;
		int input[]= {9,1,3,5,2,7,6,3};
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		input=new int[n];
		for(int i=0;i<n;i++) {
			input[i]=sc.nextInt();
		}
		for(int i=1;i<input.length;i++) {
			for(int j=i-1;j>=0;j--) {
				if(input[j]>input[i]) {
					sum+=i-j;
					break;
				}
			}
		}
		
		System.out.println(sum);
	}
}
