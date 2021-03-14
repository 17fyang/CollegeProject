package mutiTest;

/**
 * ClassName: ClassLockTest
 * Description:
 * date: 2020/8/13 23:56
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class ClassLockTest {

    public static void main(String[] args) {
        Object lock = new Object();
        ClassLock_A a = new ClassLock_A(lock);
        ClassLock_B b = new ClassLock_B(lock);
        new Thread(a::run).start();
        new Thread(b::run).start();
    }
}

class ClassLock_A {
    private Object lock;

    public ClassLock_A(Object lock) {
        this.lock = lock;
    }

    public void run() {
        synchronized (lock) {
            System.out.println("aaaaaaaaaaaaaaa");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ClassLock_B {
    private Object lock;

    public ClassLock_B(Object lock) {
        this.lock = lock;
    }

    public void run() {
        synchronized (lock) {
            System.out.println("bbbbbbbbbbbbbbbbbb");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
