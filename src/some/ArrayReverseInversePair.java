package some;

import leetcode.ArrayGenerator;

import java.util.Arrays;

/**
 * 数组 逆转后的逆序数
 * 暴力法
 */
public class ArrayReverseInversePair {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 2, 3, 1, 1, 2, 3};
//        reverseByStep(arr, 8);
//        System.out.println(Arrays.toString(arr));

        int pow = 2;
        int[] arr1 = ArrayGenerator.generateRandomArray(1 << pow, 0, 20);
        int[] reverse = ArrayGenerator.generateRandomArray(pow, 0, pow);
        int[] result = arrayReverseInversePair(arr1, pow, reverse);

        System.out.println(Arrays.toString(result));

    }

    /**
     * reverse中的每个i，按2^i次方个数，对arr逆转，得到的逆序数
     *
     * @param arr
     * @param pow
     * @param reverse
     */
    public static int[] arrayReverseInversePair(int[] arr, int pow, int[] reverse) {
        int[] result = new int[reverse.length];

        for (int i = 0; i < reverse.length; i++) {
            reverseByStep(arr, (int) Math.pow(2, reverse[i]));
            result[i] = countInversePair(arr);
        }
        return result;
    }

    private static int countInversePair(int[] arr) {
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) cnt++;
            }
        }
        return cnt;
    }

    // 每step个数，翻转
    private static void reverseByStep(int[] arr, int step) {

        int n = arr.length / step + 1;
        // 0~step-1
        // step~2*step-1
        // 2*step~3*step-1

        int i = 0;
        while (i < n) {
            int begin = i * step;
            int end = Math.min((i + 1) * step - 1, arr.length - 1);

            while (begin < end) {
                int tmp = arr[begin];
                arr[begin++] = arr[end];
                arr[end--] = tmp;
            }
            i++;
        }
    }
}
