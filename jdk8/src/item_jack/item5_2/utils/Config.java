package item_jack.item5_2.utils;

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

import item_jack.item5_2.Line;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Config {
	private static Config config=null;
	private Record[] sourceList;//dataDeal����Դ
	private List<Line> lineList;//ÿһ�е�����Դ����ȥ�أ�
	private File dataDeal_aim;//dataDeal���ɵĽ���ļ�
	public static String fileUrl="config/item_jack/item4_27/config.properties";
	private Config() throws Exception {
		File f=new File(fileUrl);
		InputStream fileIn=new FileInputStream(f);
		Properties p=new Properties();
		p.load(fileIn);

		String s=p.getProperty("dataDeal_source");
		String dataDeal_sourceString=new String(s.getBytes("iso-8859-1"),"utf-8");
		OriginalDataList origin=getOriginalData(new File(dataDeal_sourceString),3);
		this.sourceList=origin.sourceList;
		this.lineList=origin.lineList;
		
		s=p.getProperty("dataDeal_aim");
		String dataDeal_aimString=new String(s.getBytes("iso-8859-1"),"utf-8");
		this.dataDeal_aim=new File(dataDeal_aimString);
		fileIn.close();
	}
	public static Config getConfig() throws Exception {
		if(config==null)	config=new Config();
		return config;
	}
	
	
	//������Դ�ļ��е�����ȡ����ȥ�أ�����һ��list����
	//number:ÿ��������ʵ���ݸ���
	public OriginalDataList getOriginalData(File dataDeal_source,int number) throws Exception{
		if(!dataDeal_source.exists()) throw new Exception("�����ļ�·�����󣡣���");
		Workbook workbook=Workbook.getWorkbook(dataDeal_source);
		Sheet sheet=workbook.getSheet(0);
		
		Set<Record> resultSet=new HashSet<Record>();//���Set
		Set<Set<String>> stringSetTemp=new HashSet<Set<String>>();//ȥ���õ���set
		List<Line> lineRecord=new ArrayList<Line>();//�����ݵ�����
		for(int i=0;i<sheet.getRows();i++) {
			List<Record> list=new LinkedList<Record>();
			for(int j=0;j<sheet.getColumns();j++) {
				Cell cell=sheet.getCell(j,i);
				Record record=this.cellToRecord(cell, number);
				if(record==null)	continue;
				list.add(record);
				int beforeLength=stringSetTemp.size();
				stringSetTemp.add(record.getDataSet());
				if(stringSetTemp.size()!=beforeLength)			resultSet.add(record);
			}
			
			if(list.size()>2)	lineRecord.add(new Line(list));
		}
		//setתlist
		Record[] resourceList=new Record[resultSet.size()];
		Iterator<Record> it=resultSet.iterator();
		int i=0;
		while(it.hasNext())	resourceList[i++]=it.next();
		return new  OriginalDataList(resourceList,lineRecord);
	}
	
	private Record cellToRecord(Cell cell,int number) {
		String content=cell.getContents();
		if(content==null || content.equals(""))	return null;
		String spiltString[]=content.split("\\D+");
		Set<String> dataSourceSet=new HashSet<String>();
		int knownNumber=0;
		for(int k=0;k<spiltString.length;k++) {
			if(spiltString[k]==null || spiltString[k].equals(""))		continue;   	
			dataSourceSet.add(spiltString[k]);
			knownNumber++;
			if(knownNumber>=number)		break;
		}
		String index=spiltString[spiltString.length-1];
		Record record=new Record();
		record.setIndex(Integer.parseInt(index));
		record.setDataString(content);
		record.setDataSet(dataSourceSet);
		return record;
	}
	

	public List<Line> getLineList() {
		List<Line> temp=this.lineList;
		this.lineList=null;
		return temp;
	}
	public Record[]  getSourceList() {
		Record[] temp=this.sourceList;
		this.sourceList=null;
		return temp;
	}
	public File getDataDeal_aim() {
		return dataDeal_aim;
	}

}
class OriginalDataList{
	public OriginalDataList(Record[] sourceList,List<Line> lineList) {
		this.lineList=lineList;
		this.sourceList=sourceList;
	}
	Record[] sourceList;//dataDeal����Դ
	List<Line> lineList;//ÿһ�е�����Դ����ȥ�أ�
}

