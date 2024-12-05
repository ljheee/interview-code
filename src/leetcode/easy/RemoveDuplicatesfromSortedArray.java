package leetcode.easy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/submissions/
 * <p>
 * 排序数组，去重
 * 返回去重后的 new length，同时需要将原数组 0~newLength 按去重后的元素出现
 */
public class RemoveDuplicatesfromSortedArray {

    public static int removeDuplicates(int[] nums) {

        //res 从0开始往前走，走到最后：走了几步、newLength就是几
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[res]) {// 往前走一步的条件：nums[next]和 nums[res] 不相等时（即出现新元素时)）
                nums[++res] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return ++res;
    }

    /**
     * 287. Find the Duplicate Number
     * https://leetcode.com/problems/find-the-duplicate-number/
     * 找到重复数
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode/
     * <p>
     * 我们对 nums[] 数组建图，每个位置 ii 连一条 i→nums[i] 的边。由于存在的重复的数字 target，因此 target 这个位置一定有起码两条指向它的边，因此整张图一定存在环，且我们要找到的 target 就是这个环的入口，
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[hare];
            hare = nums[hare];// 兔子走两步
        } while (tortoise != hare);

        int ptr1 = nums[0];
        int ptr2 = tortoise;// 环中的相遇点
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        return ptr1;
    }


    public static void main(String[] args) {
//        System.out.println(removeDuplicates(new int[]{1, 2, 2, 3, 3}));
//        System.out.println(findDuplicate(new int[]{1, 2, 2, 2, 3}));
//        System.out.println(findDuplicate(new int[]{1, 2, 4, 5, 6, 5}));


        System.out.println(Integer.MAX_VALUE == (Math.pow(2,31) -1));
        System.out.println(Integer.MIN_VALUE == (-Math.pow(2,31)));
    }


}
