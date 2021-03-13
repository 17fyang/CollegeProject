package webank;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * Cassidy��Eleanore��һ�Ժ����ѣ����Ǿ�����һ������Ϸ��
 * ĳһ����������һ��������Ϸ����Ϸ�����������ģ�
 * ����һ��������Сд��ĸ���ַ���S����ÿһ���˵Ļغ��У����ǻ��������������
 * 1. ����������������ַ�����������԰�����ַ������гɻ����ַ�������������ʤ��
 * 2. �������Ǳ���ɾ���ַ����е�һ���ַ���
 * ��֪��Cassidy���֣��������˶���ȡ��Ѳ��Ե�����£�˭���Ի�ʤ��
 */
public class Q02 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		for(int i=0;i<num;i++) {
			System.out.println(func(sc.next().toCharArray()));
		}
		sc.close();
	}
	public static String func(char c[]) {
		char compareChar[]=new char[c.length];
		int compareLength=0;
		for(int i=0;i<c.length;i++) {
			if(remove(compareChar,compareLength,c[i]))	compareLength--;
			else		compareChar[compareLength++]=c[i];
		}
		if(compareLength%2==1)	return "Cassidy";
		else	return "Eleanore";
	}

	private static boolean remove(char[] arr,int compareLength,char c) {
		boolean flag=false;
		for(int i=0;i<compareLength;i++) {
			if(arr[i]==c) {
				flag=true;
				for(int j=i;j<compareLength-1;j++) 	arr[j]=arr[j+1];
				arr[compareLength-2]=' ';
				break;
			}
		}
		return flag;
	}
}
