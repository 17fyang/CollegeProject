package rankAlgorithm;
/*
 * 归并排序
 * 
 * 通过递归的方式将大的数组一直分割，直到数组的大小为 1，
 * 此时只有一个元素，那么该数组就是有序的了，
 * 之后再把两个数组大小为1的合并成一个大小为2的，
 * 再把两个大小为2的合并成4的 ….. 直到全部小的数组合并起来。
 * 
 * 平均时间复杂度：O(nlogn)
 * 最优时间复杂度：O(nlogn)
 * 最坏时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 * 稳定排序
 * 
 */
public class mergeSort {
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
		System.out.println("\r\n\r\n归并排序\r\nresult:"+isPass);
	}
	static void func(int arr[]) {
		sort(arr,0,arr.length-1);
	}
	//递归求解
	static void sort(int arr[],int head,int tail) {
		if(head<tail) {
			int mid=(tail+head)/2;
			sort(arr,head,mid);
			sort(arr,mid+1,tail);
			
			int newArr[]=new int[tail-head+1];
			int i=head;
			int j=mid+1;
			int location=0;
			while(i<=mid && j<=tail) {
				if(arr[i]>arr[j]) 	newArr[location++]=arr[j++];
				else newArr[location++]=arr[i++];
			}
			while(i<=mid)	newArr[location++]=arr[i++];
			while(j<=tail)	newArr[location++]=arr[j++];
			for(int k=0;k<newArr.length;k++) {
				arr[head++]=newArr[k];
			}
		}
		
		
	}
	
	
}
