package keyWord.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.tomcat.jni.Global;

public class Read {
	private static String pptToWord;
	private static String pdfToWord;
	private static String sp;
	private static String sp2;
	private static String item;
	private static String filePath;
	
	static {
		try {
			Properties p=new Properties();
			File f=new File("config.properties");
			System.out.println(f.getAbsolutePath());
			p.load(new FileInputStream(f));
			pptToWord=p.getProperty("pptToWord");
			pdfToWord=p.getProperty("pdfToWord");
			sp=p.getProperty("sp");
			sp2=p.getProperty("sp2");
			item=p.getProperty("item");
			filePath=p.getProperty("filePath");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static String getPptToWord() {
		return pptToWord;
	}
	public static String getPdfToWord() {
		return pdfToWord;
	}
	public static String getSp() {
		return sp;
	}
	public static String getFilePath() {
		return filePath;
	}
	public static String getSp2() {
		return sp2;
	}
	public static String getItem() {
		return item;
	}
}
