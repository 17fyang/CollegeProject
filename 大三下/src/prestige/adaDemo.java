package prestige;

/**
 * ClassName: adaDemo
 * Description:
 * date: 2020/7/7 0:30
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class adaDemo {
    public static void main(String[] args) {
        Thread hospital=new Thread(()-> System.out.println("tell doctor"));
        hospital.setPriority(9);//设置优先级
        Thread electric=new Thread(()-> System.out.println("electric shock"));
        electric.setPriority(10);//设置为最高优先级

        //开启观察者线程
        Integer heart=60;
        new Thread(new Observer(hospital,electric,heart)).start();
    }
}
class Observer implements Runnable{
    private Thread hospital=null;
    private Thread electric=null;
    private Integer heart=null;
    public Observer(Thread hospital, Thread electric, Integer heart) {
        this.hospital = hospital;
        this.electric = electric;
        this.heart = heart;
    }
    @Override
    public void run() {
        int interval=3000;
        while(true){
            if(heart==0){
                //如果心脏衰竭则启动两个线程并让出cpu
                this.electric.start();
                this.hospital.start();
                Thread.yield();
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
