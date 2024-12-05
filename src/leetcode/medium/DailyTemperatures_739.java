package leetcode.medium;

import java.util.Stack;

/**
 * 下一个比i日气温更高的 日期，距离i是多少天
 * https://leetcode.com/problems/daily-temperatures/
 */
public class DailyTemperatures_739 {


    public int[] dailyTemperatures(int[] arr) {

        int[] res = new int[arr.length];
        Stack<Integer> stack = new Stack<>();


        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                res[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return res;
    }


}
