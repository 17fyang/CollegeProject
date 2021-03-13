package meituan;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Q02 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		int arr[]=new int[num];
		Map<Integer,Integer> map=new LinkedHashMap<Integer,Integer>();
		for(int i=0;i<num;i++) {
			arr[i]=sc.nextInt();
			map.put(arr[i], i);
		}
		Map<Integer,Integer> newMap=new LinkedHashMap<Integer,Integer>();
		int result=0;
		for(int i=0;i<num;i++) {
			int p=sc.nextInt();
			newMap.put(p, i);
			int startLocation=map.get(p);
			if(startLocation>i) {
				result++;
				continue;
			}else {
				int count=0;
				for(Entry<Integer,Integer> entry : map.entrySet()) {
					if(count++>=startLocation)		break;
					if(newMap.get(entry.getKey())==null) {
						result++;
						break;
					}
				}
			}
		}
		System.out.println(result);
		sc.close();
	}
}
