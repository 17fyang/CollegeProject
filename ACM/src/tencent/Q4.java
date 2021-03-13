package tencent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q4 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Queue<Integer> queue=new LinkedList<Integer>();
		int n=sc.nextInt();
		for(int i=0;i<n;i++) {
			String operation=sc.next();
			if(operation.equals("add")) {
				int value=sc.nextInt();
				queue.add(value);
			}else if(operation.equals("poll")) {
				queue.poll();
			}else
				System.out.println(queue.peek());
		}
		
		sc.close();
	}
}
