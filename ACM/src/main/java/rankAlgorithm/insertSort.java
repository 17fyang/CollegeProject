package rankAlgorithm;
/*
 * ��������
 * 
 * ����������Ƶ�ʱ��������ô������Щ�Ƶ��أ�
 * һ�ּ򵥵ķ�������һ��һ�ŵ�������ÿһ���Ʋ��뵽�����Ѿ���������е��ʵ�λ�á�
 * �����Ǹ����������������ʱ��Ϊ��Ҫ����Ԫ�أ�������Ҫ�ڳ��ռ䣬
 * ����������Ԫ���ڲ���֮ǰ�������ƶ�һλ�������㷨���ǳ�֮Ϊ��������
 * 
 * ƽ��ʱ�临�Ӷȣ�O(N2)
 * ���ʱ�临�Ӷȣ�O(N)
 * �ʱ�临�Ӷȣ�O(N2)
 * �ռ临�Ӷȣ�O(1)
 * �ȶ�����
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
		System.out.println("\r\n\r\n��������\r\nresult:"+isPass);
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
