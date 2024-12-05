package sort;

import leetcode.ArrayGenerator;

import java.util.Arrays;

/**
 * 归并排序
 * 先分再治
 * 开辟o(n)的空间，反复利用。
 */
public class MergeSort {

    public static void main(String[] args) {

        int[] arr = ArrayGenerator.generateRandomArray(10, -10, 10);

        // 开辟一个数组，用于存放有序区间，避免频繁开辟
        int[] result = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, result);
        System.out.println(Arrays.toString(result));
    }


    public static void mergeSort(int[] arr, int left, int right, int[] result) {

        if (left == right) {
            result[left] = arr[left];
            return;
        }
        if (left < right) {
            int mid = left + ((right - left) >> 1);
            mergeSort(arr, left, mid, result);
            mergeSort(arr, mid + 1, right, result);
            merge(arr, result, left, mid, right);
        }
    }

    private static void merge(int[] arr, int[] result, int left, int mid, int right) {

        // arr中两个有序 段落，合并到result
        // left~mid
        // mid+1~right

        int ll = left;
        int rr = mid + 1;
        int k = left;
        while (ll <= mid && rr <= right) {
            if (arr[ll] <= arr[rr]) {
                result[k++] = arr[ll++];
            } else {
                result[k++] = arr[rr++];
            }
        }

        while (ll <= mid) {
            result[k++] = arr[ll++];
        }
        while (rr <= right) {
            result[k++] = arr[rr++];
        }

        //result 赋值给arr
        for (int i = left; i <= right; i++) {
            arr[i] = result[i];
        }
    }
}
