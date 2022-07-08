package leetcode.dp;

import leetcode.ArrayGenerator;
import leetcode.greedy.JumpGame_55;

import java.util.Arrays;

/**
 * Created by lijianhua04 on 2020/6/14.
 */
public class JumpGame_45 {


    /**
     * 跳到尾部，最小步数
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;

        int jump = 0;
        int curRightIdx = 0;
        int next = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (i > curRightIdx) {
                jump++;
                curRightIdx = next;
            }
            next = Math.max(next, i + nums[i]);
        }

        return jump;
    }


    public static boolean canJump(int[] nums) {
        if (nums.length <= 1) return true;

        int curRightIdx = 0;
        int next = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (i > curRightIdx) {
                // i下标已经来到的位置，next却不能到达
                if(i > next) return false;
                curRightIdx = next;
            }
            next = Math.max(next, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame_45().canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(new JumpGame_45().canJump(new int[]{2,0, 0, 4}));
        System.out.println(new JumpGame_45().canJump(new int[]{2,0, 0}));
        System.out.println(new JumpGame_45().canJump(new int[]{ 0}));


        for (int i = 0; i < 5010; i++) {

            int[] array = ArrayGenerator.generateRandomArray(10, 0, 5);
            if (new JumpGame_55().canJump2(array) != canJump(array)) {
                System.out.println(Arrays.toString(array));
            }

        }
    }

}
