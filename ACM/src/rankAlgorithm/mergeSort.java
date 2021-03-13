package rankAlgorithm;
/*
 * �鲢����
 * 
 * ͨ���ݹ�ķ�ʽ���������һֱ�ָֱ������Ĵ�СΪ 1��
 * ��ʱֻ��һ��Ԫ�أ���ô���������������ˣ�
 * ֮���ٰ����������СΪ1�ĺϲ���һ����СΪ2�ģ�
 * �ٰ�������СΪ2�ĺϲ���4�� ��.. ֱ��ȫ��С������ϲ�������
 * 
 * ƽ��ʱ�临�Ӷȣ�O(nlogn)
 * ����ʱ�临�Ӷȣ�O(nlogn)
 * �ʱ�临�Ӷȣ�O(nlogn)
 * �ռ临�Ӷȣ�O(n)
 * �ȶ�����
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
		System.out.println("\r\n\r\n�鲢����\r\nresult:"+isPass);
	}
	static void func(int arr[]) {
		sort(arr,0,arr.length-1);
	}
	//�ݹ����
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
