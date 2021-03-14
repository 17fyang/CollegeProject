package leetCode.match1208;

public class Q01 {
	public static void main(String[] args) {
		int result=0;
			result=solution();
			System.out.println(result);
	}
	
	public static int solution() {
		int n=0;
		int m=1200000;
		for(int i=1;i<=m;i++) {
			if(m%i==0)	n++;
		}
		return n;
	}
}
