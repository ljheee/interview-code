package leetcode.dp;

/**
 * https://leetcode.com/problems/delete-operation-for-two-strings/submissions/
 * 删除步数最小=删除最少的字符=保留最多的相等字符= 保留最长的相等子序列
 */
public class DeleteOperationforTwoStrings_583 {


    /**
     * 最长相等子序列
     * "a", "ab" 的case不过
     * 原因是 初始赋值没初始化好，dp应该多扩充一格
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        if (word1.equals(word2)) return 0;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        //最长相等的子序列
        int[][] dp = new int[word1.length()][word2.length()];
        dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 1 : 0;

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = word1.charAt(i) == word2.charAt(0) ? 1 : 0;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = word1.charAt(0) == word2.charAt(j) ? 1 : 0;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // dp求的还是 最长子序列长度
        return word1.length() + word2.length() - 2 * dp[word1.length() - 1][word2.length() - 1];
    }

    // AC
    public int minDistance0(String word1, String word2) {

        if (word1.equals(word2)) return 0;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        //最长相等的子序列
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return word1.length() + word2.length() - 2 * dp[word1.length()][word2.length()];
    }


    public int minDistance2(String word1, String word2) {

        if (word1.equals(word2)) return 0;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        // 最大相等子串
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        int max = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return word1.length() + word2.length() - 2 * max;
    }

    public static void main(String[] args) {
        DeleteOperationforTwoStrings_583 operationforTwoStrings583 = new DeleteOperationforTwoStrings_583();
        System.out.println(operationforTwoStrings583.minDistance0("a", "ab"));

    }

}
