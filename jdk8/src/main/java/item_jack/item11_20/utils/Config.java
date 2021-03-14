package item_jack.item11_20.utils;

import java.io.File;

public class Config {
	private File dataDeal_source;//dataDeal数据源
	private File dataDeal_aim;//dataDeal生成的结果文件
	private int lineNumber;//dataDeal匹配行数
	private File screen_source;//screen数据源	
	private File screen_aim;//screen生成的结果文件
	private File screen_table2;//表2数据源	
	private File screen_table3;//表3数据源	
	private File screen_table4;//表4数据源	
	
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
	public File getScreen_source() {
		return screen_source;
	}
	public void setScreen_source(File screen_source) {
		this.screen_source = screen_source;
	}
	public File getScreen_aim() {
		return screen_aim;
	}
	public void setScreen_aim(File screen_aim) {
		this.screen_aim = screen_aim;
	}
	public File getScreen_table2() {
		return screen_table2;
	}
	public void setScreen_table2(File screen_table2) {
		this.screen_table2 = screen_table2;
	}
	public File getScreen_table3() {
		return screen_table3;
	}
	public void setScreen_table3(File screen_table3) {
		this.screen_table3 = screen_table3;
	}
	public File getScreen_table4() {
		return screen_table4;
	}
	public void setScreen_table4(File screen_table4) {
		this.screen_table4 = screen_table4;
	}
	
	
	
}
