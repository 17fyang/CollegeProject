package data_yy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
	private final static String DataUrl="C:\\Users\\Administrator\\Desktop\\���ݷ���1216\\2017�걨���������";
	private final static String KeywordUrl="C:\\Users\\Administrator\\Desktop\\���ݷ���1216\\key.txt";
	private final static String resultUrl="C:\\Users\\Administrator\\Desktop\\���ݷ���1216\\��Ʊ.txt";
	public static void main(String[] args){
		Read r=new Read();
		String[][] keywords=null;
		Map<String,Double> resultMap=null;
		try {
			keywords = r.getKeyWords(KeywordUrl);
			resultMap=r.getResult(resultUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int data[][]=r.readAllData(DataUrl,keywords);
		double[] result=mapToDouble(resultMap,r);
//		System.out.println();
//		for(int i=0;i<data[0].length;i++) {
//			for(int j=0;j<data.length;j++) {
//				System.out.print(data[j][i]+"         ");
//			}
//			System.out.println();
//		}
		for(int i=0;i<data.length;i++) {
			Draw d=new Draw();
			d.addAllResult(data[i], result);
			d.showChart();
		}
		System.out.println();
		
	}
	private static double[] mapToDouble(Map<String, Double> resultMap, Read r) {
		File[] fileList=r.getFileList();
		double result[]=new double[resultMap.size()];
		for(int i=0;i<fileList.length;i++) {
			result[i]=resultMap.get(fileList[i].getName());
		}
		return result;
	}
}
