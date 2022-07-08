package leetcode.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 相对排序
 * 用arr2的相对顺序，排序arr1
 * https://leetcode.com/problems/relative-sort-array/
 */
public class RelativeSortArray_1122 {


    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        return Arrays.stream(arr1).boxed().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (map.get(o1) == null && map.get(o2) != null) {
                    return 1;
                }
                if (map.get(o1) != null && map.get(o2) == null) {
                    return -1;
                }
                if (map.get(o1) != null && map.get(o2) != null) {
                    return map.get(o1) - map.get(o2);
                }

                return o1 - o2;
            }
        }).mapToInt(e -> e.intValue()).toArray();
    }


    public int[] relativeSortArray0(int[] arr1, int[] arr2) {
        int count[] = new int[1001];
        for (int e : arr1) {
            count[e]++;
        }

        int idx = 0;
        for (int a : arr2) {
            while (count[a] > 0) {
                arr1[idx++] = a;
                count[a]--;
            }
        }

        for (int i = 0; i < 1001; i++) {
            while (count[i] > 0) {
                arr1[idx++] = i;
                count[i]--;
            }
        }
        return arr1;
    }

}
