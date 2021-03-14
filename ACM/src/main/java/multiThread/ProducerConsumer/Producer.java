package multiThread.ProducerConsumer;

public class Producer extends Thread{
	private EnterPot pot=null;
	public Producer() {}
	public Producer(EnterPot pot) {
		this.pot=pot;
	}
	public void run() {
		while(true) {
			pot.put(1);
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
