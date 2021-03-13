package rankAlgorithm;
/*
 * ��������
 * 
 * ������Ԫ�����￪ʼ�Ѵ�������и������С������(�������鶼����������Ԫ��)��
 * ��������ͨ���ݹ�ķ�ʽ��������Ԫ����ߵ�������ұߵ�����Ҳ�ظ�ͬ���Ĳ�����
 * ֱ������Ĵ�СΪ1����ʱÿ��Ԫ�ض����������λ�á�
 * 
 * ƽ��ʱ�临�Ӷȣ�O(nlogn)
 * �ʱ�临�Ӷȣ�O(n2)
 * ���ʱ�临�Ӷȣ�O(nlogn)
 * �ռ临�Ӷȣ�O(1)
 * ���ȶ�����
 */
public class quickSort {
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
		sort(arr,0,arr.length-1);
	}
	
	static void sort(int[] arr, int left, int right) {
		if(left<right) {
			int aimLocation=left;
			int i=left+1;
			int j=right;
			while(true) {
				while(i<=j && arr[i]<=arr[aimLocation])	i++;
				while(i<=j && arr[j]>=arr[aimLocation])	j--;
				if(i>=j)	break;
				int temp=arr[i];
				arr[i]=arr[j];
				arr[j]=temp;
			}
			int temp=arr[j];
			arr[j]=arr[aimLocation];
			arr[aimLocation]=temp;
			
			
			sort(arr,left,j-1);
			sort(arr,j+1,right);
		}
	}
}
