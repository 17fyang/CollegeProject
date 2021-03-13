package wangyi;

import java.util.LinkedList;
import java.util.List;

public class Q04 {
	private static int max=1;
	public static void main(String[] args) {
		int arr[][]=new int[2][3];
		arr[0][0]=7;
		arr[0][1]=7;
		arr[0][2]=7;
		
		arr[1][0]=5;
		arr[1][1]=4;
		arr[1][2]=5;
		
		System.out.println(maxBoxes(arr));
	}
	public static  int maxBoxes (int[][] boxes) {
		if(boxes.length==0)	return 0;
		Box[] box=new Box[boxes.length];
		for(int i=0;i<boxes.length;i++)	box[i]=new Box();
		
		for(int i=0;i<boxes.length;i++) {
			List<Box> boxList=new LinkedList<Box>();
			for(int j=0;j<boxes.length;j++) {
				if(i==j)	continue;
				if(boxes[j][0]>boxes[i][0] && boxes[j][1]>boxes[i][1] && boxes[j][2]>boxes[i][2]) {
					boxList.add(box[j]);
				}
			}
			box[i].next=boxList;
		}
		
		for(int i=0;i<boxes.length;i++) {
			func(box[i],1);
		}
        return max;
    }
	private static void func(Box box,int level) {
		if(box.next.isEmpty())	return;
		level++;
		if(level>max)		max=level;
		for(Box b:box.next) {
			func(b,level);
		}
	}
}

class Box{
	List<Box> next;
}
