package mcm2020Test.geneticAlgorithm;

/*
 * �Ŵ��㷨����ҵ
 */
public class Main {

	public static void main(String[] args) throws Exception {
		CLS cls=new CLS();
		CLSDeal clsDeal=new CLSDeal();
		String group[] = cls.initAll(ModelConfig.groupNumber);	//��ʼ��
		for(int i = 0; i < ModelConfig.runTimes; i++){
			group = clsDeal.cross(group);	//����
			group = clsDeal.mutation(group);	//����
			group = clsDeal.RWS(group); //ѡ��
			Draw d=new Draw();
			d.DrawGroup(group);
			d.dataTransfer(group);
		}
	}

}

