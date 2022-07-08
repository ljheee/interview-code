package leetcode.greedy;

import java.util.*;

/**
 * 合并有重叠的区间(重叠区间取并集)
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * https://leetcode.com/problems/merge-intervals/
 * <p>
 * 如何确定“当前区间是不是与prev有重叠”：当前区间的start<=prev.end
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {

        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{intervals[0][0], intervals[0][1]});

        int x_end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
//            if (interval[0] <= x_end) {
//                Integer[] integers = list.get(list.size() - 1);
//                integers[1] = Math.max(interval[1], integers[1]);
//            } else {
//
//                Integer[] integers = list.get(list.size() - 1);
//
//                if (intervals[i][1] > integers[1]) {
//                    list.add(new Integer[]{intervals[i][0], intervals[i][1]});
//                }
//            }
//            x_end = interval[1];
            Integer[] last = list.get(list.size() - 1);
            if (interval[0] <= last[1]) {
                last[1] = Math.max(interval[1], last[1]);
            } else {
                list.add(new Integer[]{intervals[i][0], intervals[i][1]});
            }
        }

        // 赋值结果
        int[][] res = new int[list.size()][2];
        int i = 0;
        for (Integer[] integers : list) {
            res[i][0] = integers[0];
            res[i][1] = integers[1];
            i++;
        }

        return res;
    }

    /**
     * https://leetcode-cn.com/problems/merge-intervals/solution/he-bing-qu-jian-by-leetcode/
     *
     * @param intervals
     * @return
     */
    public int[][] merge0(int[][] intervals) {

        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        LinkedList<Integer[]> merged = new LinkedList<Integer[]>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(new Integer[]{interval[0], interval[1]});
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        // 赋值结果
        int[][] res = new int[merged.size()][2];
        int i = 0;
        for (Integer[] integers : merged) {
            res[i][0] = integers[0];
            res[i][1] = integers[1];
            i++;
        }
        return res;
    }


    /**
     * 石头撞击，剩余最后一块石头的数量
     * https://leetcode.com/problems/last-stone-weight/submissions/
     *
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i : stones) {
            priorityQueue.add(i);
        }
        while (priorityQueue.size() > 1) {
            Integer x = priorityQueue.poll();
            Integer y = priorityQueue.poll();
            if (x != y) {
                priorityQueue.add(Math.abs(x - y));
            }
        }
        return priorityQueue.size() == 0 ? 0 : priorityQueue.poll();
    }




    public static void main(String[] args) {
        new MergeIntervals().merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
    }
}