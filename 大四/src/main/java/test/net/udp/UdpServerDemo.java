package test.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author: 涔岄甫鍧愰鏈轰籂
 * @date: 2021/1/7 18:01
 * @Description:
 */
public class UdpServerDemo {
    public static final int PORT = 8218;

    public static void main(String[] args) throws Exception {
        DatagramSocket udpServer = new DatagramSocket(PORT);
        System.out.println("---鏈嶅姟鍣ㄧ宸茬粡鍚姩锛岀瓑寰呭鎴风鍙戦�佹暟鎹�---");
        while (true) {
            // 鍒涘缓瀛楄妭鏁扮粍锛屾寚瀹氭帴鏀剁殑鏁版嵁鍖呯殑澶у皬
            byte[] data = new byte[1024];
            // 鍒涘缓鏁版嵁鎶ワ紝鐢ㄤ簬鎺ユ敹瀹㈡埛绔彂閫佺殑鏁版嵁
            DatagramPacket packet = new DatagramPacket(data, data.length);
            // 闃诲绛夊緟瀹㈡埛绔彂閫佹暟鎹�
            udpServer.receive(packet);
            // 4.璇诲彇鏁版嵁
            String info = new String(data, 0, packet.getLength());
            System.out.println("鏈嶅姟绔敹鍒板鎴风鐨勫寘锛�" + info);
        }

    }
}
