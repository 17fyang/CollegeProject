package rankAlgorithm;
/*
 * 堆排序
 * 
 * 堆排序的基本思想是：将待排序序列构造成一个大顶堆，
 * 此时，整个序列的最大值就是堆顶的根节点。
 * 将其与末尾元素进行交换，此时末尾就为最大值。
 * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
 * 如此反复执行，便能得到一个有序序列了
 * 
 * 最大时间复杂度：O(nlogn)
 * 平均时间复杂度：O(nlogn)
 * 最小时间复杂度：O(nlogn)
 * 空间复杂度：O：(1)
 */
public class heapSort {
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
		System.out.println("\r\n\r\n堆排序\r\nresult:"+isPass);
	}
	
	public static void func(int arr[]) {
		for(int i=arr.length/2-1;i>=0;i--) {
			sort(arr,i,arr.length);
		}
		for(int i=arr.length-1;i>0;i--) {
			int temp=arr[0];
			arr[0]=arr[i];
			arr[i]=temp;
			sort(arr,0,i);
		}
	}
	public static void sort(int arr[],int i,int length) {
		int value=arr[i];
		for(int k=i*2+1;k<length;k=k*2+1) {
			if(k+1<length && arr[k]<arr[k+1])	k++;
			if(arr[k]>value) {
				arr[i]=arr[k];
				i=k;
			}else {
				break;
			}
		}
		arr[i]=value;
	}
	
}
