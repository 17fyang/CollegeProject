package test.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author: 涔岄甫鍧愰鏈轰籂
 * @date: 2021/1/7 18:09
 * @Description: udp瀹㈡埛绔殑demo
 */
public class UdpClientDemo {
    public static final int SERVER_PORT = UdpServerDemo.PORT;

    public static void main(String[] args) throws Exception {
        while (true) {
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] byteData = "data".getBytes();
            DatagramPacket packet = new DatagramPacket(byteData, byteData.length, InetAddress.getByName("120.79.175.145"), SERVER_PORT);
            clientSocket.send(packet);

            Thread.sleep(3000);
        }

    }
}
