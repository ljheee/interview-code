package sort;


import java.util.Arrays;

/**
 * Created by lijianhua04 on 2020/7/30.
 */
public class MergeSort2 {


    public static void main(String[] args) {
//        int[] arr = {3, 1, 7, 0, 9, 5};
//        int[] arr = {10,3, 1, 7, 2,0, 9, 5};
//        int[] arr = {6, 5};
        int[] arr = {5};

        int[] help = new int[arr.length];

        sort(arr, 0, arr.length - 1, help);

        System.out.println(Arrays.toString(arr));
    }


    public static void sort(int[] arr, int from, int to, int[] help) {

        if (from == to) {
            help[from] = arr[from];
            return;
        }
        int mid = (from + to) / 2;

        sort(arr, from, mid, help);
        sort(arr, mid + 1, to, help);

        merge(arr, from, mid, to, help);
    }

    private static void merge(int[] arr, int from, int mid, int to, int[] help) {

        int i = from;
        int j = mid + 1;

        int idx = from;
        while (i <= mid && j <= to) {
            help[idx++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid) {
            help[idx++] = arr[i++];
        }
        while (j <= to) {
            help[idx++] = arr[j++];
        }

        for (int k = from; k <= to; k++) {
            arr[k] = help[k];
        }

    }
}
