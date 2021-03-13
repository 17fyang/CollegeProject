package qiniuyun;

import java.util.Scanner;
import java.util.Stack;

public class Q02 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		while(n-->0) {
			String s=sc.next();
			char[] c=s.toCharArray();
			System.out.println(solution(c));
		}
		sc.close();
	}

	private static String solution(char[] c) {
		if(c.length==1)	return "No";
		if(c.length==0)	return "Yes";
		int i=0;
		Stack<Integer> stack=new Stack<Integer>();
		while(i<c.length) {
//			if(!stack.isEmpty()) System.out.println("head:"+stack.peek()+"char:"+(int)c[i]);
			if(stack.isEmpty()) {
				stack.add((int)c[i]);
			}else if(stack.peek()==(int)c[i]) {
				stack.pop();
			}else {
				stack.add((int)c[i]);
			}
			i++;
		}
		if(stack.isEmpty())	return "Yes";
		return "No";
	}
}
