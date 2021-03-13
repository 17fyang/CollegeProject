package item_jack.item5_2.utils;

import java.util.List;
import java.util.Set;

public class OutData {
	private Set<String> outSet;//排列中的数据
	private List<Record> outList;//源文件中的数据
	private List<List<Record>> satisfyList;
	public Set<String> getOutSet() {
		return outSet;
	}
	public void setOutSet(Set<String> outSet) {
		this.outSet = outSet;
	}
	public List<Record> getOutList() {
		return outList;
	}
	public void setOutList(List<Record> outList) {
		this.outList = outList;
	}
	public List<List<Record>> getSatisfyList() {
		return satisfyList;
	}
	public void setSatisfyList(List<List<Record>> satisfyList) {
		this.satisfyList = satisfyList;
	}
}
