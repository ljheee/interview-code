package leetcode.sliding;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断 s2 是否包含 s1 的排列。
 * 在s2上滑动窗口，判断当前窗口内的子串，是否是s1的排列
 * https://leetcode.com/problems/permutation-in-string/
 */
public class PermutationInString_567 {


    public boolean checkInclusion(String s1, String s2) {

        int len = s1.length();

        boolean inclusion = false;
        for (int i = 0; i < s2.length(); i++) {
            int to = len + i - 1;
            if (to > s2.length() - 1) {
                break;
            }
            inclusion = inclusion(s1, s2, i, to);
            if (inclusion) {
                return true;
            }
        }
        return false;
    }

    /**
     * How will you check whether one string is a permutation of another string?
     * use 0~26 represent a~z;
     * a~z都是唯一的int数值，
     * <p>
     * 此题尝试：
     * 1、加和，不行 abc bbb值相等
     * 2、平方和，不行，s1Val = v*v
     * 3、立方和，通过
     *
     * @param s1
     * @param s2
     * @param from
     * @param to
     * @return
     */
    private boolean inclusion(String s1, String s2, int from, int to) {
        int s1Val = 0;
        for (int i = 0; i < s1.length(); i++) {
            int v = s1.charAt(i) - 'a';
            s1Val += v * v * v;
        }
        int s2Val = 0;
        for (int i = from; i <= to; i++) {
            int v = s2.charAt(i) - 'a';
            s2Val += v * v * v;
        }
        return s1Val == s2Val;
    }


    /**
     * 找出 同分异构词
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int len = p.length();

        boolean inclusion = false;
        for (int i = 0; i < s.length(); i++) {
            int to = len + i - 1;
            if (to > s.length() - 1) {
                break;
            }
            inclusion = inclusion(p, s, i, to);
            if (inclusion) {
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        new PermutationInString_567().checkInclusion("abc", "ccccbbbbaaaa");

    }
}
