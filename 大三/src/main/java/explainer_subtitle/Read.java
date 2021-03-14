package explainer_subtitle;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Read {

	public Subt[] read(String path) {
		File f=new File(path);
		String content=null;
		try {
			content=FileToString(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String split_s[]=content.split("\r\n\r\n");
		List<Subt> list=new ArrayList<Subt>(split_s.length);
		for(int i=0;i<split_s.length;i++) {
			if(split_s[i]==null || split_s[i].equals(""))  continue;
			Subt subt=StringToSubt(split_s[i]);
			list.add(subt);
		}
		return list.toArray(new Subt[list.size()]);
	}

	private Subt StringToSubt(String s) {
		int sid=0;
		Time2 startTime=null;
		Time2 endTime=null;
		String content="";
		String split_s[]=s.split("\r\n");
		//第一行
		split_s[0]=split_s[0].trim();
		sid=Integer.parseInt(split_s[0]);
		//第二行
		String timeSplit[]=split_s[1].split(" --> ");
		startTime=StringToTime(timeSplit[0]);
		endTime=StringToTime(timeSplit[1]);
		//剩下行
		for(int i=2;i<split_s.length;i++) 	content=content.concat(split_s[i]+"\r\n");
		return new Subt(sid,startTime,endTime,content);
	}
	
	
	private Time2 StringToTime(String s) {
		int hour;
		int minute;
		int second;
		int msecond;
		
		String split_s[]=s.split(":");
		hour=Integer.parseInt(split_s[0]);
		minute=Integer.parseInt(split_s[1]);
		
		String split_s2[]=split_s[2].split(",");
		second=Integer.parseInt(split_s2[0]);
		msecond=Integer.parseInt(split_s2[1]);
		
		return new Time2(hour,minute,second,msecond);
	}

	//文件转string
	private String FileToString(File f) throws Exception{
		int len=0;
		byte[] buf=new  byte[1024];
		InputStream in=new FileInputStream(f);
		StringBuilder sb=new StringBuilder();
		while((len=in.read(buf))!=-1) {
			sb.append(new String(buf,0,len,"utf-8"));
		}
		in.close();
		return sb.toString();
	}
}
