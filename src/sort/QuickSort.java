package sort;

import java.util.Arrays;

/**
 * 快排-两种实现
 * <p>
 * 另一种快排，使用两个指针，完成一趟交换https://www.jvruo.com/archives/639/
 */
public class QuickSort {


    public static void quickSort(int[] data, int left, int right) {
        int base = data[left];// 轴

        int begin = left;
        int end = right;

        while (begin < end) {

            //从后往前找，小于base的
            while (begin < end && data[end] >= base) {// 大于base的跳过
                end--;
            }
            // 说明找到了
            if (begin < end) {
                int temp = data[end];
                data[end] = data[begin];
                data[begin] = temp;
                begin++;
            }

            //从前往后找，大于base的
            while (begin < end && data[begin] <= base) {
                begin++;
            }
            // 说明找到了
            if (begin < end) {
                int temp = data[end];
                data[end] = data[begin];
                data[begin] = temp;
                end--;
            }
        }
        // 循环完一轮，数组被分成    小于base base 大于base 三段
        // 递归左边部分和右边部分

        // 此时begin 就是[当前]轴 的下标位置
        if (left < begin) {
            quickSort(data, left, begin - 1);
        }
        if (end < right) {
            quickSort(data, begin + 1, right);
        }
        System.out.println(Arrays.toString(data));
    }


    /**
     * 三路快排
     * 用pivot 将arr划分成 <, =, >的三部分。
     *
     * @param arr
     * @param left
     * @param right
     * @param pivot
     * @return
     */
    public static int threeWayPatition(int[] arr, int left, int right, int pivot) {

        int beginIndex = left;
        int endIndex = right;
        int current = left;

        while (current <= endIndex) {
            if (arr[current] < pivot) {
                int tmp = arr[current];
                arr[current] = arr[beginIndex];
                arr[beginIndex] = tmp;
                current++;
                beginIndex++;
            } else if (arr[current] > pivot) {
                int tmp = arr[current];
                arr[current] = arr[endIndex];
                arr[endIndex] = tmp;
                //current++; 此处current不能自增
                endIndex--;
            } else {
                current++;
            }
        }
        return beginIndex;
    }

    public static void main(String[] args) {
        int[] ints = {5, 1, 2, 6, 7, 8, 5};
        quickSort(ints, 0, ints.length - 1);

        System.out.println(threeWayPatition(ints, 0, ints.length - 1, 5));

        int[] arr = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println(threeWayPatition(arr, 0, ints.length - 1, 5));
    }
}
