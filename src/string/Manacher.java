package string;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by lijianhua04 on 2020/5/1.
 */
public class Manacher {


    public static int manacher(String str) {

        String s = process(str);
        int length = s.length();

        int R = -1;
        int C = -1;
        int[] p = new int[length];
        for (int i = 0; i < p.length; i++) {
            // 先获取保底的回文右边界
            p[i] = i < R ? Math.min(R - i, p[2 * C - i]) : 1;

            // 没有越界，就尝试扩一次
            while (i + p[i] < length && i - p[i] > -1) {
                if (s.charAt(i + p[i]) == s.charAt(i - p[i])) {
                    p[i]++;
                } else {
                    break;
                }
            }

            // 更新 回文右边界
            if (i + p[i] > R) {
                R = i + p[i];
                C = i;
            }

        }

        System.out.println(Arrays.toString(s.toCharArray()));
        System.out.println(Arrays.toString(p));

        int max = Arrays.stream(p).max().getAsInt();

        // 最长的回文前缀是
        str.substring(0, max);// 0~max-1
        return max - 1;
    }

    private static String process(String str) {

        char[] chars = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer("#");
        for (char aChar : chars) {
            stringBuffer.append(aChar);
            stringBuffer.append('#');
        }
        return stringBuffer.toString();
    }


    public static void main(String[] args) {
        System.out.println(manacher("abccbat"));
        System.out.println(manacher("tabccbata"));
    }
}
