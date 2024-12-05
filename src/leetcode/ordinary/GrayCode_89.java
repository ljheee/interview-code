package leetcode.ordinary;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷码 https://leetcode.com/problems/gray-code/submissions/
 */
public class GrayCode_89 {


    /**
     * 任意两个相邻的代码只有一位二进制数不同，则称这种编码为格雷码（Gray Code）
     * https://leetcode.com/problems/gray-code/submissions/
     * <p>
     * https://www.jianshu.com/p/cd0e29b01811
     * <p>
     * <p>
     * 格雷码应用
     * https://www.cnblogs.com/zhuruibi/p/8988044.html
     * <p>
     * 0000
     * 1000
     * 1100
     * 1110
     * 1111
     * <p>
     * 0111
     * 0011
     * 0001
     * <p>
     * 该方式的不足
     * 输入3，返回5个，缺失了010 和101，跳跃形式的
     * 000
     * 100
     * 110
     * 111
     * 001
     * 011
     * <p>
     * 格雷码 是反射码，N位的格雷码一定是偶数个，且一定是 两两成对构成反射，如100 取反是011；
     * 基于 取反，生成格雷码。
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode0(int n) {

        List<Integer> list = new ArrayList<>();
        if (n == 0) {
            list.add(0);
            return list;
        }
        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            chars[i] = '0';
        }

        list.add(Integer.valueOf(String.valueOf(chars), 2));

        for (int i = 0; i < n; i++) {
            chars[i] = '1';
            list.add(Integer.valueOf(String.valueOf(chars), 2));
        }
        for (int i = 0; i < n - 1; i++) {
            chars[i] = '0';
            list.add(Integer.valueOf(String.valueOf(chars), 2));
        }
        return list;
    }


    /**
     * 解法来自：https://leetcode-cn.com/problems/gray-code/solution/gray-code-jing-xiang-fan-she-fa-by-jyd/
     * 关键点
     * 推导N+1位greyCode 和n位之间的关系；
     * 在add n位的greyCode时，其实对于n+1位的 首位加0的已在结果list中，
     * 在add n+1位greyCode时，只需将list中逆序首位加1即可。
     * <p>
     * 第i位 变成1，其实就是加上2^[i-1]，这就是加head的作用。
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {

        List<Integer> list = new ArrayList<Integer>() {{
            add(0);
        }};

        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                // 将最高位加1
                list.add(head + list.get(j));
            }
            head <<= 1;// 相当于扩大两倍
        }
        return list;
    }


    /**
     * 格雷码 也是是反射码，N位的格雷码一定是偶数个，且一定是 两两成对构成反射，如100 取反是011；
     * 基于 取反，生成格雷码。
     * https://leetcode-cn.com/problems/gray-code/solution/gen-ju-ge-lei-ma-de-xing-zhi-by-powcai/
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode00(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--) {
                // 相当于：新增一个首位为1，其他位取反的数。
                res.add(res.get(j) ^ (1 << i));
            }
        }
        return res;
    }


    /**
     * 循环格雷码  https://leetcode-cn.com/problems/circular-permutation-in-binary-representation/solution/c-89-ge-lei-ma-189-xuan-zhuan-shu-zu-by-yanghk/
     * 要求结果的第一项 和最后一项也只差一位；
     *
     * @param n
     * @param start
     * @return
     */
    public List<Integer> circularPermutation(int n, int start) {

        // 先生成 格雷码
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--) {
                // 相当于：新增一个首位为1，其他位取反的数。
                res.add(res.get(j) ^ (1 << i));
            }
        }

        // 找出start的下标
        int idx = -1;
        for (int i = 0; i <= res.size() - 1; i++) {
            if (res.get(i) == start) {
                idx = i;
                break;
            }
        }

        reverse(res, 0, idx - 1);
        reverse(res, idx, res.size() - 1);
        reverse(res, 0, res.size() - 1);
        return res;
    }

    private void reverse(List<Integer> list, int from, int to) {
        while (from < to) {
            Integer temp = list.get(from);
            list.set(from, list.get(to));
            list.set(to, temp);
            from++;
            to--;
        }
    }

    public static void main(String[] args) {
        new GrayCode_89().circularPermutation(1, 1);
    }
}
