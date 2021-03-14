package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;

/**
 * ClassName: socketTest
 * Description:
 * date: 2020/6/14 0:52
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class socketServerTest {
    public static void main(String[] args) throws Exception {
        ServerSocket server=new ServerSocket(3425);
        Socket socket=server.accept();
        InputStream in=socket.getInputStream();        byte buf[]=new byte[1024];
        int len=in.read(buf);
        System.out.println(new String(buf,0,len));
        OutputStream out=socket.getOutputStream();
        out.write("服务机 2017101101 朱孟成".getBytes());
        Thread.sleep(1050);
    }
}
