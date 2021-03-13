package data_yy;

public class Main {
	private final static String DataUrl="C:\\Users\\Administrator\\Desktop\\数据分析1216\\2017年报讨论与分析";
	private final static String ResultUrl="File/resu5lt.txt";
	public static void main(String[] args) throws Exception {
		Read r=new Read();
		Draw d=new Draw();
		String[] keywords= {"文件","目录"};
		int data[][]=r.readAllData(DataUrl,keywords);
		double result = r.readOneResult(ResultUrl);
//		data=new int[2];
//		data[0]=2;
//		result=3.2;
//		d.addOneResult(data[0],result);
		d.showChart();
		
	}
}
