package webank;

import java.util.Scanner;

/*
 * ��nλС����ȥС��������꣬С��׼����m�����
 * С�������������ƽ���ָ�ÿ��С���ѣ�ÿ��С���ѵõ���ͬ���������
 * ����mδ���ܱ�n������С������ʹ���������ֲ��������Σ����ֲ�������ͬʱʹ�ã���
 * 1�� ������һ��С���ѷ�������յ������С���ѻ��뿪С���ҡ�ÿ�������Ҫ����aԪ��
 * 2�� ����һ�������ÿ�������ֵΪbԪ��
 * ��С�����ٻ��Ѷ���Ԫ������ʹ������������Ա�ʣ�µ�С����ƽ�֡�
 */
public class Q01 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int a=sc.nextInt();
		int b=sc.nextInt();
		int result=cost(n,m,a,b);
		System.out.println(result);
	}
	public static int cost(int n,int m,int a,int b) {
		int min=Integer.MAX_VALUE;
		if(m%n==0)	min=0;
		int i=0;
		int j=0;
		for(i=0;i<=(n*(m/n+1)-(m%n));i++) {
			for(j=n;j>=1;j--) {
				if((i+m)%j==0) {
					int value=i*b+(n-j)*a;
					if(value<min)	min=value;
				}
			}
		}
		return min;
	}
}
