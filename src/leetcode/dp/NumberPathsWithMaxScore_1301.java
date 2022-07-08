package leetcode.dp;

import java.time.ZoneOffset;
import java.util.*;

/**
 * 从右下角，起始位置，到达左上角结束位置；
 * 能够获取的最大score
 */
public class NumberPathsWithMaxScore_1301 {


    /**
     * 只计算 能够获得的最大分值
     *
     * @param board
     * @return
     */
    public static int[] pathsWithMaxScore0(List<String> board) {
        if (board.size() == 0 || board.get(0).length() == 0) return new int[]{0, 0};

        int[][] maxScore = new int[board.size() + 1][board.get(0).length() + 1];

        for (int i = maxScore.length - 1; i >= 0; i--) {
            for (int j = maxScore[0].length - 1; j >= 0; j--) {

                if (i == maxScore.length - 1 || j == maxScore[0].length - 1) {
                    maxScore[i][j] = 0;
                    continue;
                }
                if (board.get(i).charAt(j) == 'X' || board.get(i).charAt(j) == 'S') {
                    maxScore[i][j] = 0;
                    continue;
                }
                maxScore[i][j] = Math.max(Math.max(maxScore[i + 1][j], maxScore[i][j + 1]),
                        maxScore[i + 1][j + 1]) + board.get(i).charAt(j) - '0';
            }
        }

        return new int[]{maxScore[0][0] + '0' - 'E', 0};
    }


    // 相似解法 https://leetcode.com/problems/number-of-paths-with-max-score/discuss/469098/JAVA-Clean-DP-Solution-with-Explanation
    public static int[] pathsWithMaxScore(List<String> board) {
        if (board.size() == 0 || board.get(0).length() == 0) return new int[]{0, 0};

        //max score when reach (j, i)
        int[][] maxScore = new int[board.size() + 1][board.get(0).length() + 1];
        // path to reach (j, i) with max score
        int[][] dp = new int[board.size() + 1][board.get(0).length() + 1];

        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {

                if (i == dp.length - 1 || j == dp[0].length - 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (board.get(i).charAt(j) == 'X' || board.get(i).charAt(j) == 'S') {
                    maxScore[i][j] = 0;
                    dp[i][j] = 0;
                    continue;
                }
                int max = Math.max(Math.max(maxScore[i + 1][j], maxScore[i][j + 1]),
                        maxScore[i + 1][j + 1]);
                maxScore[i][j] = max + board.get(i).charAt(j) - '0';

                dp[board.size() - 1][board.get(0).length() - 1] = 1;

                dp[i][j] = (max == maxScore[i + 1][j] ? dp[i + 1][j] : 0) +
                        (max == maxScore[i][j + 1] ? dp[i][j + 1] : 0) +
                        (max == maxScore[i + 1][j + 1] ? dp[i + 1][j + 1] : 0);
                dp[i][j] %= 1000000007;
            }
        }

        return dp[0][0] == 0 ? new int[]{0, 0} : new int[]{maxScore[0][0] + '0' - 'E', dp[0][0]};
    }


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("E23");
        list.add("2X2");
        list.add("12S");
        System.out.println(Arrays.toString(pathsWithMaxScore(list)));


        list.clear();
        list.add("E12");
        list.add("1X1");
        list.add("21S");
        System.out.println(Arrays.toString(pathsWithMaxScore(list)));

        list.clear();
        list.add("E11");
        list.add("XXX");
        list.add("11S");
        System.out.println(Arrays.toString(pathsWithMaxScore0(list)));


    }
}
