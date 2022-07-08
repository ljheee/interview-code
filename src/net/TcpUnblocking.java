package net;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP断开时，解阻塞
 * 本程序 模拟服务端启动后；
 * 在终端 nc 127.00.1 8088 建立连接后，不发送数据会一直“阻塞读”；
 * 终端直接断开连接，TCP会解阻塞；
 *
 * 也就是收到了主动关闭方 四次挥手的第一个包，立即进行回复；
 * 因为对端不在给自己发送数据，因此会自动解阻塞。
 *
 * 为什么TCP断开，必须是4次：
 * 主动关闭方 发送包说“我不给你发送数据了”，被动关闭方收到、回复并解阻塞(不再读他发的数据)；
 * 但之前读到的数据，可能还需处理，不能立即close()，这段时间叫 close_wait;
 *
 */
public class TcpUnblocking {

    public static void main(String[] args) {

        byte[] bs = new byte[1024];
        Socket accept = null;
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8088));

            accept = serverSocket.accept();
            System.out.println("wait to read data...");
            accept.getInputStream().read(bs);// 阻塞读：当TCP断开时，会解阻塞
            System.out.println("TcpUnblocking");

            // 还在处理，没有调用accept.close()
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
