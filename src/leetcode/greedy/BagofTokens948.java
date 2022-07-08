package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 你的初始能量为 P，初始分数为 0，只有一包令牌。
 * 令牌的值为 token[i]，每个令牌最多只能使用一次，可能的两种使用方法如下：
 * -如果你至少有 token[i] 点能量，可以将令牌置为正面朝上，失去 token[i] 点能量，并得到 1 分。
 * -如果我们至少有 1 分，可以将令牌置为反面朝上，获得 token[i] 点能量，并失去 1 分。
 * 在使用任意数量的令牌后，返回我们可以得到的最大分数。
 * <p>
 * <p>
 * 输入：tokens = [100,200], P = 150
 * 输出：1
 * 输入：tokens = [100,200,300,400], P = 200
 * 输出：2
 * <p>
 * 贪心：保留更多的能量、用最少的能量 换更多的分。
 * tokens排序完后：用小token换score，用score换得更大的能量，进而换更多score。
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/bag-of-tokens
 */
public class BagofTokens948 {


    /**
     * 最终得分，就是失去token[i]能力值，换得分；
     * <p>
     * 失去1分 换得token[i] 点能量，————这么做的益处是换得的token[i]点能力 非常大，能在后续换更多分。
     *
     * @param tokens
     * @param P
     * @return
     */
    public static int bagOfTokensScore0(int[] tokens, int P) {
        if (tokens.length == 0) {
            return 0;
        }
        Arrays.sort(tokens);
        if (P < tokens[0]) {
            return 0;//能量值连 最小的令牌都不能翻过
        }

        int maxScore = 0;
        int score = 0;
        for (int i = 0, j = tokens.length - 1; i <= j; ) {
            // 只有能量还够，就一直翻拍得分
            while (i <= j && P >= tokens[i]) {
                P -= tokens[i];
                score++;
                i++;
            }
            maxScore = Math.max(maxScore, score);// 有可能下面用score换得能量，不能加分，始终用ans记录历史最高得分
            if (score > 0) {
                P += tokens[j];
                score--;
                j--;
            }
        }

        return maxScore;
    }

    public static int bagOfTokensScore(int[] tokens, int P) {
        if (tokens.length == 0) {
            return 0;
        }
        Arrays.sort(tokens);
        if (P < tokens[0]) {
            return 0;
        }

        int score = 0;
        for (int i = 0, j = tokens.length - 1; i <= j; ) {
            while (i <= j && P >= tokens[i]) {
                P -= tokens[i];
                score++;
                i++;
            }

            if (i + 1 <= tokens.length - 1) {
                if (P + tokens[j] >= tokens[i] + tokens[i + 1]) {
                    //用一分换两分
                    P = P + tokens[j] - (tokens[i] + tokens[i + 1]);
                    i += 2;
                    j--;
                    score++;
                } else if (i + 3 <= tokens.length - 1) {
                    if (P + tokens[j] + tokens[j - 1] >= tokens[i] + tokens[i + 1] + tokens[i + 2]) {
                        //用2分换3分
                        P = P + tokens[j] + tokens[j - 1] - (tokens[i] + tokens[i + 1] + tokens[i + 2]);
                        i += 4;
                        j -= 2;
                        score += 1;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        return score;
    }

    public static int bagOfTokensScore2(int[] tokens, int P) {
        if (tokens.length == 0) {
            return 0;
        }
        Arrays.sort(tokens);
        if (P < tokens[0]) {
            return 0;
        }

        int ans = 0;
        int points = 0;
        for (int i = 0, j = tokens.length - 1; i <= j; ) {
            while (i <= j && P >= tokens[i]) {
                P -= tokens[i++];
                points++;
            }

            ans = Math.max(ans, points);// 有可能下面用score换得能量，不能加分，始终用ans记录历史最高得分
            if (i <= j && points > 0) {
                P += tokens[j--];
                points--;
            }
        }

        return ans;
    }


    /**
     * 变换数组k个数字的符号，使数组和最大
     * https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/submissions/
     * <p>
     * 思路：
     * 0、排序
     * 1、A[0]=0,变换0 K次，直接求和返回
     * 2、A[0]>0,变换A[0] K次，直接求和返回
     * 3、A[0]<0 先将负数变正数，存在0，翻完剩余的K
     * 不存在0，选正负交界处的最小值，翻完剩余的K
     *
     * @param A
     * @param K
     * @return
     */
    public static int largestSumAfterKNegations(int[] A, int K) {
        if (A.length == 0) {
            return 0;
        }
        Arrays.sort(A);
        if (A[0] == 0) {
            return IntStream.of(A).sum();
        } else if (A[0] < 0) {

            while (K > 0) {
                for (int i = 0; i < A.length; i++) {

                    // 优先 把负数变正
                    if (A[i] < 0 && K > 0) {
                        A[i] = -A[i];
                        K--;
                    } else if (A[i] == 0 && K > 0) {
                        K = 0;
                        break;
                    } else {
                        if (A[i - 1] <= A[i]) {
                            A[i - 1] = K % 2 == 0 ? A[i - 1] : -A[i - 1];
                        } else {
                            A[i] = K % 2 == 0 ? A[i] : -A[i];
                        }
                        K = 0;
                        break;
                    }
                }
            }
            return IntStream.of(A).sum();
        } else {
            //A[0] < 0
            A[0] = K % 2 == 0 ? A[0] : -A[0];
            return IntStream.of(A).sum();
        }
    }


    /**
     * 用完材料，做汉堡最佳方案
     * https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients/submissions/
     * 就是解方程
     *
     * @param tomatoSlices
     * @param cheeseSlices
     * @return
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> list = new ArrayList<>();
        if (tomatoSlices == 0 && cheeseSlices == 0) {
            list.add(0);
            list.add(0);
            return list;
        }
        if (tomatoSlices == 0 || cheeseSlices == 0) {
            return list;
        }

        int x = tomatoSlices / 2 - cheeseSlices;
        int y = cheeseSlices - x;
        if ((int) Math.floor(x) != x || (int) Math.floor(y) != y) {
            return list;
        }

        if (4 * x + 2 * y != tomatoSlices) {
            return list;
        }
        if (x < 0 || y < 0) {
            return list;
        }
        list.add(x);
        list.add(y);

        return list;
    }




    public static void main(String[] args) {
        System.out.println(bagOfTokensScore(new int[]{6, 0, 39, 52, 45, 49, 59, 68, 42, 37}, 99));

        largestSumAfterKNegations(new int[]{2, -3, -1, 5, -4}, 2);
    }
}
