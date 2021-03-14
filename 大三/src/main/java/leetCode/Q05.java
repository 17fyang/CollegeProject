package leetCode;

/*
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 
 * 动态规划
 */
public class Q05 {
	public static void main(String[] args) {
		String s="cbbd";
		String result=longestPalindrome(s);
		System.out.println(result);
	}


	
	public static String longestPalindrome(String s) {
		char[] c=s.toCharArray();
		if(c.length==0 || c.length==1)		return s;

		int maxLocal=0;
		int max=0;
		int maxType=0;
		for(int i=0;i<c.length;i++) {
			int max1=getMax_1(c,i);
			int max2=getMax_2(c,i);
			int max0=Math.max(max1, max2);
			if(max0>max || (max0==max && maxType==0)) {
				maxLocal=i;
				max=max0;
				maxType = max1>=max2 ? 1:0;
			}
		}
		if(max==0)	return String.valueOf(c[0]);
		if(maxType==1)	return s.substring(maxLocal-max,maxLocal+max+1);
		else		return s.substring(maxLocal-max+1,maxLocal+max+1);
	}


	private static int getMax_2(char[] c, int i) {
		if(i+1==c.length || c[i]!=c[i+1] )	return 0;
		int now=1;
		while(true) {
			if(i-now<0 || i+now+1>=c.length)	return now;
			if(c[i+now+1] == c[i-now]) now++;
			else return now;
		}
	}


	private static int  getMax_1(char[] c, int i) {
		int now=0;
		while(true) {
			if(i-now-1<0 || i+now+1>=c.length)	return now;
			if(c[i+now+1] == c[i-now-1]) now++;
			else return now;
		}

	}
	//动态规划法
	//	    public static String longestPalindrome(String s) {
	//	    	char[] c=s.toCharArray();
	//	    	if(c.length==1)		return s;
	//	    	
	//	    	int maxLocal=0;
	//	    	int max=0;
	//	    	int now=0;
	//	    	for(int i=1;i<c.length;i++) {
	//	    		if((now==0 && c[i] == c[i-1-now]) || (i-2-now>=0 && c[i] == c[i-2-now])) {
	//	    			now++;
	//	    			if(now>max) {
	//	    				max=now;
	//	    				maxLocal=i-now;
	//	    			}
	//	    		}else	now=0;
	//	    	}
	//	        return s.substring(maxLocal-max,maxLocal+max+1);
	//	    }

}
