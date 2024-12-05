package leetcode.greedy;

/**
 * 情侣 挨对坐
 * https://leetcode.com/problems/couples-holding-hands/
 * 旁边不是我的情况，就换  https://leetcode-cn.com/problems/couples-holding-hands/solution/qing-lu-qian-shou-by-leetcode/
 */
public class CouplesHoldingHands_765 {

    public int minSwapsCouples(int[] row) {
        int ans = 0;
        for (int i = 0; i < row.length; i += 2) {
            int me = row[i];
            if (row[i + 1] == (me ^ 1)) {
                continue;
            }
            ans++;

            for (int j = i+2; j < row.length; j++) {
                if (row[j] == (me ^ 1)) {
                    int temp = row[i + 1];
                    row[i + 1] = row[j];
                    row[j] = temp;
                    break;
                }
            }
        }
        return ans;
    }
}
