package tencent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q01 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int group=sc.nextInt();
		for(int time=0;time<group;time++) {
			int n=sc.nextInt();
			Queue<Integer> queue=new LinkedList<Integer>(); 
			for(int i=0;i<n;i++) {
				String operation=sc.next();
				if(operation.startsWith("PUSH")) {
					int value=sc.nextInt();
					queue.add(value);
				}else if(operation.startsWith("TOP")) {
					Integer value=queue.peek();
					if(value==null)		System.out.println(-1);
					else System.out.println(value);
				}else if(operation.startsWith("POP")) {
					if(queue.isEmpty())	System.out.println(-1);
					else		queue.poll();
				}else if(operation.startsWith("SIZE")) {
					System.out.println(queue.size());
				}else
					queue.clear();
			}
		}
		sc.close();
	}
}
