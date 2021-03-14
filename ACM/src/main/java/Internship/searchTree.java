package Internship;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * 非递归遍历二叉树
 */
public class searchTree {
	public static void main(String[] args) {
		Node head=newTree();
		func(head);
		beforeSearchTree(head);
	}
	public static Node newTree() {
		Node head=new Node(999);
		int count=0;
		Queue<Node> s=new LinkedBlockingQueue<Node>();
		s.add(head);
		while(count++<20) {
			Node n=s.peek();
			Node newNode=new Node(count);
			if(n.leftChild==null)	n.leftChild=newNode;
			else if(n.rightChild==null) {
				n.rightChild=newNode;
				s.poll();
			}
			s.add(newNode);
		}
		return head;
	}
	//递归实现
	public static void func(Node head) {
		if(head==null)	return;
		System.out.println("递归实现："+head.value);
		func(head.leftChild);
		func(head.rightChild);
		
	}
	//非递归
	public static void beforeSearchTree(Node head) {
		Stack<Node> s=new Stack<Node>();
		Node n=head;
		Node last=null;
		while(n!=null || !s.isEmpty()) {
			if(n!=null) {
				s.add(n);
				n=n.leftChild;
			}else {
				n=s.peek();
				if(n.rightChild==null || last==n.rightChild) {
					System.out.println(n.value);
					
					last=n;
					s.pop();
					n=null;
				}else {
					n=n.rightChild;
				}
				
			}
			
//			前序和中序
//			while(n!=null) {
//				flag=false;
//				s.add(n);
//				n=n.leftChild;
//			}
//			if(!s.isEmpty()) {
//				n=s.pop();
//				System.out.println(n.value);
//				n=n.rightChild;
//				flag=true;
//			}
		}
	}
}
class Node{
	int value;
	Node leftChild;
	Node rightChild;
	public Node(int value) {
		this.value=value;
	}
}
