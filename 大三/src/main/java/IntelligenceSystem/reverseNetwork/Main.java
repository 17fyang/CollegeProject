package IntelligenceSystem.reverseNetwork;

import java.util.List;

/*
 * ��ҵ�����������࣬���򴫲�������
 */
public class Main {
	public final static String dataUrl="File/IntelligenceSystem/data.txt";//�ļ���ַ
	public final static int neuronLength=20;//����������
	public final static double learnSpeed=0.1;//ѧϰ�ٶ�
	public final static double activationTimes=2000;//��������
	public final static double resultThreshold[]= {Math.random()-0.5,Math.random()-0.5,Math.random()-0.5};//�����ֵ
	
	public static void main(String[] args) throws Exception {
		Data data=new Data(dataUrl,neuronLength);
		Draw draw=new Draw();
		Train train=new Train();
		//��һ�������ݳ�ʼ��
		List<Neuron> neuronList=data.getNeuronList();//��Ԫ����
		List<Flower> trainList=data.getTrainList();//ѵ����
		List<Flower> testList=data.getTestList();//���Լ�
		//�ڶ����������,Ȩ��ѵ��
		for(int i=0;i<activationTimes;i++) {
			List<double[]> errorList=train.activationAll(trainList,neuronList);
			draw.addErrorData(i, errorList);//��¼ѧϰ���ȵ������
		}
		//������������
		for(int i=0;i<testList.size();i++) {
			double[] result=train.test(neuronList,testList.get(i));
			draw.addTestData(i, result);//��¼�����������
			train.caculateSuccessNumber(testList.get(i),result);//ͳ�Ƴɹ���
		}
		System.out.println(testList.size()+"�����ݵĳɹ���Ϊ��"+train.getSuccessRate());
		//��ͼ
		draw.drawAllChart();
	}

}
