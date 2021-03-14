package IntelligenceSystem.reverseNetwork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Train {
	private final static double resultThreshold[]=Main.resultThreshold;
	private  final static int neuronLength=Main.neuronLength;
	private final static double learnSpeed=Main.learnSpeed;//学习速度
	private int totalNumber=0;
	private int successNumber=0;
	
	//单个训练
	public double[] activation(Flower flower, List<Neuron> neuronList) {
		List<Double> neuronResult=new LinkedList<Double>();
		for(int i=0;i<neuronLength;i++) {
			Neuron neuron=neuronList.get(i);
			List<Double> attributeList=flower.getAttributeList();
			double total=0;
			for(int j=0;j<attributeList.size();j++) 		total+=attributeList.get(j)*neuron.getBefore()[j];
			total-=neuron.getThreshold();
			double result=1.0/(1+Math.pow(Math.E, total*-1));
			neuronResult.add(result);
		}
		double[] trueResult=new double[resultThreshold.length];//实际输出
		double[] hopeResult=new double[resultThreshold.length];//期望输出
		double[] errorDouble=new double[resultThreshold.length];//误差
		hopeResult[flower.getType()]=1;
		double errorGradient=0;

		for(int i=0;i<resultThreshold.length;i++) {
			double total=0;
			for(int j=0;j<neuronLength;j++) {
				Neuron neuron=neuronList.get(j);
				total+=neuronResult.get(j)*neuron.getAfter()[i];
			}
			total-=resultThreshold[i];
			double result=1.0/(1+Math.pow(Math.E, total*-1));
			trueResult[i]=result;
			errorDouble[i]=hopeResult[i]-trueResult[i];
			//调整权重
			errorGradient=result*(hopeResult[i]-result)*(1-result);
			resultThreshold[i]+=errorGradient*(-1)*learnSpeed;
			for(int j=0;j<neuronLength;j++) {
				Neuron neuron=neuronList.get(j);
				double afterWight=learnSpeed*errorGradient*neuronResult.get(j);
				double[] afterMap=neuron.getAfter();
				double[] beforeMap=neuron.getBefore();
				double errorGradient2=neuronResult.get(j)*(1-neuronResult.get(j));
				errorGradient2=errorGradient*errorGradient2*afterMap[i];
				for(int k=0;k<flower.getAttributeList().size();k++) {
					double d=learnSpeed*errorGradient2*flower.getAttributeList().get(k);
					beforeMap[k]=beforeMap[k]+d;
				}
				neuron.setThreshold(learnSpeed*(-1)*errorGradient2);
				afterMap[i]=afterMap[i]+afterWight;
			}
		}
		return errorDouble;
	}
	//批量训练
	public List<double[] > activationAll(List<Flower> trainList,List<Neuron> neuronList){
		List<double[]> errorList=new ArrayList<double[]>(trainList.size());
		for(int i=0;i<trainList.size();i++) errorList.add(activation(trainList.get(i),neuronList));
		return errorList;
	}


	//对测试数据进行测试
	public double[] test(List<Neuron> neuronList, Flower flower) {
		List<Double> neuronResult=new LinkedList<Double>();
		for(int i=0;i<neuronLength;i++) {
			Neuron neuron=neuronList.get(i);
			List<Double> attributeList=flower.getAttributeList();
			double total=0;
			for(int j=0;j<attributeList.size();j++) 		total+=attributeList.get(j)*neuron.getBefore()[j];
			total-=neuron.getThreshold();
			double result=1.0/(1+Math.pow(Math.E, total*-1));
			neuronResult.add(result);
		}
		double[] realResult=new double[resultThreshold.length];
		for(int i=0;i<realResult.length;i++) {
			double total=0;
			for(int j=0;j<neuronLength;j++) {
				Neuron neuron=neuronList.get(j);
				total+=neuronResult.get(j)*neuron.getAfter()[i];
			}
			realResult[i]=total;
		}
		return realResult;
	}
	//统计成功个数
	public void caculateSuccessNumber(Flower flower, double[] result) {
		totalNumber++;
		int type=flower.getType();
		if(result[type]>resultThreshold[type])		successNumber++;
	}
	//获取成功率
	public double getSuccessRate() {
		return successNumber*1.0/totalNumber;
	}
	
	
	
}
