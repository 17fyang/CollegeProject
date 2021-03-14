package net;

import java.io.IOException;
import java.net.Socket;

/**
 * ClassName: baiduClient
 * Description:
 * date: 2020/6/14 1:00
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class baiduClient {
    public static void main(String[] args) throws IOException {
        //建立连接
        Socket socket = new Socket("www.baidu.com", 80);
        //获取地址
        System.out.println("百度ip地址为："+socket.getInetAddress().getHostAddress());
        System.out.println("2017101081 杨帆");
    }
}
