package sort;

import java.util.Arrays;

/**
 * 冒泡
 */
public class BubbleSort {

    /**
     * 3 1 5 4 6 1  =input
     * 1 5 4 6 1 3	=第一轮：3一直下沉到数组 最后一个smaller
     * 1 4 6 1 3 5	=第二轮，下沉5
     * 1 6 2 3 4 5
     * 1 2 3 4 5 6
     *
     * @param array
     * @return
     */
    public static int[] bubble(int[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(bubble(new int[]{3, 1, 0, 5, 6, 2, 7})));// [0, 1, 2, 3, 5, 6, 7]
    }
}
