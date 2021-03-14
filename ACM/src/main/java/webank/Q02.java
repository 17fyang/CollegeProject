package webank;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * Cassidy和Eleanore是一对好朋友，她们经常会一起玩游戏。
 * 某一天她们玩了一个回文游戏。游戏规则是这样的：
 * 给出一个仅包含小写字母的字符串S，在每一个人的回合中，她们会进行两个操作：
 * 1. 尝试重新排列这个字符串，如果可以把这个字符串排列成回文字符串，则立即获胜。
 * 2. 否则，她们必须删掉字符串中的一个字符。
 * 已知，Cassidy先手，在两个人都采取最佳策略的情况下，谁可以获胜。
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
