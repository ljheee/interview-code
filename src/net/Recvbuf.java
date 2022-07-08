package net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 演示：服务端recvbuf
 * 先启动服务端，仅接受5字节数据后，阻塞程序；
 *
 * 再启动客户端，不断发送数据，最后服务端接收数据的recvbuf满了，会阻塞客户端发送（大约执行30849次）
 *
 */
public class Recvbuf {

    public static void main(String[] args) {

        startServer();
        //startClient();
    }

    private static void startClient() {
        try {
            int cnt = 0;
            Socket socket = new Socket("127.0.0.1", 8089);
            while (true) {
                System.out.println(++cnt);
                socket.getOutputStream().write("abcd123abcd123abcd".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startServer() {
        byte[] bs = new byte[5];
        Socket accept = null;
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8089));

            accept = serverSocket.accept();
            accept.getInputStream().read(bs);

            System.in.read();// 阻塞程序

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
