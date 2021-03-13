package ¿ìÊÖ;


public class Q03 {
	public static void main(String[] args) {
		int a[]= {8,9,7};
		int b[]= {5,8,3};
		WaitInLine(a,b);
	}
	public static int[] WaitInLine (int[] a, int[] b) {
		Person list[]=new Person[a.length];
		for(int i=0;i<a.length;i++) {
			list[i]=new Person(i,a[i]-b[i]);
		}
		for(int i=0;i<a.length;i++) {
			for(int j=1;j<a.length-i;j++) {
				if(list[j-1].value<list[j].value) {
					Person p=list[j-1];
					list[j-1]=list[j];
					list[j]=p;
				}
			}
		}
		int rank[]=new int[list.length];
		for(int i=0;i<rank.length;i++) {
			rank[i]=list[i].rank+1;
		}
		
		for(int i=0;i<rank.length;i++) {
			System.out.println(rank[i]);
		}
		
        return rank;
    }
}
class Person{
	Person(int rank,int value){
		this.rank=rank;
		this.value=value;
	}
	int rank=0;
	int value=0;
}
