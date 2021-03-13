package meituan;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Q04 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		String staticString[]=new String[k];
		Set<String>	dynamicSet=new HashSet<String>();
		for(int i=0;i<k;i++) {
			String s=sc.next();
			staticString[i]=s;
			dynamicSet.add(s);
		}
		for(int i=0;i<n;i++) {
			String opera=sc.next();
			if(opera.startsWith("?")) {
				int times=askOperation(opera,dynamicSet);
				System.out.println(times);
			}else {
				dealOperation(opera,staticString,dynamicSet);
			}
		}
		sc.close();
	}

	private static void dealOperation(String opera, String[] staticString, Set<String> dynamicSet) {
		String	symbol=opera.substring(0,1);
		opera=opera.substring(1);
		if(symbol.equals("+"))		dynamicSet.add(staticString[Integer.parseInt(opera)-1]);
		else	dynamicSet.remove(staticString[Integer.parseInt(opera)-1]);
	}

	private static int askOperation(String opera,Set<String> dynamicSet) {
		opera=opera.substring(1);
		Iterator<String>	it=dynamicSet.iterator();
		int sum=0;
		while(it.hasNext()) {
			sum+=count(opera,it.next());
		}
		return sum;
	}
	public static int count(String srcStr, String findStr) {
		int count = 0;
		int index = 0;
		while ((index = srcStr.indexOf(findStr, index)) != -1) {
			index++;
			count++;
		}
		return count;
	}
}
