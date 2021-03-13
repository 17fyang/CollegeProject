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
 * ɸѡ��׼��
 * 1�������л����ϵ����У��������������ϵ��������ͬ���������������
*  2����һ�л����ϵ����У��������������ϵ��������һ���ĸ��������У����ĸ����ݶ����������������");
*  2.23����������
 * ɸѡ����������Ҫ�ڱ�3�г���3�λ�����
 * ����д�ӡ���ڱ�1�е���λ��
 */
public class Screen {
	private static final int conditionLength=3;
	private static Screen screen=null;//����ģʽ
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
	 * ���˼·��
	 * 1�����ÿ�������ڸ����Ƿ����������ϵ��Ӽ�
	 * 2�����ÿ�����е��������ڸ����Ƿ����������ϵ��Ӽ�
	 */
	private List<OutData> condition1(List<OutData> outList,File conditionFile) throws Exception {
		List<OutData> resultList=new LinkedList<OutData>();
		Record[][] screenList=this.getScreenData(conditionFile);
		for(OutData data:outList) {
			//1�����ÿ�������ڸ����Ƿ����������ϵ��Ӽ�
			List<Integer> passRow=new LinkedList<Integer>();//������������
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
			//2�����ÿ�����е��������ڸ����Ƿ����������ϵ��Ӽ�
			List<Integer> passRow2=new LinkedList<Integer>();//������������
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
			
			//ͳ�ƴ������ж��ܲ��ܱ���
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
	
	//����������ɸѡ����������Ҫ�ڱ��г���times�λ�����
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
	
	//������Դ�ļ��е�����ȡ����ȥ�أ�����һ����ά����
		private Record[][] getScreenData(File screen_table) throws Exception {
			Workbook workbook=Workbook.getWorkbook(screen_table);
			Sheet sheet=workbook.getSheet(0);
			List<Record[]> resultList=new ArrayList<Record[]>();
			for(int i=0;i<sheet.getRows();i++) {
				Set<Set<String>> stringSetTemp=new HashSet<Set<String>>();//ȥ��
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
