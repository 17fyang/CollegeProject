package leetCode;

/*
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * 注意：给定 n 是一个正整数。
 */
public class Q70 {
	private static int cishu=0;
	public static void main(String[] args) {
		int result=climbStairs(45);
		System.out.println(result);
	}
	
	//循环解法
	public static int climbStairs(int n) {
        int stair[] = new int[n+1];
        stair[0] = stair[1] = 1;
        for(int i = 2; i != n+1; ++i){
            stair[i] = stair[i - 1] + stair[i - 2];
        }
        
        return stair[n];
    }
	/*
	 * 递归解法
	 
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
