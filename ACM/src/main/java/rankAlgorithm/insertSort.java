package rankAlgorithm;
/*
 * 插入排序
 * 
 * 我们在玩打牌的时候，你是怎么整理那些牌的呢？
 * 一种简单的方法就是一张一张的来，将每一张牌插入到其他已经有序的牌中的适当位置。
 * 当我们给无序数组做排序的时候，为了要插入元素，我们需要腾出空间，
 * 将其余所有元素在插入之前都向右移动一位，这种算法我们称之为插入排序。
 * 
 * 平均时间复杂度：O(N2)
 * 最好时间复杂度：O(N)
 * 最坏时间复杂度：O(N2)
 * 空间复杂度：O(1)
 * 稳定排序
 */
public class insertSort {
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
		System.out.println("\r\n\r\n插入排序\r\nresult:"+isPass);
	}
	static void func(int arr[]) {
		for(int i=1;i<arr.length;i++) {
			int temp=arr[i];
			for(int j=i;j>=0;j--) {
				if(j==0) {
					arr[0]=temp;
					break;
				}
				if(temp<arr[j-1]) {
					arr[j]=arr[j-1];
				}else {
					arr[j]=temp;
					break;
				}
			}
		}
	}
}
