package leetCode;

/*
 * date:2019-11-32
 * type:鍔ㄦ�佽鍒�
 * 缁欎綘涓�涓瓧绗︿覆聽s聽鍜屼竴涓瓧绗﹁寰嬄爌锛岃浣犳潵瀹炵幇涓�涓敮鎸� '.'聽鍜屄�'*'聽鐨勬鍒欒〃杈惧紡鍖归厤銆�
 * '.' 鍖归厤浠绘剰鍗曚釜瀛楃
 * '*' 鍖归厤闆朵釜鎴栧涓墠闈㈢殑閭ｄ竴涓厓绱�
 * 鎵�璋撳尮閰嶏紝鏄娑电洊聽鏁翠釜聽瀛楃涓猜爏鐨勶紝鑰屼笉鏄儴鍒嗗瓧绗︿覆銆�
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
