package data_yy;

import java.io.File;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

public class test {
	public static void main(String[] args) {
		String url="C:\\Users\\Administrator\\Desktop\\数据分析1216\\2017年报讨论与分析\\中国石化.pdf";
		File f=new File(url);
		String content=FileToString(f);
		System.out.println(content);
	}
	//文件转string
	private static String FileToString(File f){
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
}
