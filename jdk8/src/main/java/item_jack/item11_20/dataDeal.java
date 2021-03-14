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
 * author:�
 * request����1������5���������5�����֣�����53322�ĸ�ʽ��
 * ��5������ͬʱ������2��3�����ݡ�
 * ������ֵ�53���ϱ�3�е�ĳһ������
 * ���г���3�κ�3�ε��Ǹ����֣������ڱ�4��
 */

public class dataDeal {
	public static void main(String[] args) {
		System.out.println("item_1120");
		System.out.println("version��1.0");
		System.out.println("date:2019-11-20\r\n" + 
				" * request����1������5���������5�����֣�����53322�ĸ�ʽ��\r\n" + 
				" *                   ��5������ͬʱ������2��3�����ݡ�\r\n" + 
				" *                   ������ֵ�53���ϱ�3�е�ĳһ������\r\n" + 
				" *                   ���г���3�κ�3�ε��Ǹ����֣������ڱ�4��");
		System.out.println();
		System.out.println();
		try {
			run();
			System.out.print("���ݴ�����ϣ�");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print("���ݴ������");
		}
		System.out.println("��������˳���");
		Scanner sc = new Scanner(System.in); 
		sc.next();
		sc.close();
	}
	public static void run() {
		//��һ������ȡ�����ļ��е�����
		System.out.println("���ڶ�ȡ�����ļ�......");
		ConfigRead configRead=new ConfigRead();
		Config config=null;
		try {
			config=configRead.dataDealConfig();
		} catch (Exception e) {
			System.out.println("��ȡ�����ļ�ʧ�ܣ���");
			e.printStackTrace();
			return;
		}

		//�������յ��������ݼ�
		Set<Set<String>> finalSet=new HashSet<Set<String>>();


		//�ڶ�����������Դ�ļ��е�����ȡ����ȥ�أ�����һ��list����
		System.out.println("���ڶ�������Դ�ļ�......");
		List<Record> originalList=null;
		try {
			originalList=getOriginalData(config.getDataDeal_source());
		} catch (Exception e) {
			System.out.println("��������Դ����ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
		//��2.1����������Դ�ļ��е�����ȡ����ȥ�أ�����һ��list����
		List<Record> screenList2=null;
		List<Record> screenList3=null;
		List<Record> screenList4=null;
		try {
			screenList2=getScreenData(config.getScreen_table2());
			screenList3=getScreenData(config.getScreen_table3());
			screenList4=getScreenData(config.getScreen_table4());
		} catch (Exception e) {
			System.out.println("��������Դ2.1����ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
		System.out.println("ȥ�غ���������"+originalList.size());

		//����������һ�����ݴ����õ���������λ��������,����δ������������
		System.out.println("���ݴ������ɺ�����......");
		List<Set<String>> firstDealList=null;
		try {
			firstDealList=getFirstDealResult(originalList,finalSet);
		} catch (Exception e) {
			System.out.println("��һ�����ݴ���ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
		System.out.println("���ɺ����У�"+originalList.size()+"/"+originalList.size()+"......");
		//		���Ĳ����ڶ������ݴ��������������в�����©�����У�����δ��������
		//		List<Set<String>> secondDealList=null;
		//		try {
		//			secondDealList=getSecondDealResult(originalList,firstDealList,finalSet);
		//		} catch (Exception e) {
		//			System.out.println("��һ�����ݴ���ʧ�ܣ���");
		//			e.printStackTrace();
		//			return;
		//		}

		//���岽������53322�Ĺ���ɸѡ����
		System.out.println("��ʼ��һ�׶�ɸѡ......");
		List<OutData> outList=null;
		try {
			outList=chooseResult(originalList,finalSet);
		} catch (Exception e) {
			System.out.println("����ƥ��ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
		System.out.println("��һ�׶�ɸѡ��100%");
		System.out.println("����53322��������������"+outList.size());
		
		//����������53322ɸѡ�Ļ����ϼ���ɸѡ
		System.out.println("��ʼ�ڶ��׶�ɸѡ......");
		List<OutData> screenList=null;
		try {
			screenList=screen(outList,screenList2,screenList3,screenList4);
		} catch (Exception e) {
			System.out.println("ɸѡ����ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
		System.out.println("�ڶ��׶�ɸѡ��"+outList.size()+"/"+outList.size()+"......");
		System.out.println("����ɸѡ����������������"+screenList.size());
		
		//���߲������������д���ļ�
		System.out.println("���ڽ�����д���ļ�......");
		try {
			writeResult(screenList,config.getDataDeal_aim());
		} catch (Exception e) {
			System.out.println("д������ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
	}

	//�ڶ�����������Դ�ļ��е�����ȡ����ȥ�أ�����һ��list����
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

	//��2.1����������Դ�ļ��е�����ȡ����ȥ�أ�����һ��list����
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
	
	//����������һ�����ݴ����õ���������λ��������
	private static List<Set<String>> getFirstDealResult(List<Record> originalList,Set<Set<String>> finalSet) throws Exception{
		Set<Set<String>> firstResultSet=new HashSet<Set<String>>();
		int listLength=originalList.size();
		for(int i=0;i<listLength;i++) {
			if(i%5==0)		System.out.println("���ɺ����У�"+(i+1)+"/"+listLength+"......");
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

	//���Ĳ����ڶ������ݴ���������©������
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

	//���岽������53322�Ĺ���ɸѡ����
	private static List<OutData> chooseResult(List<Record> originalList, Set<Set<String>> finalSet) {
		List<OutData> resultList=new LinkedList<OutData>();
		Iterator<Set<String>> finalIt=finalSet.iterator();
		int total=finalSet.size();
		int count=0;
		while(finalIt.hasNext()) {
			//�������
			count++;
			double temp=(int)(count*1.0/total*10000)*1.0/100;
			if(count % 1000==0)		System.out.println("��һ�׶�ɸѡ��"+temp+"%");
			
			Set<String> finalSingleSet=finalIt.next();
			List<Record> recordList=new LinkedList<Record>();
			for(int i=0;i<originalList.size();i++) {
				Set<String> tempSet=new HashSet<String>(finalSingleSet);
				int beforeLength=tempSet.size();
				tempSet.addAll(originalList.get(i).getDataSet());
				if(tempSet.size()==beforeLength)	recordList.add(originalList.get(i));
			}

			//�ж��ǲ���53322
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

	//����������53322ɸѡ�Ļ����ϼ���ɸѡ
	private static List<OutData> screen(List<OutData> outList, List<Record> screenList2, List<Record> screenList3, List<Record> screenList4) {
		List<OutData> screenResultList=new LinkedList<OutData>();
		for(int i=0;i<outList.size();i++) {
			if(i%5==0)		System.out.println("�ڶ��׶�ɸѡ��"+i+"/"+outList.size()+"......");
			OutData out=outList.get(i);
			//1��ɸѡ��������к��б�2������;
			int match2=0;
			for(int j=0;j<screenList2.size();j++) {
				Set<String> set=new HashSet<String>(out.getOutSet());
				int beforeLength=set.size();
				set.addAll(screenList2.get(j).getDataSet());
				if(set.size()==beforeLength) 	match2++;
			}
			if(match2<3)	continue;
			
			//��ȡ��53��3�ļ���
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
			//������ֵ�53���ϱ�3�е�ĳһ�����ݣ��������е�����
			int match3=0;
			for(int j=0;j<screenList3.size();j++) {
				Set<String> set=new HashSet<String>(fiveThreeSet);
				int beforeLength=set.size();
				set.addAll(screenList3.get(j).getDataSet());
				if(set.size()==beforeLength) 	match3++;
			}
			if(match3<1)	continue;
			
			//���г���3�κ�3�ε��Ǹ����֣������ڱ�4��
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

	//���߲������������д���ļ�
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
		
		//д�벢�ر�
		workbook.write();
		workbook.close();
	}

}