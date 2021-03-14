package net;

import java.io.IOException;
import java.net.Socket;

/**
 * ClassName: baiduClient
 * Description:
 * date: 2020/6/14 1:00
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class baiduClient {
    public static void main(String[] args) throws IOException {
        //寤虹珛杩炴帴
        Socket socket = new Socket("www.baidu.com", 80);
        //鑾峰彇鍦板潃
        System.out.println("鐧惧害ip鍦板潃涓猴細"+socket.getInetAddress().getHostAddress());
        System.out.println("2017101081 鏉ㄥ竼");
    }
}
