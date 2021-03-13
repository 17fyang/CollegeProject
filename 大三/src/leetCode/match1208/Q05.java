package leetCode.match1208;

import java.util.Scanner;

public class Q05 {
	public static  int max=0;
	public static boolean carryOn=false;
	public static void main(String[] args) {
		int n=7;
		int input[]= {5,2,4,1,3,7,2};
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		input=new int[n];
		for(int i=0;i<n;i++) {
			input[i]=sc.nextInt();
		}
		int maxTemp=0;
		for(int i=0;i<input.length-1;i++) {
			if(input[i+1]>input[i]) {
				carryOn=true;
				maxTemp++;
				if(maxTemp>max)	max=maxTemp;
			}else {
				maxTemp=0;
				carryOn=false;
			}
		}
		System.out.println(max+1);
	}
}
