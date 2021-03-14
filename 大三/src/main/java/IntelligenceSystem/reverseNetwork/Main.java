package IntelligenceSystem.reverseNetwork;

import java.util.List;

/*
 * 作业二：给花分类，反向传播神经网络
 */
public class Main {
	public final static String dataUrl="File/IntelligenceSystem/data.txt";//文件地址
	public final static int neuronLength=20;//隐含层数量
	public final static double learnSpeed=0.1;//学习速度
	public final static double activationTimes=2000;//迭代次数
	public final static double resultThreshold[]= {Math.random()-0.5,Math.random()-0.5,Math.random()-0.5};//结果阈值
	
	public static void main(String[] args) throws Exception {
		Data data=new Data(dataUrl,neuronLength);
		Draw draw=new Draw();
		Train train=new Train();
		//第一步：数据初始化
		List<Neuron> neuronList=data.getNeuronList();//神经元集合
		List<Flower> trainList=data.getTrainList();//训练集
		List<Flower> testList=data.getTestList();//测试集
		//第二步：激活函数,权重训练
		for(int i=0;i<activationTimes;i++) {
			List<double[]> errorList=train.activationAll(trainList,neuronList);
			draw.addErrorData(i, errorList);//记录学习进度点的数据
		}
		//第三步：测试
		for(int i=0;i<testList.size();i++) {
			double[] result=train.test(neuronList,testList.get(i));
			draw.addTestData(i, result);//记录点的坐标数据
			train.caculateSuccessNumber(testList.get(i),result);//统计成功率
		}
		System.out.println(testList.size()+"个数据的成功率为："+train.getSuccessRate());
		//画图
		draw.drawAllChart();
	}

}
