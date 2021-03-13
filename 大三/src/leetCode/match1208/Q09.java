package leetCode.match1208;

import java.util.Scanner;

public class Q09 {
	public static void main(String[] args) {
		int hang=3;
		int lie=4;
		int city[][]= {{9,3,3,1},{3,3,3,0},{0,0,0,0}};
		int waterHeight=10;
		Scanner sc=new Scanner(System.in);
		hang=sc.nextInt();
		lie=sc.nextInt();
		city=new int[hang][lie];
		for(int i=0;i<hang;i++) {
			for(int j=0;j<lie;j++) {
				city[i][j]=sc.nextInt();
			}
		}
		waterHeight=sc.nextInt();
		
		for(int i=1;i<=waterHeight;i++) {
			int total=0;
			for(int j=0;j<hang;j++) {
				for(int k=0;k<lie;k++) {
					if(city[j][k]>=i)		total+=i;
					else		total+=city[j][k];
				}
			}
			System.out.println(total);
		}
	}
}
