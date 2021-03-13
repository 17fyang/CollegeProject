package sf.Q6_7;

import java.util.*;

/**
 * ClassName: Q02
 * Description:
 * date: 2020/6/7 19:58
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class Q02 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        while(n-->0){
            int k=sc.nextInt();
            Person[] arr=new Person[k];
            String s=sc.nextLine();
            for(int i=0;i<k;i++)    arr[i]=new Person(sc.nextLine());
            for(int i=0;i<k;i++){
                for(int j=1;j<k-i;j++){
                    if(arr[j].year>arr[j-1].year){
                        Person p=arr[j];
                        arr[j]=arr[j-1];
                        arr[j-1]=p;
                    }
                }
            }
//            Collections.sort(arr,(Person p1,Person p2)-> p2.year - p1.year);
            for(Person p:arr) System.out.println(p.name);
        }
        sc.close();
    }

}

class Person{
    String name;
    int year;
    public Person(String line){
        this.name=line.substring(0,line.length()-5);
        this.year=Integer.valueOf(line.substring(line.length()-4,line.length()));
    }
}