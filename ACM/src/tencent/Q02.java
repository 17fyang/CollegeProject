package tencent;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Q02 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int time=sc.nextInt();
		for(int t=0;t<time;t++) {
			int n=sc.nextInt();
			int arr[][]=new int[n][];
			for(int i=0;i<n;i++) {
				arr[i]=new int[2];
				arr[i][0]=sc.nextInt();
				arr[i][1]=sc.nextInt();
			}
			List<int[]> minList=new LinkedList<int[]>();
			double minDistance=Integer.MAX_VALUE;
			int x=sc.nextInt();
			int y=sc.nextInt();
			for(int i=0;i<n;i++) {
				double distance=distance(x,y,arr[i][0],arr[i][1]);
				if(distance<minDistance) {
					minDistance=distance;
					minList.add(arr[i]);
				}
			}
			for(int i=1;i<n;i++) {
				x=sc.nextInt();
				y=sc.nextInt();
				for(int[] location:minList) {
					double distance=distance(x,y,location[0],location[1]);
					if(distance<minDistance) {
						minDistance=distance;
					}
				}
			}


			System.out.println(String.format("%.3f",minDistance));
		}
		sc.close();
	}

	private static double distance(int x1,int y1,int x2,int y2) {
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
}
