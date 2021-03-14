package meituan;

import java.util.Scanner;

/*
 * 有一个很经典的问题是，当前时间是aa:bb,请问若干分钟后是什么时间？
 * 我们今天的问题是一个相反的问题。
 * 已知现在的时刻是星期x的yy:zz时刻，请问n分钟前是周几，时间是多少。
 * 例如现在是周三，02:10,则200分钟之前，应该是周二，22:50。
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
