package data_yy;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

public class Read {
	private File[] fileList;
	//读取一份数据
	public int readOneData(String content,String[] keywords) {
		int times=0;
		int result[]=new int[keywords.length];
		for(int i=0;i<result.length;i++) {
			int locate=0;
			while(locate<content.length()-1){
				locate=content.indexOf(keywords[i], locate+1);
				if(locate==-1)	break;
				else times++;
			}
		}
		return times;
	}

	//读取多份数据
	public int[][] readAllData(String url,String[][] keywords){
		File file = new File(url);
		fileList = file.listFiles();
		int result[][]=new int[keywords.length][fileList.length];
		for(int i=0;i<fileList.length;i++) {
			String content = this.FileToString(fileList[i]);
			for(int j=0;j<keywords.length;j++) {
				result[j][i]=readOneData(content,keywords[j]);
//				if(result[j][i]==0)	System.out.println(fileList[i].getName());
			}
		}
		return result;
	}
	
	//文件转string
	private String FileToString(File f){
		//创建PdfDocument实例
		PdfDocument doc= new PdfDocument();
		//加载PDF文件
		doc.loadFromFile(f.getPath());
		StringBuilder sb= new StringBuilder();
		PdfPageBase page;
		for(int i=0;i<doc.getPages().getCount();i++){
			page=doc.getPages().get(i);
			sb.append(page.extractText(true));
		}
		return sb.toString();
	}
	
	//获取关键字数组
	public String[][] getKeyWords(String keywordUrl) throws Exception {
		int len=0;
		byte[] buf=new  byte[10240];
		InputStream in=new FileInputStream(new File(keywordUrl));
		StringBuilder sb=new StringBuilder();
		while((len=in.read(buf))!=-1) {
			sb.append(new String(buf,0,len,"utf-8"));
		}
		String content=sb.toString();
		String group[]=content.split("@@@\r\n");
		String[][] result=new String[group.length][];
		for(int i=0;i<group.length;i++) {
			result[i]=group[i].split("\r\n");
		}
		in.close();
		return result;
	}

	//获取股票成交量结果
	public Map<String, Double> getResult(String resultUrl) throws Exception {
		int len=0;
		byte[] buf=new  byte[2048];
		InputStream in=new FileInputStream(new File(resultUrl));
		StringBuilder sb=new StringBuilder();
		while((len=in.read(buf))!=-1) {
			sb.append(new String(buf,0,len,"utf-8"));
		}
		String content=sb.toString();
		String group[]=content.split("@@@\r\n");
		Map<String,Double> map=new HashMap<String,Double>();
		String number[]=group[0].split("\r\n");
		String name[]=group[1].split("\r\n");
		
		for(int i=0;i<name.length;i++) {
//			name[i]=name[i].substring(2,4);
			map.put(name[i], Double.parseDouble(number[i]));
		}
		
		in.close();
		return map;
	}

	public File[] getFileList() {
		return fileList;
	}
	
	
}
