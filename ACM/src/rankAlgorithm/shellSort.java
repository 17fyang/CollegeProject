package rankAlgorithm;
/*
 * ϣ�������˼���ǲ��ò�������ķ�����
 * ����������������Ϊ h ��Ԫ������
 * �տ�ʼ h �Ĵ�С������ h = n / 2,������ h = n / 4���� h һֱ��С��
 * �� h = 1 ʱ��Ҳ���Ǵ�ʱ������������Ϊ1��Ԫ�����򣬴�ʱ����������������
 * 
 * ƽ��ʱ�临�Ӷȣ�O(N1.3)
 * ���ʱ�临�Ӷȣ�O(N)
 * �ʱ�临�Ӷȣ�O(N2)
 * �ռ临�Ӷȣ�O(1)
 * ���ȶ�����
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
		System.out.println("\r\n\r\nϣ������\r\nresult:"+isPass);
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
