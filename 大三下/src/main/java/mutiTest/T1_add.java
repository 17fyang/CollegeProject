package mutiTest;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ClassName: T1_add
 * Description:
 * date: 2020/8/11 23:51
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class T1_add {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<String> atomicReference = new AtomicReference<>();
        atomicReference.get();
        MyValue myValue = new T1_add.MyValue();
        int p = 0;
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(() -> myValue.add());
            t.join();
            t.start();
        }
//        Thread.sleep(3000);
        System.out.println(myValue.value);
    }

    static class MyValue {
        int value;

        public void add() {
            value++;
        }
    }
}
