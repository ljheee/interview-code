package net;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 端口重用
 * setReuseAddress()一定在bind之前；
 * window下 reuseAddress 为false（不支持）；
 * Unix reuseAddress 默认为true。
 * <p>
 * 本程序模拟server启动后，终端使用nc 127.00.1 8088 建立连接后，不发送数据会一直“阻塞读”；
 * 关闭本程序后，立即再启动，会出现BindException: Address already in use (Bind failed)；
 * 因为setReuseAddress(false);
 *
 * 如果setReuseAddress(true)，则不需要等待2倍的发送时间（2*MSL）。
 * 就是通知内核，TCP连接位于 TIME_WAIT 状态时可以重用端口。
 *
 */
public class SocketReuseAddress {
    public static void main(String[] args) {

        byte[] bs = new byte[1024];
        Socket accept = null;
        try {
            ServerSocket serverSocket = new ServerSocket();
            System.out.println(serverSocket.getReuseAddress());// mac下 true

            serverSocket.setReuseAddress(false);
            serverSocket.bind(new InetSocketAddress(8088));

            accept = serverSocket.accept();
            accept.getInputStream().read(bs);
            System.out.println(String.valueOf(bs));

            // 还在处理，没有调用accept.close()
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
