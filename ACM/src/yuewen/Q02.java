package yuewen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Q02 {
	
	public static void main(String[] args) {
		ArrayList<String> list=new ArrayList<String>();
		list.add("push 123");
		list.add("push 23");
		list.add("pop");
		list.add("pop");
		System.out.println(doQueue(list));
	}
	public static String doQueue (ArrayList<String> ops) {
       StringBuilder sb=new StringBuilder();
       Queue<Integer> stack=new LinkedList<Integer>();
       for(String s:ops) {
    	   if(s.startsWith("push")) {
    		   int value=Integer.parseInt(s.substring(5));
    		   stack.add(value);
    	   }else {
    		   Integer temp=stack.poll();
    		   if(temp==null)	sb.append("null");
    		   else sb.append(temp);
    		   sb.append(",");
    	   }
       }
       sb.delete(sb.length()-1, sb.length());
       return sb.toString();
    }
}
