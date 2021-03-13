package IntelligenceSystem.geneticAlgorithm;

public class ModelConfig {
	public static final int groupNumber = 50;	//染色体个数
	public static final double mutationPercent = 0.15;	//变异概率
	public static final int runTimes = 20000;	//迭代次数
	public static final int accuracy = 24;	//精确度，选择精确到小数点后几位
	public static final double correctFitness=2;//正确的适应值
	public static final double fitnessAccuracy=0.000001;//适应度识别精度
	public static final double fitnessDistance=0.001;//当某一点的和最大值距离小于这个时忽略，也就是求得一个最大值之后下次就不求了的意思。
	
	//求出精度对应的所需基因数
	private static int temp = (int) ((int)Math.log(6)+ accuracy*Math.log(10) );
	public static final int geneNumber = temp * 2;	//基因数
	public static int getTemp() {
		return temp;
		
	}
	
}
