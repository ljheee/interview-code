package leetcode.ordinary;

import java.util.List;
import java.util.PriorityQueue;

/**
 * 找出多个队列 最小的重叠区间
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists
 * <p>
 * 现实意义
 * https://www.cnblogs.com/kexinxin/p/10381442.html
 */
public class SmallestRangeCoveringElementsfromKLists_632 {

    public int[] smallestRange(List<List<Integer>> nums) {

        int[] pointerIndex = new int[nums.size()];

        int curMin = 0;
        int curMax = Integer.MAX_VALUE;
        int max = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>((i, j) -> nums.get(i).get(pointerIndex[i]) - nums.get(j).get(pointerIndex[j]));
        for (int i = 0; i < pointerIndex.length; i++) {
            queue.add(i);
            max = Math.max(max, nums.get(i).get(0));
        }
        boolean flag = true;

        for (int i = 0; i < nums.size() && flag; i++) {
            for (int j = 0; j < nums.get(i).size() && flag; j++) {
                Integer minValueLevel = queue.poll();

                // 找到了更小的区间
                if (max - nums.get(minValueLevel).get(pointerIndex[minValueLevel]) < curMax - curMin) {
                    curMin = nums.get(minValueLevel).get(pointerIndex[minValueLevel]);
                    curMax = max;
                }

                // 移动当前找到的最小值对应的pointer
                pointerIndex[minValueLevel]++;

                if (nums.get(minValueLevel).size() == pointerIndex[minValueLevel]) {
                    flag = false;
                    break;
                }
                queue.offer(minValueLevel);
                max = Math.max(max, nums.get(minValueLevel).get(pointerIndex[minValueLevel]));
            }
        }
        return new int[]{curMin, curMax};
    }


}
