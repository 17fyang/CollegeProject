package item_jack.item11_22.utils;

import java.io.File;
import java.util.List;

public class Config {
	private File dataDeal_source;//dataDeal数据源
	private File dataDeal_aim;//dataDeal生成的结果文件
	private int lineNumber;//dataDeal匹配行数
	private List<String> conditionList;//筛选数据list
	
	public File getDataDeal_source() {
		return dataDeal_source;
	}
	public void setDataDeal_source(File dataDeal_source) {
		this.dataDeal_source = dataDeal_source;
	}
	public File getDataDeal_aim() {
		return dataDeal_aim;
	}
	public void setDataDeal_aim(File dataDeal_aim) {
		this.dataDeal_aim = dataDeal_aim;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public List<String> getConditionList() {
		return conditionList;
	}
	public void setConditionList(List<String> conditionList) {
		this.conditionList = conditionList;
	}
	
	
	
}
