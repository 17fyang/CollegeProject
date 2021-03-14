package test.net.tcp;

import test.net.udp.UdpServerDemo;

import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: 涔岄甫鍧愰鏈轰籂
 * @date: 2021/1/7 18:04
 * @Description: 瀹㈡埛绔敤tcp锛屾湇鍔＄鐢╱dp锛岀湅udp鎶ユ枃涓殑鍐呭
 */
public class TcpUdpConnectClient {
    public static final int SERVER_PORT = UdpServerDemo.PORT;
    public static final String SERVER_HOST = "120.79.175.145";

    public static void main(String[] args) throws Exception {
        Socket client = new Socket(SERVER_HOST, SERVER_PORT);
        OutputStream out = client.getOutputStream();
        out.write("data".getBytes());
        client.close();
    }
}
