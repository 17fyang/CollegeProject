package taidi2020.network;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class dataEcxcel {
	public static void main(String[] args) throws Exception {
		File newFile=new File("File/test.xls");
		WritableWorkbook workbook2=Workbook.createWorkbook(newFile);
		WritableSheet sheet2=workbook2.createSheet("result", 0);
		
		File readFile=new File("File/my.xls");
		Workbook workbook=Workbook.getWorkbook(readFile);
		Sheet sheet=workbook.getSheet(0);
//		File readFile0=new File("File/predict.xls");
//		Workbook workbook0=Workbook.getWorkbook(readFile0);
//		Sheet sheet0=workbook0.getSheet(0);
		File readFile1=new File("File/bert.xls");
		Workbook workbook1=Workbook.getWorkbook(readFile1);
		Sheet sheet1=workbook1.getSheet(0);
		
		
		int startCol=6;
		for(int i=1;i<sheet.getRows();i++) {
			String s1=sheet.getCell(1,i).getContents();
//			String s2=sheet0.getCell(5,i).getContents();
			String s3=sheet1.getCell(1,i).getContents();
			String result="No";
			if(s1.equals(s3))	result="Yes";
			sheet2.addCell(new Label(startCol,i,s1));
//			sheet2.addCell(new Label(startCol+1,i,s2));
			sheet2.addCell(new Label(startCol+2,i,s3));
			sheet2.addCell(new Label(startCol+3,i,result));
		}
		
		
		//写入并关闭
		workbook2.write();
		workbook2.close();
	}
	
	private static void copy(Sheet sheet,WritableSheet sheet2,int i) throws Exception {
		for(int j=0;j<sheet.getColumns();j++) {
			sheet2.addCell(new Label(j,i,sheet.getCell(j,i).getContents()));
		}
	}
}
