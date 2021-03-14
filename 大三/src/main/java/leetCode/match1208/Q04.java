package leetCode.match1208;

public class Q04 {
	public static int number=0;
	public static void main(String[] args) {
		int result=0;
			result=solution();
			System.out.println(number);
	}
	
	public static int solution() {
		int n=0;
		int m=2019;
		for(int i=1;i<=m;i++) {
			String s=String.valueOf(i);
			if(s.contains("9"))	number++;	
		}
		return n;
	}
}
