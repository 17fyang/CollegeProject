package leetCode.match1208;

public class Q03 {
	public static int number=0;
	public static final int n=4;
	public static void main(String[] args) {
		int result=0;
			result=solution();
			System.out.println(number);
	}
	
	public static int solution() {
		huan(0,0);
		return 0;
	}
	public static void huan(int x,int y) {
		if(x==n-1 && y==n-1) {
			number++;
			return;
		}
		if(x<n-1)	huan(x+1,y);
		if(y<n-1)	huan(x,y+1);
	}
}
