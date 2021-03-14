package Algorithm;

public class Sort {
	public static void main(String[] args) {
		int num[]=new int[100];
		for(int i=0;i<num.length;i++) 	num[i]=(int)(Math.random()*100);
		
		int result[]=insertSort(num);
		
		//测试代码
		for(int i=0;i<result.length;i++)	System.out.print(num[i]+"  ");
		System.out.println();
		int test=result[0];
		boolean isPass=true;
		for(int i=1;i<result.length;i++) {
			if(result[i]<test) {
				isPass=false;
				break;
			}
			test=result[i];
		}
		System.out.println(isPass);
	}
	//插入排序（二分法查找）
	private static int[] insertSort2(int num[]) {
		int temp=0;
		return num;
	}
	
	//插入排序
	private static int[] insertSort(int num[]) {
		int temp=0;
		for(int i=1;i<num.length;i++) {
			temp=num[i];
			int j=i-1;
			while(j>=0 && temp<num[j]) {
				num[j+1]=num[j];
				j--;
			}
			num[j+1]=temp;
		}
		return num;
	}
}
