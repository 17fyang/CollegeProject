package mcm2020Test.geneticAlgorithm;

public class ModelConfig {
	public static final int groupNumber = 5000;	//Chromosome Number
	public static final double mutationPercent = 0.15;	//mutation probability
	public static final int runTimes = 20;	//iterations
	public static final int accuracy = 15;	//accuracy
	
	//Number of genes
	private static int temp = (int) ((int)Math.log(6)+ accuracy*Math.log(10) );
	public static final int geneNumber = temp * 2;	//Number of genes
	public static int getTemp() {
		return temp;
	}
	
}
