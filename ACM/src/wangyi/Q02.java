package wangyi;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Q02 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int level[]=new int[n];
		Job firstJob=null;
		Job lastJob=null;
		for(int i=0;i<n;i++) level[i]=sc.nextInt();
		for(int i=0;i<n;i++) {
			int dif=sc.nextInt();
			Job job=new Job();
			for(int j=0;j<n;j++) {
				if(level[j]>=dif) {
					job.woker.add(j);
				}
			}
			if(i==0) {
				firstJob=job;
				lastJob=job;
			}else {
				lastJob=lastJob.next=job;
			}
		}
		int m=sc.nextInt();
		long total=0;
		total+=func(firstJob,new LinkedList<Integer>());
		System.out.println(total%m);
		sc.close();
	}
	private static long func(Job job,List<Integer> set) {
		Iterator<Integer>	it=job.woker.iterator();
		if(job.next==null) {
			while(it.hasNext()) {
				int work=it.next();
				if(!set.contains(work))		return 1;
			}
		}
		long total=0;
		while(it.hasNext()) {
			
			int work=it.next();
			if(set.contains(work))	continue;
			List<Integer> newSet=new LinkedList<Integer>(set);
			newSet.add(work);
			total+=func(job.next,newSet);
		}
		return total;
	}
	
//	public static void main(String[] args) {
//		Scanner sc=new Scanner(System.in);
//		int n=sc.nextInt();
//		int level[]=new int[n];
//		int levelNum[]=new int[n];
//		int hard[]=new int[n];
//		for(int i=0;i<n;i++) level[i]=sc.nextInt();
//		for(int i=0;i<n;i++)	hard[i]=sc.nextInt();
//		int m=sc.nextInt();
//		
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				if(level[i]>=hard[j])	levelNum[i]+=1;
//			}
//		}
//		
//		long result=1;
//		for(int i=0;i<n;i++) {
//			result*=levelNum[i];
//		}
//		System.out.println(result%m);
//		sc.close();
//	}
}

class Job{
	Job next=null;
	List<Integer> woker=new LinkedList<Integer>();
}
