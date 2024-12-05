package leetcode.monofonicStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * nums2 数组是 nums1的全集
 * 从 nums2 找出 nums1每个元素 在nums2 出现位置后面的greater
 * <p>
 * https://www.cnblogs.com/grandyang/p/6399855.html
 * https://leetcode.com/problems/next-greater-element-i/
 */
public class NextGreaterElementsI {


    /**
     * AC
     * map缓存下标
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            result[i] = -1;
            for (int j = map.get(nums1[i]) + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    result[i] = nums2[j];
                    break;
                }
            }
        }
        return result;
    }


    /**
     * AC
     * 单调（递增）栈
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        // 之所以可以使用map，是因为题目说明元素不重复
        HashMap<Integer, Integer> map = new HashMap<>();

        Stack<Integer> stack = new Stack<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }

        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);

        }
        return result;
    }


    /**
     * 交换一次，使数值最大
     * https://leetcode.com/problems/maximum-swap/submissions/
     * 只交换一次
     * <p>
     * 不过的case
     * Imput 98368
     * Output 98638
     * Expected 98863
     * <p>
     * chars[0]看作最大值，后续又比他大的，就交换；
     * chars[0]看作最小值，不断记录遍历到的最小值；有比其大时，与之交换
     *
     * @param num
     * @return
     */
    public int maximumSwap0(int num) {

        char[] chars = String.valueOf(num).toCharArray();

        char max = chars[0];
        char min = chars[0];
        int minIdx = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > max) {
                // swap
                chars[0] = chars[i];
                chars[i] = max;
                break;
            }
            if (chars[i] < min) {
                // swap
                min = chars[i];
                minIdx = i;
                continue;
            } else if (chars[i] > min) {
                chars[minIdx] = chars[i];
                chars[i] = min;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }


    public int maximumSwap(int num) {

        char[] chars = String.valueOf(num).toCharArray();
        char max = chars[0];
        int maxIdx = 0;

        char min = chars[0];
        int minIdx = 0;

        int greaterIdx = -1;
        char greater = ' ';

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= max) {
                max = chars[i];
                maxIdx = i;
            }

            if (chars[i] < min) {
                // swap
                min = chars[i];
                minIdx = i;
                continue;
            } else if (chars[i] > min) {
                if (chars[i] > greater) {
                    greater = chars[i];
                    greaterIdx = i;
                }
            }
        }
        if (maxIdx > 0) {
            char tmp = chars[0];
            chars[0] = max;
            chars[maxIdx] = tmp;
            int newV = Integer.parseInt(String.valueOf(chars));
            if (newV > num) {
                return newV;
            }
        }

        if (greaterIdx > 0) {
            chars[minIdx] = greater;
            chars[greaterIdx] = min;
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    public static void main(String[] args) {
        NextGreaterElementsI greaterElementsI = new NextGreaterElementsI();
        int[] arr = greaterElementsI.nextGreaterElement2(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});

        System.out.println(Arrays.toString(arr));

    }

}