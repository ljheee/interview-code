package num;

/**
 * ip 用int存储
 * <p>
 * Integer.parseUnsignedInt方法中第二个参数为基数(radix)，2即二进制。这个方法最多可解析32位的二进制数，即无符号int型
 * Integer.parseInt也类似，但只能解析31位二进制数，即有符号int型
 */
public class Ip2Int {

    public static void main(String[] args) {

        // 二进制，转十进制
        int j = Integer.parseUnsignedInt("11000000101010000000000101100100", 2);


        int i = ip2Int("192.168.1.101");

        String ip = int2Ip(i);
        System.out.println(ip);
    }

    public static String int2Ip(int value) {
        //转成32位的 二进制数
        String binaryString = Integer.toBinaryString(value);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            String substring = binaryString.substring((i - 1) * 8, i * 8);
            stringBuffer.append(Integer.parseUnsignedInt(substring, 2)).append(".");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    /**
     * IP是点分字节
     * 单个 最大255(2^8-1)
     *
     * @param ip
     * @return
     */
    public static int ip2Int(String ip) {

        String[] split = ip.split("\\.");

        StringBuffer binaryString = new StringBuffer();
        for (String s : split) {
            String s1 = Integer.toBinaryString(Integer.parseInt(s));
            while (s1.length() < 8) {
                s1 = "0" + s1;

            }
            // 拼接成 32位的 二进制数
            binaryString.append(s1);
        }
        return Integer.parseUnsignedInt(binaryString.toString(), 2);
    }
}
