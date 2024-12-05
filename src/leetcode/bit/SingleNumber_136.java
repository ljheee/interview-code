package leetcode.bit;

/**
 * Created by lijianhua04 on 2020/2/16.
 */
public class SingleNumber_136 {

    /**
     * 两两成对的数，找出落单的那个
     * https://leetcode.com/problems/single-number/
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }


    /**
     * 每三个成对的数，找出落单的那个
     * https://leetcode.com/problems/single-number-ii/
     * <p>
     * 一种解法
     * https://leetcode-cn.com/problems/single-number-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--31/
     * 32位的整数，每个位上1的个数 一定是3的整数倍，否则这个数出现次数小于3.
     *
     * @param nums
     * @return
     */
    public int singleNumberII(int[] nums) {

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((nums[j] >>> i) & 1) == 1) {
                    count++;
                }
            }

            if (count % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }


    /**
     * 两两成对的数，找出落单的两个数
     * 思想：将nums分组，让落单的两个数分别在两组中，对两组分别执行singleNumber的解法
     * 由于A，B肯定是不相等的，因此在二进制上必定有一位是不同的。根据这一位是0还是1可以将A，B分开到A组和B组。而
     * 来源 https://leetcode-cn.com/problems/single-number-iii/solution/java-yi-huo-100-yao-shi-kan-bu-dong-wo-jiu-qu-chu-/
     * https://leetcode.com/problems/single-number-iii/
     */
    public int[] singleNumberIII(int[] nums) {

        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }

        //标记哪一位为1
        int group = 1;
        while ((xor & 1) != 1) {
            group = group << 1;
            xor = xor >> 1;
        }

        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & group) == 0) { // 对应位为0
                res[0] ^= nums[i];
            } else {
                res[1] ^= nums[i];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        new SingleNumber_136().singleNumberIII(new int[]{1, 2, 1, 3, 2, 5});


        // 返回num最右侧为1的位
        int num = 6;
        int num_of_rightmost1 = num & (-num);// 2
        System.out.println(num_of_rightmost1);
    }

}
