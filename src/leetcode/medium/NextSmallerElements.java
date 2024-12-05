package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 */
public class NextSmallerElements {


    /**
     * 单调（递减）栈
     * 没有重复数字
     *
     * @param nums
     * @return
     */
    public int[] nextSmallerElements0(int[] nums) {
        int[] result = new int[nums.length];

        // 之所以可以使用map，是因为题目说明元素不重复
        HashMap<Integer, Integer> map = new HashMap<>();

        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() > num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for (int i = 0; i < nums.length; i++) {
            result[i] = map.getOrDefault(nums[i], -1);
        }
        return result;
    }

    /**
     * 单调（递减）栈
     * 有重复数字
     *
     * @param nums
     * @return
     */
    public int[] nextSmallerElements(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int ii = i - 1;
            while (!stack.isEmpty() && stack.peek() > num) {
                result[ii--] = num;
                stack.pop();
            }
            stack.push(num);
        }
        return result;
    }

    public int[] nextSmallerIndex(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && nums[stack.peek()] > num) {
                result[stack.pop()] = i;
            }
            stack.push(i);
        }
        return result;
    }


    public static void main(String[] args) {
        NextSmallerElements greaterElementsI = new NextSmallerElements();
//        int[] arr = greaterElementsI.nextSmallerElements(new int[]{1, 3, 4, 2, 3, 1});// -1,2,2,1,1,-1
//        int[] arr2 = greaterElementsI.nextSmallerElements(new int[]{2, 1, 5, 6, 2, 3});// 1,-1,2,2,-1,-1
//        int[] arr3 = greaterElementsI.nextSmallerElements(new int[]{3, 2, 6, 5, 1, 2});// 2,1,5,1,-1,-1


        int[] arr2 = greaterElementsI.nextSmallerIndex(new int[]{2, 1, 5, 6, 2, 3});// 1,-1,4,4,-1,-1
        int[] arr3 = greaterElementsI.nextSmallerIndex(new int[]{3, 2, 6, 5, 1, 2});// 1,4,3,4,-1,-1
        System.out.println(Arrays.toString(arr2));

    }

}