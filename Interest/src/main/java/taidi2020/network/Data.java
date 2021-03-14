package taidi2020.network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Data {
	public final static String trainUrl="File/train.txt";//文件地址
	public final static String testUrl="File/test.txt";//文件地址
	private int dataLength=0;
	private String table="GBK";
	private Map<String,Integer> typeMap=new HashMap<String,Integer>();
	private List<Neuron> neuronList=null;
	private List<Flower> trainList=null;
	private List<Flower> testList=null;
	//初始化
	public Data(int neuronLength) {
		try {
			List<Flower> trainPreList = readData(trainUrl);
			List<Flower> testPreList = readData(testUrl);
			this.dataLength=trainPreList.get(0).getAttributeList().size()+testPreList.get(0).getAttributeList().size();
			trainList=preDeal(trainPreList);
			testList=preDeal(testPreList);
			neuronList=initNeuron(neuronLength);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public  List<Flower> readData(String dataUrl) throws Exception {
		File f=new File(dataUrl);
		List<Flower> list=new LinkedList<Flower>();

		InputStreamReader in=new InputStreamReader(new FileInputStream(f),table);
		BufferedReader br=new BufferedReader(in);
		List<String> dataList=new LinkedList<String>();
		String line = null;  
		while ((line = br.readLine()) != null) {  
		     dataList.add(line);
		}
		String data[]=dataList.toArray(new String[dataList.size()]);
		
		for(int i=0;i<data.length;i++) {
			if(data[i]==null || data[i].equals(""))	continue;
			String[] detail=data[i].split("\t");
			Integer type=typeMap.get(detail[detail.length-1]);
			if(type==null) {
				type=typeMap.size();
				typeMap.put(detail[detail.length-1], type);
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
		double min[]= new double[dataLength];
		double max[]= new double[dataLength];
		for(int i=0;i<min.length;i++) {
			min[i]=999;
			max[i]=-999;
		}
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
			double[] before=new double[dataLength];
			for(int j=0;j<before.length;j++) 		before[j]=Math.random()-0.5;
			double[] after=initResult();
			neuron.setThreshold(Math.random()-0.5);
			neuron.setBefore(before);
			neuron.setAfter(after);
			list.add(neuron);
		}
		return list;
	}
	//结果阈值初始化
	public double[] initResult() {
		double result[]=new double[typeMap.size()];
		for(int i=0;i<result.length;i++) {
			result[i]=Math.random()-0.5;
		}
		return result;
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
