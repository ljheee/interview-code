package some;

import leetcode.ArrayGenerator;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 滑动窗口-经典题
 * 给定一个有序数组arr，从左到右表示哪些点依次坐落在x上
 * 给定一个正数K，表示有一根长度为K的绳子，
 * 返回这根绳子最多压中几个点，在绳子的边缘处也算压中
 * <p>
 * 窗口大小设为K，不断往右移动，看窗口内能压中几个点；
 */
public class CordCoverMaxPoint {


    public static int maxPointCordCover(int[] arr, int k) {

        if (arr == null || arr.length == 0 || k == 0) return 0;

        int cur = 0;
        int max = 1;
        int left = 0;
        int right = 0;

        for (; left < arr.length; left++, cur -= 1) {
            while (right < arr.length && arr[right] - arr[left] <= k) {
                right++;
                cur++;
            }
            max = Math.max(max, cur);
        }
        return max;
    }


    /**
     * 左神的实现；
     * 唯一的不同，当k=0时，绳子长度为0认为也能覆盖一个点。
     * @param arr
     * @param L
     * @return
     */
    public static int maxPoint2(int[] arr, int L) {
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        while (left < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(maxPointCordCover(new int[]{3, 4, 5, 7, 9, 12, 13}, 4));// 3
        System.out.println(maxPointCordCover(new int[]{3, 4, 5, 7, 9, 12, 13}, 5));// 4


        for (int i = 0; i < 200; i++) {
            int[] array = ArrayGenerator.distinctSortedArray(8, 1, 10);
            int nextInt = ThreadLocalRandom.current().nextInt(1,9);
            if (maxPointCordCover(array, nextInt) != maxPoint2(array, nextInt)) {
                System.out.println(String.format("%s,%s,%s,%d", maxPoint2(array, nextInt), maxPointCordCover(array, nextInt), Arrays.toString(array), nextInt));
            }
        }
    }
}
