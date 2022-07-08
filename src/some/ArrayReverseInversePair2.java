package some;

import leetcode.ArrayGenerator;

import java.util.Arrays;

/**
 * 数组 逆转后的逆序数
 * <p>
 * <p>
 * 对于任意数组arr，其逆序总数=2个数一组的划分中 逆序的数量 + 4个数一组的划分中 逆序的数量 + 8个数一组的划分中 逆序的数量 +……
 * n个数一组的划分中，第一个数取自前一半，第二个数取自后一半；
 */
public class ArrayReverseInversePair2 {

    public static void main(String[] args) {

        int pow = 2;
        int[] arr = ArrayGenerator.generateRandomArray(1 << pow, 0, 20);
        int[] clone = arr.clone();
        int[] reverse = ArrayGenerator.generateRangeArray(pow);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(reverse));

        int[] result1 = ArrayReverseInversePair.arrayReverseInversePair(arr, pow, reverse);
        int[] result2 = ArrayReverseInversePair2.arrayReverseInversePair(clone, pow, reverse);
        System.out.println(Arrays.toString(clone));

        System.out.println(Arrays.toString(result1) + "==" + Arrays.toString(result2));

    }

    /**
     * reverse中的每个i，按2^i次方个数，对arr逆转，得到的逆序数；
     *
     * 每次数组变换后，求一次逆序数，结果是总的逆序数。
     *
     * @param arr
     * @param pow
     * @param reverse
     */
    public static int[] arrayReverseInversePair(int[] arr, int pow, int[] reverse) {


        int[] arrClone = arr.clone();
        reverse(arrClone);

        //2的i次方个数一组的划分中，降序的数量
        int[] recordDown = new int[pow + 1];
        int[] recordUp = new int[pow + 1];

        mergeSortTransform(arr, 0, arr.length - 1, recordDown, pow);
        mergeSortTransform(arrClone, 0, arrClone.length - 1, recordUp, pow);

        int[] result = new int[reverse.length];
        for (int i = 0; i < reverse.length; i++) {

            // 处理 reverse[i] 次方的变换；小于等于reverse[i]次方的，才需要交换
            // 交换 逆序、升序数量
            for (int j = 1; j <= reverse[i]; j++) {
                int tmp = recordDown[j];
                recordDown[j] = recordUp[j];
                recordUp[j] = tmp;
            }

            // reverse[i] 次方变换下，逆序总数=2^1、2^2、2^3、……所有逆序总和
            for (int j = 1; j <= pow; j++) {
                result[i] += recordDown[j];
            }
        }
        return result;
    }

    private static void reverse(int[] arrClone) {
        int left = 0;
        int right = arrClone.length - 1;
        while (left < right) {
            int tmp = arrClone[left];
            arrClone[left++] = arrClone[right];
            arrClone[right--] = tmp;
        }
    }

    private static void mergeSortTransform(int[] arr, int left, int right, int[] recordDown, int pow) {

        if (left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortTransform(arr, left, mid, recordDown, pow - 1);
        mergeSortTransform(arr, mid + 1, right, recordDown, pow - 1);
        recordDown[pow] += merge(arr, left, right, mid);
    }

    private static int merge(int[] arr, int left, int right, int mid) {
        //merge
        int[] res = new int[arr.length];// 辅助数组

        // arr 两个有序段：left~mid,mid+1~right
        int ll = left;
        int rr = mid + 1;
        int k = left;
        int ans = 0;
        while (ll <= mid && rr <= right) {
//            if (arr[ll] <= arr[rr]) {
//                res[k++] = arr[ll++];
//            } else {
//                recordDown[pow] += (mid - ll + 1);// 累加逆序数
//                res[k++] = arr[rr++];
//            }
            ans += arr[ll] > arr[rr] ? (mid - ll + 1) : 0;
            res[k++] = arr[ll] <= arr[rr] ? arr[ll++] : arr[rr++];
        }
        while (ll <= mid) res[k++] = arr[ll++];
        while (rr <= right) res[k++] = arr[rr++];

        for (int i = left; i <= right; i++) {
            arr[i] = res[i];
        }
        return ans;
    }

}
