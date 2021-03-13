package mcm2020Test.geneticAlgorithm;

/*
 * 遗传算法，作业
 */
public class Main {

	public static void main(String[] args) throws Exception {
		CLS cls=new CLS();
		CLSDeal clsDeal=new CLSDeal();
		String group[] = cls.initAll(ModelConfig.groupNumber);	//初始化
		for(int i = 0; i < ModelConfig.runTimes; i++){
			group = clsDeal.cross(group);	//交叉
			group = clsDeal.mutation(group);	//变异
			group = clsDeal.RWS(group); //选择
			Draw d=new Draw();
			d.DrawGroup(group);
			d.dataTransfer(group);
		}
	}

}

