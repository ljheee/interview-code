package leetcode.greedy;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 重构字符串：使相邻字符不相等
 * https://leetcode.com/problems/reorganize-string
 * <p>
 * Created by lijianhua04 on 2019/12/9.
 */
public class ReorganizeString_767 {

    /**
     * 最初的想法：排序后，把出现单次的“插到”相邻的中间；
     * 缺陷：baaba 排序后aaabb 没有“单身元素”
     *
     * @param S
     * @return
     */
    public static String reorganizeString0(String S) {

        char[] chars = S.toCharArray();
        Arrays.sort(chars);

        List<Integer> needInsert = new ArrayList<>();
        List<Integer> singleIndex = new ArrayList<>();
        int need = 0;
        int single = 0;
        int thisAppear = 1;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                thisAppear++;
                needInsert.add(i);
            } else {
                if (thisAppear != 1) {
                    need += (thisAppear - 1);
                    if (i == chars.length - 2) {
                        single++;
                        singleIndex.add(i + 1);
                    }
                } else {
                    single++;
                    singleIndex.add(i);
                    if (i == chars.length - 2) {
                        single++;
                        singleIndex.add(i + 1);
                    }
                }
                thisAppear = 1;
            }

        }

        if (need > single) {
            return "";
        }

        int used = 0;
        int i = 0;
        for (; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                char aChar = chars[i + 1];
                Integer integer = singleIndex.get(used++);
                chars[i + 1] = chars[integer];
                chars[integer] = aChar;
                i = i + 2;
            }
        }

        return new String(chars);
    }


    /**
     * https://leetcode-cn.com/problems/reorganize-string/solution/zui-you-jie-fa-tong-guo-by-18520397110-2/
     * 将相同的字符个数小于字符串长度的一半的字符放在奇数下标位置，否则放在偶数下标位置。
     * 先把相同的字符按顺序放在奇数位置上，当奇数位置满了的时候，在按顺序放到偶数位置上。
     *
     * @param S
     * @return
     */
    public static String reorganizeString(String S) {

        char[] chars = S.toCharArray();

        // map用于记录每个char对应的频率
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        AtomicInteger q = new AtomicInteger();
        AtomicInteger maxFrequency = new AtomicInteger();//用于找出 最大频率

        // 按出现的频率倒序
        map.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEachOrdered(e -> {
                            if (maxFrequency.get() < e.getValue()) {
                                maxFrequency.set(e.getValue());
                            }
                            for (int i = 0; i < e.getValue(); i++) {
                                chars[q.getAndIncrement()] = e.getKey();
                            }
                        }
                );

        if (maxFrequency.get() > (chars.length + 1) / 2) {
            return "";
        }

        int j = 0;
        char[] clone = chars.clone();

        // 先放奇数位
        for (int i = 0; i < clone.length; i += 2) {
            clone[i] = chars[j++];
            if (i + 2 <= clone.length - 1) {
                continue;
            } else {
                break;
            }
        }

        // 再放偶数位
        for (int i = 1; i < clone.length; i += 2) {
            clone[i] = chars[j++];
            if (i + 2 <= clone.length - 1) {
                continue;
            } else {
                break;
            }
        }
        return String.valueOf(clone);
    }

    public static void main(String[] args) {
        String vvvlo = reorganizeString("aab");
        System.out.println(vvvlo);
    }


    /**
     * Runtime: 1 ms, faster than 84.06% of Java online submissions for Reorganize String.
     * Memory Usage: 37.2 MB, less than 67.35% of Java online submissions for Reorganize String.
     *
     * @param s
     * @return
     */
    public String reorganizeString_fast(String s) {
        if (s == null || s.length() == 0) return s;

        int[][] frequency = new int[26][2];
        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a'][0] = s.charAt(i) - 'a';
            frequency[s.charAt(i) - 'a'][1]++;
        }
        Arrays.sort(frequency, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for (int i = 0; i < frequency.length; i++) {
            System.out.println(Arrays.toString(frequency[i]));
        }

        if (frequency[0][1] > (s.length() + 1) / 2) return "";
        if (s.length() % 2 == 1 && frequency[0][1] > s.length() / 2 + 1) return "";

        char[] chars = s.toCharArray();
        int idx = 0;
        for (int i = 0; i < frequency.length; i++) {
            int[] ints = frequency[i];
            for (int j = 0; j < ints[1]; j++, idx += 2) {
                if (idx >= chars.length) idx = 1;
                chars[idx] = (char) (ints[0] + 'a');
            }
        }
        return String.valueOf(chars);

    }
}