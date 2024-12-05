package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 *
 */
public class IntersectionTwoArraysII_350 {


    public int[] intersect(int[] nums1, int[] nums2) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {

            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    nums2[j] = Integer.MIN_VALUE;
                    list.add(nums1[i]);
                }
            }
        }
        return list.stream().distinct().mapToInt(a -> a.intValue()).toArray();
    }

    public int[] intersect1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int k = 0;
        for (int i = 0; i < nums2.length; i++) {
            int v = map.getOrDefault(nums2[i], 0);
            if (v > 0) {
                nums1[k++] = nums2[i];
                map.put(nums2[i],v-1);
            }

        }

        return Arrays.copyOfRange(nums1,0,k);
    }

}
