package leetcode.binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lijianhua04 on 2020/6/29.
 */
public class FindKclosestElements_658 {


    public static List<Integer> findClosestElements0(int[] arr, int k, int x) {

        List<Integer> list = new ArrayList();

        int minAbs = Math.abs(arr[0] - x);
        int idx = -1;
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= x;
            if (Math.abs(arr[i]) < minAbs) {
                minAbs = Math.abs(arr[i]);
                idx = i;
            }
        }

        int j = idx;
        for (int i = idx + 1; i < arr.length; i++) {
            if (Math.abs(arr[i]) == Math.abs(arr[i - 1])) {
                j = i;
            }
        }
        int minNum = j - idx + 1;
        if (minNum >= k) {
            minNum = k;
        }

        int rest = k - minNum;
        int ii = idx;
        for (int i = 0; i < minNum; i++) {
            list.add(arr[ii++] + x);
        }
        while (rest > 0) {
            if (rest > 0 && (idx - 1) >= 0) {
                --idx;
                list.add(arr[idx] + x);
                rest--;
            }
            if (rest > 0 && (j + 1) < arr.length) {
                ++j;
                list.add(arr[j] + x);
                rest--;
            }
        }

        Collections.sort(list);
        return list;


    }

    /**
     * 二分查找到 与x最接近的数（绝对值差最小），
     * 以该位置为中心，找出k-1个数。
     * <p>
     * 1,2, 4,5,6
     * x=3 ,k=4
     *
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public static List<Integer> findClosestElements1(int[] arr, int k, int x) {

        List<Integer> list = new ArrayList();

        int left = 0;
        int right = arr.length - 1;

        int idx = -1;
        int minAbs = Math.abs(arr[idx = right / 2] - x);
        ;
        // 二分查找 最左边界
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (Math.abs(arr[mid] - x) <= minAbs) {
                minAbs = Math.abs(arr[mid] - x);
                idx = Math.min(mid, idx);
                right = mid;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else if (arr[mid] > x) {
                right = mid;
            } else if (arr[mid] == x && mid < idx) {
                idx = mid;
            } else {
                right--;
            }
        }
        System.out.println("idx=" + idx);


        return list;
    }


    /**
     * https://leetcode-cn.com/problems/find-k-closest-elements/solution/pai-chu-fa-shuang-zhi-zhen-er-fen-fa-python-dai-ma/
     * 排除法:从两边开始删除，保留中间k个
     *
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public static List<Integer> findClosestElements_(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList();

        int left = 0;
        int right = arr.length - 1;
        while (right - left + 1 > k) {
            if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                right--;
            }else {
                left++;
            }
        }
        for (int i = left; i <=right; i++) {
            list.add(arr[i]);
        }

        return list;
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> list = new ArrayList();

        int left = 0;
        int right = arr.length - 1;

        int idx = -1;
        int minAbs = Math.abs(arr[idx = right / 2] - x);

        // 二分查找 最左边界
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (Math.abs(arr[mid] - x) <= minAbs) {
                minAbs = Math.abs(arr[mid] - x);
                idx = Math.min(mid, idx);
            }

            if (arr[mid] < x) {
                left = mid;
            } else if (arr[mid] >= x) {
                right = mid;
            } else {
                right--;
            }
        }
        System.out.println("idx=" + idx);


        return list;
    }

    public static void main(String[] args) {
//        System.out.println(findClosestElements0(new int[]{1, 2, 3, 4, 5}, 4, 3));
//        System.out.println(findClosestElements0(new int[]{1, 2, 4, 5}, 2, 3));
//        System.out.println(findClosestElements0(new int[]{1, 2, 4, 5}, 1, 3));

        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3));
        System.out.println(findClosestElements(new int[]{1, 2, 4, 5}, 2, 3));
        System.out.println(findClosestElements(new int[]{1, 2, 4, 5}, 1, 3));
        System.out.println(findClosestElements(new int[]{1, 2, 4, 5}, 1, 1));
        System.out.println(findClosestElements(new int[]{1, 2, 2, 2, 4, 5}, 1, 1));
        System.out.println(findClosestElements(new int[]{1, 2, 2, 2, 4, 5}, 1, 3));
        System.out.println(findClosestElements(new int[]{1, 2, 2, 2, 4, 5}, 1, 5));
    }


}
