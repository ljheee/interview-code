package some;

import java.util.Arrays;

/**
 * 三色 数字排序
 * 荷兰国旗问题 https://www.cnblogs.com/gnuhpc/archive/2012/12/21/2828166.html
 * ===将前部和后部各排在数组的前边和后边，中部自然就排好了。
 * <p>
 * <p>
 * https://leetcode.com/problems/sort-colors/submissions/
 */
public class SortColors {
    class Solution {
        public void sortColors(int[] nums) {
            int red = 0;
            int white = 0;
            int blue = 0;

            for (int i = 0; i < nums.length; i++) {
                switch (nums[i]) {
                    case 0:
                        red++;
                        break;
                    case 1:
                        white++;
                        break;
                    case 2:
                        blue++;
                        break;
                }
            }
            int i = 0;
            for (; i < red; i++) {
                nums[i] = 0;
            }
            for (; i < white + red; i++) {
                nums[i] = 1;
            }
            for (; i < blue + white + red; i++) {
                nums[i] = 2;
            }
        }
    }


    public static void sortColors(int[] nums) {

        int beginIndex = 0;//前部分：0~beginIndex 都是存放1的区间
        int endIndex = nums.length - 1;// 后部分：endIndex~(len-1) 都是存放2的区间

        int currentIndex = 0;// 活动指针

        while (currentIndex <= endIndex) {
            if (nums[currentIndex] == 0) {
                // 0 往前移
                int temp = nums[currentIndex];
                nums[currentIndex] = nums[beginIndex];
                nums[beginIndex] = temp;
                currentIndex++;
                beginIndex++;
            } else if (nums[currentIndex] == 2) {
                // 2 往后移
                int temp = nums[currentIndex];
                nums[currentIndex] = nums[endIndex];
                nums[endIndex] = temp;
                endIndex--;
                continue;
            } else {
                // nums[currentIndex] == 1; 1留位不变
                currentIndex++;
            }
        }

    }


    /**
     * 排序结果：序号为偶数的，一定比相邻两边的大
     * https://leetcode.com/problems/wiggle-sort-ii/submissions/
     * 排序后 1 2 3 4 5 6
     * 切分
     * 1 2 3
     * 4 5 6
     * 1 4 2 5 3 6
     * <p>
     * 两两成对，倒序：3 6 2 5 1 4 也满足，能满足所有case
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {

        int length = nums.length;
        int[] clone = nums.clone();
        Arrays.sort(clone);

        int mid = length / 2;
        if (2 * mid < length) {
            mid++;
        }
        int count = mid;
        int k = 0;
        // 0~mid 每个元素后面按序跟一个较大值
//        for (int i = 0; i < count; i++) {
//            nums[k++] =clone[i];
//            if(mid<length){
//                nums[k++] = clone[mid++];
//            }
//        }

        // 上面注释的：反向、从后往前
        length--;
        for (int i = count - 1; i >= 0; i--) {
            nums[k++] = clone[i];
            if (length >= mid) {
                nums[k++] = clone[length--];
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }

}
