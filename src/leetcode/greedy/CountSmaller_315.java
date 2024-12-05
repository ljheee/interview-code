package leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * 返回每个元素 右边比他小的个数
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * <p>
 * Created by lijianhua04 on 2019/12/13.
 */
public class CountSmaller_315 {

    /**
     * 暴力 双重for
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                if (nums[i] > nums[i1]) {
                    count++;
                }
            }
            res.add(count);
        }
        return res;
    }
}
