package some;

import java.util.Arrays;

/**
 * Created by lijianhua04 on 2020/4/23.
 */
public class KthMin2 {


    /**
     * 双指针的 partition
     * https://selfboot.cn/2016/09/01/lost_partition/
     * 存在：pivot有多个时，不能完全划分成三部分
     *
     * @param arr
     * @param left
     * @param right
     * @param pivot
     * @return
     */
    public static int partition(int[] arr, int left, int right, int pivot) {
        while (left < right) {
            while (left < right && arr[right] >= pivot) right--;
            arr[left] = arr[right];

            while (left < right && arr[left] <= pivot) left++;
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }


    public static int getMed(int[] arr, int left, int right, int k) {

        if (right - left + 1 <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + k - 1];
        }


        int n = (right - left + 1) / 5;
        int[] media = new int[n];
        for (int i = 0; i < n; i++) {
            int begin = left + i * 5;
            int end = begin + 5;
            Arrays.sort(arr, begin, end);//不包含end
            media[i] = arr[begin + 2];
        }
        Arrays.sort(media);
        int povit = media[n / 2];

        System.out.println(Arrays.toString(media));
        partition(arr, left, right, povit);

        System.out.println(Arrays.toString(arr));


        return 0;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println(Recursion(arr, 0, arr.length - 1, 3));

    }

    private static int Recursion(int[] arr, int left, int right, int k) {
        int pivotindex = partition(arr, left, right, 3);
        if (pivotindex == k) {
            return arr[pivotindex];
        } else if (pivotindex > k) {
            return Recursion(arr, left, pivotindex - 1, k);
        } else {
            return Recursion(arr, pivotindex + 1, right, k);
        }
    }
}
