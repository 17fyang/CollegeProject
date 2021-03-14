package prestige;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: mutiDemo
 * Description:
 * date: 2020/7/6 23:31
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class MutiDemo {
    public static void main(String[] args) {
        AtomicInteger i= new AtomicInteger();
        new Thread(new MyThread(i)).start();
        new Thread(new MyThread(i)).start();
        new Thread(new MyThread(i)).start();
    }
}
class MyThread implements Runnable{
    AtomicInteger i=null;
    public MyThread(AtomicInteger i){
        this.i=i;
    }
    @Override
    public void run() {
        while(true){
            System.out.println(i.getAndIncrement());
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
