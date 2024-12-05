package frequency;

/**
 * 收集雨水
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class CollectRainWater {


    /**
     * 找出位置i 左右两边 最高的柱子；
     * <p>
     * value[i] = min(left[i],right[i]) - arr[i];
     * i和n-1位置，不可能有水的
     * <p>
     * 3,1,2,5,0,4
     * <p>
     * 每次达到i位置，分别左右遍历得到左右最大值；
     *
     * @param arr
     * @return
     */
    public static int maxRain(int[] arr) {

        int[] left = new int[arr.length + 1];
        int[] right = new int[arr.length + 1];

        for (int i = 0; i < arr.length; i++) {
            left[i + 1] = Math.max(arr[i], left[i]);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            right[i] = arr[i] > right[i + 1] ? arr[i] : right[i + 1];
        }

        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int cur = Math.min(left[i], right[i]) - arr[i];
            ans += cur > 0 ? cur : 0;
        }

        return ans;
    }

    /**
     * AC
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        if(len == 0 || len == 1) return 0;

        int[] left = new int[len + 1];
        int[] right = new int[len + 1];
        for (int i = 0; i < len; i++) {
            left[i + 1] = Math.max(left[i], height[i]);
        }
        for (int i = len - 1; i > 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += Math.max(0, Math.min(left[i], right[i]) - height[i]);
        }
        return ans;
    }

    /**
     * 最优解
     *
     * @param arr
     * @return
     */
    public static int maxRain2(int[] arr) {

        int left = 0;
        int right = arr.length - 2;

        /**
         * leftMax 和 rightMax 谁小，就结算那一侧的水
         */
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];

        int ans = 0;

        while (left <= right) {
            if (leftMax <= rightMax) {
                ans += Math.max(leftMax - arr[left], 0);
                leftMax = Math.max(leftMax, arr[left++]);
            } else {
                ans += Math.max(rightMax - arr[right], 0);
                rightMax = Math.max(leftMax, arr[right--]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        CollectRainWater collectRainWater = new CollectRainWater();


    }
}
