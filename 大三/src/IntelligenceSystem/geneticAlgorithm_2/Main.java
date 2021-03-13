package IntelligenceSystem.geneticAlgorithm_2;

import java.util.List;

/*
 * �Ŵ��㷨����ҵ
 */
public class Main {

	public static void main(String[] args) throws Exception {
		CLS cls=new CLS();
		CLSDeal clsDeal=new CLSDeal();
		double x[]= {-1.4,0.8};
		System.out.println(cls.formula(x));
		Draw d=new Draw();
		String group[] = cls.initAll(ModelConfig.groupNumber);	//��ʼ��
		for(int i = 0; i < ModelConfig.runTimes; i++){
			group = clsDeal.cross(group);	//����
			group = clsDeal.mutation(group);	//����
			group = clsDeal.RWS(group); //ѡ��
			d.addGroupData(i, group);
		}
		d.drawLine("test");
		double draw[][]=d.dataTransfer(group);
		d.showChart(draw);
		
		//������ս��
		List<double[]> resultDoubleList=CLS.getDoubleList();
		List<String> resultStringList=CLS.getSingleList();
		double data[][]=new double[2][resultDoubleList.size()];
		for(int i=0;i<resultDoubleList.size();i++) {
			double bestResult[]=resultDoubleList.get(i);
			data[0][i]=bestResult[0];
			data[1][i]=bestResult[1];
			double result=-1*cls.fitSingle(resultStringList.get(i));
			System.out.println("x1 = "+bestResult[0]+"  "+"x2 = "+bestResult[1]+"   ����ֵ��"+result);
		}
		d.showChart(data);
		System.out.println("���ҵ����������ĵ㣺"+resultDoubleList.size()+"��");
	}

}

