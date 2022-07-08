package leetcode.sliding;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 仅包含K个 distinct元素 子数组的个数
 * Created by lijianhua04 on 2020/4/4.
 */
public class SubarraysK_DifferentIntegers_992 {


    public int subarraysWithKDistinct(int[] A, int K) {

        if (A.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>(K);
        int left = 0;
        int ans = 0;
        int right = 0;
        while (set.size() < K && set.add(A[right++])) {
        }

        if (set.size() == K) {
            ans++;
        }

        for (; right < A.length; right++) {
            if (set.size() <= K && set.add(A[right])) {
                ans++;
            }

            if (set.size() <= K && !set.add(A[right])) {
                continue;
            }
            while (set.add(A[right]) == false) {
                set.remove(A[left]);
                left++;
                ans++;
            }

        }


        return ans;
    }

    public int subarraysWithKDistinct0(int[] A, int K) {

        if (A.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int ans = 0;
        int right = 0;
        while (map.size() < K) {
            map.put(A[right], map.getOrDefault(A[right], 0) + 1);
            right++;
        }

        if (map.size() == K) {
            ans++;
        }

        for (; right < A.length; right++) {
            if (map.size() <= K && map.containsKey(A[right])) {
                map.put(A[right], map.get(A[right]) + 1);
                ans++;
                if (right < A.length - 1)
                    continue;
            }

            while (map.size() >= K && map.get(A[left]) > 1) {
                map.put(A[left], map.get(A[left]) - 1);
                ans++;
                int rr = map.containsKey(A[right]) ? right:right-1;
                if (A[rr] == A[left] && right - left >= 2) {
                    ans++;
                }
                left++;
            }
            while (map.size() == K && !map.containsKey(A[right])) {
                map.remove(A[left++]);
                map.put(A[right], map.getOrDefault(A[right], 0) + 1);
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println(new SubarraysK_DifferentIntegers_992().subarraysWithKDistinct0(new int[]{1, 2, 1, 2, 3}, 2));
//        System.out.println(new SubarraysK_DifferentIntegers_992().subarraysWithKDistinct0(new int[]{1, 2, 1, 3, 4}, 3));
//        System.out.println(new SubarraysK_DifferentIntegers_992().subarraysWithKDistinct0(new int[]{2,1,1,1,2}, 1));
        System.out.println(new SubarraysK_DifferentIntegers_992().subarraysWithKDistinct0(new int[]{2, 1, 2, 1, 2}, 2));
    }

}