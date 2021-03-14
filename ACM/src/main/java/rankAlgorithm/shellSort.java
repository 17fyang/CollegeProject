package rankAlgorithm;
/*
 * 希尔排序的思想是采用插入排序的方法，
 * 先让数组中任意间隔为 h 的元素有序，
 * 刚开始 h 的大小可以是 h = n / 2,接着让 h = n / 4，让 h 一直缩小，
 * 当 h = 1 时，也就是此时数组中任意间隔为1的元素有序，此时的数组就是有序的了
 * 
 * 平均时间复杂度：O(N1.3)
 * 最好时间复杂度：O(N)
 * 最坏时间复杂度：O(N2)
 * 空间复杂度：O(1)
 * 非稳定排序
 */
public class shellSort {
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
		System.out.println("\r\n\r\n希尔排序\r\nresult:"+isPass);
	}
	static void func(int arr[]) {
		int n=arr.length;
		for(int h=n/2;h>0;h=h/2) {
			insertSort(arr,h);
		}
	}
	static void insertSort(int arr[],int h) {
		for(int i=h;i<arr.length;i++) {
			int temp=arr[i];
			for(int j=i;j>=0;j-=h) {
				if(j<h) {
					arr[j]=temp;
					break;
				}
				if(temp<arr[j-h]) {
					arr[j]=arr[j-h];
				}else {
					arr[j]=temp;
					break;
				}
			}
		}
	}
}
