package leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/next-greater-element-iii/
 * <p>
 * <p>
 * 多个digits 组成的数=========
 * -如果依次递增，一定存在；
 * -如果依次递减，一定不存在；
 * -如果是乱序，忽略firstDigit，后面的按 greater smallest smallest；
 * <p>
 * 1、如果依次递增，一定存在；
 * greater smallest smallest
 * 123
 * 213
 * <p>
 * 1122334
 * 1122343 =交换最后两个
 * <p>
 * 1223344
 * 1223434 =交换 最后两个不同的digits
 * <p>
 * <p>
 * 3、如果是乱序
 * 从后往前 找出array[i] 在其后面的 最小greater
 * 312
 * 321
 * <p>
 * 213
 * 132
 * <p>
 * 230241
 * 230412
 * <p>
 * 12443322
 */
public class NextGreaterElementsIII {


    public static int nextGreaterElement(int n) {

        if (n <= 11) {
            return -1;
        }

        char[] chars = String.valueOf(n).toCharArray();

        // 遍历一遍，得出：递增、递减、还是乱序
        boolean incr = false;
        boolean desc = false;
        for (int i = 1; i < chars.length; i++) {
            int diff = chars[i] - chars[i - 1];
            if (diff > 0) {
                if (chars[i] - chars[i - 1] > 0) {
                    diff = chars[i] - chars[i - 1];
                    incr = true;
                }
            }
            if (diff < 0) {
                if (chars[i] - chars[i - 1] < 0) {
                    diff = chars[i] - chars[i - 1];
                    desc = true;
                }
            }
        }


        // 乱序
        if (incr && desc) {

            int current = -1;
            int greaterIndex = -1;
            for (int i = chars.length - 2; i >= 0; i--) {// 从后往前找，把后面的greater 交换到前面
                current = i;

                // 从最后开始找，找到 chars[i] 的 greater
                int cha = Integer.MAX_VALUE;
                for (int j = chars.length - 1; j > i; j--) {
                    if (chars[j] == chars[i]) {
                        continue;
                    } else {
                        int tempCha = chars[j] - chars[i];
                        if (tempCha > 0 && tempCha < cha) {
                            cha = tempCha;
                            greaterIndex = j;
                        }
                    }
                }
                if (greaterIndex == -1) {
                    continue;
                } else {
                    break;
                }
            }
            System.out.println("" + current + greaterIndex);
            swap(chars, current, greaterIndex);
            Arrays.sort(chars, current + 1, chars.length);
            long res = Long.parseLong(new String(chars));
            return res > Integer.MAX_VALUE ? -1 : (int) res;// 超出MAX_VALUE 返回-1
        }

        //递增
        if (incr) {
            // 从最后开始找，交换最后两个不同的digits
            int firstIndexOfLastChar = -1;
            int lastIndexOfSecondChar = -1;
            for (int i = chars.length - 1; i > 0; i--) {
                if (chars[i - 1] == chars[i]) {
                    firstIndexOfLastChar = i - 1;
                    continue;
                } else {
                    firstIndexOfLastChar = i;
                    lastIndexOfSecondChar = i - 1;
                    break;
                }
            }
            swap(chars, firstIndexOfLastChar, lastIndexOfSecondChar);
            long res = Long.parseLong(new String(chars));
            return res > Integer.MAX_VALUE ? -1 : (int) res;
        }

        if (desc) {
            return -1;
        }
        return -1;
    }

    private static void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    public static void main(String[] args) {
        System.out.println(nextGreaterElement(1999999999));
    }
}
