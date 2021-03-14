package multiThread.ProducerConsumer;

public class EnterPot {
	private int MAX_SIZE=30;
	private Integer num=10;
	public int get() {
		synchronized(num) {
			if(num>=MAX_SIZE) {
				num.notify();
			}
			if(num>0) {
				num--;
				System.out.println("仓库中减少了1个，当前数量是"+num);
			}else {
				try {
					num.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
	public void put(int number) {
		synchronized(num) {
			if(num==0) {
				num.notifyAll();
			}
			if(num<=MAX_SIZE-number) {
				num+=number;
				System.out.println("仓库中增加了"+number+"个，当前数量是"+num);
			}else {
				try {
					System.out.println();
					num.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
