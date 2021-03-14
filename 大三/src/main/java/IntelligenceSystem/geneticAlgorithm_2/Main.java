package IntelligenceSystem.geneticAlgorithm_2;

import java.util.List;

/*
 * 遗传算法，作业
 */
public class Main {

	public static void main(String[] args) throws Exception {
		CLS cls=new CLS();
		CLSDeal clsDeal=new CLSDeal();
		double x[]= {-1.4,0.8};
		System.out.println(cls.formula(x));
		Draw d=new Draw();
		String group[] = cls.initAll(ModelConfig.groupNumber);	//初始化
		for(int i = 0; i < ModelConfig.runTimes; i++){
			group = clsDeal.cross(group);	//交叉
			group = clsDeal.mutation(group);	//变异
			group = clsDeal.RWS(group); //选择
			d.addGroupData(i, group);
		}
		d.drawLine("test");
		double draw[][]=d.dataTransfer(group);
		d.showChart(draw);
		
		//输出最终结果
		List<double[]> resultDoubleList=CLS.getDoubleList();
		List<String> resultStringList=CLS.getSingleList();
		double data[][]=new double[2][resultDoubleList.size()];
		for(int i=0;i<resultDoubleList.size();i++) {
			double bestResult[]=resultDoubleList.get(i);
			data[0][i]=bestResult[0];
			data[1][i]=bestResult[1];
			double result=-1*cls.fitSingle(resultStringList.get(i));
			System.out.println("x1 = "+bestResult[0]+"  "+"x2 = "+bestResult[1]+"   函数值："+result);
		}
		d.showChart(data);
		System.out.println("共找到满足条件的点："+resultDoubleList.size()+"个");
	}

}

