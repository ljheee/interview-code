package leetcode.array;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 找出数组1~n中未出现的数。数组长度为n
 * 缺少了某些数，意味着数组中某些数重复了。
 * <p>
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 * <p>
 * Created by lijianhua04 on 2020/2/23.
 */
public class FindAllNumbersDisappearedArray_448 {

    /**
     * 空间复杂度O(n)
     * 访问过的位置 变成负数，代表数字出现过。
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> list = new ArrayList<>();
        int[] clone = nums.clone();
        for (int i = 0; i < nums.length; i++) {
            clone[nums[i] - 1] = nums[i];
        }
        for (int i = 0; i < clone.length; i++) {
            if (clone[i] != i + 1) {
                list.add(i + 1);
            }
        }

        return list;
    }

    /**
     * 原地修改法：空间复杂度O(1)
     * 参考https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/solution/zhao-dao-suo-you-shu-zu-zhong-xiao-shi-de-shu-zi-2/
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers1(int[] nums) {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num < 0) {
                num = -num;// 已经访问过，改变过符号
            }
            if (nums[num - 1] > 0) {
                nums[num - 1] *= (-1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }

        return list;
    }


    /**
     * 找出数组中重复的数
     * https://leetcode.com/problems/find-all-duplicates-in-an-array/submissions/
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num < 0) {
                num = -num;
            }
            if (nums[num - 1] > 0) {
                nums[num - 1] *= (-1);
            } else {
                // nums[num - 1] <0说明之前 改变过符号，num是重复数字
                list.add(num);
            }
        }

        return list;
    }

    /**
     * 找出数组最大值和次大值
     * <p>
     * https://leetcode.com/problems/largest-number-at-least-twice-of-others/submissions/
     * 找出数组中 别其他所有数字都大2倍的最大值，不存在则返回-1；
     * 如果最大值 比 次大值 大2倍，一定比其他数大两倍
     *
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {

        //最大值
        int maxNum = -1;
        //次大值
        int secondMax = -1;
        int idx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxNum) {
                secondMax = maxNum;
                maxNum = nums[i];
                idx = i;
            } else if (nums[i] > secondMax) {
                secondMax = nums[i];
            }
        }
        if (2 * secondMax <= maxNum) {
            return idx;
        }

        return -1;
    }

    /**
     * https://leetcode.com/problems/add-binary
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {

        StringBuffer sb = new StringBuffer();
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        int i = aChars.length - 1;
        int j = bChars.length - 1;

        char carry = '0';
        while (i >= 0 || j >= 0) {
            int sum = 0;
            if (i >= 0 && aChars[i] == '1') {
                sum += 1;
            }
            if (j >= 0 && bChars[j] == '1') {
                sum += 1;
            }
            if (carry == '1') {
                sum += 1;
            }

            if (sum == 2) {
                sb.append('0');
                carry = '1';
            } else if (sum == 1) {
                sb.append('1');
                carry = '0';
            } else if (sum == 0) {
                sb.append('0');
                carry = '0';
            } else {
                sb.append('1');
            }
            i--;
            j--;
        }
        if (carry == '1') {
            sb.append('1');
        }

        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        new FindAllNumbersDisappearedArray_448().findDisappearedNumbers1(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        new FindAllNumbersDisappearedArray_448().findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        new FindAllNumbersDisappearedArray_448().dominantIndex(new int[]{0, 0, 0, 1});


        System.out.println(34 % 10);
    }
}
