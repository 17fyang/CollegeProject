package IntelligenceSystem.reverseNetwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class Data {
	private List<Neuron> neuronList=null;
	private List<Flower> trainList=null;
	private List<Flower> testList=null;
	//初始化
	public Data(String dataUrl,int neuronLength) {
		List<Flower> preDataList;
		try {
			preDataList = init(dataUrl);
			List<Flower> dataList=preDeal(preDataList);
			neuronList=initNeuron(neuronLength);
			trainList=splitList(dataList,0);//训练集
			testList=dataList;//测试集
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public  List<Flower> init(String dataUrl) throws Exception {
		File f=new File(dataUrl);
		List<Flower> list=new LinkedList<Flower>();

		InputStream in=new FileInputStream(f);
		byte buf[]=new byte[1024];
		int len=0;
		StringBuilder sb=new StringBuilder();
		while((len=in.read(buf))!=-1) {
			sb.append(new String(buf,0,len,"utf-8"));
		}
		String oneData[]=sb.toString().split("\r\n");
		for(int i=0;i<oneData.length;i++) {
			String[] detail=oneData[i].split(",");
			int type=0;
			switch(detail[4]) {
			case "Iris-setosa" :type=0;break;
			case "Iris-versicolor" :type=1;break;
			case "Iris-virginica" :type=2;break;
			}
			Flower flower=new Flower();
			List<Double> attribute=new LinkedList<Double>();
			for(int j=0;j<detail.length-1;j++) {
				attribute.add(Double.parseDouble(detail[j]));
			}
			flower.setAttributeList(attribute);
			flower.setType(type);
			list.add(flower);
		}

		in.close();
		return list;
	}

	//最大最小值规范化对数据进行预处理
	public List<Flower> preDeal(List<Flower> preDataList) {
		double min[]= {999,999,999,999};
		double max[]= {-999,-999,-999,-999};
		for(Flower flower:preDataList) {
			for(int i=0;i<preDataList.get(0).getAttributeList().size();i++) {
				if(flower.getAttributeList().get(i)<min[i])	min[i]=flower.getAttributeList().get(i);
				if(flower.getAttributeList().get(i)>max[i])	max[i]=flower.getAttributeList().get(i);
			}
		}

		for(Flower flower:preDataList) {
			List<Double> attributeList=flower.getAttributeList();
			List<Double> list=new LinkedList<Double>();
			for(int i=0;i<attributeList.size();i++) {
				double result=normalization(attributeList.get(i),min[i],max[i],-1,1);
				list.add(result);
			}
			flower.setAttributeList(list);
		}
		return preDataList;
	}
	//神经元初始化
	public List<Neuron> initNeuron(int neuronLength) {
		List<Neuron> list=new LinkedList<Neuron>();
		for(int i=0;i<neuronLength;i++) {
			Neuron neuron=new Neuron();
			double[] before=new double[4];
			for(int j=0;j<before.length;j++) 		before[j]=Math.random()-0.5;
			double[] after= {Math.random()-0.5,Math.random()-0.5,Math.random()-0.5};
			neuron.setThreshold(Math.random()-0.5);
			neuron.setBefore(before);
			neuron.setAfter(after);
			list.add(neuron);
		}
		return list;
	}

	//105个训练数据，45个测试数据
		public List<Flower> splitList(List<Flower> dataList, int type) {
			List<Flower> list=new LinkedList<Flower>();
			for(int i=0;i<dataList.size();i++) {
				if(type==0 && i%50<=34)	list.add(dataList.get(i));
				if(type==1 && i%50>34)	list.add(dataList.get(i));
			}
			return list;
		}



	private static double normalization(double v, double Min, double Max,  
			double newMin, double newMax) {  
		return (v - Min) / (Max - Min) * (newMax - newMin) + newMin;  
	}


	public List<Neuron> getNeuronList() {
		return neuronList;
	}
	public List<Flower> getTrainList() {
		return trainList;
	}
	public List<Flower> getTestList() {
		return testList;
	}

}
