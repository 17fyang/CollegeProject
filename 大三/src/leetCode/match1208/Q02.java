package leetCode.match1208;

public class Q02 {
	public static void main(String[] args) {
		int result=0;
			result=solution();
			System.out.println(result);
	}
	
	public static int solution() {
		int n=0;
		int m=19000;
		for(int i=1;i<=m;i++) {
			if(isCorrect(m,i)==true)	n++;
		}
		return n;
	}
	public static boolean isCorrect(int m,int n) {
		for(int i=2;i<=n;i++) {
			if(n%i==0) {
				if(m%i==0) {
					return false;
				}
			}
		}
		return true;
	}
}
