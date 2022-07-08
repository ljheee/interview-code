package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lijianhua04 on 2019/2/26.
 */
public class ServerProxy {



    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        final InputStream inputStream = socket.getInputStream();
                        byte b[] = new byte[2048*2];
                        inputStream.read(b);
                        System.out.println(new String(b,"UTF-8"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
