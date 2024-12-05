package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijianhua04 on 2020/3/18.
 */
public class Subsets_78 {



    /**
     * https://leetcode.com/problems/subsets/
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList();
        list.add(new ArrayList());

        List<Integer> totalSub = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> single = new ArrayList();
            single.add(nums[i]);
            list.add(single);
            totalSub.add(nums[i]);
            for (int j = i + 1; j < nums.length; j++) {

                List<Integer> two = new ArrayList();


            }

        }
        list.add(totalSub);
        return list;
    }

    /**
     * length为0 的子集
     * length为1 的子集
     *
     * ....
     * length为n 的子集
     *
     * 回溯法。
     */
}
