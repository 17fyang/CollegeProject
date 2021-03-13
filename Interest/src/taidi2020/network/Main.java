package taidi2020.network;

import java.util.List;

/*
 * ��ҵ�����������࣬���򴫲�������
 */
public class Main {
	public static int neuronLength=3;//����������
	public static double learnSpeed=0.06;//ѧϰ�ٶ�
	public static double activationTimes=1000;//��������
	public static double resultThreshold[]= null;//�����ֵ
	public static double resultRate=0;//�ɹ���
	public static void main(String[] args) throws Exception {
		//��һ�������ݳ�ʼ��
		Data data=new Data(neuronLength);
		resultThreshold=data.initResult();
		List<Neuron> neuronList=data.getNeuronList();//��Ԫ����
		List<Flower> trainList=data.getTrainList();//ѵ����
		List<Flower> testList=data.getTestList();//���Լ�
		//�ڶ����������,Ȩ��ѵ��
		Train train=new Train();
		Draw draw=new Draw();
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
		System.out.println(testList.size()+"�����ݵĳɹ���Ϊ��"+train.getSuccessRate());
		//	��ͼ
		draw.drawAllChart();
	}

}
