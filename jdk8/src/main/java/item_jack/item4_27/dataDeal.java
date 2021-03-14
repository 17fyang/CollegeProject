package item_jack.item4_27;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import item_jack.item4_27.utils.Config;
import item_jack.item4_27.utils.OutData;
import item_jack.item4_27.utils.Record;
import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class dataDeal {

	public static void main(String[] args) {
		System.out.println("item4_27");
		System.out.println("version：1.0");
		System.out.println("date:2020.4.27\r\n" + 
				"表1中正好6条或以上条数据组成一次六个数据排列，根据每个数据的冒号后面的数字排序，最小的排1号，然后2号。。。。。\r\n" + 
				"* 1、有1行或以上的行中，正好有4个数据在同一行上\r\n" + 
				"* 2、有1行或以上的行中，正好有3个数据在同一行上\r\n" + 
				"* 3、这4个数据和3个数据必须是在所有排序中排在前6位，而且都要出现。\r\n\r\n");
		try {
			System.out.println(System.currentTimeMillis());
			run();
			System.out.println(System.currentTimeMillis());
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
	public static void run() throws Exception {
		//将数据源文件中的数据取出，去重，生成一个list对象
		System.out.println("正在读入数据源文件......");
		Config config=Config.getConfig();
		Record[] originalList=config.getSourceList();
		System.out.println("去重后数据量："+originalList.length);

		//数据处理，获取满足条件的排列
		System.out.println("数据处理：生成候补排列......");
		System.out.println("此过程可能占用较长时间且没有提示，请耐心等待......");
		Queue<Set<String>> finalSet=getFirstDealResult(originalList,5);
		System.out.println("候补排列数："+finalSet.size());

		//根据符合要求的规则筛选排列
		System.out.println("开始检测排列......");
		List<List<Record>> lineList=config.getLineList();
		finalSet=chooseResult(lineList,finalSet,5,new int[] {3,3});
		System.out.println("满足排列数据条数："+finalSet.size());
		
		//根据符合要求的规则筛选排列
		System.out.println("开始增加排列......");
		finalSet=addRanked(originalList,finalSet,6);
		
		//根据符合要求的规则筛选排列
		System.out.println("开始检测排列2......");
		List<OutData> outList=chooseResult2(lineList,finalSet);
		lineList=null;
		System.out.println("满足排列数据条数："+outList.size());

		//将结果数据写回文件
		System.out.println("正在将数据写回文件......");
		writeResult(outList,config.getDataDeal_aim());
	}
	
	
	private static Queue<Set<String>> addRanked(Record[] originalList, Queue<Set<String>> finalSet,int n) {
		Set<Set<String>>  set=new HashSet<Set<String>>();
		while(!finalSet.isEmpty()) {
			Set<String>single=finalSet.poll();
			for(int i=0;i<originalList.length;i++) {
				Set<String> temp=new HashSet<String>(single);
				temp.addAll(originalList[i].getDataSet());
				if(temp.size()==n)	set.add(temp);
			}
		}
		Queue<Set<String>> queue=new LinkedList<Set<String>>(set);
		return queue;
	}
	//第三步：第一次数据处理，得到初步的n位数字排列
	private static Queue<Set<String>> getFirstDealResult(Record[] originalList,int n) throws Exception{
		Set<Set<String>> finalSet=new HashSet<Set<String>>();
		Set<Set<String>> leftSet=new HashSet<Set<String>>();
		int listLength=originalList.length;
		//第一轮添加
		for(int i=0;i<listLength;i++) {
			for(int j=i+1;j<listLength;j++) {
				Set<String> firstSet=new HashSet<String>(originalList[i].getDataSet());
				firstSet.addAll(originalList[j].getDataSet());
				if(firstSet.size()==n)	finalSet.add(firstSet);
				else if(firstSet.size()<n)		leftSet.add(firstSet);
			}
		}
		Set<Set<String>> leftSetTemp=new HashSet<Set<String>>();
		for(int t=0;t<n;t++) {
			if(!leftSetTemp.isEmpty()) {
				leftSet=leftSetTemp;
				leftSetTemp=new HashSet<Set<String>>();
			}
			Iterator<Set<String>> it=leftSet.iterator();
			while(it.hasNext()) {
				Set<String> set=it.next();
				int beforeLength=set.size();
				for(int i=0;i<listLength;i++) {
					int add=0;
					Iterator<String> it2=originalList[i].getDataSet().iterator();
					while(it2.hasNext())		if(set.contains(it2.next()))	add++;
					if(add==0)		continue;
					if((beforeLength+add)>n)	continue;
					Set<String> tempSet=new HashSet<String>(set);
					tempSet.addAll(originalList[i].getDataSet());
					if((beforeLength+add)==n)	finalSet.add(tempSet);
					else if((beforeLength+add)<n)		leftSetTemp.add(tempSet);
				}
			}
		}

		Queue<Set<String>> queue=new LinkedList<Set<String>>(finalSet);
		return queue;
	}

	//第五步：根据规则筛选排列(六排列)
		private static List<OutData> chooseResult2(List<List<Record>> lineList, Queue<Set<String>> queue) {
			List<OutData> resultList=new ArrayList<OutData>();
			int count=0;
			int total=queue.size();
			while(!queue.isEmpty()) {
				//计数输出
				count++;
				double temp=(int)(count*1.0/total*10000)*1.0/100;
				if(count % 1000==0)		System.out.println("检测排列："+temp+"%");

				Set<String> single=queue.poll();
				List<List<Record>> satisfyList=new ArrayList<List<Record>>();
				for(int i=0;i<lineList.size();i++) {
					List<Record> sonList=sonRecord(single,lineList.get(i));
					if(sonList.size()==3 || sonList.size()==4)	satisfyList.add(sonList);
				}
				OutData out=conditionScreen(satisfyList,single,6,new int[] {3,4});
				if(out!=null)		resultList.add(out);
			}
			System.out.println("检测排列：100%");
			return resultList;
		}
	
	//第五步：根据规则筛选排列
	private static Queue<Set<String>> chooseResult(List<List<Record>> lineList, Queue<Set<String>> queue,int minLength,int[] line) {
		Queue<Set<String>> finalSet=new LinkedList<Set<String>>();
		
		List<OutData> resultList=new ArrayList<OutData>();
		int count=0;
		int total=queue.size();
		while(!queue.isEmpty()) {
			//计数输出
			count++;
			double temp=(int)(count*1.0/total*10000)*1.0/100;
			if(count % 1000==0)		System.out.println("检测排列："+temp+"%");

			Set<String> single=queue.poll();
			List<List<Record>> satisfyList=new ArrayList<List<Record>>();
			for(int i=0;i<lineList.size();i++) {
				List<Record> sonList=sonRecord(single,lineList.get(i));
				if(sonList.size()==line[0] || sonList.size()==line[1])	satisfyList.add(sonList);
			}
			OutData out=conditionScreen(satisfyList,single,minLength,line);
			if(out!=null)		resultList.add(out);
			if(out!=null)		finalSet.add(single);
		}
		System.out.println("检测排列：100%");
		return finalSet;
	}


	//第七步：将结果数据写回文件
	private static void writeResult(List<OutData> outList, File f) throws Exception{
		WritableWorkbook workbook=Workbook.createWorkbook(f);
		WritableSheet sheet=workbook.createSheet("result", 0);

		int firstSpilt=2;//第一列空格
		int secondSpilt=2;//第二列空格
		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			//写回最终排列
			String arrange=setToString(out.getOutSet());
			sheet.addCell(new Label(0,i,arrange));

			//写回原始数据
			List<Record> list=out.getOutList();
			for(int j=0;j<list.size();j++) {
				secondSpilt=Math.max(secondSpilt, list.size()+firstSpilt+1);
				sheet.addCell(new Label(firstSpilt+j,i,list.get(j).getDataString()));
			}
		}
		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			//写回要求中的条件数据
			List<List<Record>> satisfyList=out.getSatisfyList();
			int point=secondSpilt+1;
			for(int j=0;j<satisfyList.size();j++) {
				List<Record> single=satisfyList.get(j);
				for(int k=0;k<single.size();k++) {
					sheet.addCell(new Label(point++,i,single.get(k).getDataString()));
				}
				point++;
			}
		}
		CellView cv = new CellView();
		cv.setAutosize(true);
		for(int i=0;i<sheet.getColumns();i++) {
			sheet.setColumnView(i, 24);
		}
		sheet.setColumnView(firstSpilt-1, 8);
		//写入并关闭
		workbook.write();
		workbook.close();
	}
	//把set中的排序整理成输出格式
	private static String setToString(Set<String> set) {
		StringBuilder sb=new StringBuilder();
		sb.append("(");
		List<String> rankedList=new LinkedList<String>(set);
		Collections.sort(rankedList);
		for(int j=0;j<rankedList.size();j++) {
			sb.append(rankedList.get(j)+",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	//条件筛选
	private static OutData conditionScreen(List<List<Record>> satisfyList,Set<String> set, int minLength,int[] line) {
		if(satisfyList.size()<2)	return null;//至少条件总数要满足
		List<Record> list=new LinkedList<Record>();
		//第一次检测，剔除不满足三个或者四个的行，统计record总个数
		for(int i=0;i<satisfyList.size();i++) {
			List<Record> single=satisfyList.get(i);
			for(Record record : single) {
				if(list.isEmpty())	list.add(record);
				if(!containsRecord(list,record)) list.add(record);
			}
			if(!(single.size()==line[0] || single.size()==line[1])) 	satisfyList.remove(i--);
		}
		if(list.size()<minLength)	return null;

		List<Record>  rankedList=sortRecord(list);
		List<Record>  tempList=new LinkedList<Record>(rankedList.subList(0, minLength));
		//第二次检测：留下全部排前n的
		for(int i=0;i<satisfyList.size();i++) {
			List<Record> single=satisfyList.get(i);
			for(int j=0;j<single.size();j++) {
				if(!containsRecord(tempList,single.get(j))) {
					satisfyList.remove(i--);
					break;
				}
			}
		}
		//第三次检测：检查是否占满
		List<Record>  testList=new LinkedList<Record>();
		for(int i=0;i<satisfyList.size();i++) {
			List<Record> single=satisfyList.get(i);
			for(int j=0;j<single.size();j++) {
				if(!containsRecord(testList,single.get(j))) testList.add(single.get(j));
			}
		}

		if(testList.size()!=minLength)	return null;//没有占满
		int number[]= {0,0};
		for(List<Record> single:satisfyList) {
			if(single.size()==line[0])	number[0]++;
			if(single.size()==line[1])	number[1]++;
		}
		if(number[0]==0 || number[1]==0)	return null;//没有满足第一第二个条件

		//satisfyList排序
		List<List<Record>> rankTempList=new LinkedList<List<Record>>();
		for(List<Record> l : satisfyList)		if(l.size()==line[0])	rankTempList.add(l);
		for(List<Record> l : satisfyList)		if(l.size()==line[1])	rankTempList.add(l);
		satisfyList=rankTempList;

		OutData out=new OutData();
		out.setOutList(rankedList);
		out.setSatisfyList(satisfyList);
		out.setOutSet(set);
		return out;
	}
	
	
	//检测一个集合中是否包含
	private static boolean containsRecord(List<Record> list,Record r) {
		for(Record record : list) {
			if(record.getDataSet().equals(r.getDataSet()))	return true;
		}
		return false;
	}
	//对一个集合排序
	private static List<Record> sortRecord(List<Record> list) {
		for(int i=0;i<list.size();i++) {
			for(int j=1;j<list.size()-i;j++) {
				if(list.get(j-1).getIndex()>list.get(j).getIndex()) {
					Record r=list.get(j-1);
					list.set(j-1, list.get(j));
					list.set(j, r);
				}
			}
		}
		return list;
	}
	//给定一个大排列和小排列的链表，返回一个链表，里面的元素都是大排列的子集
	private static List<Record> sonRecord(Set<String> father,List<Record> son){
		List<Record> sonList=new LinkedList<Record>();
		for(Record record : son) {
			if(father.containsAll(record.getDataSet()))		sonList.add(record);
		}
		return sonList;
	}
}
