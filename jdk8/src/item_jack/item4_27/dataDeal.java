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
		System.out.println("version��1.0");
		System.out.println("date:2020.4.27\r\n" + 
				"��1������6�����������������һ�������������У�����ÿ�����ݵ�ð�ź��������������С����1�ţ�Ȼ��2�š���������\r\n" + 
				"* 1����1�л����ϵ����У�������4��������ͬһ����\r\n" + 
				"* 2����1�л����ϵ����У�������3��������ͬһ����\r\n" + 
				"* 3����4�����ݺ�3�����ݱ���������������������ǰ6λ�����Ҷ�Ҫ���֡�\r\n\r\n");
		try {
			System.out.println(System.currentTimeMillis());
			run();
			System.out.println(System.currentTimeMillis());
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
	public static void run() throws Exception {
		//������Դ�ļ��е�����ȡ����ȥ�أ�����һ��list����
		System.out.println("���ڶ�������Դ�ļ�......");
		Config config=Config.getConfig();
		Record[] originalList=config.getSourceList();
		System.out.println("ȥ�غ���������"+originalList.length);

		//���ݴ�����ȡ��������������
		System.out.println("���ݴ������ɺ�����......");
		System.out.println("�˹��̿���ռ�ýϳ�ʱ����û����ʾ�������ĵȴ�......");
		Queue<Set<String>> finalSet=getFirstDealResult(originalList,5);
		System.out.println("����������"+finalSet.size());

		//���ݷ���Ҫ��Ĺ���ɸѡ����
		System.out.println("��ʼ�������......");
		List<List<Record>> lineList=config.getLineList();
		finalSet=chooseResult(lineList,finalSet,5,new int[] {3,3});
		System.out.println("������������������"+finalSet.size());
		
		//���ݷ���Ҫ��Ĺ���ɸѡ����
		System.out.println("��ʼ��������......");
		finalSet=addRanked(originalList,finalSet,6);
		
		//���ݷ���Ҫ��Ĺ���ɸѡ����
		System.out.println("��ʼ�������2......");
		List<OutData> outList=chooseResult2(lineList,finalSet);
		lineList=null;
		System.out.println("������������������"+outList.size());

		//���������д���ļ�
		System.out.println("���ڽ�����д���ļ�......");
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
	//����������һ�����ݴ����õ�������nλ��������
	private static Queue<Set<String>> getFirstDealResult(Record[] originalList,int n) throws Exception{
		Set<Set<String>> finalSet=new HashSet<Set<String>>();
		Set<Set<String>> leftSet=new HashSet<Set<String>>();
		int listLength=originalList.length;
		//��һ�����
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

	//���岽�����ݹ���ɸѡ����(������)
		private static List<OutData> chooseResult2(List<List<Record>> lineList, Queue<Set<String>> queue) {
			List<OutData> resultList=new ArrayList<OutData>();
			int count=0;
			int total=queue.size();
			while(!queue.isEmpty()) {
				//�������
				count++;
				double temp=(int)(count*1.0/total*10000)*1.0/100;
				if(count % 1000==0)		System.out.println("������У�"+temp+"%");

				Set<String> single=queue.poll();
				List<List<Record>> satisfyList=new ArrayList<List<Record>>();
				for(int i=0;i<lineList.size();i++) {
					List<Record> sonList=sonRecord(single,lineList.get(i));
					if(sonList.size()==3 || sonList.size()==4)	satisfyList.add(sonList);
				}
				OutData out=conditionScreen(satisfyList,single,6,new int[] {3,4});
				if(out!=null)		resultList.add(out);
			}
			System.out.println("������У�100%");
			return resultList;
		}
	
	//���岽�����ݹ���ɸѡ����
	private static Queue<Set<String>> chooseResult(List<List<Record>> lineList, Queue<Set<String>> queue,int minLength,int[] line) {
		Queue<Set<String>> finalSet=new LinkedList<Set<String>>();
		
		List<OutData> resultList=new ArrayList<OutData>();
		int count=0;
		int total=queue.size();
		while(!queue.isEmpty()) {
			//�������
			count++;
			double temp=(int)(count*1.0/total*10000)*1.0/100;
			if(count % 1000==0)		System.out.println("������У�"+temp+"%");

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
		System.out.println("������У�100%");
		return finalSet;
	}


	//���߲������������д���ļ�
	private static void writeResult(List<OutData> outList, File f) throws Exception{
		WritableWorkbook workbook=Workbook.createWorkbook(f);
		WritableSheet sheet=workbook.createSheet("result", 0);

		int firstSpilt=2;//��һ�пո�
		int secondSpilt=2;//�ڶ��пո�
		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			//д����������
			String arrange=setToString(out.getOutSet());
			sheet.addCell(new Label(0,i,arrange));

			//д��ԭʼ����
			List<Record> list=out.getOutList();
			for(int j=0;j<list.size();j++) {
				secondSpilt=Math.max(secondSpilt, list.size()+firstSpilt+1);
				sheet.addCell(new Label(firstSpilt+j,i,list.get(j).getDataString()));
			}
		}
		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			//д��Ҫ���е���������
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
		//д�벢�ر�
		workbook.write();
		workbook.close();
	}
	//��set�е���������������ʽ
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
	
	//����ɸѡ
	private static OutData conditionScreen(List<List<Record>> satisfyList,Set<String> set, int minLength,int[] line) {
		if(satisfyList.size()<2)	return null;//������������Ҫ����
		List<Record> list=new LinkedList<Record>();
		//��һ�μ�⣬�޳����������������ĸ����У�ͳ��record�ܸ���
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
		//�ڶ��μ�⣺����ȫ����ǰn��
		for(int i=0;i<satisfyList.size();i++) {
			List<Record> single=satisfyList.get(i);
			for(int j=0;j<single.size();j++) {
				if(!containsRecord(tempList,single.get(j))) {
					satisfyList.remove(i--);
					break;
				}
			}
		}
		//�����μ�⣺����Ƿ�ռ��
		List<Record>  testList=new LinkedList<Record>();
		for(int i=0;i<satisfyList.size();i++) {
			List<Record> single=satisfyList.get(i);
			for(int j=0;j<single.size();j++) {
				if(!containsRecord(testList,single.get(j))) testList.add(single.get(j));
			}
		}

		if(testList.size()!=minLength)	return null;//û��ռ��
		int number[]= {0,0};
		for(List<Record> single:satisfyList) {
			if(single.size()==line[0])	number[0]++;
			if(single.size()==line[1])	number[1]++;
		}
		if(number[0]==0 || number[1]==0)	return null;//û�������һ�ڶ�������

		//satisfyList����
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
	
	
	//���һ���������Ƿ����
	private static boolean containsRecord(List<Record> list,Record r) {
		for(Record record : list) {
			if(record.getDataSet().equals(r.getDataSet()))	return true;
		}
		return false;
	}
	//��һ����������
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
	//����һ�������к�С���е���������һ�����������Ԫ�ض��Ǵ����е��Ӽ�
	private static List<Record> sonRecord(Set<String> father,List<Record> son){
		List<Record> sonList=new LinkedList<Record>();
		for(Record record : son) {
			if(father.containsAll(record.getDataSet()))		sonList.add(record);
		}
		return sonList;
	}
}
