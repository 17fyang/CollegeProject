package item_jack.item1_12.utils;

import java.util.List;
import java.util.Set;

public class Record {
	private Set<String> dataSet;//原始数据中三个数字的集合
	private String dataString;//原始数据的字符串
	private List<Integer> locateList;//原始数据所在列数
	public Set<String> getDataSet() {
		return dataSet;
	}
	public void setDataSet(Set<String> dataSet) {
		this.dataSet = dataSet;
	}
	public String getDataString() {
		return dataString;
	}
	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	public List<Integer> getLocateList() {
		return locateList;
	}
	public void setLocateList(List<Integer> locateList) {
		this.locateList = locateList;
	}

	
}
