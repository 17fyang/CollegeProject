package leetCode;

/*
 * ������������¥�ݡ���Ҫ n ������ܵ���¥����
 * 
 * ÿ��������� 1 �� 2 ��̨�ס����ж����ֲ�ͬ�ķ�����������¥���أ�
 * 
 * ע�⣺���� n ��һ����������
 */
public class Q70 {
	private static int cishu=0;
	public static void main(String[] args) {
		int result=climbStairs(45);
		System.out.println(result);
	}
	
	//ѭ���ⷨ
	public static int climbStairs(int n) {
        int stair[] = new int[n+1];
        stair[0] = stair[1] = 1;
        for(int i = 2; i != n+1; ++i){
            stair[i] = stair[i - 1] + stair[i - 2];
        }
        
        return stair[n];
    }
	/*
	 * �ݹ�ⷨ
	 
	public static int climbStairs(int n) {
		int cen=0;
		dp(cen,n);
		return cishu;
	}
	public static int dp(int cen,int n) {
		if(cen==n || cen==n-1) {
			cishu++;
			return 0;
		}
		dp(cen+1,n);
		dp(cen+2,n);
		
		return 0;
	}
	*/
}
