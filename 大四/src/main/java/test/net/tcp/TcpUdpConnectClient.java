package test.net.tcp;

import test.net.udp.UdpServerDemo;

import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/1/7 18:04
 * @Description: 客户端用tcp，服务端用udp，看udp报文中的内容
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
