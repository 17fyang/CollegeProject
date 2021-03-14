package mcm2020;

public class Test {
	Main m=new Main();
	public static void main(String[] args) throws Exception {
		/*
		int trainRange=10;
		int neuronRange=30;
		int trainStart=10;
		int neuronStart=5;
		Parameter p[]=new Parameter[trainRange*neuronRange];
		for(int i=0;i<p.length;i++) {
			p[i]=new Parameter();
			p[i].trainNumber=trainStart+i%trainRange;
			p[i].neuronLength=neuronStart+i/trainRange;
		}
		for(int i=0;i<p.length;i++) {
			setParameter(p[i]);
			Main.main(args);
			double result=Main.resultRate;
			System.out.print(trainStart+i%trainRange+"  ");
			System.out.print(neuronStart+i/trainRange+"  ");
			System.out.println(result);
		}
		*/
		for(int i=0;i<20;i++) {
			setParameter(new Parameter());
			Main.main(args);
			double result=Main.resultRate;
			System.out.println(i+" "+result);
		}
		
	}
	public static void setParameter(Parameter p) {
		Main.activationTimes=p.activationTimes;
		Main.trainNumber=p.trainNumber;
		Main.learnSpeed=p.learnSpeed;
		Main.learnSpeed=p.learnSpeed;
	}
}

class Parameter{
	public int neuronLength=15;//隐含层数量
	public double learnSpeed=0.06;//学习速度
	public  int trainNumber=15;//训练集数量
	public double activationTimes=10000;//迭代次数
}
