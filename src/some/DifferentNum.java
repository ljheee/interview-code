package some;

import leetcode.ArrayGenerator;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://blog.csdn.net/qq_28468707/article/details/103672590
 * 一个有序数组， 其中值可能为正、负、0，返回arr中每个数都平方之后不同的结果有多少种?
 */
public class DifferentNum {
    // -3,-3,-1,0,3,5
    // 2,3,3,5
    public static int diff_bf(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i * i);
        }
        return set.size();
    }

    /**
     * 自己是思路实现 AC
     * 前后两个指针，绝对值相等时，值能添加1；否则 绝对值较大的一侧进行移动，每次移动移动一坨(略过所有相等的值)
     *
     * @param arr
     * @return
     */
    public static int diff(int[] arr) {
        int ans = 0;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            if (left == right) {
                ans++;
                break;
            }
            if (arr[left] < 0) {
                if (-arr[left] == arr[right]) {
                    ans++;
                    int leftValue = arr[left];
                    while (arr[left] == leftValue) left++;

                    int rightValue = arr[right];
                    while (arr[right] == rightValue) right--;
                } else {
                    ans++;
                    if ((-arr[left]) <= arr[right]) {
                        int rightValue = arr[right];
                        while (arr[right] == rightValue) right--;
                    } else {
                        int leftValue = arr[left];
                        while (arr[left] == leftValue) left++;
                    }
                }
            } else {
                ans += 1;
                if (arr[left] != arr[right]) ans++;
                int leftValue = arr[left];
                while (arr[left] == leftValue) left++;

                int rightValue = arr[right];
                while (arr[right] == rightValue) right--;
            }
        }
        return ans;
    }

    /**
     * -3,-3,-2,-1,0,3,5
     * 双指针解法：绝对值较大的一侧移动，每次移动移动一坨
     *
     * @param arr
     * @return
     */
    public static int diff2(int[] arr) {
        int ans = 0;
        int left = 0;
        int length = arr.length;
        int right = length - 1;
        int leftAbs;
        int rightAbs;
        while (left <= right) {

            leftAbs = Math.abs(arr[left]);
            rightAbs = Math.abs(arr[right]);
            if (leftAbs < rightAbs) {
                while (right >= 0 && Math.abs(arr[right]) == rightAbs) right--;
                ans++;
            } else if (leftAbs > rightAbs) {
                while (left < length && Math.abs(arr[left]) == leftAbs) left++;
                ans++;
            } else {
                while (right >= 0 && Math.abs(arr[right]) == rightAbs) right--;
                while (left < length && Math.abs(arr[left]) == leftAbs) left++;
                ans++;
            }
        }
        return ans;

    }


    public static void main(String[] args) {
//        System.out.println(diff2(new int[]{-3, -3, -2, -1, 0, 3, 5}));
//        int[] arr = ArrayGenerator.randomSortedArray(10, -10, 10);
        int[] arr = new int[]{-9, 1, 6, 6, 8, 18, 22, 23, 23, 25};
        System.out.println(Arrays.toString(arr));
        System.out.println(diff(arr));
        System.out.println(diff2(arr));


        for (int i = 0; i < 10000; i++) {
            int[] arr1 = ArrayGenerator.randomSortedArray(100, -10, 30);
            if (diff(arr1) != diff2(arr1)) {
                System.out.println(Arrays.toString(arr1));
                System.out.println(diff_bf(arr1) + "=" + diff2(arr1) + "=" + diff(arr1));
                break;
            }
        }
    }
}
