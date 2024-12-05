package leetcode.math;

import java.util.Arrays;

/**
 * 将数组arr切分成两部分，两个子数组的平均值相同====>都大于arr的平均值
 * https://leetcode.com/problems/split-array-with-same-average/
 *
 * 可以将 A 中的每个元素减去它们的平均值，这样 A 的平均值变为 0。此时我们的问题变成：找出若干个元素组成集合 B，这些元素的和为 0。===>如何找出和为0的真子集？
 *
 * https://leetcode-cn.com/problems/split-array-with-same-average/solution/c-zhe-ban-sou-suo-gui-bing-pai-xu-4ms-by-newhar/
 */
public class SplitArrayWithSameAverage_805 {


    public boolean splitArraySameAverage(int[] arr) {

        double average = Arrays.stream(arr).average().getAsDouble();


        return false;
    }

    public static void main(String[] args) {

    }
}
