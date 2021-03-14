package prestige;

/**
 * ClassName: adaDemo
 * Description:
 * date: 2020/7/7 0:30
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class adaDemo {
    public static void main(String[] args) {
        Thread hospital=new Thread(()-> System.out.println("tell doctor"));
        hospital.setPriority(9);//璁剧疆浼樺厛绾�
        Thread electric=new Thread(()-> System.out.println("electric shock"));
        electric.setPriority(10);//璁剧疆涓烘渶楂樹紭鍏堢骇

        //寮�鍚瀵熻�呯嚎绋�
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
                //濡傛灉蹇冭剰琛扮鍒欏惎鍔ㄤ袱涓嚎绋嬪苟璁╁嚭cpu
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
