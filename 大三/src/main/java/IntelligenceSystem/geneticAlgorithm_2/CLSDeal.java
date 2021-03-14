package IntelligenceSystem.geneticAlgorithm_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CLSDeal {
	private static double[] splitDouble=getSplitLocate();
	CLS cls=new CLS();
	
	/*
	 * ����Ⱦɫ�彻��ķ���
	 * ����Ӧ�ȴ�Сռ�Ⱦ������Խ�������ĺ������
	 * ʹ�ú���e-e^x�����������
	 */
	public String[] cross(String[] group){
		String newGroup[]=new String[group.length*2];
		double[] fit = cls.fitAll(group);
		int fitRank[]=cls.rank(fit);

		for(int i = 0; i < group.length; i++){
			String cls1=null;
			String cls2=null;
			double random=Math.random();
			for(int j=0;j<group.length-1;j++){//������һ��������Ϊ�ӽ�����
				if(random<splitDouble[0])		cls1=group[fitRank[0]];
				if(random>=splitDouble[j] &&random < splitDouble[j+1]) 	cls1=group[fitRank[j]];
			}
			
			while(cls2==null || cls2==cls1) {//����Ҫ�ظ����ڶ���
				random=Math.random();
				for(int j=0;j<group.length-1;j++){
					if(random<splitDouble[0])		cls2=group[fitRank[0]];
					if(random>=splitDouble[j] &&random < splitDouble[j+1]) 	cls2=group[fitRank[j]];
				}
			}
			
			int pos = (int)(Math.random()*ModelConfig.geneNumber) + 1;	//�����
			String temp = cls1.substring(0, pos) + cls2.substring(pos);	
			newGroup[i] = temp;
		}
		//������һ������
		for(int i=group.length;i<newGroup.length;i++) newGroup[i]=group[i-group.length];
		return newGroup;
	}
	/*
	 * ����Ⱦɫ�����ķ���
	 */
	public String[] mutation(String[] group){
		CLS cls=new CLS();
		double[] fit = cls.fitAll(group);
		int mFitNum = cls.mFitNum(fit);	//������Ӧ������Ⱦɫ�����
		String max = group[mFitNum];
 
		for(int i = 0; i < group.length *ModelConfig.mutationPercent; i++){
			int n = (int) (Math.random() * ModelConfig.geneNumber * group.length );	//��[0��GENE * group.length)����ȡ�����
			int chrNum = (int) (n / ModelConfig.geneNumber);	//ȡ�õ�Ⱦɫ�������±�
			int gNum = (int)(n % ModelConfig.geneNumber); 	//ȡ�õĻ����±�
			String temp = "";

			if(group[chrNum].charAt(gNum) == '0' ){
				temp = replacePos(group[chrNum], gNum, "1");
			}else{
				temp = replacePos(group[chrNum], gNum, "0");
			}
			group[chrNum] = temp;
		}
		group[0] = max;
		return group;
	}
	/*
	 * ����Ⱦɫ��ѡ��ķ���
	 * ѡȡԭȾɫ����Ӧ��ǰ3/4�����ݣ��������1/4������
	 */
	public String[] RWS(String[] group){
		group=beOnly(group);
		String[] newGroup = new String[ModelConfig.groupNumber];//�µ�Ⱦɫ����
		double[] fitArr = cls.fitAll(group);	//������Ӧ������
		int rank[]=cls.rank(fitArr);
		int oldNumber=ModelConfig.groupNumber*3/4;
		for(int i=0;i<oldNumber;i++) 		newGroup[i]=group[rank[i]];
		for(int i=oldNumber;i<ModelConfig.groupNumber;i++) 	newGroup[i] = cls.initSingle();
		return newGroup;
	}
	
	//��groupȥ��
	private String[] beOnly(String[] group) {
		Set<String> set=new HashSet<String>();
		for(int i=0;i<group.length;i++)	 set.add(group[i]);
		List<String> list=new ArrayList<String>(set);
		return list.toArray(new String[list.size()]);
	}
	
	private String replacePos(String str,int num,String pos){
		String temp;
		if(num == 0){
			temp = pos + str.substring(1);
		}else if(num == str.length()-1){
			temp = str.substring(0, str.length() - 1) + pos;
		}else{
			String temp1 = str.substring(0, num);
			String temp2 = str.substring(num + 1);
			temp = temp1 + pos + temp2;
		}
		return temp;		
	}
	
	private static double[] getSplitLocate() {//���ÿ���������鵽�ĸ���
		double[] result=new double[ModelConfig.groupNumber];
		double total=formula(1)-formula(0);
		for(int i=0;i<ModelConfig.groupNumber;i++) {
			result[i]=(formula((i+1.0)/ModelConfig.groupNumber)-formula(0))/total;
		}
		return result;
	}
	private static double formula(double x) {
		return Math.E*x-Math.pow(Math.E,x);
	}
}
