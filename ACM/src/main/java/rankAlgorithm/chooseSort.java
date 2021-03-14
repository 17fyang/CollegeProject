package rankAlgorithm;
/*
 * 选择排序
 * 
 * 首先，找到数组中最小的那个元素，
 * 其次，将它和数组的第一个元素交换位置(如果第一个元素就是最小元素那么它就和自己交换)。
 * 其次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。
 * 如此往复，直到将整个数组排序。这种方法我们称之为选择排序。
 * 
 * 平均时间复杂度：O(N2)
 * 最好时间复杂度：O(N2)
 * 最坏时间复杂度：O(N2)
 * 空间复杂度：O(1)
 * 非稳定排序
 */
public class chooseSort {
	public static void main(String[] args) {
		int arr[]=new int[100];
		for(int i=0;i<arr.length;i++) {
			arr[i]=(int)(Math.random()*100);
		}
		
		func(arr);
		
		boolean isPass=true;
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
			if(i==0)	continue;
			if(arr[i]<arr[i-1])	isPass=false;
		}
		System.out.println("\r\n\r\n选择排序\r\nresult:"+isPass);
	}
	static void func(int arr[]) {
		for(int i=0;i<arr.length;i++) {
			int min=arr[i];
			int minLocation=i;
			for(int j=i+1;j<arr.length;j++) {
				if(arr[j]<min) {
					min=arr[j];
					minLocation=j;
				}
			}
			int temp=min;
			arr[minLocation]=arr[i];
			arr[i]=temp;
		}
	}
}
