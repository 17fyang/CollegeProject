package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Time;

/**
 * ClassName: socketClient
 * Description:
 * date: 2020/6/14 0:54
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class socketClient {
    public static void main(String[] args) throws Exception {
        Socket socket=new Socket("127.0.0.1",3425);
        OutputStream out=socket.getOutputStream();
        out.write("瀹㈡埛鏈� 2017101101 鏈卞瓱鎴�".getBytes());
        Thread.sleep(1000);
        InputStream in=socket.getInputStream();
        byte buf[]=new byte[1024];
        int len=in.read(buf);
        System.out.println(new String(buf,0,len));
    }
}
