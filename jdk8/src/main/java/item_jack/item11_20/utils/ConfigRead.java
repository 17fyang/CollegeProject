package item_jack.item11_20.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigRead {
	
	//dataDealµÄconfigÄÚÈÝ
	public Config dataDealConfig() throws Exception {
		File f=new File("config/item_jack/item11_20/config.properties");
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
		
		s=p.getProperty("screen_table2");
		String screen_table2String=new String(s.getBytes("iso-8859-1"),"utf-8");
		File screen_table2File=new File(screen_table2String);
		config.setScreen_table2(screen_table2File);
		
		s=p.getProperty("screen_table3");
		String screen_table3String=new String(s.getBytes("iso-8859-1"),"utf-8");
		File screen_table3File=new File(screen_table3String);
		config.setScreen_table3(screen_table3File);
		
		s=p.getProperty("screen_table4");
		String screen_table4String=new String(s.getBytes("iso-8859-1"),"utf-8");
		File screen_table4File=new File(screen_table4String);
		config.setScreen_table4(screen_table4File);
		
		return config;
	}
}
