package sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * 二路归并
 * 分治思想
 */
public class BinaryMergeSort {


    public static void mergeSort(int[] data, int left, int right) {
        if (left < right) {
            // 分的过程
            int mid = (left + right) / 2;
            mergeSort(data, left, mid);
            mergeSort(data, mid + 1, right);

            // 和的过程
            merge(data, left, mid, right);
        }

    }

    private static void merge(int[] data, int left, int mid, int right) {
        int[] temp = new int[data.length];// 每次申请一个临时数组
        int point1 = left;
        int point2 = mid + 1;

        int idx = left;
        while (point1 <= mid && point2 <= right) {
            if (data[point1] <= data[point2]) {
                temp[idx] = data[point1];
                point1++;
                idx++;
            } else {
                temp[idx] = data[point2];
                point2++;
                idx++;
            }
        }

        while (point1 <= mid) {
            temp[idx++] = data[point1];
            point1++;
        }
        while (point2 <= right) {
            temp[idx++] = data[point2];
            point2++;
        }

        // 把left~right 排好序的替换给原数组
        for (int i = left; i <= right; i++) {
            data[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 1, 3, 5};
        mergeSort(arr, 0, 4);
        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr);// 双轴快排
        Collections.sort(Collections.EMPTY_LIST);// 调用Arrays.sort(T[] a, Comparator<? super T> c) 是归并排序
    }
}
