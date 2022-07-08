package leetcode.greedy;

/**
 * https://leetcode.com/problems/jump-game/
 * Created by lijianhua04 on 2020/2/17.
 */
public class JumpGame_55 {

    /**
     * 递归，最后一个case超时
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        return jump(0, nums);
    }

    public boolean jump(int i, int[] nums) {
        int num = nums[i];
        if (num == 0 && nums.length == 1) {
            return false;
        }
        if (num + i >= nums.length - 1) {
            return true;
        }

        for (int j = 1; j <= num && i + j < nums.length; j++) {
            boolean b = jump(i + j, nums);
            if (b) {
                return true;
            }
        }
        return false;
    }


    /**
     * 可达性判断：https://leetcode-cn.com/problems/jump-game/solution/javaji-bai-100yi-fen-zhong-kan-dong-de-chao-jian-b/
     * 只有nums[i]为0时，才可能造成不可达；
     * 从倒数第二项，往前找哪些是0；
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {

        // 需要跨越的步数，只有0前面的数 足以填坑
        int needStep = 0;
        for (int i = nums.length - 2; i >= 0; i--) {

            // 当前的数，足以填坑，能够跨过0
            if (needStep != 0 && nums[i] >= needStep + 1) {
                needStep = 0;
                continue;
            }
            if (needStep == 0 && nums[i] != 0) {
                continue;
            }
            needStep += 1;
        }

        return needStep == 0;
    }

    /**
     * https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-tan-xin-fa-by-sarafina/
     * 贪心法 每次选取上一跳中可达范围 i+a[i]最大的
     *
     * @param nums
     * @return
     */
    public boolean canJump_greedy(int[] nums) {
        if (nums.length == 0) return false;
        int right = nums[0];
        for (int i = 0; i <= right; i++) {
            if (right >= nums.length - 1) return true;
            if (i + nums[i] > right) right = i + nums[i];
        }
        return false;
    }


    public int jump(int[] nums) {

        int maxRange = 0;
        int stepCount = 0;


        return 0;
    }




    public static void main(String[] args) {
//        new JumpGame_55().canJump(new int[]{2,3,1,1,4});
        new JumpGame_55().canJump(new int[]{2, 0, 0});
    }
}
