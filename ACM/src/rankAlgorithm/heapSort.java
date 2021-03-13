package rankAlgorithm;
/*
 * ������
 * 
 * ������Ļ���˼���ǣ������������й����һ���󶥶ѣ�
 * ��ʱ���������е����ֵ���ǶѶ��ĸ��ڵ㡣
 * ������ĩβԪ�ؽ��н�������ʱĩβ��Ϊ���ֵ��
 * Ȼ��ʣ��n-1��Ԫ�����¹����һ���ѣ�������õ�n��Ԫ�صĴ�Сֵ��
 * ��˷���ִ�У����ܵõ�һ������������
 * 
 * ���ʱ�临�Ӷȣ�O(nlogn)
 * ƽ��ʱ�临�Ӷȣ�O(nlogn)
 * ��Сʱ�临�Ӷȣ�O(nlogn)
 * �ռ临�Ӷȣ�O��(1)
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
		System.out.println("\r\n\r\n������\r\nresult:"+isPass);
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
