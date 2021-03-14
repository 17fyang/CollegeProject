package item_jack.item2_21.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/*
 * 筛选标准：
 * 1、有两行或以上的行中，都有三个或以上的数据组成同样的五个数据排列
*  2、有一行或以上的行中，都有两个或以上的数据组成一个四个数据排列，这四个数据都在五个数据排列中");
*  2.23增加条件：
 * 筛选出来的数据要在表3中出现3次或以上
 * 结果中打印出在表1中的列位置
 */
public class Screen {
	private static final int conditionLength=3;
	private static Screen screen=null;//单例模式
	public static String fileUrl=Config.fileUrl;
	private static File[] screenFile=new File[conditionLength];
	
	
	private Screen() throws Exception {
		File f=new File(fileUrl);
		InputStream fileIn=new FileInputStream(f);
		Properties p=new Properties();
		p.load(fileIn);
		for(int i=0;i<conditionLength;i++) {
			String s="condition"+(i+1);
			String value=p.getProperty(s);
			if(value!=null && !value.equals("OFF") && !value.equals("off")) {
				String urlKey="screen_table"+(i+1);
				screenFile[i]=new File(p.getProperty(urlKey));
			}
		}
		fileIn.close();
	}
	public static Screen getScreen() throws Exception{
		if(screen==null)	screen=new Screen();
		return screen;
	}
	
	
	public  List<OutData> startScreen(List<OutData> outList) throws Exception{
		List<OutData> resultList=outList;
		if(screenFile[0]!=null)	resultList=condition1(resultList,screenFile[0]);
		if(screenFile[1]!=null)	resultList=condition2(resultList,screenFile[1],3);
		if(screenFile[2]!=null)	resultList=condition2(resultList,screenFile[2],2);
		return resultList;
	}
	
	/*
	 * 解决思路：
	 * 1、检测每个排列在该行是否有三个以上的子集
	 * 2、检测每个排列的子排列在该行是否有两个以上的子集
	 */
	private List<OutData> condition1(List<OutData> outList,File conditionFile) throws Exception {
		List<OutData> resultList=new LinkedList<OutData>();
		Record[][] screenList=this.getScreenData(conditionFile);
		for(OutData data:outList) {
			//1、检测每个排列在该行是否有三个以上的子集
			List<Integer> passRow=new LinkedList<Integer>();//满足条件的行
			for(int i=0;i<screenList.length;i++) {
				int passNumber=0;
				for(int j=0;j<screenList[i].length;j++) {
					Set<String> testSet=new HashSet<String>(data.getOutSet());
					int beforeLength=testSet.size();
					testSet.addAll(screenList[i][j].getDataSet());
					if(testSet.size()==beforeLength) 	passNumber++;
				}
				if(passNumber>=3)		passRow.add(i);
			}
			//2、检测每个排列的子排列在该行是否有两个以上的子集
			List<Integer> passRow2=new LinkedList<Integer>();//满足条件的行
			for(int i=0;i<screenList.length;i++) {
				Iterator<String> it=data.getOutSet().iterator();
				while(it.hasNext()) {
					String s=it.next();
					Set<String> testSet=new HashSet<String>(data.getOutSet());
					testSet.remove(s);
					int passNumber=0;
					for(int j=0;j<screenList[i].length;j++) {
						Set<String> tempSet=new HashSet<String>(testSet);
						int beforeLength=tempSet.size();
						tempSet.addAll(screenList[i][j].getDataSet());
						if(tempSet.size()==beforeLength) 	passNumber++;
					}
					if(passNumber>=2)	{
						passRow2.add(i);
						break;
					}
				}
			}
			
			//统计次数，判定能不能保留
			boolean isPass=true;
			if(passRow.size()<2)	isPass=false;
			if(passRow2.isEmpty())		isPass=false;
			Set<Integer> mergeSet=new HashSet<Integer>(passRow);
			mergeSet.addAll(passRow2);
			if(mergeSet.size()<3)	isPass=false;
			if(isPass==true)	resultList.add(data);
		}
		return resultList;
	}
	
	//附加条件：筛选出来的数据要在表中出现times次或以上
	private List<OutData> condition2(List<OutData> outList,File conditionFile,int times) throws Exception {
		List<OutData> resultList=new LinkedList<OutData>();
		List<Record> screenList=Config.getOriginalData(conditionFile, 2);
		for(OutData data:outList) {
			int screenTimes=0;
			for(Record record:screenList) {
				Set<String> screenSet=new HashSet<String>(data.getOutSet());
				int beforeLength=screenSet.size();
				screenSet.addAll(record.getDataSet());
				if(screenSet.size()==beforeLength) {
					screenTimes++;
				}
			}
			if(screenTimes>=times)		resultList.add(data);
		}
		return resultList;
	}
	
	//将数据源文件中的数据取出，去重，生成一个二维数组
		private Record[][] getScreenData(File screen_table) throws Exception {
			Workbook workbook=Workbook.getWorkbook(screen_table);
			Sheet sheet=workbook.getSheet(0);
			List<Record[]> resultList=new ArrayList<Record[]>();
			for(int i=0;i<sheet.getRows();i++) {
				Set<Set<String>> stringSetTemp=new HashSet<Set<String>>();//去重
				List<Record> recordList=new ArrayList<Record>();
				for(int j=0;j<sheet.getColumns();j++) {
					Cell cell=sheet.getCell(j,i);
					String content=cell.getContents();
					if(content==null || content.equals(""))	continue;
					content=content.substring(0,content.length()-5);
					String spiltString[]=content.split("\\D+");
					Set<String> dataSourceSet=new HashSet<String>();
					for(int k=0;k<spiltString.length;k++) {
						if(spiltString[k]==null || spiltString[k].equals(""))		continue;   	
						dataSourceSet.add(spiltString[k]);
					}
					int beforeLength=stringSetTemp.size();
					stringSetTemp.add(dataSourceSet);
					if(stringSetTemp.size()==beforeLength)		continue;
					Record record=new Record();
					record.setDataSet(dataSourceSet);
					recordList.add(record);
				}
				Record[] r=recordList.toArray(new Record[recordList.size()]);
				resultList.add(r);
			}
			
			Record[][] r=resultList.toArray(new Record[resultList.size()][]);
			return r;
		}
		
}
