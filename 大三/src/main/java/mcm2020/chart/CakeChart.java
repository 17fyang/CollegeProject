package mcm2020.chart;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class CakeChart {
	public CakeChart() throws Exception {
		File f=new File("File/mcm2020/passingevents.xls");
		Workbook workbook=Workbook.getWorkbook(f);
		Sheet sheet=workbook.getSheet(0);

//		Map<String,Integer> map=getTotalMap(sheet);
//		Map<String,Integer> map=getWinMap(sheet);
//		Map<String,Integer> map=getTieMap(sheet);
		Map<String,Integer> map=getLoseMap(sheet);
		showMap(map);
	}

	private Map<String, Integer> getLoseMap(Sheet sheet) {
		Map<String,Integer> map=new HashMap<String,Integer>();
		for(int i=0;i<sheet.getRows();i++) {
			Cell testCell=sheet.getCell(1,i);
			if(!testCell.getContents().equals("Huskies")) continue;
			Cell testCell0=sheet.getCell(0,i);
			if(!(testCell0.getContents().equals("3") || testCell0.getContents().equals("4") || 
					testCell0.getContents().equals("5") || testCell0.getContents().equals("7") ||
					testCell0.getContents().equals("9") || testCell0.getContents().equals("10") ||
					testCell0.getContents().equals("13") || testCell0.getContents().equals("21") || 
					testCell0.getContents().equals("22") || testCell0.getContents().equals("23") || 
					testCell0.getContents().equals("26") || testCell0.getContents().equals("28") || 
					testCell0.getContents().equals("29") || testCell0.getContents().equals("32") || 
					testCell0.getContents().equals("38"))) continue;
			Cell cell=sheet.getCell(6,i);
			String passType=cell.getContents();
			if(map.get(passType)==null)	map.put(passType, 1);
			else map.put(passType, map.get(passType)+1);
		}
		return map;
	}
	
	private Map<String, Integer> getTieMap(Sheet sheet) {
		Map<String,Integer> map=new HashMap<String,Integer>();
		for(int i=0;i<sheet.getRows();i++) {
			Cell testCell=sheet.getCell(1,i);
			if(!testCell.getContents().equals("Huskies")) continue;
			Cell testCell0=sheet.getCell(0,i);
			if(!(testCell0.getContents().equals("2") || testCell0.getContents().equals("8") || 
					testCell0.getContents().equals("12") || testCell0.getContents().equals("16") ||
					testCell0.getContents().equals("19") || testCell0.getContents().equals("20") ||
					testCell0.getContents().equals("24") || testCell0.getContents().equals("33") || 
					testCell0.getContents().equals("34") || testCell0.getContents().equals("37"))) continue;
			Cell cell=sheet.getCell(6,i);
			String passType=cell.getContents();
			if(map.get(passType)==null)	map.put(passType, 1);
			else map.put(passType, map.get(passType)+1);
		}
		return map;
	}
	
	private Map<String, Integer> getWinMap(Sheet sheet) {
		Map<String,Integer> map=new HashMap<String,Integer>();
		for(int i=0;i<sheet.getRows();i++) {
			Cell testCell=sheet.getCell(1,i);
			if(!testCell.getContents().equals("Huskies")) continue;
			Cell testCell0=sheet.getCell(0,i);
			if(!(testCell0.getContents().equals("1") || testCell0.getContents().equals("6") || 
					testCell0.getContents().equals("11") || testCell0.getContents().equals("14") ||
					testCell0.getContents().equals("15") || testCell0.getContents().equals("17") ||
					testCell0.getContents().equals("18") || testCell0.getContents().equals("25") || 
					testCell0.getContents().equals("27") || testCell0.getContents().equals("30") || 
					testCell0.getContents().equals("31") || testCell0.getContents().equals("35") || 
					testCell0.getContents().equals("36"))) continue;
			Cell cell=sheet.getCell(6,i);
			String passType=cell.getContents();
			if(map.get(passType)==null)	map.put(passType, 1);
			else map.put(passType, map.get(passType)+1);
		}
		return map;
	}

	private void showMap(Map<String, Integer> map) {
		int  total=0;
		for(String key:map.keySet()) {
			System.out.println(key);
			total+=map.get(key);
		}
		for(String key:map.keySet()) {
			System.out.println(map.get(key)*100.0/total+"%");
		}
	}

	private Map<String, Integer> getTotalMap(Sheet sheet) {
		Map<String,Integer> map=new HashMap<String,Integer>();
		for(int i=0;i<sheet.getRows();i++) {
			Cell testCell=sheet.getCell(1,i);
			if(!testCell.getContents().equals("Huskies")) continue;
			Cell cell=sheet.getCell(6,i);
			String passType=cell.getContents();
			if(map.get(passType)==null)	map.put(passType, 1);
			else map.put(passType, map.get(passType)+1);
		}
		return map;
	}
}
