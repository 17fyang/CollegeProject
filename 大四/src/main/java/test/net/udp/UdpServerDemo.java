package test.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/1/7 18:01
 * @Description:
 */
public class UdpServerDemo {
    public static final int PORT = 8218;

    public static void main(String[] args) throws Exception {
        DatagramSocket udpServer = new DatagramSocket(PORT);
        System.out.println("---服务器端已经启动，等待客户端发送数据---");
        while (true) {
            // 创建字节数组，指定接收的数据包的大小
            byte[] data = new byte[1024];
            // 创建数据报，用于接收客户端发送的数据
            DatagramPacket packet = new DatagramPacket(data, data.length);
            // 阻塞等待客户端发送数据
            udpServer.receive(packet);
            // 4.读取数据
            String info = new String(data, 0, packet.getLength());
            System.out.println("服务端收到客户端的包：" + info);
        }

    }
}
