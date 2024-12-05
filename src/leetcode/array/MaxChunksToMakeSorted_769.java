package leetcode.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by lijianhua04 on 2020/3/7.
 */
public class MaxChunksToMakeSorted_769 {


    /**
     * 利用下标位置
     * i == arr[i]
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted2(int[] arr) {
        int err = -1;
        int ans = 0;
        int continueCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (err == i) {
                ans++;
                err = -1;
                continue;
            }

            if (err == -1 && i == arr[i]) {
                continueCount++;
                continue;
            }
            err = err == -1 ? arr[i] : err;
        }
        return ans + continueCount;
    }

    /**
     * arr:0~n-1
     * <p>
     * 4 3 2 1 0
     * 递减的是1
     * <p>
     * <p>
     * 0 1 2 3 4
     * 递增的 n段
     * <p>
     * <p>
     * 1,0,2,3,4
     * 只有先递减、再递增的才能分块
     * <p>
     * 1230
     * 先递增、再递减，不能分块
     * <p>
     * 按递增递减算，先递增、再递减的特殊case 021，不能处理
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted0(int[] arr) {

        if (arr.length == 1) return 1;
        boolean firstDecrese = false;
        int idx = -1;
        boolean increasing = false;
        boolean decreasing = false;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] > 0) {
                increasing = true;
                if (firstDecrese && idx == -1) {
                    idx = i;
                }
            } else {
                if (i == 1) firstDecrese = true;
                decreasing = true;
            }
        }
        if (increasing && !decreasing) {
            return arr.length;
        }
        if (!increasing && decreasing) {
            return 1;
        }
        if (increasing && decreasing && firstDecrese) {
            return 1 + arr.length - idx;
        }
        return 1;
    }


    /**
     * https://leetcode-cn.com/problems/max-chunks-to-make-sorted/solution/cyu-yan-qing-song-guo-by-10-1/
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int ans = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                max++;
            }
        }
        return ans;
    }


    /**
     * 对序列A来说，如果知道其排序后的序列B。然后将序列A与序列B对比，容易分析出各个块。
     * 那么，块，满足两个特点：
     * 1，块的位置和长度，在序列A和序列B中，相同。
     * 2，块中的数字，在序列A和序列B中，相同，但顺序可能不同。
     * https://leetcode-cn.com/problems/max-chunks-to-make-sorted-ii/solution/hua-dong-chuang-kou-fa-by-jason-2-6/
     * <p>
     * 用滑动窗口，确定每个可以切分的块；
     * 可切分的块，一定和排序后的长度相等&&元素相同；
     * <p>
     * 用立方和，确定 窗口内 多个元素相等。
     *
     * @param arr
     * @return
     */
    public int maxChunksToSortedII(int[] arr) {

        int[] clone = arr.clone();
        Arrays.sort(clone);

        int ans = 0;
        double x = 0;
        double y = 0;
        for (int i = 0; i < arr.length; i++) {
            x += arr[i] * arr[i] * arr[i];
            y += clone[i] * clone[i] * clone[i];

            if (x == y) {
                x = y = 0;
                ans++;
            }
        }
        return ans;
    }



    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {

        return Arrays.stream(restaurants).filter(e -> {
            if (veganFriendly == 1) {
                return e[2] == veganFriendly;
            } else {
                return true;
            }
        }).filter(e -> e[3] <= maxPrice && e[4] <= maxDistance).sorted(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o2[0] - o1[0];
                }
                return o2[1] - o1[1];
            }
        }).map(e -> e[0]).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        System.out.println(Double.MAX_VALUE);
        System.out.println(Math.pow(10, 8));
        new MaxChunksToMakeSorted_769().maxChunksToSorted2(new int[]{4, 3, 2, 1, 0});
        new MaxChunksToMakeSorted_769().maxChunksToSorted2(new int[]{1, 0, 2, 3, 4});
    }


}
