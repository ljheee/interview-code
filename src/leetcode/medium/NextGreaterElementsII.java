package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 环形数组 找greater
 * 注意点：没有从 i+1 到len-1 找到，再从0~i去找
 * <p>
 * https://leetcode.com/problems/next-greater-element-ii/
 */
public class NextGreaterElementsII {

    //[1,5,3,6,8]
    public int[] nextGreaterElements(int[] nums) {

        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            result[i] = -1;
            boolean hasFound = false;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    result[i] = nums[j];
                    hasFound = true;
                    break;
                }
            }
            // 没有从 i+1 到len-1 找到，再从0~i去找
            if (hasFound == false) {    // 此处不能使用 result[i] == -1进行判断，因为case中有元素是-1
                for (int j = 0; j < i; j++) {
                    if (nums[j] > nums[i]) {
                        result[i] = nums[j];
                        break;
                    }
                }
            }
        }
        return result;
    }


    /**
     * 单调栈，遍历2遍的数组
     * 不能用map保存结果，前面的会被覆盖
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements2(int[] nums) {

        Stack<Integer> stack = new Stack<>();


        int[] result = new int[nums.length];
        Arrays.fill(result,-1);

        for (int i = 0; i < 2 * nums.length; i++) {
            int num = nums[i % nums.length];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                result[stack.pop()] = num;
            }
            if (i < nums.length) stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] ints = new NextGreaterElementsII().nextGreaterElements2(new int[]{1, 5, 3, 6, 8});

        System.out.println(Arrays.toString(ints));
    }

}
