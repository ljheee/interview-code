package some;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大重叠区间的个数
 * 类似 射气球https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * <p>
 * <p>
 * 1、区间范围按start从小到大排序；
 * 2、小跟堆里，放的是之前线段的结尾数字；
 * 3、遍历下一个区间，堆顶<=start的弹出，
 */
public class MaxCover {


    public int max(int[][] arr) {

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        int ans = 1;
        for (int[] range : arr) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek() < range[0]) priorityQueue.poll();
            priorityQueue.add(range[1]);
            ans = Math.max(ans, priorityQueue.size());
        }
        return ans;
    }


    public static void main(String[] args) {
        MaxCover maxCover = new MaxCover();

        System.out.println(maxCover.max(new int[][]{new int[]{1, 6}, new int[]{2, 8}, new int[]{7, 12}, new int[]{10, 16}}));
        System.out.println(maxCover.max(new int[][]{new int[]{1, 6}, new int[]{6, 8}}));
        System.out.println(maxCover.max(new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{4, 5}}));



    }


}