package multiThread.lessonDemo;

public class Producer extends Thread{
	EnterPot p=null;
	Producer (){}
	Producer(EnterPot p){
		this.p=p;
	}
	
	public void run() {
		int n=6;
		while(true) {
			p.getAndPut(n);
		}
	}
}
