package leetcode.array;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualsK_560 {


    public int subarraySum0(int[] nums, int k) {

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == k) ans++;
            //if (sum > k) continue;// 不能continue
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    ans++;
                }
                if (sum > k) {
                    continue;
                }
            }
        }
        return ans;
    }

    /**
     * O(n)
     * https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/dai-you-xiang-xi-jie-shi-shi-jian-fu-za-du-wei-ond/
     * key是dp[i]，value是dp[j]有多少种选择。
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {

        int ans = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sum.length; i++) {
            if (map.containsKey(sum[i] - k)) {
                ans += map.get(sum[i] - k);
            }
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/subarray-sum-equals-k/
     * 用滑动窗口 可以解决吗
     *
     * 数组中存在负数，当前sum>k，不能立即收缩left，可能后续的负数让sum减小
     */
    public int subarraySum_slidingWindow(int[] nums, int k) {

        int left = 0;
        int ans = 0;

        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            if (sum == k) ans++;


        }
        while (sum > k) {
            sum -= nums[left++];
            if (sum == k) ans++;
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/two-sum/submissions/
     * nums = [2, 7, 11, 15], target = 9,
     * 返回和为target的 两个数下标
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && i != map.get(target - nums[i])) {
                int[] ans = new int[2];
                ans[0] = i;
                ans[1] = map.get(target - nums[i]);
                return ans;
            }
        }

        return nums;
    }


    /**
     * https://leetcode-cn.com/problems/count-negative-numbers-in-a-sorted-matrix/solution/tong-ji-you-xu-ju-zhen-zhong-de-fu-shu-by-leetcode/
     *
     * @param grid
     * @return
     */
    public int countNegatives(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        /*
        [4,3,2,-1],
        [3,2,1,-1],
        [1,1,-1,-2],
        [-1,-1,-2,-3]
        从右上角开始遍历，i = 0, j = grid[0].length - 1
        如果当前值大于等于 0，那么前面的值肯定都非负，那么直接跳过，进入下一行, 即 i++
        如果当前值小于 0，那么当前值以及同列下的值都是小于 0 的，那么直接添加，然后进行下一列，即 j--
        */
        int count = 0;
        for (int i = 0, j = grid[0].length - 1; i < grid.length && j >= 0; ) {
            if (grid[i][j] >= 0) {
                i++;
            } else {
                count += grid.length - i;
                j--;
            }
        }
        return count;
    }

    /**
     * https://leetcode.com/problems/fibonacci-number/submissions/
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int prev1 = 0;
        int prev2 = 1;
        int cur = 0;
        for (int i = 2; i <= n; i++) {
            cur = prev1 + prev2;
            prev1 = prev2;
            prev2 = cur;
        }
        return cur;
    }


    public static void main(String[] args) {
        int a = new SubarraySumEqualsK_560().subarraySum(new int[]{1, 1, 1}, 2);
        a = new SubarraySumEqualsK_560().subarraySum(new int[]{28, 54, 7, -70, 22, 65, -6}, 100);
        a=new SubarraySumEqualsK_560().subarraySum(new int[]{-815, 388, 230, 365, -106, 284, -222, -884, 240, 422, 599, -315, -281, -14, 29, -812, -55, 106, -858, -581, -725, 382, 730, 780, 785, 576, 440, -378, 529, -467, 816, -754, 983, -921, -616, -668, -6, -140, -795, -448, -96, -146, -435, 141, 951, 933, 566, 821, -996, -380, 542, 810, -384, 912, -442}, 62);
        System.out.println(a);


//        System.out.println(new SubarraySumEqualsK_560().subarraySum_slidingWindow(new int[]{1, 1, 1}, 2));
//        System.out.println(new SubarraySumEqualsK_560().subarraySum_slidingWindow(new int[]{28, 54, 7, -70, 22, 65, -6}, 100));
        System.out.println(new SubarraySumEqualsK_560().subarraySum_slidingWindow(new int[]{-815, 388, 230, 365, -106, 284, -222, -884, 240, 422, 599, -315, -281, -14, 29, -812, -55, 106, -858, -581, -725, 382, 730, 780, 785, 576, 440, -378, 529, -467, 816, -754, 983, -921, -616, -668, -6, -140, -795, -448, -96, -146, -435, 141, 951, 933, 566, 821, -996, -380, 542, 810, -384, 912, -442}, 62));
//        new SubarraySumEqualsK_560().pivotIndex1(new int[]{1, 7, 3, 6, 5, 6});
    }
}