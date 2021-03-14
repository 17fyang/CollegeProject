package mcm2020.chart;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class chartMain {
	public chartMain() throws Exception {
		File f=new File("File/mcm2020/passingevents.xls");
		Workbook workbook=Workbook.getWorkbook(f);
		Sheet sheet=workbook.getSheet(0);

		Map<String,List<Person>> map=getMap(sheet);
		showMap(map);
	}


	private void showMap(Map<String, List<Person>> map) {
		Map<String,Double>	powerMap=getPowerMap(map);
		for(String key:powerMap.keySet()) {
			System.out.println("var "+key+" = graph.newNode({label: '"+key+"  "+powerMap.get(key)+"'});");
		}
		System.out.println();
		for(String key:map.keySet()) {
			for(Person p:map.get(key)) {
				double  h=255-normalization(p.weight,1,15,0,1)*255;
				String name=p.name;
				System.out.println("graph.newEdge("+key+", "+name+", {color: 'RGB("+h+","+h+","+h+")'});");
			}
		}
	}

	//获取中心度
	private Map<String, Double> getPowerMap(Map<String, List<Person>> map) {
		Map<String, Double> resultMap=new HashMap<String,Double>();
		for(String key:map.keySet()) {
			resultMap.put(key, 0.0);
		}
		for(String key:map.keySet()) {
			for(Person p:map.get(key)) {
				Double receivePerson=resultMap.get(p.name);
				if(receivePerson==null)		resultMap.put(p.name,0.0);
				resultMap.put(key, resultMap.get(key)+p.weight);
				resultMap.put(p.name, resultMap.get(p.name)+p.weight);
			}
		}
		//归一化处理
		double max=0;
		double min=99999;
		for(double weight:resultMap.values()) {
			if(weight>max)		max=weight;
			if(weight<min)		min=weight;
		}
		
		double newMax=1;
		double newMin=0;
		for(String key:resultMap.keySet()) {
			double centrality=normalization(resultMap.get(key),min,max,newMin,newMax);
			centrality=(int)(centrality*1000)*1.0/1000;
			resultMap.put(key, centrality);
		}
		return resultMap;
	}


	public Map<String,List<Person>> getMap(Sheet sheet){
		String match="27";//场次
		Map<String,List<Person>> map=new HashMap<String,List<Person>>();
		for(int i=1;i<sheet.getRows();i++) {
			Cell testCell=sheet.getCell(1,i);
			if(!testCell.getContents().equals("Huskies")) continue;
			Cell testCell2=sheet.getCell(0,i);
			if(!testCell2.getContents().equals(match)) continue;

			Cell originCell=sheet.getCell(2,i);
			Cell destinationCell=sheet.getCell(3,i);
			String originName=originCell.getContents();
			String destinationName=destinationCell.getContents();
			if(map.get(originName)==null) {	
				map.put(originName, new ArrayList<Person>());
			}
			List<Person>list=map.get(originName);
			boolean b=false;
			for(Person p:list) {
				if(p.name.equals(destinationName)) {
					p.weight++;
					b=true;
				}
			}
			if(!b) {
				Person p=new Person(destinationName,1);
				list.add(p);
			}
		}
		return map;
	}
	private static double normalization(double v, double Min, double Max,  
			double newMin, double newMax) {  
		return (v - Min) / (Max - Min) * (newMax - newMin) + newMin;  
	}
}

class Person{
	String name;
	int weight;
	public Person(String name,int weight) {
		this.name=name;
		this.weight=weight;
	}
}
