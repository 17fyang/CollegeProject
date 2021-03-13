package mcm2020Test.geneticAlgorithm;


/*
 * 处理染色体编码求适应度等操作的类
 */
public class CLS {
	private int trainTimes=10000;//训练数据集个数
	public static final double radius=1.7;//service radius
	private double intensiveProportion=0.8;
	private double fitProportion=0.1;

	//单个染色体解码
	public double[] decode(String single){
		Long a=Long.parseLong(single.substring(0,ModelConfig.geneNumber/2),2);
		Long b=Long.parseLong(single.substring(ModelConfig.geneNumber/2,ModelConfig.geneNumber),2);
		double[] x = {-1,-1};
		x[0] = a * (4.3 - 0) / (Math.pow(2, ModelConfig.geneNumber/2) - 1);	//x的基因
		x[1] = b * (5.8 - 0) / (Math.pow(2, ModelConfig.geneNumber/2) - 1);	//y的基因
		return x;
	}

	//初始化一条染色体
	public String initSingle(){
		String res = "";
		for(int i = 0; i < ModelConfig.geneNumber; i++){
			if(Math.random() < 0.5){
				res += 0;
			}else{
				res +=1;
			}
		}
		return res;
	}

	//初始化一组染色体
	public String[] initAll(int groupsize){
		String[] iAll = new String[groupsize];
		for(int i = 0; i < groupsize; i++){
			iAll[i] = initSingle();
		}
		return iAll;
	}

	//批量计算数组的适应度
	public TestResult fitAll(String str[]){
		double coord[][]=new double[str.length][];
		for(int i=0;i<coord.length;i++) {
			coord[i]=this.decode(str[i]);
		}

		int fitTimes[]=new int[coord.length];//测试数据在某染色体范围内次数
		int coverTimes=0;//测试数据准确次数
		for(int i=0;i<trainTimes;i++) {
			double newCls[]=this.decode(this.initSingle());
			boolean isCover=false;
			for(int j=0;j<coord.length;j++) {
				double distance=this.distance(newCls, coord[j]);
				if(inDistance(distance)) {
					fitTimes[j]++;
					if(!isCover) {
						isCover=true;
						coverTimes++;
					}
				}
			}
		}

		int intensiveNumber[]=new int[coord.length];
		for(int i=0;i<coord.length;i++) {
			for(int j=i+1;j<coord.length;j++) {
				double distance=this.distance(coord[i], coord[j]);
				if(inDistance(distance)) {
					intensiveNumber[i]++;
					intensiveNumber[j]++;
				}
			}
		}
		double coverage=coverTimes*1.0/trainTimes;
		double fitness[]=new double[coord.length];
		//适应度计算影响因素有密集程度和命中率
		for(int i=0;i<fitness.length;i++) {
			double fitProperty=fitTimes[i]*1.0/trainTimes;
			double intensiveProperty=intensiveNumber[i]*1.0/coord.length*-1;
			fitness[i]=fitProperty*fitProportion+intensiveProperty*intensiveProportion;
		}
		int rank[]=this.rank(fitness);


		return new TestResult(coverage,fitness,rank);
	}
	//返回是否在圈内
	private boolean inDistance(double distance) {
		return distance*distance<radius*radius;
	}
	//返回距离的平方
	private double distance(double[] x,double[] y) {
		return (x[0]-y[0])*(x[0]-y[0])+(x[1]-y[1])*(x[1]-y[1]);
	}

	//适应度最值（返回序号）
	public int mFitNum(double fit[]){
		double m = fit[0]; 
		int n = 0;
		for(int i = 0;i < fit.length; i++){
			if(fit[i] > m){
				//最大值
				m = fit[i];
				n = i;
			}
		}
		return n;
	}

	//适应度最值（返回适应度）
	public double mFitVal(double fit[]){
		double m = fit[0]; 
		for(int i = 0;i < fit.length; i++){
			if(fit[i] > m){
				//最大值
				m = fit[i];
			}
		}
		return m;
	}

	//给适应度排序
	public  int[] rank(double[] fit) {
		int result[]=new int[fit.length];
		double temp[]=new double[fit.length];
		for(int i=0;i<fit.length;i++)	temp[i]=fit[i];//复制一份适应度;
		for(int i=0;i<fit.length;i++) {
			double max=temp[0];
			int locate=0;
			for(int j=0;j<fit.length;j++) {
				if(temp[j]>max) {
					max=temp[j];
					locate=j;
				}
			}
			result[i]=locate;
			temp[locate]=-999;
		}
		return result;
	}
}

class TestResult{
	double coverage;
	int[] intensiveNumber;
	double[] fitness;
	int[] rank;
	public TestResult(double coverage,double fitness[],int rank[]) {
		this.coverage=coverage;
		this.fitness=fitness;
		this.rank=rank;
	}
}
