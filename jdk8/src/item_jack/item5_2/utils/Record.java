package item_jack.item5_2.utils;

import java.util.List;
import java.util.Set;

public class Record {
	private Set<String> dataSet;//ԭʼ�������������ֵļ���
	private String dataString;//ԭʼ���ݵ��ַ���
	private int index;//ԭʼ���ݵ����
	private List<Integer> locateList;//ԭʼ������������
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
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Integer> getLocateList() {
		return locateList;
	}
	public void setLocateList(List<Integer> locateList) {
		this.locateList = locateList;
	}
	
	public boolean equals(Object c) {
		if(c instanceof Record)	return this.getDataSet().equals(((Record) c).getDataSet());
		return super.equals(c);
	}
}
