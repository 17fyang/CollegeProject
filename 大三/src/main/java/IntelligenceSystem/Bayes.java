package IntelligenceSystem;

import java.util.LinkedList;
import java.util.List;

public class Bayes {

	public static void main(String[] args) {
		//建立训练集
		List<PersonInfo> trainList=new LinkedList<PersonInfo>();
		trainList.add(new PersonInfo(0,6,180,12));
		trainList.add(new PersonInfo(0,5.92,190,11));
		trainList.add(new PersonInfo(0,5.58,170,12));
		trainList.add(new PersonInfo(0,5.92,165,10));
		trainList.add(new PersonInfo(1,5,100,6));
		trainList.add(new PersonInfo(1,5.5,150,8));
		trainList.add(new PersonInfo(1,5.42,130,7));
		trainList.add(new PersonInfo(1,5.75,150,9));

		//训练模型
		PersonModel maleModel=getModel(trainList,0);
		PersonModel femaleModel=getModel(trainList,1);

		
		//计算结果
		PersonInfo person=new PersonInfo(-1,6,130,8);
		double maleResult=getResult(maleModel,person);
		double femaleResult=getResult(femaleModel,person);
		System.out.println("女性概率："+femaleResult*10000);
		System.out.println("男性概率："+maleResult*10000);
	}

	//用正态分布计算出模型预测结果
	private static double getResult(PersonModel model, PersonInfo person) {
		double footResult=countProbability(model.foot.average,model.foot.variance,person.foot);
		double heightResult=countProbability(model.height.average,model.height.variance,person.height);
		double weightResult=countProbability(model.weight.average,model.weight.variance,person.weight);
		return footResult*heightResult*weightResult;
	}

	private static PersonModel getModel(List<PersonInfo> trainList,int sex) {
		int m=trainList.size();
		double heightSum=0;
		double weightSum=0;
		double footSum=0;
		int sexLength=0;
		for(int i=0;i<m;i++){//求和
			if(trainList.get(i).sex!=sex)	continue;
			heightSum+=trainList.get(i).height;
			weightSum+=trainList.get(i).weight;
			footSum+=trainList.get(i).foot;
			sexLength++;
		}
		double heightAve=heightSum/sexLength;//求平均值
		double weightAve=weightSum/sexLength;//求平均值
		double footAve=footSum/sexLength;//求平均值

		double heightVar=0;
		double weightVar=0;
		double footVar=0;
		for(int i=0;i<m;i++){//求方差
			if(trainList.get(i).sex!=sex)	continue;
			heightVar+=(trainList.get(i).height-heightAve)*(trainList.get(i).height-heightAve);
			weightVar+=(trainList.get(i).weight-weightAve)*(trainList.get(i).weight-weightAve);
			footVar+=(trainList.get(i).foot-footAve)*(trainList.get(i).foot-footAve);
		}
		footVar=footVar/sexLength;
		heightVar=heightVar/sexLength;
		weightVar=weightVar/sexLength;

		PersonModel personModel=new PersonModel();
		Model footModel=new Model(footAve,footVar);
		Model heightModel=new Model(heightAve,heightVar);
		Model weightModel=new Model(weightAve,weightVar);
		personModel.foot=footModel;
		personModel.height=heightModel;
		personModel.weight=weightModel;
		return personModel;
	}

	// 计算概率
	private static double countProbability(double mean, double veriance, double test) {
		double probability = 0;
		probability = Math.pow(Math.E, -Math.pow(test - mean, 2) / (2 * veriance)) / (Math.sqrt(2 * Math.PI * veriance));
		return probability;
	}
}
class PersonModel{
	Model height;
	Model weight;
	Model foot;
}

class Model{
	double average;
	double variance;
	public Model(double average,double variance) {
		this.average=average;
		this.variance=variance;
	}
}

class PersonInfo{
	int sex;
	double height;
	double weight;
	double foot;
	public PersonInfo(int sex,double height,double weight,double foot) {
		this.sex=sex;
		this.height=height;
		this.foot=foot;
		this.weight=weight;
	}
}
