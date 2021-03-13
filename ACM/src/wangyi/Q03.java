package wangyi;

import java.util.Scanner;

public class Q03 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int job=sc.nextInt();
		int player=sc.nextInt();
		char[][] name=new char[job][];
		for(int i=0;i<job;i++) {
			name[i]=sc.next().toCharArray();
		}
	}
}
