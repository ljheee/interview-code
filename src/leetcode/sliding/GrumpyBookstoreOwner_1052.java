package leetcode.sliding;

/**
 * 正在grumpy数组中，找出连续X个数，对应在customers数组的子数组和最大
 * https://leetcode.com/problems/grumpy-bookstore-owner/submissions/
 */
public class GrumpyBookstoreOwner_1052 {


    public int maxSatisfied(int[] customers, int[] grumpy, int X) {

        int sum = 0;
        for (int i = 0; i < grumpy.length; i++) {
            if (grumpy[i] == 0) {
                sum += customers[i];
            }
        }

        int max = sum;
        int left = 0;
        for (int i = 0; i < grumpy.length; ) {
            if (grumpy[i] == 0) {
                i++;
                continue;
            }

            if (grumpy[i] == 1 && i - left < X) {
                sum += customers[i];
                max = Math.max(max, sum);
                i++;
            }

            if (i - left >= X) {
                if (grumpy[left] == 1) {
                    sum -= customers[left];
                }
                left++;
            }

        }
        return max;
    }


    public static void main(String[] args) {
//        new GrumpyBookstoreOwner_1052().maxSatisfied(new int[]{1,0,1,2,1,1,7,5},new int[]{0,1,0,1,0,1,0,1},3);

        System.out.println(new GrumpyBookstoreOwner_1052().maxSatisfied(new int[]{2, 6, 6, 9},
                new int[]{0, 0, 1, 1}, 1));


    }
}
