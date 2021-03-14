package sf.Q5_31;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * ClassName: Q06
 * Description:
 * date: 2020/5/31 22:03
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q06 {
    static int min=Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            min=Integer.MAX_VALUE;
            int row=sc.nextInt();
            int col=sc.nextInt();
            Position arr[][]=new Position[row][col];
            for(int i=0;i<row;i++){
                char c[]=sc.next().toCharArray();
                for(int j=0;j<c.length;j++){
                    arr[i][j]=new Position(c[j]);
                }
            }
            Position start=null;
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[0].length;j++){
                    if(arr[i][j].type=='#') continue;
                    if(arr[i][j].type=='r') start=arr[i][j];
                    if(i>0 && arr[i-1][j].type!='#') arr[i][j].up=arr[i-1][j];
                    if(j>0 && arr[i][j-1].type!='#') arr[i][j].left=arr[i][j-1];
                    if(i<arr.length-1 && arr[i+1][j].type!='#') arr[i][j].down=arr[i+1][j];
                    if(j<arr[0].length-1 && arr[i][j+1].type!='#') arr[i][j].right=arr[i][j+1];
                }
            }
            func(new LinkedList<Position>(),start,0);
            System.out.println(min);
        }
        sc.close();
    }
    private static void func(List<Position> list,Position location,int length){
        List<Position> newList=new LinkedList<>(list);
        newList.add(location);
        length++;
        if(location.type=='x')  length++;
        if(location.type=='a'){
            if(length-1<min)  min=length-1;
        }
        if(location.left!=null && !list.contains(location.left))    func(newList,location.left,length);
        if(location.up!=null && !list.contains(location.up))    func(newList,location.up,length);
        if(location.right!=null && !list.contains(location.right))    func(newList,location.right,length);
        if(location.down!=null && !list.contains(location.down))    func(newList,location.down,length);
    }
}
class Position{
    char type;
    Position up;
    Position down;
    Position left;
    Position right;
    public Position(char type){
        this.type=type;
    }
}
