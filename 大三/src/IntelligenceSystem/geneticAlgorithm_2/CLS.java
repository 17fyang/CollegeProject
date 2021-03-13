package IntelligenceSystem.geneticAlgorithm_2;

import java.util.LinkedList;
import java.util.List;

/*
 * ����Ⱦɫ���������Ӧ�ȵȲ�������
 */
public class CLS {
	private static List<String> singleList=new LinkedList<String>();//�������ɵĵ����ǰ������
	private static List<double[]> doubleList=new LinkedList<double[]>();//�������ɵĵ����������
	//����Ⱦɫ�����
	public double[] decode(String single){
//		System.out.println(single.substring(0,ModelConfig.geneNumber/2));
		long a=Long.parseLong(single.substring(0,ModelConfig.geneNumber/2),2);
		long b=Long.parseLong(single.substring(ModelConfig.geneNumber/2,ModelConfig.geneNumber),2);
		double[] x = {-1,-1};
		x[0] = a * 20/ (Math.pow(2, ModelConfig.geneNumber/2) - 1)-10;	//x�Ļ���
		x[1] = b * 20 / (Math.pow(2, ModelConfig.geneNumber/2) - 1)-10;	//y�Ļ���
		return x;
	}

	//����Ⱦɫ�����
	public double[] encode(double[] single){
		return null;
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

	//��Ӧ�ȼ��㹫ʽ��sin+sin��
	//sin+sinԽ��3-sin-sinԽС�����õ���ֵԽС�������Ӧ�Ⱦ�Խ��
	public double formula(double[] x) {
		int n=2;
		double cheng=1;
		for(int i=0;i<n;i++) {
			double sum=0;
			for(int j=1;j<=5;j++) {
				sum+=j*Math.cos((j+1)*x[i]+j);
			}
			cheng=cheng*sum;
		}
		return cheng*-1;
	}

	//����������Ӧ��
	public double fitSingle(String str){
		double[] x =this.decode(str);
		//��Ӧ�ȼ��㹫ʽ
		double fitness =formula(x);
		if(doubleList.isEmpty()) {
			doubleList.add(x);
			singleList.add(str);
		}
		double fitnessMax=this.formula(doubleList.get(0));
		if(fitness>fitnessMax) {
			doubleList.set(0, x);
			singleList.set(0, str);
		}
		
		
		//�ж��Ƿ�����֪���ֵ��Χ,�ǵĻ��򽵵�����Ӧ��
//		for(int i=0;i<doubleList.size();i++) {
//			double exitMax[]=doubleList.get(i);
//			double distance=(exitMax[0]-x[0])*(exitMax[0]-x[0])+(exitMax[1]-x[1])*(exitMax[1]-x[1]);
//			if(distance<ModelConfig.fitnessDistance) {
//				if(fitness>formula(exitMax)) {//�滻�����ֵ
//					doubleList.set(i, x);
//					singleList.set(i, str);
//				}
//				fitness=fitness/2;
//			}
//		}
//
//		//�ж��Ƿ��ҵ��µ����ֵ
//		if(ModelConfig.correctFitness-fitness<=ModelConfig.fitnessAccuracy) {
//			doubleList.add(x);
//			singleList.add(str);
//		}
		return fitness;
	}

	//���������������Ӧ��
	public double[] fitAll(String str[]){
		double [] fit = new double[str.length];
		for(int i = 0;i < str.length; i++){
			fit[i] = fitSingle(str[i]);
		}
		return fit;
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

		public static List<double[]> getDoubleList() {
			return doubleList;
		}

		public static List<String> getSingleList() {
			return singleList;
		}


	}
