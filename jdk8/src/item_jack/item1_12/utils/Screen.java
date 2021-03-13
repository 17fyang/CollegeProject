package item_jack.item1_12.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Screen {
	private static Screen screen=null;//����ģʽ
	private List<Record> conditionList1=null;//1����5������ͬʱ������2��3������
	private List<Record> conditionList2=null;//2��������ֵ�53���ϱ�3�е�ĳһ������
	private List<Record> conditionList3=null;//3�����г���3�κ�3�ε��Ǹ����֣������ڱ�4��
	private List<Record> conditionList4=null;//4������3�κ�2�ε��Ǹ����֣����ܳ����ڱ�5��
	private List<Record> conditionList5=null;//5��������ֵ�����53�����ϱ�6�е�ĳ2������
	public static String fileUrl=Config.fileUrl;
	
	
	private Screen() throws Exception {
		File f=new File(fileUrl);
		InputStream fileIn=new FileInputStream(f);
		Properties p=new Properties();
		p.load(fileIn);
		for(int i=1;i<=5;i++) {
			String status=p.getProperty("condition"+i);
			if(status.equals("OFF"))	continue;
			String s=p.getProperty("screen_table"+i);
			String tableLocate=new String(s.getBytes("iso-8859-1"),"utf-8");
			if(i==1)	this.conditionList1=getScreenData(new File(tableLocate));
			if(i==2)	this.conditionList2=getScreenData(new File(tableLocate));
			if(i==3)	this.conditionList3=getScreenData(new File(tableLocate));
			if(i==4)	this.conditionList4=getScreenData(new File(tableLocate));
			if(i==5)	this.conditionList5=getScreenData(new File(tableLocate));
		}
		
	}
	public static Screen getScreen() throws Exception{
		if(screen==null)	screen=new Screen();
		return screen;
	}
	
	//��ʼɸѡ
	public  List<OutData> startScreen(List<OutData> outList){
		List<OutData> screenResultList=new LinkedList<OutData>();
		for(int i=0;i<outList.size();i++) {
			if(i%5==0)		System.out.println("�ڶ��׶�ɸѡ��"+i+"/"+outList.size()+"......");
			OutData out=outList.get(i);
			//��ȡ��5��3��2�ļ���
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
			Set<String> fiveSet=new  HashSet<String>();
			Set<String> threeSet=new  HashSet<String>();
			Set<String> twoSet=new  HashSet<String>();
			for(Entry<String,Integer> entry:map.entrySet()) {
				if(entry.getValue()==5)		fiveSet.add(entry.getKey());
				if(entry.getValue()==3)		threeSet.add(entry.getKey());
				if(entry.getValue()==2)		twoSet.add(entry.getKey());
			}
			//1��ɸѡ��������к��б�2������;
			if(this.conditionList1!=null) {
				int match=0;
				for(int j=0;j<this.conditionList1.size();j++) {
				Set<String> set=new HashSet<String>(out.getOutSet());
				int beforeLength=set.size();
				set.addAll(this.conditionList1.get(j).getDataSet());
				if(set.size()==beforeLength) 	match++;
				}
				if(match<3)	continue;
			}
			
			//2��������ֵ�53���ϱ�3�е�ĳһ������
			//����threeset�й̶����������ݣ������������Hashset�غ�֮�󳤶ȼ�һ˵����������һ��
			if(this.conditionList2!=null) {
				int match=0;
				for(int j=0;j<this.conditionList2.size();j++) {
					Set<String> set=new HashSet<String>(this.conditionList2.get(j).getDataSet());
					int beforeLength=set.size();
					set.addAll(fiveSet);
					if(set.size()!=beforeLength) continue;
					set.addAll(threeSet);
					if(set.size()==beforeLength+1) 	match++;
				}
				if(match<1)	continue;
			}
			
			//3�����г���3�ε��Ǹ����֣������ڱ�4��
			if(this.conditionList3!=null) {
				int match=0;
				for(int j=0;j<this.conditionList3.size();j++) {
					Set<String> set=new HashSet<String>(threeSet);
					int beforeLength=set.size();
					set.addAll(this.conditionList3.get(j).getDataSet());
					if(set.size()==beforeLength) 	match++;
				}
				if(match<1)		continue;
			}
			//4������3�κ�2�ε��Ǹ����֣����ܳ����ڱ�5��
			if(this.conditionList4!=null) {
				int match=0;
				for(int j=0;j<this.conditionList4.size();j++) {
					Set<String> set=new HashSet<String>(this.conditionList4.get(j).getDataSet());
					int beforeLength=set.size();
					set.addAll(threeSet);
					if(set.size()!=beforeLength+1) continue;
					set.addAll(twoSet);
					if(set.size()==beforeLength+2) 	match++;
				}
				if(match>0)		continue;
			}
			//5��������ֵ�����53�����ϱ�6�е�ĳ2������
			if(this.conditionList5!=null) {
				int match=0;
				for(int j=0;j<this.conditionList5.size();j++) {
					Set<String> set1=new HashSet<String>(fiveSet);//��һ��53
					Set<String> set2=new HashSet<String>(fiveSet);//�ڶ���53
					Iterator<String> it=threeSet.iterator();
					set1.add(it.next());
					set2.add(it.next());
					
					Set<String> set=new HashSet<String>(this.conditionList5.get(j).getDataSet());
					int beforeLength=set.size();
					set.addAll(set1);
					if(set.size()==beforeLength) 	match++;
					
					set=new HashSet<String>(this.conditionList5.get(j).getDataSet());
					beforeLength=set.size();
					set.addAll(set2);
					if(set.size()==beforeLength) 	match++;
				}
				if(match!=2)		continue;
			}
			
			screenResultList.add(out);
		}
		return screenResultList;
	}
	
	//������Դ�ļ��е�����ȡ����ȥ�أ�����һ��list����
		private  static List<Record> getScreenData(File screen_table) throws Exception {
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
	
	
	public List<Record> getConditionList1() {
		return conditionList1;
	}
	public List<Record> getConditionList2() {
		return conditionList2;
	}
	public List<Record> getConditionList3() {
		return conditionList3;
	}
	public List<Record> getConditionList4() {
		return conditionList4;
	}
	public List<Record> getConditionList5() {
		return conditionList5;
	}
}
