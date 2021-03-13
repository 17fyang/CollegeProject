package meituan;

import java.util.Scanner;

/*
 * ��һ���ܾ���������ǣ���ǰʱ����aa:bb,�������ɷ��Ӻ���ʲôʱ�䣿
 * ���ǽ����������һ���෴�����⡣
 * ��֪���ڵ�ʱ��������x��yy:zzʱ�̣�����n����ǰ���ܼ���ʱ���Ƕ��١�
 * ����������������02:10,��200����֮ǰ��Ӧ�����ܶ���22:50��
 */
public class Q01 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int week=sc.nextInt();
		String time=sc.next();
		int n=sc.nextInt();
		solution(week,time,n);
		sc.close();
	}
	public static void solution(int week,String time,int n) {
		String arr[]=time.split(":");
		int hour=Integer.parseInt(arr[0]);
		int minite=Integer.parseInt(arr[1]);
		
		int addHour=n/60;
		int addMinite=n%60;
		
		hour-=addHour;
		minite-=addMinite;
		
		if(minite<0) {
			hour--;
			minite+=60;
		}
		
		while(hour<0) {
			hour+=24;
			week--;
			if(week==0)	week=7;
		}
		System.out.println(week);
		StringBuilder sb=new  StringBuilder();
		if(hour<10)	sb.append("0");
		sb.append(hour+":");
		if(minite<10)		sb.append("0");
		sb.append(minite);
		System.out.println(sb);
		
	}
}
