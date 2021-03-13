package dataExcavate;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class homework_1117 {
	private static String sourceFile="C:\\Users\\Administrator\\Desktop\\阿萨德.xls";
	public static void main(String[] args) {
		question1();
	}

	//数据离散化-stl
	public static void question1() {
		File resource=new File(sourceFile);
		try {
			//1:创建excel文件
			File fileResult=new File("File/h1117_1.xls");
			//2:创建工作簿
			WritableWorkbook workbookResult=Workbook.createWorkbook(fileResult);
			WritableSheet sheetResult=workbookResult.createSheet("result", 0);

			Workbook workbook=Workbook.getWorkbook(resource);
			Sheet sheet=workbook.getSheet(0);
			int arr[]={8,14,16};

			System.out.println("行："+sheet.getRows());
			System.out.println("列："+sheet.getColumns());
			
			for(int j=0;j<3;j++) {
				List<Double> list=new LinkedList<Double>();
				Set<Double> set=new HashSet<Double>();
				for(int i=1;i<sheet.getRows();i++) {
					Cell cell=sheet.getCell(arr[j],i);
					String content=cell.getContents();
					list.add(Double.parseDouble(content));
					set.add(Double.parseDouble(content));
				}

				List<Double> tempList=new LinkedList<Double>();
				Iterator<Double> it=set.iterator();
				while(it.hasNext()) {
					tempList.add(it.next());
				}
				Collections.sort(tempList);
				Map<Double,Integer> map=new HashMap<Double,Integer>();
				for(int i=0;i<tempList.size();i++) {
					map.put(tempList.get(i), i);
				}
				for(int i=0;i<list.size();i++) {
					String resultString=String.valueOf(map.get(list.get(i)));
					Label label=new Label(arr[j],i,resultString);
					//7：添加单元格
					sheetResult.addCell(label);
				}
				
			}
			//写入数据，一定记得写入数据，不然你都开始怀疑世界了，excel里面啥都没有
			workbookResult.write();
			//最后一步，关闭工作簿
			workbookResult.close();

			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}