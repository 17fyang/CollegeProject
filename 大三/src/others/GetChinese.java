package others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class GetChinese {
	public static void main(String[] args) {
		File f=new File("File/baidu.txt");
		String text=null;
		try {
			text=getString(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String textSplit[]=text.split("[^([\\u4E00-\\u9FA5]| [\\u3002\\uff1b\\uff0c\\uff1a\\u201c\\u201d\\uff08\\uff09\\u3001\\uff1f\\u300a\\u300b])+]");
		for(int i=0;i<textSplit.length;i++) {
			if(textSplit[i]==null || "".equals(textSplit[i]))	 continue;
				System.out.print(textSplit[i]);
		}
	}

	private static String getString(File f) throws Exception {
		InputStream in=new FileInputStream(f);
		byte buf[]=new byte[1024];
		int len=0;
		StringBuilder sb=new StringBuilder();
		while((len=in.read(buf))!=-1) {
			sb.append(new String(buf,0,len,"UTF-8"));
		}
		
		
		return sb.toString();
	}
}
