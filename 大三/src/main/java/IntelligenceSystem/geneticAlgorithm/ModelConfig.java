package IntelligenceSystem.geneticAlgorithm;

public class ModelConfig {
	public static final int groupNumber = 50;	//Ⱦɫ�����
	public static final double mutationPercent = 0.15;	//�������
	public static final int runTimes = 20000;	//��������
	public static final int accuracy = 24;	//��ȷ�ȣ�ѡ��ȷ��С�����λ
	public static final double correctFitness=2;//��ȷ����Ӧֵ
	public static final double fitnessAccuracy=0.000001;//��Ӧ��ʶ�𾫶�
	public static final double fitnessDistance=0.001;//��ĳһ��ĺ����ֵ����С�����ʱ���ԣ�Ҳ�������һ�����ֵ֮���´ξͲ����˵���˼��
	
	//������ȶ�Ӧ�����������
	private static int temp = (int) ((int)Math.log(6)+ accuracy*Math.log(10) );
	public static final int geneNumber = temp * 2;	//������
	public static int getTemp() {
		return temp;
		
	}
	
}
