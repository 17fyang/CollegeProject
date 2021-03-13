package dataExcavate;

import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class homework_1019 {
	//�۲ⴰ���ܻ�������,�۲ⴰ���ܷ��й�����,�۲ⴰ���ܼ�Ȩ���й�����,�۲ⴰ�ڼ���ƽ�����������ۻ�,ƽ���ۿ���
	static double min[]= {200,760,267.96,25,0.14};
	static double max[]= {286164,234721,240843.6,35770.5,1.026084586};
	static int row[]= {2,8,10,12,14};
	
	static double averge[]= {11045.49465,16979.69,12534.95432,1439.208053,0.706290428};
	static double cha[]= {310773874.6,504858420.9,335053166,5111880.94,0.035660807};
	
	public static void main(String[] args) {
//		double e=normalization(0.20651,0.0006989,1,8,100);
//		System.out.println(e);
		question4_2();
	}
	
	//�����ʵڶ�С����滮
		public static void question4_2() {
			File resource=new File("File/f1.xls");
			try {
				
				 //1:����excel�ļ�
				 File fileResult=new File("File/result_zero.xls");
				//2:����������
				WritableWorkbook workbookResult=Workbook.createWorkbook(fileResult);
				WritableSheet sheetResult=workbookResult.createSheet("result", 0);
				
				Workbook workbook=Workbook.getWorkbook(resource);
				Sheet sheet=workbook.getSheet(1);
				System.out.println("�У�"+sheet.getRows());
				System.out.println("�У�"+sheet.getColumns());
				for(int j=0;j<5;j++) {
					for(int i=2;i<sheet.getRows()-1;i++) {
						Cell cell=sheet.getCell(row[j],i);
		                String content=cell.getContents();
//		                System.out.println(content+"  "+i);
		                Double d=Double.valueOf(content);
		                Double result=normalization_zero(d,averge[j],cha[j]);
		               String resultString= String.valueOf(result);
		                
		                Label label=new Label(row[j],i,resultString);
		   			 //7����ӵ�Ԫ��
		   			sheetResult.addCell(label);
					}
				}
				
				//д�����ݣ�һ���ǵ�д�����ݣ���Ȼ�㶼��ʼ���������ˣ�excel����ɶ��û��
				workbookResult.write();
				//���һ�����رչ�����
				workbookResult.close();
				
		        workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	//������
	public static void question4() {
		File resource=new File("File/f1.xls");
		try {
			
			 //1:����excel�ļ�
			 File fileResult=new File("File/result.xls");
			//2:����������
			WritableWorkbook workbookResult=Workbook.createWorkbook(fileResult);
			WritableSheet sheetResult=workbookResult.createSheet("result", 0);
			
//			Label label=new Label(2,5,"ds");
//			 //7����ӵ�Ԫ��
//			sheetResult.addCell(label);
			
			
			
			Workbook workbook=Workbook.getWorkbook(resource);
			Sheet sheet=workbook.getSheet(1);
			System.out.println("�У�"+sheet.getRows());
			System.out.println("�У�"+sheet.getColumns());
			for(int j=0;j<5;j++) {
				for(int i=2;i<sheet.getRows()-1;i++) {
					Cell cell=sheet.getCell(row[j],i);
	                String content=cell.getContents();
//	                System.out.println(content+"  "+i);
	                Double d=Double.valueOf(content);
	                Double result=normalization(d,min[j],max[j],10,100);
	               String resultString= String.valueOf(result);
	                
	                Label label=new Label(row[j],i,resultString);
	   			 //7����ӵ�Ԫ��
	   			sheetResult.addCell(label);
				}
			}
			
			//д�����ݣ�һ���ǵ�д�����ݣ���Ȼ�㶼��ʼ���������ˣ�excel����ɶ��û��
			workbookResult.write();
			//���һ�����رչ�����
			workbookResult.close();
			
	        workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /** 
     * @author 
     * @param v 
     *            ����׼������������ 
     * @param Min 
     *            ����������Сֵ 
     * @param Max 
     *            �����������ֵ 
     * @param newMin 
     *            �µ�ӳ��������Сֵ 
     * @param newMax 
     *            �µ�ӳ���������ֵ 
     * @return 
     */  
	//�����С�淶��
	public static double normalization(double v, double Min, double Max,  
            double newMin, double newMax) {  
        return (v - Min) / (Max - Min) * (newMax - newMin) + newMin;  
    }  
	//��滮
		public static double normalization_zero(double v,double averge,double cha) {  
	        return (v - averge) / cha;  
	    }  
}
