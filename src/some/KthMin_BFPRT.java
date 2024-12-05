package some;

import java.util.Arrays;

/**
 * bfprt解决topK问题
 * 代码参考 https://blog.csdn.net/qq_42403295/article/details/89082304
 */
public class KthMin_BFPRT {


    /**
     * 三路快排的partition
     * 通过pivot，将arr划分成 <, =, >的三部分；
     * 返回 pivot组成的闭区间
     *
     * @param arr
     * @param left
     * @param right
     * @param pivot
     * @return
     */
    public static int[] partition(int[] arr, int left, int right, int pivot) {

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
                endIndex--;
            } else {
                current++;
            }
        }
        return new int[]{beginIndex, endIndex};
    }


    public static int medianOfMedians(int[] arr, int begin, int end) {

        if (end - begin + 1 < 5) {
            Arrays.sort(arr, begin, end + 1);
            return arr[(end - begin) / 2 + begin];
        }
        int num = end - begin + 1;
        //中位数组成的数组，只取能组成5份的
        int[] mArr = new int[num / 5];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + 5 * i;
            int endI = beginI + 5;
            Arrays.sort(arr, beginI, endI);
            mArr[i] = arr[beginI + 2];
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);//求中位数数组的中位数
    }


    /**
     * 查找数组，第k小的数
     *
     * @param arr
     * @param left
     * @param right
     * @param k
     * @return
     */
    public static int bfprt(int[] arr, int left, int right, int k) {
        if (left == right)
            return arr[left];

        int pivot = medianOfMedians(arr, left, right);//中位数组成的数组的中位数
        int[] pivotRange = partition(arr, left, right, pivot);//根据pivot来划分“小于区域”，“等于区域”，“大于区域”，返回“等于区域”的范围

        if (k >= pivotRange[0] && k <= pivotRange[1])
            return arr[k];//命中第i大的，直接返回第i大的数值
        else if (k < pivotRange[0])
            return bfprt(arr, left, pivotRange[0] - 1, k);
        else
            return bfprt(arr, pivotRange[1] + 1, right, k);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        int k = 10;
        System.out.println(bfprt(arr, 0, arr.length - 1, k - 1));

        System.out.println(Arrays.toString(partition(arr, 0, 9, 3)));


        arr = new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        k = 17;
        System.out.println(bfprt(arr, 0, arr.length - 1, k - 1));


    }

}
