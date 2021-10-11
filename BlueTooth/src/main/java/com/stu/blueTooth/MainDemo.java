package com.stu.blueTooth;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/10/4 1:02
 * @Description:
 */
public class MainDemo {
    public static void main(String[] args) {
        final String serverName = "Bluetooth Server Test";
        final String serverUUID = "1000110100001000800000805F9B34FB";  //根据需要自定义

        BluetoothServer server = new BluetoothServer(serverUUID, serverName);
        server.setServerListener(new BluetoothServer.OnServerListener() {
            @Override
            public void onConnected(InputStream inputStream, OutputStream outputStream) {
                System.out.printf("Connected");
                //添加通信代码
                System.out.println(server.serverName);
                System.out.println(server.serverUUID);
            }

            @Override
            public void onDisconnected() {

            }

            @Override
            public void onClose() {

            }

        });

        server.start();
    }
}
