package leetcode.array;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays_4 {


    /**
     * 合并数组
     * 但空间复杂度为O(m+n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays0(int[] nums1, int[] nums2) {
        int[] arr = merge(nums1, nums2);
        if (arr.length % 2 != 0) {
            return arr[arr.length / 2];
        }

        return (arr[arr.length / 2] + arr[arr.length / 2 - 1]) / 2d;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] temp = new int[nums1.length + nums2.length];

        int idx = 0;
        int left = 0;
        int right = 0;
        while (left < nums1.length || right < nums2.length) {

            if (left == nums1.length) {
                temp[idx++] = nums2[right];
                right++;
                continue;
            }
            if (right == nums2.length) {
                temp[idx++] = nums1[left];
                left++;
                continue;
            }
            if (nums1[left] <= nums2[right]) {
                temp[idx++] = nums1[left];
                left++;
            } else {
                temp[idx++] = nums2[right];
                right++;
            }
        }
        return temp;
    }


    /**
     * 其实，我们不需要将两个数组真的合并，我们只需要找到中位数在哪里就可以了。
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
     *
     * 空间复杂度O(1)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len = nums1.length + nums2.length;
        int mid = len / 2;

        int idx = 0;
        int left = 0;
        int right = 0;
        int cur = 0;
        int first = 0;// 如果len是偶数时，第一个值

        while (left < nums1.length || right < nums2.length) {

            if (left == nums1.length) {
                //temp[idx++] = nums2[right];
                cur = nums2[right];
                right++;
                idx++;

                if (idx - 1 == mid-1) {
                    first = cur;
                }else if (idx - 1 == mid ) {
                    if (len % 2 != 0) {
                        return cur;
                    }
                    return (first + cur) / 2d;
                }
                continue;
            }
            if (right == nums2.length) {
                //temp[idx++] = nums1[left];
                cur = nums1[left];
                left++;
                idx++;

                if (idx - 1 == mid-1) {
                    first = cur;
                }else if (idx - 1 == mid ) {
                    if (len % 2 != 0) {
                        return cur;
                    }
                    return (first + cur) / 2d;
                }
                continue;
            }
            if (nums1[left] <= nums2[right]) {
                //temp[idx++] = nums1[left];
                cur = nums1[left];
                left++;
                idx++;
            } else {
                //temp[idx++] = nums2[right];
                cur = nums2[right];
                right++;
                idx++;
            }

            if (idx - 1 == mid-1) {
                first = cur;
            }else if (idx - 1 == mid ) {
                if (len % 2 != 0) {
                    return cur;
                }
                return (first + cur) / 2d;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        double a = new MedianOfTwoSortedArrays_4().findMedianSortedArrays(new int[]{1, 2}, new int[]{3,4});
        System.out.println(a);
    }


}
