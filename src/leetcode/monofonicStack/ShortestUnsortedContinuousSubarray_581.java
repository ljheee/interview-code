package leetcode.monofonicStack;

import java.util.Stack;

/**
 * 入参数组中，有一段区间顺序被打乱了，找出这部分区间长度；
 * <p>
 * <p>
 * 很简单，如果最右端的一部分已经排好序，这部分的每个数都比它左边的最大值要大，同理，如果最左端的一部分排好序，这每个数都比它右边的最小值小。所以我们从左往右遍历，如果i位置上的数比它左边部分最大值小，则这个数肯定要排序， 就这样找到右端不用排序的部分，同理找到左端不用排序的部分，它们之间就是需要排序的部分
 * 思路来源：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/comments/64965
 */
public class ShortestUnsortedContinuousSubarray_581 {


    /**
     * 从左侧 查找单调递增区间，同时要求 左侧元素要比右侧所有元素都小；
     * 从右侧 查找单调区间，同时要求 右侧元素要比左侧所有元素都大；
     * 中间剩余的，就是乱序区间
     * <p>
     *
     * 不过的case：1,3,5,4,2
     * 第一步从左到右查找时，认为1,3有序，没有继续向后找到更小的2
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {

        int left = 0;
        int right = nums.length;
        Stack<Integer> stack = new Stack<>();

        int leftSize = 0;
        int rightSize = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!stack.isEmpty() && stack.peek() > nums[i]) {
                left = i - 1;
                leftSize = stack.size();
                stack.clear();
                while (left - 1 >= 0 && nums[left] > nums[i]) {
                    left -= 1;
                }
                break;
            }
            stack.push(nums[i]);
        }
        // 全为递增
        if (stack.size() == nums.length) return 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (!stack.isEmpty() && stack.peek() < nums[i]) {
                right = i + 1;
                rightSize = stack.size();
                stack.clear();

                while (right + 1 <= nums.length && nums[right] < nums[i]) {
                    right += 1;
                }
                break;
            }
            stack.push(nums[i]);
        }

        if (leftSize == rightSize && leftSize == 1) return nums.length;
        return right - left - 1;
    }


    public static void main(String[] args) {

        ShortestUnsortedContinuousSubarray_581 continuousSubarray581 = new ShortestUnsortedContinuousSubarray_581();

//        System.out.println(continuousSubarray581.findUnsortedSubarray(new int[]{1,2, 3, 4}));
        System.out.println(continuousSubarray581.findUnsortedSubarray(new int[]{1,3,5,4,2}));
        System.out.println(continuousSubarray581.findUnsortedSubarray(new int[]{2,2,2,3,1}));
        System.out.println(continuousSubarray581.findUnsortedSubarray(new int[]{1, 3, 2, 2, 2}));
        System.out.println(continuousSubarray581.findUnsortedSubarray(new int[]{4, 3, 2}));
        System.out.println(continuousSubarray581.findUnsortedSubarray(new int[]{2, 3, 4, 6, 6, 6, 4, 8, 10, 19, 9, 15}));
        System.out.println(continuousSubarray581.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(continuousSubarray581.findUnsortedSubarray(new int[]{2, 3, 4, 6, 6, 4, 8, 10, 9, 9, 15}));
    }
}
