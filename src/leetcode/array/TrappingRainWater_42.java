package leetcode.array;

/**
 * 收集雨水
 * https://leetcode.com/problems/trapping-rain-water/
 * Created by lijianhua04 on 2020/3/8.
 */
public class TrappingRainWater_42 {

    public int trap0(int[] height) {

        if (height.length <= 1) {
            return 0;
        }
        int ans = 0;
        int startIdx = 0;
        int startHight = height[0];
        int execlusied = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] >= startHight && i - startIdx == 1) {
                startHight = height[i];
                startIdx = i;
                continue;
            }

            if (height[i] >= startHight) {
                // 找到了更高的
                ans += (i - startIdx - 1) * Math.min(startHight, height[i]) - execlusied;
                startHight = height[i];
                startIdx = i;
            } else {
                execlusied += height[i];
            }
        }

        if (execlusied != 0 && startIdx < height.length - 1) {
            execlusied = 0;
            startIdx = startIdx + 1;
            startHight = height[startIdx];

            for (int i = startIdx + 1; i < height.length; i++) {
                if (height[i] >= startHight && i - startIdx == 1) {
                    startHight = height[i];
                    startIdx = i;
                }

                if (height[i] >= startHight) {
                    // 找到了更高的
                    ans += (i - startIdx - 1) * Math.min(startHight, height[i]) - execlusied;
                    startHight = height[i];
                    startIdx = i;
                } else {
                    execlusied += height[i];
                }
            }
        }
        return ans;
    }

    public int trap1(int[] height) {

        if (height.length <= 1) {
            return 0;
        }
        int ans = 0;
        int startIdx = 0;
        int startHight = height[0];
        int execlusied = 0;

        for (int i = startIdx + 1; i < height.length; i++) {
            if (height[i] >= startHight && i - startIdx == 1) {
                startHight = height[i];
                startIdx = i;
                continue;
            }

            if (height[i] >= startHight) {
                // 找到了更高的
                ans += (i - startIdx - 1) * Math.min(startHight, height[i]) - execlusied;
                startHight = height[i];
                startIdx = i;
                execlusied = 0;
            } else {
                execlusied += height[i];
            }

            if (i == height.length - 1 && execlusied != 0 && startIdx < height.length - 1) {
                execlusied = 0;
                startIdx = startIdx + 1;
                startHight = height[startIdx];
                i = startIdx;
                continue;
            }
        }
        return ans;
    }


    public int trap(int[] height) {

        if (height.length <= 1) {
            return 0;
        }
        int ans = 0;
        int startIdx = 0;
        int startHight = height[0];
        int execlusied = 0;

        for (int i = startIdx + 1; i < height.length; i++) {
            if (height[i] >= startHight && i - startIdx == 1) {
                startHight = height[i];
                startIdx = i;
                continue;
            }

            if (height[i] >= startHight) {
                // 找到了更高的
                ans += (i - startIdx - 1) * Math.min(startHight, height[i]) - execlusied;
                startHight = height[i];
                startIdx = i;
                execlusied = 0;
            } else {
                execlusied += height[i];
            }

            if (i == height.length - 1 && execlusied != 0 && startIdx < height.length - 1) {
                execlusied = 0;
                // 降低开头兵的高度
                startHight--;
                i = startIdx;
                continue;
            }
        }
        return ans;
    }


    /**
     * 正向找一遍+逆向找一遍
     * 每次只找 更高的；
     * 不通过case {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height.length <= 1) {
            return 0;
        }
        return findHigher(height, true) + findHigher(reserve(height), false);
    }

    public int[] reserve(int[] arr) {
        int begin = 0;
        int end = arr.length - 1;
        while ((begin < end)) {
            int temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp;

            begin++;
            end--;
        }

        return arr;
    }

    private int findHigher(int[] height, boolean containsEqual) {
        if (height.length <= 1) {
            return 0;
        }
        int ans = 0;
        int startIdx = 0;
        int startHight = height[0];
        int execlusied = 0;

        for (int i = startIdx + 1; i < height.length; i++) {
            if (height[i] >= startHight && i - startIdx == 1) {
                startHight = height[i];
                startIdx = i;
                continue;
            }

            if (height[i] >= startHight) {
                // 找到了更高的
                if (height[i] == startHight && containsEqual) {
                    ans += (i - startIdx - 1) * Math.min(startHight, height[i]) - execlusied;
                } else if (height[i] > startHight) {
                    ans += (i - startIdx - 1) * Math.min(startHight, height[i]) - execlusied;
                }
                startHight = height[i];
                startIdx = i;
                execlusied = 0;
            } else {
                execlusied += height[i];
            }
        }
        return ans;
    }

    /**
     * 两个柱子 之间围成的最大面积
     * 前后两个指针 往中间走
     * https://leetcode.com/problems/container-with-most-water/submissions/
     * 双指针
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height.length <= 2) {
            return Math.min(height[0], height[1]);
        }
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                max = Math.max((right - left) * height[left], max);
                left++;
            } else {
                max = Math.max((right - left) * height[right], max);
                right--;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int m = 1;
        int start = 0;
        int s = 0;

//        label:
//        while (start < 5) {
//            for (int i = start + 1; i <= 5; i++) {
//                System.out.println(i + "-----" + m);
//                s += i;
//                if (i == 5 && s < 30) {
//                    start = 3;
//                    continue label;
//                }
//            }
//        }

//        new TrappingRainWater_42().trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
//        new TrappingRainWater_42().trap(new int[]{4, 2, 3});

    }

}
