package multiThread.lessonDemo;

public class demo {
	public static void main(String[] args) throws InterruptedException {
		EnterPot pot=new EnterPot();
		Consumer c1=new Consumer(pot);
		Consumer c2=new Consumer(pot);
		
		c1.start();
		c2.start();
		
	}
}
