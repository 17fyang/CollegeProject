package data_yy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

public class Read {
	//	static final String url="config/config.properties";

	//读取一份数据
	public int[] readOneData(File f,String[] keywords) throws Exception {
		String content=this.FileToString(f);
		System.out.println(content);
		int result[]=new int[keywords.length];
		for(int i=0;i<result.length;i++) {
			int times=0;
			int locate=0;
			while(locate<content.length()-1) 		{
				locate=content.indexOf(keywords[i], locate+1);
				if(locate==-1)	break;
				else times++;
			}
			result[i]=times;
		}

		return result;
	}
	public double readOneResult(String url) {
		return 1;
	}

	//读取多份数据
	public int[][] readAllData(String url,String[] keywords) throws Exception {
		File file = new File(url);
		File[] fileList = file.listFiles();
		int result[][]=new int[fileList.length][];
		for(int i=0;i<fileList.length;i++) 	result[i]=readOneData(fileList[i],keywords);
		return result;
	}

	//读取多份股票结果
	public double[] readAllResult(String[] url) {
		double result[]=new double[url.length];
		for(int i=0;i<url.length;i++) 	result[i]=readOneResult(url[i]);
		return result;
	}

	public String readOneDataUrl() {
		return null;
	}
	public String readOneResultUrl() {
		return null;
	}
	public String[] readAllDataUrl() {
		return null;
	}
	public String[] readAllResultUrl() {
		return null;
	}
	//文件转string
	private String FileToString(File f) throws Exception {
//		getTextFromPDF(f.getPath());
//		if(1==1)	return "ok";
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


	public static void getTextFromPDF(String file)   throws Exception {  
		boolean sort = false;  // 是否排序  
		String pdfFile = file;  // pdf文件名  
		String textFile = null;  // 输入文本文件名称  
		String encoding = "UTF-8";  // 编码方式  
		int startPage = 1;  // 开始提取页数  
		int endPage = Integer.MAX_VALUE;  // 结束提取页数  
		Writer output = null;  // 文件输入流，生成文本文件  
		PDDocument document = null;  // 内存中存储的PDF Document  
		try {  
			try {  
				// 首先当作一个URL来装载文件，如果得到异常再从本地文件系统//去装载文件  
				URL url = new URL(pdfFile);  
				//注意参数已不是以前版本中的URL.而是File。  
				document = PDDocument.load(new File(pdfFile));  
				// 获取PDF的文件名  
				String fileName = url.getFile();  
				// 以原来PDF的名称来命名新产生的txt文件  
				if (fileName.length() > 4) {  
					File outputFile = new File(fileName.substring(0, fileName  
							.length() - 4)  
							+ ".txt");  
					textFile = outputFile.getName();  
				}  
			} catch (MalformedURLException e) {  
				// 如果作为URL装载得到异常则从文件系统装载  
				//注意参数已不是以前版本中的URL.而是File。  
				document = PDDocument.load(new File(pdfFile));  
				if (pdfFile.length() > 4) {  
					textFile = pdfFile.substring(0, pdfFile.length() - 4)  
							+ ".txt";  
				}  
			}  
			// 文件输入流，写入文件倒textFile  
			output = new OutputStreamWriter(new FileOutputStream(textFile), encoding);  
			// PDFTextStripper来提取文本  
			PDFTextStripper stripper = null;  
			stripper = new PDFTextStripper();  
			// 设置是否排序  
			stripper.setSortByPosition(sort);  
			// 设置起始页  
			stripper.setStartPage(startPage);  
			// 设置结束页  
			stripper.setEndPage(endPage);  
			// 调用PDFTextStripper的writeText提取并输出文本  
			stripper.writeText(document, output);  
		} finally {  
			if (output != null) {  
				// 关闭输出流  
				output.close();  
			}  
			if (document != null) {  
				// 关闭PDF Document  
				document.close();  
			}  
		}  

	} 
}
