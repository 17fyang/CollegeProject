package qiniuyun;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Q01 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		Map<Integer,List<Integer>> map=new HashMap<Integer,List<Integer>>();
		for(int i=0;i<m;i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			if(map.get(x)==null)	map.put(x, new LinkedList<Integer>());
			if(map.get(y)==null)	map.put(y, new LinkedList<Integer>());
			map.get(x).add(y);
			map.get(y).add(x);
		}
		System.out.println(solution(map,n,m));
		sc.close();
	}
	private static String solution(Map<Integer,List<Integer>> map,int n,int m) {
		if(m==0)	return "NO";
		boolean flag[]=new boolean[n+1];
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(1);
		while(!q.isEmpty()) {
			int value=q.poll();
			flag[value]=true;
			List<Integer> list=map.get(value);
			for(int single : list) {
				if(!flag[single])	q.add(single);
			}
		}
		
		for(int i=1;i<flag.length;i++) {
			if(flag[i]==false)	return "NO";
		}
		return "YES";
	}
}
