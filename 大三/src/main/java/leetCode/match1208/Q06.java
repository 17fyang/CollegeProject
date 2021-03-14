package leetCode.match1208;

import java.util.Scanner;

public class Q06 {
	public static  int number=0;
	public static void main(String[] args) {
		int n=6;
		int input[]= {3,1,5,2,3,5};
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		input=new int[n];
		for(int i=0;i<n;i++) {
			input[i]=sc.nextInt();
		}
		
		for(int i=0;i<input.length;i++) {
			for(int j=i+1;j<input.length;j++) {
				if(input[j]<input[i])	number++;
			}
		}
		
		System.out.println(number);
	}
}
