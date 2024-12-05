package some;

import java.util.HashSet;

/**
 * 如果两个字符串，所含字符种类完全一样，就算作一类
 * 只由小写字母(a~z) 组成的一批字符串，
 * 都放在字符类型的数组String[] arr中，返回arr中一 共有多少类?
 */
public class StringGroup {


    public static int group0(String[] arr) {

        HashSet<String> set = new HashSet<>();
        boolean[] hasBit = new boolean[26];
        for (String s : arr) {
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                hasBit[chars[i] - 'a'] = true;
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < hasBit.length; i++) {
                if (hasBit[i]) {
                    stringBuffer.append(i + 'a');
                }
            }
            set.add(stringBuffer.toString());
        }
        return set.size();
    }


    public static int group(String[] arr) {

        HashSet<Integer> set = new HashSet<>();
        // 用一个int的26位，标识a~z是否出现过
        int val;
        for (String s : arr) {
            val = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                val |= (1 << (chars[i] - 'a'));
            }
            set.add(val);
        }
        return set.size();
    }


    public static void main(String[] args) {
        System.out.println(group(new String[]{"abac", "abcc"}));
        System.out.println(group(new String[]{"abac", "abcc", "aaaa", "a"}));
        System.out.println(group(new String[]{"abac", "abcc", "aaaakzz", "akz", "kkzzaa"}));
    }

}
