package leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 翻转A[i]或B[i] ，是的A或B 数组每个元素相等。
 * 求最小翻转次数
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
 */
public class MinimumDominoRotationsForEqualRow_1007 {


    public int minDominoRotations(int[] A, int[] B) {
        return Math.min(minStep(A, B), minStep(B, A));
    }

    public int minStep(int[] A, int[] B) {

        // 找到 频率最高的众数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entries = map.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).collect(Collectors.toList());

        // 可能 多个数，频率相等
        Map.Entry<Integer, Integer> prev = null;
        for (int j = 0; j < entries.size(); j++) {
            Map.Entry<Integer, Integer> entry = entries.get(j);
            int value = entry.getKey();

            if (prev != null && prev.getValue() != entry.getValue()) {
                break;
            }

            int ans = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] != value && B[i] == value) {
                    ans++;
                }
                if (A[i] != value && B[i] != value) {
                    ans = -1;
                    break;
                }
            }
            if (ans != -1) {
                return ans;
            }
            prev = entry;
        }
        return -1;
    }

    /**
     * 利用数字特征 1 <= A[i], B[i] <= 6
     *
     * @param A
     * @param B
     * @return
     */
    public int minDominoRotations2(int[] A, int[] B) {

        int ans = -1;
        for (int i = 1; i < 7; i++) {
            int countA = 0;
            int countB = 0;
            for (int j = 0; j < A.length; j++) {
                if (A[j] != i && B[j] != i) {
                    countA = -1;
                    countB = -1;
                    break;
                }
                if (A[j] == i && B[j] != i) {
                    countB++;
                }
                if (A[j] != i && B[j] == i) {
                    countA++;
                }
            }
            if (countA != -1 && countB != -1) {
                countA = Math.min(countA, countB);
                if (ans == -1) {
                    ans = countA;
                }
                ans = Math.min(countA, ans);
            }
        }
        return ans;
    }

    /**
     *
     * 官方题解，只选择i=0;
     * 只看A[0] 和B[0]
     * 因为对于数组A和B，任意一位i，最后都是A翻转给B或B翻转给A，或不翻转。
     * https://leetcode-cn.com/problems/minimum-domino-rotations-for-equal-row/solution/lian-xu-chai-xiang-tong-de-shu-zi-by-leetcode/
     */


    /**
     * 连续相等的字符数>=3,输出这样子串的 start、end下标
     * https://leetcode.com/problems/positions-of-large-groups/submissions/
     *
     * @param S
     * @return
     */
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> listList = new ArrayList<>();
        char[] chars = S.toCharArray();
        int start = 0;
        int end = 0;
        for (int i = 1; i < chars.length; i++) {

            if (chars[i] == chars[i - 1]) {
                end = i;
            } else {
                if (end - start >= 2) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(end);
                    listList.add(list);
                    start = i;
                    end = i;
                } else {
                    start = i;
                    end = i;
                }
            }
        }
        //最后一个
        if (end - start >= 2) {
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(end);
            listList.add(list);
        }
        return listList;
    }


    static class Pair {
        int sum;
        int product;
        int count = 1;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return sum == pair.sum &&
                    product == pair.product;
        }

        @Override
        public int hashCode() {
            return Objects.hash(sum, product);
        }
    }

    /**
     * 将 {1, 2}, {2, 1} 看作是相等的pair
     *
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs(int[][] dominoes) {

        List<Pair> list = new LinkedList<>();

        for (int[] dominoe : dominoes) {
            Pair pair = new Pair();
            pair.sum = dominoe[0] + dominoe[1];
            pair.product = dominoe[0] * dominoe[1];
            int idx = -1;
            if ((idx = list.indexOf(pair)) != -1) {
                Pair pair1 = list.get(idx);
                pair1.count = pair1.count + 1;
            } else {
                list.add(pair);
            }
        }

        int ans = 0;
        for (Pair pair : list) {
            ans += pair.count * (pair.count - 1) / 2;
        }

        return ans;
    }

    /**
     * 将 {1, 2}, {2, 1} 映射成相等的数值：采用立方和
     * <p>
     * 其他映射解法 https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/solution/deng-jie-duo-mi-nuo-gu-pai-by-coldme-2/
     * i 和 j 组成两位数，较小者当十位。
     *
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs2(int[][] dominoes) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int[] dominoe : dominoes) {
            int key = dominoe[0] * dominoe[0] * dominoe[0] + dominoe[1] * dominoe[1] * dominoe[1];
            map.put(key, map.getOrDefault(key, 0) + 1);

        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans += entry.getValue() * (entry.getValue() - 1) / 2;
        }

        return ans;
    }

    /**
     * 1,5	 5,6
     * 2,3  5,6	11
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Pair> list = new LinkedList<>();
        Pair p = new Pair();
        p.sum = 3;
        p.product = 2;

        list.add(p);
        list.remove(p);
        System.out.println(list.contains(p));


        Set<Integer> set = new HashSet<Integer>();

        for (int i = 1; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                if (!set.add(i * i * i + j * j * j)) {
                    System.out.println(i + "===" + j);
                }
            }
        }

        new MinimumDominoRotationsForEqualRow_1007().numEquivDominoPairs(new int[][]{{1, 2}, {2, 1}});

    }
}
