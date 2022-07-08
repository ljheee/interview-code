package leetcode.sliding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 《程序员代码面试指南》
 * <p>
 * 有一个整型数组arr 和一一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 * 返回每次滑动，窗口里数值的最大值。
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class MaxWindowValue {

    // TODO: 2020/1/12
    public static void maxWindow(int[] arr, int k) {

        int idx = 0;
        int[] res = new int[arr.length - k + 1];

        int thisWindowMaxValue = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            thisWindowMaxValue = Math.max(thisWindowMaxValue, arr[i]);
        }
        res[idx++] = thisWindowMaxValue;

        for (int i = k; i < arr.length; i++) {
            int willAddNum = arr[i];
            int removedNum = arr[i - k];
            if (removedNum < thisWindowMaxValue) {

            }

        }

    }


}
