package sort;

import java.util.Arrays;

/**
 * Created by lijianhua04 on 2020/3/2.
 */
public class InsertionSort {


    /**
     * 普通插入排序
     *
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums) {

        /**
         * 从第1项开始，第0项我们任务他自身有序
         *
         */
        for (int i = 1; i < nums.length; i++) {

            //从后往前找插入位置
            int num = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (num < nums[j]) {
                    int temp = nums[j];
                    nums[j] = num;
                    nums[j + 1] = temp;

                }
            }
        }
        System.out.println(Arrays.toString(nums));

        return nums;
    }

    public static void main(String[] args) {
        sort(new int[]{2, 3, 1, 5, 4, 9, 0, 7});
    }
}
