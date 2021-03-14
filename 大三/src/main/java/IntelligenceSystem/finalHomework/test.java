package IntelligenceSystem.finalHomework;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class test {
	public static void main(String[] args) throws Exception {
		String exe = "python";
		String command = "E:\\python\\pycharm\\workSpace\\system\\pptToWorld.py";
		String num1 = "E:\\python\\pycharm\\workSpace\\system\\作业3遗传算法.pptx";
		String num2 = "2";
		String[] cmdArr = new String[] {exe,command,num1,num2};
		Process process = Runtime.getRuntime().exec(cmdArr);
//		Thread.sleep(18000);
		InputStream in = process.getInputStream();
		InputStream errIn = process.getErrorStream();
		String strErr=getStream(errIn);
		String str=getStream(in);
		process.waitFor();
		System.out.println(str);
	}
	private static String getStream(InputStream in) throws Exception {
		StringBuilder sb=null;
			sb=new StringBuilder();
			int len=0;
			byte buf[]=new byte[10240];
			while((len=in.read(buf))!=-1) {
				sb.append(new String(buf,0,len,"gbk"));
			}
		return sb.toString();
	}
}
