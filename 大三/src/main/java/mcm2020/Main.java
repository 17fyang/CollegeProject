package mcm2020;

import java.util.List;

/*
 * ��ҵ�����������࣬���򴫲�������
 */
public class Main {
	public final static String dataUrl="File/mcm2020/threeGrade.txt";//�ļ���ַ
	public static int neuronLength=30;//����������
	public static double learnSpeed=0.06;//ѧϰ�ٶ�
	public static int trainNumber=15;//ѵ��������
	public static double activationTimes=10000;//��������
	public final static double resultThreshold[]= {Math.random()-0.5,Math.random()-0.5,Math.random()-0.5};//�����ֵ
	public static double resultRate=0;
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
		resultRate=train.getSuccessRate();
//		System.out.println(testList.size()+"�����ݵĳɹ���Ϊ��"+train.getSuccessRate());
		//��ͼ
//		draw.drawAllChart();
	}

}
