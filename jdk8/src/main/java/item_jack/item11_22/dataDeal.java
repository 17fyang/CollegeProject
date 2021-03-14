package item_jack.item11_22;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import item_jack.item11_22.utils.Config;
import item_jack.item11_22.utils.ConfigRead;
import item_jack.item11_22.utils.OutData;
import item_jack.item11_22.utils.Record;
import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
/*
 * date:2019-11-22
 * author:杨帆
 * request：表1中正好6条或者6条以上的数据组成6个数字
 * 根据配置文件中的筛选数据筛选，都包含这些数据则保留
 */

public class dataDeal {
	public static void main(String[] args) {
		System.out.println("item_1122");
		System.out.println("version：1.0");
		System.out.println("date:2019-11-22\r\n" + 
				" *request：表1中正好6条或者6条以上的数据组成6个数字\r\n" + 
				" * 				  根据配置文件中的筛选数据筛选，都包含这些数据则保留");
		System.out.println();
		System.out.println();
		try {
			run();
			System.out.print("数据处理完毕，");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print("数据处理出错，");
		}
		System.out.println("按任意键退出！");
		Scanner sc = new Scanner(System.in); 
		sc.next();
		sc.close();
	}
	public static void run() {
		//第一步：读取配置文件中的内容
		System.out.println("正在读取配置文件......");
		ConfigRead configRead=new ConfigRead();
		Config config=null;
		try {
			config=configRead.dataDealConfig();
		} catch (Exception e) {
			System.out.println("读取配置文件失败！！");
			e.printStackTrace();
			return;
		}

		//定义最终的排列数据集
		Set<Set<String>> finalSet=new HashSet<Set<String>>();


		//第二步：将数据源文件中的数据取出，去重，生成一个list对象
		System.out.println("正在读入数据源文件......");
		List<Record> originalList=null;
		try {
			originalList=getOriginalData(config.getDataDeal_source());
		} catch (Exception e) {
			System.out.println("生成数据源对象失败！！");
			e.printStackTrace();
			return;
		}

		//第三步：第一次数据处理，得到初步的五位数字排列,返回未满的数字排列
		System.out.println("数据处理：生成候补排列......");
		List<Set<String>> firstDealList=null;
		try {
			firstDealList=getFirstDealResult(originalList,finalSet,config);
		} catch (Exception e) {
			System.out.println("第一次数据处理失败！！");
			e.printStackTrace();
			return;
		}
		System.out.println("生成候补排列："+originalList.size()+"/"+originalList.size()+"......");
		
		//第五步：根据筛选规则筛选排列：满足lineNumber和筛选条件
		System.out.println("开始第一阶段筛选......");
		List<OutData> outList=null;
		try {
			outList=chooseResult(originalList,finalSet,config);
		} catch (Exception e) {
			System.out.println("数据匹配失败！！");
			e.printStackTrace();
			return;
		}
		System.out.println("第一阶段筛选：100%");
		System.out.println("满足"+config.getLineNumber()+"行排列数据条数："+outList.size());
		
		//第七步：将结果数据写回文件
		System.out.println("正在将数据写回文件......");
		try {
			writeResult(outList,config.getDataDeal_aim());
		} catch (Exception e) {
			System.out.println("写回数据失败！！");
			e.printStackTrace();
			return;
		}
	}

