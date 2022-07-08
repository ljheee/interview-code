package leetcode.array;

import java.util.*;

/**
 * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
 * <p>
 * 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
 * 返回重复了 N 次的那个元素。
 * <p>
 * 频率超出1的数，就是答案
 */
public class Repeate2NArray_961 {


    public int repeatedNTimes(int[] A) {
        int n = A.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == n / 2) {
                return entry.getKey();
            }
        }

        return 0;
    }

    /**
     * 连续4个数，必有重复
     *
     * @param A
     * @return
     */
    public int repeatedNTimes1(int[] A) {

        for (int i = 0; i < A.length - 3; i++) {
            if (A[i] == A[i + 1] || A[i] == A[i + 2] || A[i] == A[i + 3]) {
                return A[i];
            }
            if (A[i + 1] == A[i + 2] || A[i + 2] == A[i + 3]) {
                return A[i + 2];
            }
            if (A[i + 1] == A[i + 3]) {
                return A[i + 1];
            }
        }
        return 0;
    }


    public int hIndex(int[] citations) {

        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;
            if (citations[i] >= h) {
                return h;
            }
        }
        return 0;
    }





}
