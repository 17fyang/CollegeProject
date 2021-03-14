package others;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Test {
	public static void main(String[] args) {
		Calendar d=Calendar.getInstance();
	    System.out.println(d);
	    //获取当前的年份。
	    System.out.println(d.get(Calendar.YEAR));
	    System.out.println(d.get(Calendar.MONTH));
	    System.out.println(d.get(Calendar.DAY_OF_MONTH));
	}
	
	private static void testMethon(String[] s) {
		for(int i=0;i<s.length;i++) {
			System.out.println(s[i]);
		}
	}
}
