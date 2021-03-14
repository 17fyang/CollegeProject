package IntelligenceSystem.geneticAlgorithm_2;

import java.util.LinkedList;
import java.util.List;

/*
 * 处理染色体编码求适应度等操作的类
 */
public class CLS {
	private static List<String> singleList=new LinkedList<String>();//存放已完成的点编码前的链表
	private static List<double[]> doubleList=new LinkedList<double[]>();//存放已完成的点编码后的链表
	//单个染色体解码
	public double[] decode(String single){
//		System.out.println(single.substring(0,ModelConfig.geneNumber/2));
		long a=Long.parseLong(single.substring(0,ModelConfig.geneNumber/2),2);
		long b=Long.parseLong(single.substring(ModelConfig.geneNumber/2,ModelConfig.geneNumber),2);
		double[] x = {-1,-1};
		x[0] = a * 20/ (Math.pow(2, ModelConfig.geneNumber/2) - 1)-10;	//x的基因
		x[1] = b * 20 / (Math.pow(2, ModelConfig.geneNumber/2) - 1)-10;	//y的基因
		return x;
	}

	//单个染色体编码
	public double[] encode(double[] single){
		return null;
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

	//适应度计算公式：sin+sin；
	//sin+sin越大，3-sin-sin越小，即得到的值越小个体的适应度就越大
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

	//计算个体的适应度
	public double fitSingle(String str){
		double[] x =this.decode(str);
		//适应度计算公式
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
		
		
		//判断是否在已知最大值周围,是的话则降低其适应度
//		for(int i=0;i<doubleList.size();i++) {
//			double exitMax[]=doubleList.get(i);
//			double distance=(exitMax[0]-x[0])*(exitMax[0]-x[0])+(exitMax[1]-x[1])*(exitMax[1]-x[1]);
//			if(distance<ModelConfig.fitnessDistance) {
//				if(fitness>formula(exitMax)) {//替换掉最大值
//					doubleList.set(i, x);
//					singleList.set(i, str);
//				}
//				fitness=fitness/2;
//			}
//		}
//
//		//判断是否找到新的最大值
//		if(ModelConfig.correctFitness-fitness<=ModelConfig.fitnessAccuracy) {
//			doubleList.add(x);
//			singleList.add(str);
//		}
		return fitness;
	}

	//批量计算数组的适应度
	public double[] fitAll(String str[]){
		double [] fit = new double[str.length];
		for(int i = 0;i < str.length; i++){
			fit[i] = fitSingle(str[i]);
		}
		return fit;
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

		public static List<double[]> getDoubleList() {
			return doubleList;
		}

		public static List<String> getSingleList() {
			return singleList;
		}


	}
