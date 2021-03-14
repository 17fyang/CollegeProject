package mcm2020Test.geneticAlgorithm;


/*
 * ����Ⱦɫ���������Ӧ�ȵȲ�������
 */
public class CLS {
	private int trainTimes=10000;//ѵ�����ݼ�����
	public static final double radius=1.7;//service radius
	private double intensiveProportion=0.8;
	private double fitProportion=0.1;

	//����Ⱦɫ�����
	public double[] decode(String single){
		Long a=Long.parseLong(single.substring(0,ModelConfig.geneNumber/2),2);
		Long b=Long.parseLong(single.substring(ModelConfig.geneNumber/2,ModelConfig.geneNumber),2);
		double[] x = {-1,-1};
		x[0] = a * (4.3 - 0) / (Math.pow(2, ModelConfig.geneNumber/2) - 1);	//x�Ļ���
		x[1] = b * (5.8 - 0) / (Math.pow(2, ModelConfig.geneNumber/2) - 1);	//y�Ļ���
		return x;
	}

	//��ʼ��һ��Ⱦɫ��
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

	//��ʼ��һ��Ⱦɫ��
	public String[] initAll(int groupsize){
		String[] iAll = new String[groupsize];
		for(int i = 0; i < groupsize; i++){
			iAll[i] = initSingle();
		}
		return iAll;
	}

	//���������������Ӧ��
	public TestResult fitAll(String str[]){
		double coord[][]=new double[str.length][];
		for(int i=0;i<coord.length;i++) {
			coord[i]=this.decode(str[i]);
		}

		int fitTimes[]=new int[coord.length];//����������ĳȾɫ�巶Χ�ڴ���
		int coverTimes=0;//��������׼ȷ����
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
		//��Ӧ�ȼ���Ӱ���������ܼ��̶Ⱥ�������
		for(int i=0;i<fitness.length;i++) {
			double fitProperty=fitTimes[i]*1.0/trainTimes;
			double intensiveProperty=intensiveNumber[i]*1.0/coord.length*-1;
			fitness[i]=fitProperty*fitProportion+intensiveProperty*intensiveProportion;
		}
		int rank[]=this.rank(fitness);


		return new TestResult(coverage,fitness,rank);
	}
	//�����Ƿ���Ȧ��
	private boolean inDistance(double distance) {
		return distance*distance<radius*radius;
	}
	//���ؾ����ƽ��
	private double distance(double[] x,double[] y) {
		return (x[0]-y[0])*(x[0]-y[0])+(x[1]-y[1])*(x[1]-y[1]);
	}

	//��Ӧ����ֵ��������ţ�
	public int mFitNum(double fit[]){
		double m = fit[0]; 
		int n = 0;
		for(int i = 0;i < fit.length; i++){
			if(fit[i] > m){
				//���ֵ
				m = fit[i];
				n = i;
			}
		}
		return n;
	}

	//��Ӧ����ֵ��������Ӧ�ȣ�
	public double mFitVal(double fit[]){
		double m = fit[0]; 
		for(int i = 0;i < fit.length; i++){
			if(fit[i] > m){
				//���ֵ
				m = fit[i];
			}
		}
		return m;
	}

	//����Ӧ������
	public  int[] rank(double[] fit) {
		int result[]=new int[fit.length];
		double temp[]=new double[fit.length];
		for(int i=0;i<fit.length;i++)	temp[i]=fit[i];//����һ����Ӧ��;
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
