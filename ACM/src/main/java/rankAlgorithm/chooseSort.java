package rankAlgorithm;
/*
 * ѡ������
 * 
 * ���ȣ��ҵ���������С���Ǹ�Ԫ�أ�
 * ��Σ�����������ĵ�һ��Ԫ�ؽ���λ��(�����һ��Ԫ�ؾ�����СԪ����ô���ͺ��Լ�����)��
 * ��Σ���ʣ�µ�Ԫ�����ҵ���С��Ԫ�أ�����������ĵڶ���Ԫ�ؽ���λ�á�
 * ���������ֱ�������������������ַ������ǳ�֮Ϊѡ������
 * 
 * ƽ��ʱ�临�Ӷȣ�O(N2)
 * ���ʱ�临�Ӷȣ�O(N2)
 * �ʱ�临�Ӷȣ�O(N2)
 * �ռ临�Ӷȣ�O(1)
 * ���ȶ�����
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
		System.out.println("\r\n\r\nѡ������\r\nresult:"+isPass);
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
