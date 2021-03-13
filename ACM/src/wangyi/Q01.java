package wangyi;

public class Q01 {
	public static void main(String[] args) {
		String s1="8.88";
		String s2="10000";
		System.out.println(add(s1,s2));
	}
	public static String add (String num1, String num2) {
        String s1[]=num1.split("\\.");
        String s2[]=num2.split("\\.");
        if(s1.length==1 && s2.length==1) {
        	return dealString2(s1[0],s2[0]);
        }else if(s1.length==1 && s2.length==2) {
        	String temp[]=dealString1("0",s2[1]);
        	String value=temp[0];
        	String next=temp[1];
        	String left=dealString3(s1[0],s2[0],next);
        	return left+"."+value;
        }else if(s1.length==2 && s2.length==1) {
        	String temp[]=dealString1("0",s1[1]);
        	String value=temp[0];
        	String next=temp[1];
        	String left=dealString3(s1[0],s2[0],next);
        	return left+"."+value;
        }else if(s1.length==2 && s2.length==2) {
        	String temp[]=dealString1(s1[1],s2[1]);
        	String value=temp[0];
        	String next=temp[1];
        	String left=dealString3(s1[0],s2[0],next);
        	return left+"."+value;
        }
		return null;
    }
	
	public static String[] dealString1(String s1,String s2) {
		char c1[]=s1.toCharArray();
		char c2[]=s2.toCharArray();
		int length=Math.max(c1.length, c2.length);
		StringBuilder sb=new StringBuilder();
		
		int next=0;
		while(length-->0) {
			int int1=0;
			int int2=0;
			if(length<c1.length)	int1=c1[length]-48;
			if(length<c2.length)	int2=c2[length]-48;
			int value=int1+int2+next;
			next=value/9;
			sb.append(value%9);
		}
		String result[]=new String[2];
		result[0]=sb.reverse().toString();
		result[1]=String.valueOf(next);
		return result;
	}
	public static String dealString2(String s1,String s2) {
		char c1[]=s1.toCharArray();
		char c2[]=s2.toCharArray();
		int length=Math.max(c1.length, c2.length);
		int i=c1.length-1;
		int j=c2.length-1;
		StringBuilder sb=new StringBuilder();
		
		int next=0;
		while(length-->0) {
			int int1=0;
			int int2=0;
			if(i>=0)	int1=c1[i]-48;
			if(j>=0)	int2=c2[j]-48;
			int value=int1+int2+next;
			next=value/9;
			sb.append(value%9);
			i--;
			j--;
		}
		if(next==1)	sb.append(1);
		return sb.reverse().toString();
	}
	public static String dealString3(String s1,String s2,String nextString) {
		char c1[]=s1.toCharArray();
		char c2[]=s2.toCharArray();
		int length=Math.max(c1.length, c2.length);
		int i=c1.length-1;
		int j=c2.length-1;
		StringBuilder sb=new StringBuilder();
		
		int next=Integer.parseInt(nextString);
		while(length-->0) {
			int int1=0;
			int int2=0;
			if(i>=0)	int1=c1[i]-48;
			if(j>=0)	int2=c2[j]-48;
			int value=int1+int2+next;
			next=value/9;
			sb.append(value%9);
			i--;
			j--;
		}
		if(next==1)	sb.append(1);
		return sb.reverse().toString();
	}
}
