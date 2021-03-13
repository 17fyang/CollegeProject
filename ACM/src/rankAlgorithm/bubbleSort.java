package rankAlgorithm;
/*
 * ð������
 * 
 * ƽ��ʱ�临�Ӷȣ�O(N2)
 * ���ʱ�临�Ӷȣ�O(N)
 * �ʱ�临�Ӷȣ�O(N2)
 * �ռ临�Ӷȣ�O(1)
 * �ȶ�����
 */
public class bubbleSort {
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
		System.out.println("\r\n\r\nð������\r\nresult:"+isPass);
	}
	static void func(int arr[]) {
		for(int i=0;i<arr.length;i++) {
			boolean flag=false;
			for(int j=1;j<arr.length-i;j++) {
				if(arr[j]<arr[j-1]) {
					flag=true;
					int temp=arr[j];
					arr[j]=arr[j-1];
					arr[j-1]=temp;
				}
			}
			if(!flag)	break;
		}
	}
}
