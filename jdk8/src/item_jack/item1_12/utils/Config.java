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

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Config {
	private static Config config=null;
	private List<Record> sourceList;//dataDeal数据源
	private File dataDeal_aim;//dataDeal生成的结果文件
	public static String fileUrl="config/item_jack/item1_12/config.properties";
	private Config() throws Exception {
		File f=new File(fileUrl);
		InputStream fileIn=new FileInputStream(f);
		Properties p=new Properties();
		p.load(fileIn);
		
		String s=p.getProperty("dataDeal_source");
		String dataDeal_sourceString=new String(s.getBytes("iso-8859-1"),"utf-8");
		sourceList=getOriginalData(new File(dataDeal_sourceString));
		
		s=p.getProperty("dataDeal_aim");
		String dataDeal_aimString=new String(s.getBytes("iso-8859-1"),"utf-8");
		File dataDeal_aimFile=new File(dataDeal_aimString);
		this.setDataDeal_aim(dataDeal_aimFile);
		
		fileIn.close();
	}
	public static Config getConfig() throws Exception {
		if(config==null)	config=new Config();
		return config;
	}
	
	
	//第二步：将数据源文件中的数据取出，去重，生成一个list对象
		private List<Record> getOriginalData(File dataDeal_source) throws Exception{
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
					if(stringSetTemp.size()==beforeLength)	{
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
	
	
	
	public List<Record> getSourceList() {
			return sourceList;
		}
		public void setSourceList(List<Record> sourceList) {
			this.sourceList = sourceList;
		}
	public File getDataDeal_aim() {
		return dataDeal_aim;
	}
	public void setDataDeal_aim(File dataDeal_aim) {
		this.dataDeal_aim = dataDeal_aim;
	}
	
	
	
}
