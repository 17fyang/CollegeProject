package item_jack.item5_2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import item_jack.item5_2.utils.Record;

public class Line {
	List<Record> data;
	Set<String> publicData;
	Set<String> otherData;
	public Line() {}
	public Line(List<Record> data) throws Exception {
		this.data=data;
		publicData=new HashSet<String>();
		
		//publicData
		Set<String> set0=data.get(0).getDataSet();
		Set<String> set1=data.get(1).getDataSet();
		Iterator<String> it=set0.iterator();
		while(it.hasNext()) {
			String value=it.next();
			if(set1.contains(value))	publicData.add(value);
		}
		
		//otherData
		otherData=new HashSet<String>();
		for(int i=0;i<data.size();i++) {
			Set<String> set=data.get(i).getDataSet();
			otherData.addAll(set);
		}
		otherData.removeAll(publicData);
	}
	
	public List<Record> getData() {
		return data;
	}
	public Set<String> getOtherData() {
		return otherData;
	}
	public Set<String> getPublicData() {
		return publicData;
	}
	public Set<String> getTotalData() {
		Set<String> set=new HashSet<String>(otherData);
		set.addAll(publicData);
		return set;
	}
}
