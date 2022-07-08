package sort;

import java.util.Arrays;

/**
 * Created by lijianhua04 on 2020/7/28.
 */
public class QuickSort2 {


    public static void main(String[] args) {
        int[] arr = new int[]{-4,0,7,4,9,-5,-1,0,-7,-1};
//        int[] arr = new int[]{3, 5, 1, 7, 0, 4, 6, 9, -1};
//        int[] arr = new int[]{1,2};
        quickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int from, int to) {

        if (from >= to) return;
        int mid = quickSort(arr, from, to, arr[(from + to) / 2]);
        System.out.println("mid=" + mid);

        quickSort(arr, from, mid-1);
        quickSort(arr, mid , to);
    }

    private static int quickSort(int[] arr, int from, int to, int base) {

//        System.out.println(from + "==" + to);
        int i = from;
        int j = to;

        while (i < j) {
            while (i < j && arr[i] <= base) i++;
            while (i < j && arr[j] > base) j--;

            //swap(arr[i],arr[j]);
            if(arr[i] >= arr[j]){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return i;
    }
}
