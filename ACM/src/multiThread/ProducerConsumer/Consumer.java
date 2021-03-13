package multiThread.ProducerConsumer;

public class Consumer extends Thread{
	private EnterPot pot=null;
	public Consumer() {}
	public Consumer(EnterPot pot) {
		this.pot=pot;
	}
	
	public void run() {
		while(true) {
			pot.get();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
