package leetcode.dp;

import leetcode.math.ValidPerfectSquare_367;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/uncrossed-lines/submissions/
 */
public class UncrossedLines_1035 {


    /**
     * https://leetcode.com/problems/uncrossed-lines/submissions/
     *
     * @param A
     * @param B
     * @return
     */
    public int maxUncrossedLines(int[] A, int[] B) {

        // B 中每个数，及其出现的次数
        HashMap<Integer, LinkedList<Integer>> startNumMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            LinkedList<Integer> list = startNumMap.get(B[i]);
            if (list == null) {
                LinkedList<Integer> objects = new LinkedList<>();
                objects.add(i);
                startNumMap.put(B[i], objects);
            } else {
                list.add(i);
            }
        }

        int ans = findUncross(A, startNumMap);

        int left = 0;
        int right = A.length - 1;
        while (left < right) {
            int tem = A[left];
            A[left] = A[right];
            A[right] = tem;
        }


        int c = findUncross(A, startNumMap);
        return Math.max(ans, c);
    }

    private int findUncross(int[] A, HashMap<Integer, LinkedList<Integer>> startNumMap) {
        int ans = 0;
        int endIdx = -1;
        for (int i : A) {
            LinkedList<Integer> list = startNumMap.get(i);
            if (list == null || list.isEmpty()) {
                continue;
            }
            Integer first = list.removeFirst();
            while (first < endIdx && !list.isEmpty()) {
                first = list.removeFirst();
            }
            if (first < endIdx) {
                continue;
            }
            endIdx = first;
            ans++;
        }
        return ans;
    }

    //暴力
    public int maxUncrossedLines_bf(int[] A, int[] B) {

        if (A.length > B.length) {
            return maxUncrossedLines_dp(B, A);
        }


        return 0;
    }

    // TODO: 2020/3/16 DP 解法
    public int maxUncrossedLines_dp(int[] A, int[] B) {

        if (A.length > B.length) {
            return maxUncrossedLines_dp(B, A);
        }

        int[] dp = new int[A.length];
        // B 中每个数，及其出现的次数
        HashMap<Integer, LinkedList<Integer>> startNumMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            LinkedList<Integer> list = startNumMap.get(B[i]);
            if (list == null) {
                LinkedList<Integer> objects = new LinkedList<>();
                objects.add(i);
                startNumMap.put(B[i], objects);
            } else {
                list.add(i);
            }
        }
        int bLast = -1;
        LinkedList<Integer> linkedList = startNumMap.get(A[0]);
        if (linkedList == null || linkedList.isEmpty()) {
            dp[0] = 0;
        } else {
            dp[0] = 1;
            bLast = linkedList.removeLast();
        }
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + 1;
        }

        return 0;
    }


    /**
     * 动态规划
     * 这道题：本质就是求 最长的相等子序列
     *
     * @param A
     * @param B
     * @return
     */
    public static int maxUncrossedLines_dp2(int[] A, int[] B) {

        int[][] dp = new int[A.length + 1][B.length + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] =Math.max(dp[i - 1][j],dp[i][j - 1]);
                }
            }
        }
        return dp[A.length][B.length];
    }

    /**
     * [1,1,2,1,2]
     * [1,3,2,3,1]
     * 正向、逆向case不能过；需要看得更远
     *
     * @param args
     */
    public static void main(String[] args) {
//        new UncrossedLines_1035().maxUncrossedLines(new int[]{2, 1},
//                new int[]{1, 2, 1, 3, 3, 2});

        System.out.println(maxUncrossedLines_dp2(new int[]{2, 1},
                new int[]{1, 2, 1, 3, 3, 2}));

        System.out.println(maxUncrossedLines_dp2(new int[]{2, 1},
                new int[]{1, 2, 1, 3, 3, 2}));

    }
}


