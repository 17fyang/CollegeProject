package item_jack.item11_22.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ConfigRead {
	
	//dataDealµÄconfigÄÚÈÝ
	public Config dataDealConfig() throws Exception {
		File f=new File("config/item_jack/item11_22/config.properties");
		InputStream fileIn=new FileInputStream(f);
		Properties p=new Properties();
		p.load(fileIn);
		
		Config config=new Config();
		
		String s=p.getProperty("dataDeal_source");
		String dataDeal_sourceString=new String(s.getBytes("iso-8859-1"),"utf-8");
		File dataDeal_sourceFile=new File(dataDeal_sourceString);
		config.setDataDeal_source(dataDeal_sourceFile);
		
		s=p.getProperty("dataDeal_aim");
		String dataDeal_aimString=new String(s.getBytes("iso-8859-1"),"utf-8");
		File dataDeal_aimFile=new File(dataDeal_aimString);
		config.setDataDeal_aim(dataDeal_aimFile);
		
		s=p.getProperty("lineNumber");
		String lineNumberString=new String(s.getBytes("iso-8859-1"),"utf-8");
		config.setLineNumber(Integer.parseInt(lineNumberString));
		
		List<String> list=new LinkedList<String>();
		for(int i=1;i<=5;i++) {
			s=p.getProperty("condition"+i);
			String conditionString=new String(s.getBytes("iso-8859-1"),"utf-8");
			if(conditionString.equals("null"))		continue;
			list.add(conditionString);
		}
		config.setConditionList(list);
		
		return config;
	}
}
