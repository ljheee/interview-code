package some;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 长度为N的数组arr，一定可以组成N^2个数值对。
 * 给定一个数组arr,和整数k，返回第k小的数值对。
 * 例如arr= [3,1,2],
 * 数值对有(3,3) (3,1) (3,2) (1,3), (1,1)(1,2) (2,3) (2,1)(2,2)，也就是任意两个数都有数值对，而且自己和自己也算数值对
 * 数值对怎么排序?规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。所以.上面的数值对排序的结果为:
 * (1,1)(1.2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
 */
public class KthMinPair {


    /**
     * 先生成，再排序
     * O(n^2)
     *
     * @param arr
     * @return
     */
    public static int[] kthMinPair1(int[] arr, int k) {

        int n = arr.length;
        n = n * n;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                pairs[--n] = new int[]{arr[i], arr[j]};
            }
        }

        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        return pairs[k - 1];
    }

    /**
     * @param arr
     * @param k
     * @return
     */
    public static int[] kthMinPair2(int[] arr, int k) {

        int n = arr.length;
        Arrays.sort(arr);

        int idx = (k - 1) / n;

        // firstNum 有几个
        int firstNusSize = 0;

        // 比firstNum小的个数
        int lessFirstNumSize = 0;
        for (int i = 0; i < arr.length && arr[i] <= arr[idx]; i++) {
            if (arr[i] == arr[idx]) {
                firstNusSize++;
            } else {
                lessFirstNumSize++;
            }
        }

        int rest = k - lessFirstNumSize * n;
        return new int[]{arr[idx], arr[(rest - 1) / firstNusSize]};
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(kthMinPair2(new int[]{3, 1, 2}, 9)));
        System.out.println(Arrays.toString(kthMinPair1(new int[]{1, 1, 1, 2, 2, 2, 3, 3}, 12)));
    }


}
