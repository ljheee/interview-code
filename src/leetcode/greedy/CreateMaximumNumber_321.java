package leetcode.greedy;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by lijianhua04 on 2019/12/17.
 */
public class CreateMaximumNumber_321 {
    // TODO: 2019/12/22  未解决

    public static int[] maxNumber0(int[] nums1, int[] nums2, int k) {

        int[] res = new int[k];
        if (k == nums1.length + nums2.length) {

        }
        // k<nums1.length + nums2.length


        int p = 0;
        int q = 0;

        int fin = 0;

        while (k > 0) {
            int half = (k - 1) / 2;

            int max = 0;
            int idxM = -1;
            int from = -1;
            int i = p;
            int j = q;
            for (; i < nums1.length - half; i++) {
                if (nums1[i] > max) {
                    max = nums1[i];
                    idxM = i;
                    from = 1;
                }
            }

            for (; j < nums2.length - half; j++) {
                if (nums2[j] > max) {
                    max = nums2[j];
                    idxM = j;
                    from = 2;
                }
            }
            if (i < nums1.length && max == nums1[i] && from == 2 &&
                    k - 1 == nums1.length - i + nums2.length - j) {
                if (nums1[i + 1] > max) {
                    from = 1;
                }
            } else if (j < nums2.length && max == nums2[j] && from == 1 &&
                    k - 1 == nums1.length - i + nums2.length - j) {
                if (nums2[j + 1] > max) {
                    from = 2;
                }
            }

            // 只剩下3个时
            if (i < nums1.length &&
                    nums1[i] > max && half == 1 &&
                    from == 2 && k == 3) {
                res[fin++] = nums1[i];
                res[fin++] = max;
                res[fin++] = nums2[j];
//                for (int l = j; l < nums2.length &&fin<res.length; l++) {
//                }
                k = 0;
                break;
            } else if (j < nums2.length &&
                    nums2[j] > max && half == 1 &&
                    from == 1) {
                res[fin++] = nums2[j];
                res[fin++] = max;
                res[fin++] = nums1[i];
                k -= 3;
                break;
            }

            if (from == 1) {
                p = idxM + 1;
            } else {
                q = idxM + 1;
            }
            k--;
            res[fin++] = max;
        }


        return res;
    }


    /**
     * 首先分别求出nums1中长度为i的最大子序列，和nums2中长度为k-i的最大子序列，然后求它们归并起来的最大子序列。
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {

        if (k == nums1.length + nums2.length) {
            return merge(nums1, nums2);
        }
        int[] res = new int[k];

        int max = 0;
        int[] maxRes = null;
        for (int i = 0; i <= k; i++) {
            int[] temp1 = selectKdigits(nums1, i);
            int[] temp2 = selectKdigits(nums2, k - i);
            int[] tempMaxRes = merge(temp1, temp2);
            int s = 0;
            for (int i1 = 0; i1 < tempMaxRes.length; i1++) {
                s += tempMaxRes[i1] * Math.pow(10, tempMaxRes.length - i1 - 1);
            }
            if (s > max) {
                max = s;
                maxRes = tempMaxRes;
            }
        }
        return maxRes;
    }

    private static int[] merge(int[] leftArray, int[] rightArray) {
        int[] mergedArray = new int[leftArray.length + rightArray.length];
        int mergedArrayPos = 0;
        int leftArrayPos = 0;
        int rightArrayPos = 0;

        while (leftArrayPos < leftArray.length && rightArrayPos < rightArray.length) {

            if (leftArray[leftArrayPos] >= rightArray[rightArrayPos]) {
                if (leftArray[leftArrayPos] > rightArray[rightArrayPos]) {
                    mergedArray[mergedArrayPos] = leftArray[leftArrayPos];
                    leftArrayPos++;
                } else {
                    if ((rightArrayPos + 1 < rightArray.length && leftArrayPos == leftArray.length - 1) ||
                            (rightArrayPos + 1 < rightArray.length &&
                                    leftArrayPos + 1 < leftArray.length &&
                                    leftArray[leftArrayPos + 1] < rightArray[rightArrayPos + 1])) {
                        mergedArray[mergedArrayPos] = rightArray[rightArrayPos];
                        rightArrayPos++;
                    } else {
                        mergedArray[mergedArrayPos] = leftArray[leftArrayPos];
                        leftArrayPos++;
                    }
                }
            } else {
                mergedArray[mergedArrayPos] = rightArray[rightArrayPos];
                rightArrayPos++;
            }
            mergedArrayPos++;
        }

        //如果 还有剩余的
        while (leftArrayPos < leftArray.length) {
            mergedArray[mergedArrayPos] = leftArray[leftArrayPos];
            leftArrayPos++;
            mergedArrayPos++;
        }

        while (rightArrayPos < rightArray.length) {
            mergedArray[mergedArrayPos] = rightArray[rightArrayPos];
            rightArrayPos++;
            mergedArrayPos++;
        }
        return mergedArray;
    }

    // 选出k个使结果最大
    private static int[] selectKdigits(int[] nums, int k) {
        if (k >= nums.length) {
            return nums;
        }
        if (k == 0) {
            return new int[]{};
        }

        int[] ans = new int[k];
        int p = 0;
        int maxIdx = -1;
        while (k > 0) {
            int max = 0;

            for (int i = maxIdx + 1; i < nums.length - k + 1; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                    maxIdx = i;
                }
            }
            ans[p++] = max;
            k--;
        }
        return ans;
    }

    public static void main0(String[] args) {
        System.out.println(Arrays.toString(maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5)));//[9, 8, 6, 5, 3]
        System.out.println(Arrays.toString(maxNumber(new int[]{3, 9}, new int[]{8, 9}, 3)));//[9, 8, 9]
        System.out.println(Arrays.toString(maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5)));//[6, 7, 6, 0, 4]
        System.out.println(Arrays.toString(maxNumber(new int[]{7, 6, 1, 9, 3, 2, 3, 1, 1}, new int[]{4, 0, 9, 9, 0, 5, 5, 4, 7}, 9)));//[6, 7, 6, 0, 4]
        System.out.println(Arrays.toString(maxNumber(new int[]{2, 5, 6, 4, 4, 0}, new int[]{7, 3, 8, 0, 6, 5, 7, 6, 2}, 15)));//[6, 7, 6, 0, 4]


        System.out.println(Arrays.toString(selectKdigits(new int[]{6, 0, 4}, 2)));
        System.out.println(Arrays.toString(selectKdigits(new int[]{3, 9}, 1)));
        System.out.println(Arrays.toString(selectKdigits(new int[]{2, 88}, 1)));
        System.out.println(Arrays.toString(selectKdigits(new int[]{9, 1, 2, 5, 8, 3}, 4)));
    }




    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxNumber0(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5)));//[9, 8, 6, 5, 3]
        System.out.println(Arrays.toString(maxNumber0(new int[]{3, 9}, new int[]{8, 9}, 3)));//[9, 8, 9]
        System.out.println(Arrays.toString(maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5)));//[6, 7, 6, 0, 4]


        System.out.println(Arrays.toString(maxNumber(new int[]{2, 5, 6, 4, 4, 0}, new int[]{7, 3, 8, 0, 6, 5, 7, 6, 2}, 15)));//
    }
}
