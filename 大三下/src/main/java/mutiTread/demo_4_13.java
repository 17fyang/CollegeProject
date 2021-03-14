package mutiTread;

public class demo_4_13 {
	public static void main(String[] args) throws InterruptedException {
		OtherThread t1=new OtherThread(1);
		OtherThread t2=new OtherThread(2);
		OtherThread t3=new OtherThread(3);
		
		t1.join();
		t2.join();
		t3.join();
		
		t1.start();
		t2.start();
		t3.start();
		
		System.out.println("main finish");
	}
}

class OtherThread extends Thread{
	private int number=0;
	public OtherThread(int number) {
		this.number=number;
	}
	public void run() {
		while(true) {
			if(1==2)	break;
		}
		System.out.println(number);
	}
}