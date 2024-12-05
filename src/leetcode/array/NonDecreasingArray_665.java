package leetcode.array;

/**
 * https://leetcode.com/problems/non-decreasing-array/
 *
 * 最多修改一个元素，使数组递增
 * Created by lijianhua04 on 2020/2/27.
 */
public class NonDecreasingArray_665 {

    public boolean checkPossibility0(int[] nums) {

        int c = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                c++;
                if (i > 0 && nums[i + 1] < nums[i - 1]) {
                    return false;
                }
                if (i > 0) {
                    nums[i] = nums[i - 1];
                }
            }
        }
        return c <= 1;
    }


    /**
     * https://leetcode-cn.com/problems/non-decreasing-array/solution/wang-xiao-kan-qi-yu-dao-ni-xu-de-xian-he-shang-yi-/
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {

        int cnt = 0;
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] > nums[i + 1]) {
                int tmp = nums[i];
                if(i >= 1) {
                    nums[i] = nums[i - 1];
                } else {
                    nums[i] = nums[i + 1];
                }
                if(nums[i] > nums[i + 1]) {
                    nums[i] = tmp;
                    nums[i + 1] = nums[i];
                }
                cnt++;
            }
        }
        return cnt <= 1;
    }

}
