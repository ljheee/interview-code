package leetcode.originalPlaceHash;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/set-mismatch/
 */
public class SetMismatch_645 {


    /**
     * Input: nums = [1,2,2,4]
     * 形成环，死循环
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums_err(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - 1 != i) {
                //swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) return new int[]{nums[i], i};
        }
        return null;
    }


    public int[] findErrorNums(int[] nums) {

        int fast = nums[0];
        int slow = nums[0];

        do {
            if (slow == nums.length) slow = nums.length - 1;
            slow = nums[slow];
            if (fast == nums.length) fast = nums.length - 1;
            fast = nums[fast];
            if (fast == nums.length) fast = nums.length - 1;
            fast = nums[fast];
        } while (fast != slow);

        System.out.println(fast);
        slow = nums[0];
        while (fast != slow) {
            if (slow == nums.length) slow = nums.length - 1;
            slow = nums[slow];
            if (fast == nums.length) fast = nums.length - 1;
            fast = nums[fast];
        }
        System.out.println(fast);
        int n = nums.length;
        return new int[]{fast, n * (n + 1) / 2 + fast - Arrays.stream(nums).sum()};

    }

    public int[] findErrorNums2(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        for (int i = 0; i < nums.length; i++) {
            nums[i] -= 1;
        }

        int fast = nums[0];
        int slow = nums[0];

        do {
            slow = nums[slow];
            fast = nums[fast];
            fast = nums[fast];
        } while (fast != slow);

        System.out.println(fast);
        slow = nums[0];
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }
        System.out.println(fast);
        fast += 1;
        int n = nums.length;
        return new int[]{fast, n * (n + 1) / 2 + fast - sum};

    }

    /**
     * AC
     * @param nums
     * @return
     */
    public int[] findErrorNums_originalPlaceHash(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int dump = 1;
        for (int i = 0; i < nums.length; i++) {

            while (i != nums[i] - 1 ){
                if (nums[nums[i] - 1] == nums[i]) {
                    System.out.println(Arrays.toString(nums));
                    dump = nums[i];
                    break;
                }
                swap(nums, i, nums[i] - 1);
            }
        }
        System.out.println(dump);

        int n = nums.length;
        return new int[]{dump, n * (n + 1) / 2 + dump - sum};

    }
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8};
        int ans = 0;
        for (int i = 0; i < ints.length; i++) {
            ans ^= ints[i];
        }
        //System.out.println(ans);


        SetMismatch_645 setMismatch645 = new SetMismatch_645();
//        System.out.println(Arrays.toString(setMismatch645.findErrorNums2(new int[]{1, 2, 3, 3, 5, 6})));
//        System.out.println(Arrays.toString(setMismatch645.findErrorNums(new int[]{1, 2, 2, 4, 5, 6})));
//        System.out.println(Arrays.toString(setMismatch645.findErrorNums(new int[]{1, 2, 6, 4, 5, 6})));
        System.out.println(Arrays.toString(setMismatch645.findErrorNums_originalPlaceHash(new int[]{8, 7, 3, 5, 3, 6, 1, 4})));
    }
}
