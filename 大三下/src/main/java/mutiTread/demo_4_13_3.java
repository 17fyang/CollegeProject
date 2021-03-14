package mutiTread;

public class demo_4_13_3 {
    public static void main(String[] args) {
        MyInteger m = new MyInteger(0);
        CountThread c1 = new CountThread("c1", m);
        CountThread c2 = new CountThread("c2", m);
        CountThread c3 = new CountThread("c3", m);
        CountThread c4 = new CountThread("c4", m);

        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
}

class CountThread extends Thread {
    MyInteger m = null;
    String name;

    public CountThread(String name, MyInteger m) {
        this.name = name;
        this.m = m;
    }

    public void run() {
        int i = 0;
        while (i++ < 20) {
            synchronized (m) {
                int key = Integer.valueOf(name.substring(1));
                while (m.value % 4 + 1 != key) {
                    try {
                        m.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                m.notifyAll();
                m.value++;
                System.out.println(name + "锟斤拷锟斤拷锟剿ｏ拷" + m.value);
            }
        }

    }
}
