package leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 出现频率超出n/2的数
 * 摩尔投票法 https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
 * https://leetcode.com/problems/majority-element/submissions/
 */
public class MajorityElement_169 {


    /**
     * 众数 必须超过半数
     *
     * @param A
     * @return
     */
    public int majorityElement(int[] A) {

        int candidate = A[0];
        int count = 1;
        for (int i = 1; i < A.length; i++) {
            if (count == 0) {
                candidate = A[i];
            }
            if (candidate == A[i]) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }


    /**
     * @param A
     * @return
     */
    public int majorityElement2(int[] A) {

        int candidate = A[0];
        int count = 1;
        for (int i = 1; i < A.length; i++) {

            if (candidate == A[i]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candidate = A[i];
                    count = 1;
                }
            }
        }
        return candidate;
    }


    /**
     * https://leetcode-cn.com/problems/majority-element-ii/solution/liang-fu-dong-hua-yan-shi-mo-er-tou-piao-fa-zui-zh/
     * 摩尔投票，先选出票数最多的两个人，再验证是否满足
     * <p>
     * 在任意多的候选人中，选出票数超过 ⌊1/3⌋ 的候选人。
     * 一个数组，最多有两个元素频率超过⌊1/3⌋
     *
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElementII(int[] nums) {

        int len = nums.length;
        if (len == 0) return Collections.emptyList();

        List<Integer> list = new ArrayList<>();
        int candidate1 = nums[0];
        int count1 = 0;

        int candidate2 = nums[0];
        int count2 = 0;

        for (int num : nums) {
            // 投票
            if (num == candidate1) {
                count1++;
                continue;
            }
            if (num == candidate2) {
                count2++;
                continue;
            }
            // 第一个候选人配对
            if (count1 == 0) {
                candidate1 = num;
                count1++;
                continue;
            }
            // 第二个候选人配对
            if (count2 == 0) {
                candidate2 = num;
                count2++;
                continue;
            }

            count1--;
            count2--;
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) count1++;
            if (num == candidate2) count2++;
        }

        if (count1 > len / 3) list.add(candidate1);
        if (candidate2 != candidate1 && count2 > len / 3) list.add(candidate2);

        return list;
    }


    public static void main(String[] args) {
        int i = new MajorityElement_169().majorityElement(new int[]{1, 2, 3, 2});//3 投票法，必须超过半数
        System.out.println(i);

        i = new MajorityElement_169().majorityElement(new int[]{1, 2, 3, 2, 2});// 2
        System.out.println(i);

        i = new MajorityElement_169().majorityElement(new int[]{1, 4, 3, 3, 4, 4});//3 投票法，必须超过半数
        System.out.println(i);
        i = new MajorityElement_169().majorityElement(new int[]{1, 4, 3, 3, 4, 4, 4});// 4
        System.out.println(i);

        i = new MajorityElement_169().majorityElement(new int[]{1, 7, 2, 7, 3, 7, 4, 7, 5, 7});// 7
        System.out.println(i);

        System.out.println("======");
        i = new MajorityElement_169().majorityElement2(new int[]{3, 2, 3});// 3
        System.out.println(i);

        i = new MajorityElement_169().majorityElement2(new int[]{0, 1, 3, 2, 3});// 3
        System.out.println(i);

        i = new MajorityElement_169().majorityElement2(new int[]{3, 1, 3, 2, 4});// x 4
        System.out.println(i);

        i = new MajorityElement_169().majorityElement2(new int[]{1, 2, 2, 3, 3});// 3
        System.out.println(i);

        i = new MajorityElement_169().majorityElement2(new int[]{1, 2, 2, 3});// 2
        System.out.println(i);

        i = new MajorityElement_169().majorityElement2(new int[]{1, 2, 2, 3, 3});// 3
        System.out.println(i);


        System.out.println(new MajorityElement_169().majorityElementII(new int[]{1, 2}));
    }

}
