package leetCode;

/*
 * date:2019-11-32
 * type:动态规划
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */
public class Q10 {
	public static void main(String[] args) {
		String s[]= {"","aa","aa","ab","aab","mississippi"};
		String p[]= {"","a","a*",".*","c*a*b","mis*is*p*."};
		boolean[] result= {true,false,true,true,true,false};
		for(int i=0;i<s.length;i++) {
			System.out.println(result[i]+"     "+isMatch(s[i],p[i]));
		}
	}
	
	 public static  boolean isMatch(String s, String p) {
		 try {
			 char[] ss=s.toCharArray();
			 char[] pp=p.toCharArray();
			 
			 char beforeChar=' ';
			 int j=-1;
			 for(int i=0;i<ss.length;i++) {
				 j++;
				 if(pp[j]=='.') {
				 }else if(pp[j]=='*') {
					 if(pp[j-1]=='.' || pp[j-1]==ss[i]) {
						 j--;
					 }else{
						 j++;
					 }
				 }else if(pp[j]==ss[i]) {
				 }else if(pp[j+1]=='*') {
					 j++;
				 }else return false;
			 }
		 }catch(Exception e) {
			 return false;
		 }
		
		 return true;
	 }
	 
	 public static void run() {
		 
	 }
}
