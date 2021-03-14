package webank;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * 
 */
public class Q03 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		List<Integer> list=new LinkedList<Integer>();
		int times=1;
		int totalMoney=0;
		for(int i=0;i<num;i++) {
			int money=sc.nextInt();
			int time=sc.nextInt();
			if(time>0)	{
				times+=time-1;
				totalMoney+=money;
			}else {
				list.add(money);
			}
		}
		if(!list.isEmpty()) {
			Collections.sort(list);
			for(int i=0;i<times;i++) {
				totalMoney+=list.get(list.size()-1-i);
			}
		}
		System.out.println(totalMoney);
		sc.close();
	}

}
