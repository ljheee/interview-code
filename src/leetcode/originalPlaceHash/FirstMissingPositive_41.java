package leetcode.originalPlaceHash;

/**
 * 找出缺失的 最小正数
 * https://leetcode.com/problems/first-missing-positive/
 * <p>
 * 思路：忽略负数 和 >nums.length的数
 * 最小的正数是1 ，确保1在nums中；
 * 找出缺失的 最小正数：就是依次确认 1、2、3...是否在nums中
 * “给定一个长度为N的数组，我们从1开始找，如果1不在给定的数中，那么答案就是1，如果在，再考虑2；如果2不在给定的数中，那么答案就是2，如果在，再考虑3...”
 * <p>
 * 抽屉原理 https://leetcode-cn.com/problems/first-missing-positive/solution/tong-pai-xu-python-dai-ma-by-liweiwei1419/
 */
public class FirstMissingPositive_41 {


    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            // 这里必须用while，[3,4,-1,1]为例，4换到1，1换到第二个位置，1不应该在第二个位置，应该继续换
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }

        // [1, -1, 3, 4]
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 都正确则返回数组长度 + 1
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


    public int firstMissingPositive_my(int[] nums) {

        if (nums.length == 0) return 1;
        if (nums.length == 1 && nums[0] != 1) return 1;

        for (int i = 0; i < nums.length && nums[i] > 0 && nums[i] < nums.length; i++) {
            while (nums[i] != i && nums[i] > 0 && nums[i] < nums.length) {
                swap(nums, i, nums[i]);
            }
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }


        return nums.length + 1;
    }

    public int firstMissingPositive_zuo(int[] nums) {

        int L = -1;
        int R = nums.length;

        while (L < R) {
            if (nums[L + 1] == L + 2) {
                L++;
            } else if (nums[L + 1] <= L || nums[L + 1] > R || nums[nums[L + 1] - 1] == nums[L + 1]) {
                swap(nums, L + 1, --R);
            } else {
                swap(nums, L + 1, nums[L + 1] - 1);
            }
        }
        return L + 1 + 1;
    }


    /**
     * 让每一个数num交换到num-1位置；
     * 如果num-1位置已经越界，则忽略；
     * 最后检查每个位置i上的数==i+1，如果不相等则返回i+1
     * <p>
     * AC
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for First Missing Positive.
     * Memory Usage: 36.5 MB, less than 85.44% of Java online submissions for First Missing Positive.
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive_my2(int[] nums) {

        for (int i = 0; i < nums.length; ) {
            // 忽略越界的
            if (nums[i] - 1 < 0 || nums[i] - 1 >= nums.length) {
                i++;
                continue;
            }

            // 不相等就交换
            if (nums[i] - 1 != i && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) return i + 1;
        }

        return nums.length + 1;
    }

    public int[] findErrorNums(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - 1 != i) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) return new int[]{nums[i], i};
        }
        return null;
    }


    public static void main(String[] args) {
        new FirstMissingPositive_41().firstMissingPositive_my(new int[]{3, 4, -1, 1});
    }
}
