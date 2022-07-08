package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局倒置 与 局部倒置
 * <p>
 * <p>
 * 全局倒置：0<= i < j < N and A[i] > A[j].
 * 局部倒置：0 <= i < N and A[i] > A[i+1].
 * <p>
 * 关键是如何高效找出 全局倒置的个数
 * https://leetcode.com/problems/global-and-local-inversions/
 */
public class GlobalAndLocalInversions_775 {

    public boolean isIdealPermutation0(int[] A) {
        int global = 0;
        int local = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > i) {
                // 这样计算 全局倒置，有问题，case [2,0,1] 不能过
                global++;
            }
            if (i < A.length - 1 && A[i] > A[i + 1]) {
                local++;
            }
        }
        return global == local;
    }

    /*
    // [2,1,0] case不过
    public boolean isIdealPermutation(int[] A) {
        int global = 0;
        int local = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > i) {
                global += (A[i] - i);
            }

            if (i < A.length - 1 && A[i] > A[i + 1]) {
                local++;
            }
        }
        return global == local;
    }
    */


    /**
     * ""
     *
     * @param A
     * @return
     */
    public boolean isIdealPermutation1(int[] A) {
        int global = 0;
        int local = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > i) {
                global += (A[i] - i);
            }
            if (global != 0) {
                global += A[i];
            }
            if (i < A.length - 1 && A[i] > A[i + 1]) {
                local++;
            }
        }
        return global == local;
    }


    public boolean isIdealPermutation(int[] A) {

        int global = 0;
        int local = 0;
        for (int i = 0; i < A.length; i++) {
            if (i < A.length - 1 && A[i] > A[i + 1]) {
                local++;
            }
        }

        // 暴力超时
        global = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    global++;
                }
            }
        }
        return global == local;
    }

    /**
     * AC
     * 如果想让全局倒置等于局部倒置，只能是数组中相邻元素进行交换，判断这个规则即可
     * https://leetcode-cn.com/problems/global-and-local-inversions/solution/jian-dan-ni-xu-dui-pan-duan-by-amchor/
     *
     * @param A
     * @return
     */
    public boolean isIdealPermutation2(int[] A) {

        for (int i = 0; i < A.length; ) {
            if (A[i] != i) {
                if (A[i] == i + 1 && A[i + 1] == i) {
                    i += 2;
                    continue;
                } else {
                    return false;
                }
            }
            i++;
        }

        return true;
    }


    public static void main(String[] args) {
//        new GlobalAndLocalInversions_775().isIdealPermutation1(new int[]{2,0,1});
//        new GlobalAndLocalInversions_775().isIdealPermutation1(new int[]{2,1,0});
        new GlobalAndLocalInversions_775().isIdealPermutation1(new int[]{1, 0, 2});
    }
}
