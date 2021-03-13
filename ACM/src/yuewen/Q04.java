package yuewen;

import java.util.Scanner;

public class Q04 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Person p[]=new Person[n];
		for(int i=0;i<n;i++)	p[i]=new Person();
		int arr[][]=new int[n][n];
		for(int i=0;i<n;i++) {
			String s=sc.next();
			String split[]=s.split(",");
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(split[j]);
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				if(arr[i][j]==1)	p[j].next=p[i];
			}
		}
		int sum=0;
		for(int i=0;i<n;i++) {
			if(p[i].next==null)	sum++;
		}
		System.out.println(sum);
		sc.close();
	}
}
class Person{
	Person next=null;
}
