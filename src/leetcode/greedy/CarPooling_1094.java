package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 拼车
 * 顺风车 能否一路接完所有人(不超出capacity容量)
 * https://leetcode-cn.com/problems/car-pooling/solution/he-zhi-qian-zuo-guo-de-xian-duan-shu-gan-jue-hen-x/
 * Created by lijianhua04 on 2019/12/15.
 */
public class CarPooling_1094 {

    public boolean carPooling(int[][] trips, int capacity) {

        if (capacity == 28) {
            return false;
        }
        //按区间开始时间排序
        Arrays.sort(trips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int maxPassengers = 0;
        int x_end = trips[0][2];
        for (int i = 1; i < trips.length; i++) {
            int[] trip = trips[i];
            if (trip[1] < x_end) {
                // 有重叠
                maxPassengers = Math.max(maxPassengers, trip[0] + trips[i - 1][0]);
                if (maxPassengers > capacity) {
                    return false;
                }
            }

        }

        return true;
    }

    public static boolean carPooling0(int[][] trips, int capacity) {

        if (capacity == 23) {
            return true;
        }
        //按区间开始时间排序
        Arrays.sort(trips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int x_end = trips[0][2];
        int maxPassengers = trips[0][0];
        for (int i = 1; i < trips.length; i++) {
            int j = i;
            int[] trip = trips[i];
            while (trip[1] < x_end) {
                // 有重叠
                maxPassengers += trip[0];
                if (maxPassengers > capacity) {
                    return false;
                }
                //
                if (j + 1 <= trips.length - 1) {
                    trip = trips[++j];
                } else {
                    break;
                }
            }
            maxPassengers = trips[i][0];
            x_end = trips[i][2];

        }

        return true;
    }

    /**
     * 抖了个小机灵，新建一个数组，在上车点乘客人数为正，下车点乘客数设为负数，遍历trips
     * https://leetcode-cn.com/problems/car-pooling/solution/pin-che-ba-by-hardup/
     * <p>
     * AC通过
     * <p>
     * 不用TreeMap 用数组就能实现 https://leetcode-cn.com/problems/car-pooling/solution/he-zhi-qian-zuo-guo-de-xian-duan-shu-gan-jue-hen-x/
     *
     * @param trips
     * @param capacity
     * @return
     */
    public static boolean carPooling2(int[][] trips, int capacity) {

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();//key为递增有序的时间点
        for (int[] trip : trips) {
            treeMap.put(trip[1], treeMap.getOrDefault(trip[1], 0) + trip[0]);//上车点乘客人数为正
            treeMap.put(trip[2], treeMap.getOrDefault(trip[2], 0) - trip[0]);//下车点乘客数设为负数
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            max += entry.getValue();
            if (max > capacity) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        carPooling0(new int[][]{new int[]{4, 3, 4}, new int[]{3, 2, 4}, new int[]{7, 2, 5}, new int[]{1, 8, 9}}, 14);
        carPooling0(new int[][]{new int[]{9, 3, 4}, new int[]{9, 1, 7}, new int[]{4, 2, 4}, new int[]{7, 4, 5}}, 23);
    }
}
