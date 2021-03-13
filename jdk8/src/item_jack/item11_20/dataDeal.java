package item_jack.item11_20;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import item_jack.item11_20.utils.Config;
import item_jack.item11_20.utils.ConfigRead;
import item_jack.item11_20.utils.OutData;
import item_jack.item11_20.utils.Record;
import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
/*
 * date:2019-11-20
 * author:杨帆
 * request：表1中正好5个数据组成5个数字，满足53322的格式，
 * 这5个数字同时包含表2中3条数据。
 * 这个数字的53符合表3中的某一条数据
 * 其中出现3次和3次的那个数字，必须在表4中
 */

public class dataDeal {
	public static void main(String[] args) {
		System.out.println("item_1120");
		System.out.println("version：1.0");
		System.out.println("date:2019-11-20\r\n" + 
				" * request：表1中正好5个数据组成5个数字，满足53322的格式，\r\n" + 
				" *                   这5个数字同时包含表2中3条数据。\r\n" + 
				" *                   这个数字的53符合表3中的某一条数据\r\n" + 
				" *                   其中出现3次和3次的那个数字，必须在表4中");
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
		//第2.1步：将数据源文件中的数据取出，去重，生成一个list对象
		List<Record> screenList2=null;
		List<Record> screenList3=null;
		List<Record> screenList4=null;
		try {
			screenList2=getScreenData(config.getScreen_table2());
			screenList3=getScreenData(config.getScreen_table3());
			screenList4=getScreenData(config.getScreen_table4());
		} catch (Exception e) {
			System.out.println("生成数据源2.1对象失败！！");
			e.printStackTrace();
			return;
		}
		System.out.println("去重后数据量："+originalList.size());

		//第三步：第一次数据处理，得到初步的五位数字排列,返回未满的数字排列
		System.out.println("数据处理：生成候补排列......");
		List<Set<String>> firstDealList=null;
		try {
			firstDealList=getFirstDealResult(originalList,finalSet);
		} catch (Exception e) {
			System.out.println("第一次数据处理失败！！");
			e.printStackTrace();
			return;
		}
		System.out.println("生成候补排列："+originalList.size()+"/"+originalList.size()+"......");
		//		第四步：第二次数据处理，往最终排列中补充遗漏的排列，返回未满的排列
		//		List<Set<String>> secondDealList=null;
		//		try {
		//			secondDealList=getSecondDealResult(originalList,firstDealList,finalSet);
		//		} catch (Exception e) {
		//			System.out.println("第一次数据处理失败！！");
		//			e.printStackTrace();
		//			return;
		//		}

		//第五步：根据53322的规则筛选排列
		System.out.println("开始第一阶段筛选......");
		List<OutData> outList=null;
		try {
			outList=chooseResult(originalList,finalSet);
		} catch (Exception e) {
			System.out.println("数据匹配失败！！");
			e.printStackTrace();
			return;
		}
		System.out.println("第一阶段筛选：100%");
		System.out.println("满足53322排列数据条数："+outList.size());
		
		//第六步：在53322筛选的基础上继续筛选
		System.out.println("开始第二阶段筛选......");
		List<OutData> screenList=null;
		try {
			screenList=screen(outList,screenList2,screenList3,screenList4);
		} catch (Exception e) {
			System.out.println("筛选数据失败！！");
			e.printStackTrace();
			return;
		}
		System.out.println("第二阶段筛选："+outList.size()+"/"+outList.size()+"......");
		System.out.println("满足筛选表条件数据条数："+screenList.size());
		
		//第七步：将结果数据写回文件
		System.out.println("正在将数据写回文件......");
		try {
			writeResult(screenList,config.getDataDeal_aim());
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
				if(stringSetTemp.size()==beforeLength)		continue;
				Record record=new Record();
				record.setDataString(content);
				record.setDataSet(dataSourceSet);
				resultSet.add(record);
			}
		}
		List<Record> resultList=new LinkedList<Record>();
		Iterator<Record> it=resultSet.iterator();
		while(it.hasNext())	resultList.add(it.next());
		return resultList;
	}

	//第2.1步：将数据源文件中的数据取出，去重，生成一个list对象
	private static List<Record> getScreenData(File screen_table) throws Exception {
		Workbook workbook=Workbook.getWorkbook(screen_table);
		Sheet sheet=workbook.getSheet(0);
		Set<Record> resultSet=new HashSet<Record>();
		Set<Set<String>> stringSetTemp=new HashSet<Set<String>>();
		for(int i=0;i<sheet.getRows();i++) {
			for(int j=0;j<sheet.getColumns();j++) {
				Cell cell=sheet.getCell(j,i);
				String content=cell.getContents();
				if(content==null || content.equals(""))	continue;
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
				resultSet.add(record);
			}
		}
		List<Record> resultList=new LinkedList<Record>();
		Iterator<Record> it=resultSet.iterator();
		while(it.hasNext())	resultList.add(it.next());
		return resultList;
	}
	
	//第三步：第一次数据处理，得到初步的五位数字排列
	private static List<Set<String>> getFirstDealResult(List<Record> originalList,Set<Set<String>> finalSet) throws Exception{
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

					if(firstSet.size()<5)		firstResultSet.add(firstSet);
					else if(firstSet.size()==5)	finalSet.add(firstSet);
				}
			}
		}
		List<Set<String>> firstResultList=new LinkedList<Set<String>>();
		Iterator<Set<String>> it=firstResultSet.iterator();
		while(it.hasNext())	firstResultList.add(it.next());
		return firstResultList;
	}

	//第四步：第二次数据处理，补充遗漏的排列
	private static List<Set<String>> getSecondDealResult(List<Record> originalList,List<Set<String>> firstDealList,Set<Set<String>> finalSet) {
		Set<Set<String>> secondResultSet=new HashSet<Set<String>>();
		int listLength=firstDealList.size();
		for(int i=0;i<listLength;i++) {
			for(int j=0;j<originalList.size();j++) {
				Set<String> secondSet=new HashSet<String>(firstDealList.get(i));
				secondSet.addAll(originalList.get(j).getDataSet());
				if(secondSet.size()<5)		secondResultSet.add(secondSet);
				else if(secondSet.size()==5)	finalSet.add(secondSet);
			}

		}


		List<Set<String>> secondResultList=new LinkedList<Set<String>>();
		Iterator<Set<String>> it=secondResultSet.iterator();
		while(it.hasNext())	secondResultList.add(it.next());
		return secondResultList;
	}

	//第五步：根据53322的规则筛选排列
	private static List<OutData> chooseResult(List<Record> originalList, Set<Set<String>> finalSet) {
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

			//判断是不是53322
			if(recordList.size()==5) {
				Map<String,Integer> map=new HashMap<String,Integer>();
				for(int i=0;i<recordList.size();i++) {
					Record record=recordList.get(i);
					Iterator<String> it=record.getDataSet().iterator();
					while(it.hasNext()) {
						String s=it.next();
						if(map.get(s)==null)	map.put(s,0);
						map.put(s, map.get(s)+1);
					}
				}

				int fiveTimes=0;
				int threeTimes=0;
				int twoTimes=0;
				for(int i:map.values()) {
					if(i==2)	twoTimes++;
					else if(i==3)	threeTimes++;
					else if(i==5)	fiveTimes++;
				}

				if(fiveTimes==1 && threeTimes==2 && twoTimes==2) {
					OutData out=new OutData();
					out.setOutList(recordList);
					out.setOutSet(finalSingleSet);
					resultList.add(out);
				}
			}
		}

		return resultList;
	}

	//第六步：在53322筛选的基础上继续筛选
	private static List<OutData> screen(List<OutData> outList, List<Record> screenList2, List<Record> screenList3, List<Record> screenList4) {
		List<OutData> screenResultList=new LinkedList<OutData>();
		for(int i=0;i<outList.size();i++) {
			if(i%5==0)		System.out.println("第二阶段筛选："+i+"/"+outList.size()+"......");
			OutData out=outList.get(i);
			//1、筛选五个数字中含有表2中三条;
			int match2=0;
			for(int j=0;j<screenList2.size();j++) {
				Set<String> set=new HashSet<String>(out.getOutSet());
				int beforeLength=set.size();
				set.addAll(screenList2.get(j).getDataSet());
				if(set.size()==beforeLength) 	match2++;
			}
			if(match2<3)	continue;
			
			//提取出53和3的集合
			List<Record> recordList=out.getOutList();
			Map<String,Integer> map=new HashMap<String,Integer>();
			for(int j=0;j<recordList.size();j++) {
				Record record=recordList.get(j);
				Iterator<String> it=record.getDataSet().iterator();
				while(it.hasNext()) {
					String s=it.next();
					if(map.get(s)==null)	map.put(s,0);
					map.put(s, map.get(s)+1);
				}
			}
			Set<String> fiveThreeSet=new  HashSet<String>();
			Set<String> threeSet=new  HashSet<String>();
			for(Entry<String,Integer> entry:map.entrySet()) {
				if(entry.getValue()==3)		threeSet.add(entry.getKey());
				if(entry.getValue()>2)		fiveThreeSet.add(entry.getKey());
			}
			
			//Todo
			//这个数字的53符合表3中的某一条数据？？？？有点问题
			int match3=0;
			for(int j=0;j<screenList3.size();j++) {
				Set<String> set=new HashSet<String>(fiveThreeSet);
				int beforeLength=set.size();
				set.addAll(screenList3.get(j).getDataSet());
				if(set.size()==beforeLength) 	match3++;
			}
			if(match3<1)	continue;
			
			//其中出现3次和3次的那个数字，必须在表4中
			int match4=0;
			for(int j=0;j<screenList4.size();j++) {
				Set<String> set=new HashSet<String>(threeSet);
				int beforeLength=set.size();
				set.addAll(screenList4.get(j).getDataSet());
				if(set.size()==beforeLength) 	match4++;
			}
			if(match4<1)		continue;
			
			screenResultList.add(out);
		}
		return screenResultList;
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
				label=new Label(2+j,i,list.get(j).getDataString());
				sheet.addCell(label);
			}
		}
		CellView cv = new CellView();
		cv.setAutosize(true);
		for(int i=0;i<sheet.getColumns();i++) {
			if(i==1)	continue;
			sheet.setColumnView(i, cv);
		}
		sheet.setColumnView(1, 17);
		
		//写入并关闭
		workbook.write();
		workbook.close();
	}

}