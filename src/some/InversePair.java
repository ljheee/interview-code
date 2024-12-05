package some;

import leetcode.ArrayGenerator;

import java.util.Arrays;


/**
 * 归并排序，求逆序对
 * merge过程，两个有序数组 合并成一个；
 * i在左边，j在右边：统计大于j的个数
 *
 * 两个有序数组merge成一个，左边元素>右边元素时，出现逆序对；对于右侧的每个j，统计左侧大于j的个数。
 *
 * 归并排序 O(nlogN)
 */
public class InversePair {


    static int count = 0;

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.generateRandomArray(11, -10, 10);

        int pair = inversePair(arr);

        int[] result = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, result);
        System.out.println(Arrays.toString(result));

        System.out.println(count + "===" + pair);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] result) {
        if (left == right) {
            result[left] = arr[left];
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, result);
        mergeSort(arr, mid + 1, right, result);

        merge(arr, left, mid, right, result);
    }

    /**
     * 合并的过程
     * arr两个有序部分
     * 左侧有序段 left~mid，移动下标i
     * 右侧有序段 mid+1~right，移动下标j
     * <p>
     * 记录每个j，左侧有序数组中，比其大的个数；就是逆序数
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param result
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] result) {
        int ll = left;
        int rr = mid + 1;
        int k = left;
        while (ll <= mid && rr <= right) {
            if (arr[ll] <= arr[rr]) {
                result[k++] = arr[ll++];
            } else {
                result[k++] = arr[rr++];
                // mid-ll+1
                count += (mid - ll + 1);
            }
        }
        while (ll <= mid) result[k++] = arr[ll++];
        while (rr <= right) result[k++] = arr[rr++];

        for (int i = left; i <= right; i++) {
            arr[i] = result[i];
        }
    }

    /**
     * 暴力求解 arr 逆序对
     * O(n^2)
     *
     * @param arr
     * @return
     */
    public static int inversePair(int[] arr) {
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) cnt++;
            }
        }
        return cnt;
    }

}
