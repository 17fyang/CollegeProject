package multiThread.lessonDemo;

public class Consumer extends Thread{
	EnterPot p=null;
	Consumer(){}
	Consumer(EnterPot p){
		this.p=p;
	}
	
	public void run() {
		int n=-5;
		while(true) {
			p.getAndPut(n);
		}
		
	}
}