	//第二步：将数据源文件中的数据取出，去重，生成一个list对象
	private static List<Record> getOriginalData(File dataDeal_source) throws Exception{
		Workbook workbook=Workbook.getWorkbook(dataDeal_source);
		Sheet sheet=workbook.getSheet(0);
		Set<Record> resultSet=new HashSet<Record>();
		Set<Set<String>> stringSetTemp=new HashSet<Set<String>>();
		Map<Set<String>,List<Integer>>	sourceLocateMap=new HashMap<Set<String>,List<Integer>>();
		for(int i=0;i<sheet.getRows();i++) {
			for(int j=0;j<sheet.getColumns();j++) {
				Cell cell=sheet.getCell(j,i);
				String content=cell.getContents();
				if(content==null || content.equals(""))	continue;
				String dataString=content.substring(0,content.length()-4);
				String spiltString[]=dataString.split("\\D+");
				Set<String> dataSourceSet=new HashSet<String>();
				for(int k=0;k<spiltString.length;k++) {
					if(spiltString[k]==null || spiltString[k].equals(""))		continue;   	
					dataSourceSet.add(spiltString[k]);
				}
				int beforeLength=stringSetTemp.size();
				stringSetTemp.add(dataSourceSet);
				if(stringSetTemp.size()==beforeLength) {
					List<Integer> tempList=sourceLocateMap.get(dataSourceSet);
					tempList.add(j);
					sourceLocateMap.put(dataSourceSet, tempList);
					continue;
				}
				List<Integer> list=new LinkedList<Integer>();
				list.add(j);
				sourceLocateMap.put(dataSourceSet, list);
				
				Record record=new Record();
				record.setDataString(content);
				record.setDataSet(dataSourceSet);
				resultSet.add(record);
			}
		}
		List<Record> resultList=new LinkedList<Record>();
		Iterator<Record> it=resultSet.iterator();
		while(it.hasNext()) {
			Record record=it.next();
			record.setLocateList(sourceLocateMap.get(record.getDataSet()));
			resultList.add(record);
		}
		return resultList;
	}

	
	//第三步：第一次数据处理，得到初步的五位数字排列
	private static List<Set<String>> getFirstDealResult(List<Record> originalList,Set<Set<String>> finalSet, Config config) throws Exception{
		Set<Set<String>> firstResultSet=new HashSet<Set<String>>();
		int listLength=originalList.size();
		for(int i=0;i<listLength;i++) {
			if(i%5==0)		System.out.println("生成候补排列："+(i+1)+"/"+listLength+"......");
			Record record1=originalList.get(i);
			for(int j=i+1;j<listLength;j++) {
				Record record2=originalList.get(j);
				for(int k=j+1;k<listLength;k++) {
					Record record3=originalList.get(k);

					Set<String> firstSet=new HashSet<String>();
					firstSet.addAll(record1.getDataSet());
					firstSet.addAll(record2.getDataSet());
					firstSet.addAll(record3.getDataSet());

					if(firstSet.size()<6)		firstResultSet.add(firstSet);
					else if(firstSet.size()==6)	finalSet.add(firstSet);
				}
			}
		}
		List<Set<String>> firstResultList=new LinkedList<Set<String>>();
		Iterator<Set<String>> it=firstResultSet.iterator();
		while(it.hasNext())	firstResultList.add(it.next());
		return firstResultList;
	}
	
	//第五步：根据lineNumber和Condition筛选排排列
	private static List<OutData> chooseResult(List<Record> originalList, Set<Set<String>> finalSet, Config config) {
		Set<String> conditionSet=new HashSet<String>(config.getConditionList());
		int lineNumber=config.getLineNumber();
		List<OutData> resultList=new LinkedList<OutData>();
		Iterator<Set<String>> finalIt=finalSet.iterator();
		int total=finalSet.size();
		int count=0;
		while(finalIt.hasNext()) {
			//计数输出
			count++;
			double temp=(int)(count*1.0/total*10000)*1.0/100;
			if(count % 1000==0)		System.out.println("第一阶段筛选："+temp+"%");
			
			Set<String> finalSingleSet=finalIt.next();
			List<Record> recordList=new LinkedList<Record>();
			for(int i=0;i<originalList.size();i++) {
				Set<String> tempSet=new HashSet<String>(finalSingleSet);
				int beforeLength=tempSet.size();
				tempSet.addAll(originalList.get(i).getDataSet());
				if(tempSet.size()==beforeLength)	recordList.add(originalList.get(i));
			}
			
			//判断是否满足条件
			if(recordList.size()<lineNumber)		continue;
			Set<String> testSet=new HashSet<String>(finalSingleSet);
			int beforeLength=testSet.size();
			testSet.addAll(conditionSet);
			if(testSet.size()!=beforeLength)		continue;
			
			OutData out=new OutData();
			out.setOutList(recordList);
			out.setOutSet(finalSingleSet);
			resultList.add(out);
		}

		return resultList;
	}


	//第七步：将结果数据写回文件
	private static void writeResult(List<OutData> outList, File f) throws Exception{
		WritableWorkbook workbook=Workbook.createWorkbook(f);
		WritableSheet sheet=workbook.createSheet("result", 0);
		
		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			StringBuilder sb=new StringBuilder();
			sb.append("(");
			List<String> rankedList=new LinkedList<String>(out.getOutSet());
			Collections.sort(rankedList);
			for(int j=0;j<rankedList.size();j++) {
				sb.append(rankedList.get(j)+",");
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append(")");
			Label label=new Label(0,i,sb.toString());
			sheet.addCell(label);
			
			List<Record> list=out.getOutList();
			for(int j=0;j<list.size();j++) {
				Record record=list.get(j);
				label=new Label(2+j,i,record.getDataString());
				sheet.addCell(label);
				
				List<Integer> integerList=record.getLocateList();
				Collections.sort(integerList);
				StringBuilder sb2=new StringBuilder();
				for(int locate:integerList) 		sb2.append(String.valueOf((char)(locate+65)));
				Label label2=new Label(15+j,i,sb2.toString());
				sheet.addCell(label2);
			}
		}
		CellView cv = new CellView();
		cv.setAutosize(true);
		for(int i=0;i<sheet.getColumns();i++) {
			if(i<15)	sheet.setColumnView(i, 24);
			else 	sheet.setColumnView(i, 8);
			
		}
		sheet.setColumnView(1, 10);
		
		//写入并关闭
		workbook.write();
		workbook.close();
	}

}