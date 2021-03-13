package others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class demo {
	public static String fileUrl="C:\\Users\\Administrator\\Desktop\\test.xls";
	public static String fileUrl2="C:\\Users\\Administrator\\Desktop\\test.xls";
	public static String newFileUrl="C:\\Users\\Administrator\\Desktop\\result.xls";
	public static void main(String[] args) throws Exception {
		File f=new File(fileUrl);
		Workbook workbook=new XSSFWorkbook(new FileInputStream(fileUrl));
		Sheet sheet=workbook.getSheetAt(0);
		File f2=new File(fileUrl2);
		Workbook workbook2=new XSSFWorkbook(new FileInputStream(fileUrl2));
		Sheet sheet2=workbook2.getSheetAt(0);
		
		Workbook newBook=new XSSFWorkbook();
		Sheet newSheet = newBook.createSheet();
		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			Row row =sheet.getRow(i);
			Row row2 =sheet2.getRow(i);
			Row newRow =newSheet.createRow(i);
			for(int j=row.getFirstCellNum();j< row.getLastCellNum();j++){
				double value1=Double.parseDouble(row.getCell(j).getStringCellValue());
				double value2=Double.parseDouble(row2.getCell(j).getStringCellValue());
				double newValue=value1*0.7+value2*0.3;
				Cell dataCell = newRow.createCell(i);
				dataCell.setCellValue(String.valueOf(newValue));
			}
		}
		
		newBook.write(new FileOutputStream(newFileUrl));
		newBook.close();
	}
}
