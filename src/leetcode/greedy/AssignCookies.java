package leetcode.greedy;

import java.util.Arrays;

/**
 * 分发饼干
 * <p>
 * https://leetcode.com/problems/assign-cookies/
 * <p>
 * https://juejin.im/post/5b4480cef265da0f990d4088
 * 贪心算法通常和排序是分不开的，如果题目给出数组没有排序，我们就需要自己进行排序。
 * <p>
 * <p>
 * <p>
 * [1,2,3]
 * [3]
 */
public class AssignCookies {

    /**
     * 最外层for：遍历每块饼干，把饼干分发出去
     * 因为都是排序后的，若饼干s[i]满足了孩子g[j]，那一定有：饼干s[i+1]满足g[j]后续的孩子
     *
     * @param g 每个孩子的胃口值
     * @param s 每块饼干大小，饼干数可能小于孩子数
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);
        int contentCount = 0;// 满足的孩子数
        int gIndex = 0; // 上次被满足孩子的索引
        for (int i = 0; i < s.length; i++) {
            // 内部这个循环，并不是每次从0开启，而是接着上次被满足的孩子后面找
            for (int j = gIndex; j < g.length; j++) {
                if (s[i] >= g[j]) {
                    contentCount++;
                    gIndex = j + 1;
                    break;// 此处要跳出，不能拿同一块饼干s[i] 去满足多个孩子
                }
            }
        }
        return contentCount;
    }
}
