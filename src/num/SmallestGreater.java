package num;

import java.util.Arrays;

/**
 * 给定数组
 * 找出 元素 i 后面的，最小greater
 *
 * 重点：
 * 1、只找 array[i]后面的
 * 2、最小的 greater
 */
public class SmallestGreater {


    public static int smallestGreater(int[] array) {

        int[] result = new int[array.length];

        for (int i = 0; i < array.length; i++) {

            result[i] = -1;

            // 找array[i] 的smallestGreater
            // 从后往前找，找到 i的位置
            int theGreater = -1;
            int cha = Integer.MAX_VALUE;
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] == array[i]) {
                    continue;
                } else {
                    int tempCha = array[j] - array[i];
                    if (tempCha > 0 && tempCha < cha) {
                        cha = tempCha;
                        theGreater = j;
                        result[i] = array[theGreater];
                    }
                }
            }

        }

        System.out.println(Arrays.toString(result));

        return 0;

    }


    public static void main(String[] args) {
        int[] a = {1, 2, 4, 4, 3, 3, 2, 2};

        System.out.println(smallestGreater(a)); // [2, 3, -1, -1, -1, -1, -1, -1]

        System.out.println(smallestGreater(new int[]{1,2,3,1,4,4,2}));

    }


}
