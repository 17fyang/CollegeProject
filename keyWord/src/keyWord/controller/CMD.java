package keyWord.controller;

import java.io.InputStream;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class CMD {
	public String getKeyword(String fileUrl,String type) {
		String result=null;
		try {
			String exe = "python";
			String command="";
			if(type.equals("pptx"))	command =Read.getPptToWord();
			else 		command=Read.getPdfToWord();
			String num1 = fileUrl.replace("\\", "\\\\");
			String num2 = "2";
			String[] cmdArr = new String[] {exe,command,num1,num2};
			Process process = Runtime.getRuntime().exec(cmdArr);
			InputStream in = process.getInputStream();
			InputStream errIn = process.getErrorStream();
			String strErr=getStream(errIn);
			String str=getStream(in);
			result=strErr;
			if(str!=null && !str.equals(""))	result=str;
			process.waitFor();
		}catch(Exception e) {
			e.printStackTrace();
			return "null";
		}
		return result;
	}
	
	public JSONObject getJson(String keyword) {
		JSONObject json=new JSONObject();
		try {
			String sp1=Read.getSp();
			JSONArray arr1=getJsonString(keyword,sp1);
			String sp2=Read.getSp2();
			JSONArray arr2=getJsonString(keyword,sp2);
			json.put("code", 0);
			JSONArray linkData=new JSONArray();
			linkData.addAll(arr1);
			linkData.addAll(arr2);
			
			JSONArray relation=getRelation(keyword);
			JSONObject o=new JSONObject();
			o.put("linkData", linkData);
			o.put("relation", relation);
			json.put("data", o);
			json.put("count", linkData.size());
			json.put("msg", "ojbk");
		}catch(Exception e) {
			e.printStackTrace();
			json.put("errcode", 500);
		}
		
		return json;
	}
	
	public JSONArray getRelation(String keyword) throws Exception {
		if(keyword==null || keyword.equals(""))	 return new JSONArray();
		String s[]=keyword.split(" ");
		if(s.length>1)	keyword=s[0].concat(" "+s[1]);
		
		System.out.println(keyword);
		JSONArray data=new JSONArray();
			String item=Read.getItem();

			String exe = "python";
			String command = item;
			String num1 = keyword;
			System.out.println(item+"  "+keyword);
			String[] cmdArr = new String[] {exe,command,num1};
			Process process = Runtime.getRuntime().exec(cmdArr);
			InputStream in = process.getInputStream();
			InputStream errIn = process.getErrorStream();
			String strErr=getStream(errIn);
			String str=getStream(in);
			String result=strErr;
			if(str!=null && !str.equals(""))	result=str;
			System.out.println(result);
			data=JSONArray.parseArray(result);
			
		
		return data;
	}
	
	
	public JSONArray getJsonString(String keyword,String spUrl) throws Exception{
		JSONArray data=new JSONArray();
			String exe = "python";
			String command = spUrl;
			String num1 = keyword;
			String num2 = "2";
			System.out.println(spUrl+"  "+keyword);
			String[] cmdArr = new String[] {exe,command,num1,num2};
			Process process = Runtime.getRuntime().exec(cmdArr);
			InputStream in = process.getInputStream();
			InputStream errIn = process.getErrorStream();
			String strErr=getStream(errIn);
			String str=getStream(in);
			String result=strErr;
			if(str!=null && !str.equals(""))	result=str;
			String splitString[]=result.split("\r\n");
			for(int i=0;i<splitString.length;i++) {
				JSONObject o=null;
				try {
					o=JSONObject.parseObject(splitString[i]);
				}catch(Exception e) {
					continue;
				}
				
				data.add(o);
			}
			
			process.waitFor();
		return data;
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
