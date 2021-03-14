package leetCode.match1208;

import java.util.Scanner;

public class Q07 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s="lanqiao";
		s=sc.next();

		char[] c=s.toCharArray();
		char[] newChar=new char[c.length];
		for(int i=0;i<c.length;i++) {
			int k=(c[i]-97+3)%26+97;
			newChar[i]=(char)k;	
		}
		System.out.println(String.valueOf(newChar));
	}
}
