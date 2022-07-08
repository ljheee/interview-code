package leetcode.array;

import java.util.*;

/**
 * 三个数的和为0
 * <p>
 * Created by lijianhua04 on 2020/3/24.
 */
public class ThreeSum_15 {


    /**
     * 要去除重复元素
     * Input[-1,0,1,2,-1,-4]
     * Output[[-1,0,1],[-1,1,0],[-1,2,-1],[-1,-1,2],[0,1,-1]]
     * Expected[[-1,-1,2],[-1,0,1]]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> listList = new ArrayList<>();
        if (nums.length < 3) {
            return listList;
        }
        if (nums.length == 3 && nums[0] == 0 && nums[1] == 0 && nums[2] == 0) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(0);
            list.add(0);
            listList.add(list);
            return listList;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 用平方和判断重复
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int third = 0 - nums[i] - nums[j];
                if ((third != nums[i] || third != nums[j]) && map.containsKey(third) && set.add(nums[i] * nums[i] + nums[j] * nums[j] + third * third)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(third);
                    listList.add(list);
                    if (map.get(third) - 1 == 0) {
                        map.remove(third);
                    } else {
                        map.put(third, map.get(third) - 1);
                    }
                }
            }
        }
        return listList;
    }

    public static void main(String[] args) {
//        new ThreeSum_15().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
//        new ThreeSum_15().threeSumClosest(new int[]{ 1, 2, -1, -4},1);
//        new ThreeSum_15().threeSumClosest(new int[]{ 0,1,2},3);
        new ThreeSum_15().threeSumClosest(new int[]{-3, -2, -5, 3, -4}, -1);
    }


    /**
     * 排序+双指针
     * https://leetcode-cn.com/problems/3sum/solution/pai-xu-shuang-zhi-zhen-zhu-xing-jie-shi-python3-by/
     * 难点是 判断重复
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> listList = new ArrayList<>();
        if (nums.length < 3) {
            return listList;
        }

        Arrays.sort(nums);
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                int s = nums[i] + nums[left] + nums[right];
                if (s == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    listList.add(list);
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (s > 0) {
                    right--;
                } else if (s < 0) {
                    left++;
                }
            }
        }
        return listList;
    }

    /**
     * https://leetcode-cn.com/problems/4sum/solution/shuang-zhi-zhen-jie-fa-can-zhao-san-shu-zhi-he-ge-/
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> listList = new ArrayList<>();
        if (nums.length < 4) {
            return listList;
        }

        Arrays.sort(nums);
        int left;
        int right;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                left = j + 1;
                right = nums.length - 1;
                while (left < right) {
                    int s = nums[i] + nums[j] + nums[left] + nums[right];
                    if (s == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        listList.add(list);
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right] == nums[right - 1])
                            right--;
                        left++;
                        right--;
                    }
                    if (s > target) {
                        right--;
                    } else if (s < target) {
                        left++;
                    }
                }

            }
        }
        return listList;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sum = -C[i] - D[j];
                if (map.containsKey(sum)) ans += map.get(sum);
            }
        }
        return ans;
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }

        // 减去一个负数，会越界
        int ans = Integer.MAX_VALUE;
        Arrays.sort(nums);
        int left = 0;
        int right = 0;
        int curSum;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            left = i + 1;
            right = nums.length - 1;
            curSum = Integer.MAX_VALUE;
            while (left < right) {
                int s = nums[i] + nums[left] + nums[right];
                curSum = Math.abs(s - target) < Math.abs(curSum - target) ? s : curSum;
                if (s == target) {
                    return target;
                } else if (s > target) {
                    right--;
                } else if (s < target) {
                    left++;
                }
            }

            ans = Math.abs(curSum - target) < Math.abs(ans - target) ? curSum : ans;
        }
        return ans;
    }
}
