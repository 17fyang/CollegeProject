package data_yy;

import java.io.File;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

public class test {
	public static void main(String[] args) {
		String url="C:\\Users\\Administrator\\Desktop\\���ݷ���1216\\2017�걨���������\\�й�ʯ��.pdf";
		File f=new File(url);
		String content=FileToString(f);
		System.out.println(content);
	}
	//�ļ�תstring
	private static String FileToString(File f){
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
}
