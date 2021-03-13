package øÏ ÷;

import java.util.Scanner;

public class Q01 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char c[]=s.toCharArray();
		int left=0;
		int right=0;
		int match=0;
		for(int i=0;i<c.length;i++) {
			if(c[i]=='(') {
				left++;
			}else if(c[i]==')'){
				if(left!=0) {
					match++;
					left--;
				}else {
					right++;
				}
			}
		}
		System.out.println(match+" "+left+" "+right);
		
		sc.close();
	}
}
