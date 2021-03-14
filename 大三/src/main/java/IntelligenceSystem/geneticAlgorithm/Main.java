package IntelligenceSystem.geneticAlgorithm;

import java.util.List;

/*
 * 遗传算法，作业
 */
public class Main {
	
	//求出精度对应的所需基因数
//	int temp = (int) ((int)Math.log(6)+ ModelConfig.accuracy*Math.log(10) );
//	int geneNumber = temp * 2;	//基因数

	public static void main(String[] args) throws Exception {
		CLS cls=new CLS();
		CLSDeal clsDeal=new CLSDeal();
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
			double result=3-2*cls.fitSingle(resultStringList.get(i));
			System.out.println("x1 = "+bestResult[0]+"  "+"x2 = "+bestResult[1]+"   函数值："+result);
		}
		d.showChart(data);
		System.out.println("共找到满足条件的点："+resultDoubleList.size()+"个");
	}

}

