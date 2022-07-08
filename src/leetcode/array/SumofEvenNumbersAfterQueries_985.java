package leetcode.array;

/**
 * https://leetcode.com/problems/sum-of-even-numbers-after-queries/
 * Created by lijianhua04 on 2020/3/3.
 */
public class SumofEvenNumbersAfterQueries_985 {

    /**
     * case超时：A数组非常长
     *
     * @param A
     * @param queries
     * @return
     */
    public int[] sumEvenAfterQueries0(int[] A, int[][] queries) {

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int val = query[0];
            int index = query[1];
            A[index] += val;

            for (int i1 : A) {
                if (i1 % 2 == 0) {
                    ans[i] += i1;
                }
            }
        }
        return ans;
    }

    /**
     * O(n)的解法
     *
     * @param A
     * @param queries
     * @return
     */
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {

        int[] ans = new int[queries.length];

        int s = 0;
        for (int i : A) {
            if (i % 2 == 0) {
                s += i;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int val = query[0];
            int index = query[1];

            if (A[index] % 2 == 0) {
                s -= A[index];
            }
            A[index] += val;
            if (A[index] % 2 == 0) {
                s += A[index];
            }
            ans[i] = s;

        }
        return ans;
    }

}
