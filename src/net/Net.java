package net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Java 查看网卡
 */
public class Net {
    public static void main(String[] args) throws UnknownHostException {

        // 获取本机IP
        String localHostName = InetAddress.getLocalHost().getCanonicalHostName();
        System.out.println(localHostName);// 172.30.143.207


        System.out.println(InetAddress.getLocalHost().getHostAddress());// 172.30.143.207
        System.out.println(InetAddress.getByName("LOCALHOST").getHostAddress());//127.0.0.1

        try {
            System.out.println(NetworkInterface.getNetworkInterfaces().nextElement().getName());
            NetworkInterface anInterface = NetworkInterface.getByName("default");
            System.out.println(anInterface.getName());
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
