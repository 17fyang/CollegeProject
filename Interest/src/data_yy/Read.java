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

	//��ȡһ������
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

	//��ȡ�������
	public int[][] readAllData(String url,String[] keywords) throws Exception {
		File file = new File(url);
		File[] fileList = file.listFiles();
		int result[][]=new int[fileList.length][];
		for(int i=0;i<fileList.length;i++) 	result[i]=readOneData(fileList[i],keywords);
		return result;
	}

	//��ȡ��ݹ�Ʊ���
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
	//�ļ�תstring
	private String FileToString(File f) throws Exception {
//		getTextFromPDF(f.getPath());
//		if(1==1)	return "ok";
		//����PdfDocumentʵ��
		PdfDocument doc= new PdfDocument();
		//����PDF�ļ�
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
		boolean sort = false;  // �Ƿ�����  
		String pdfFile = file;  // pdf�ļ���  
		String textFile = null;  // �����ı��ļ�����  
		String encoding = "UTF-8";  // ���뷽ʽ  
		int startPage = 1;  // ��ʼ��ȡҳ��  
		int endPage = Integer.MAX_VALUE;  // ������ȡҳ��  
		Writer output = null;  // �ļ��������������ı��ļ�  
		PDDocument document = null;  // �ڴ��д洢��PDF Document  
		try {  
			try {  
				// ���ȵ���һ��URL��װ���ļ�������õ��쳣�ٴӱ����ļ�ϵͳ//ȥװ���ļ�  
				URL url = new URL(pdfFile);  
				//ע������Ѳ�����ǰ�汾�е�URL.����File��  
				document = PDDocument.load(new File(pdfFile));  
				// ��ȡPDF���ļ���  
				String fileName = url.getFile();  
				// ��ԭ��PDF�������������²�����txt�ļ�  
				if (fileName.length() > 4) {  
					File outputFile = new File(fileName.substring(0, fileName  
							.length() - 4)  
							+ ".txt");  
					textFile = outputFile.getName();  
				}  
			} catch (MalformedURLException e) {  
				// �����ΪURLװ�صõ��쳣����ļ�ϵͳװ��  
				//ע������Ѳ�����ǰ�汾�е�URL.����File��  
				document = PDDocument.load(new File(pdfFile));  
				if (pdfFile.length() > 4) {  
					textFile = pdfFile.substring(0, pdfFile.length() - 4)  
							+ ".txt";  
				}  
			}  
			// �ļ���������д���ļ���textFile  
			output = new OutputStreamWriter(new FileOutputStream(textFile), encoding);  
			// PDFTextStripper����ȡ�ı�  
			PDFTextStripper stripper = null;  
			stripper = new PDFTextStripper();  
			// �����Ƿ�����  
			stripper.setSortByPosition(sort);  
			// ������ʼҳ  
			stripper.setStartPage(startPage);  
			// ���ý���ҳ  
			stripper.setEndPage(endPage);  
			// ����PDFTextStripper��writeText��ȡ������ı�  
			stripper.writeText(document, output);  
		} finally {  
			if (output != null) {  
				// �ر������  
				output.close();  
			}  
			if (document != null) {  
				// �ر�PDF Document  
				document.close();  
			}  
		}  

	} 
}
