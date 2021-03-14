package mutiTread;

public class demo_4_13_2 {

    public static void main(String[] args) {
        MyInteger s12 = new MyInteger(1);
        MyInteger s34 = new MyInteger(3);
        huchiThread h1 = new huchiThread(s12, 13);
        huchiThread h2 = new huchiThread(s12, 14);
        huchiThread h3 = new huchiThread(s34, 23);
        huchiThread h4 = new huchiThread(s34, 24);
        h1.start();
        h2.start();
        h3.start();
        h4.start();
    }
}

class huchiThread extends Thread {
    MyInteger m = null;
    int newInteger;

    public huchiThread(MyInteger m, int newInteger) {
        this.m = m;
        this.newInteger = newInteger;
    }

    public void run() {
        while (true) {
            synchronized (m) {
                System.out.println("ԭ����ֵ�ǣ�" + m.value + "�������ǣ�" + newInteger);
                m.value = newInteger;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class MyInteger {
    public MyInteger(int value) {
        this.value = value;
    }

    int value = 0;
}