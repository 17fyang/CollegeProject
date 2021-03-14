package IntelligenceSystem.reverseNetwork;

import java.util.List;

public class Flower {
	private List<Double> attributeList;
	private int type;//¿‡–Õ

	public Flower() {}
	public Flower(List<Double> attributeList,int type) {
		this.attributeList=attributeList;
		this.type=type;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Double> getAttributeList() {
		return attributeList;
	}
	public void setAttributeList(List<Double> attributeList) {
		this.attributeList = attributeList;
	}
}
