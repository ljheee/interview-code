package leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 按位相加
 * https://leetcode.com/problems/add-to-array-form-of-integer/
 * Created by lijianhua04 on 2020/2/23.
 */
public class AddtoArrayFormofInteger_989 {


    /**
     * case不过，是因为K比A数组长
     *
     * @param A
     * @param K
     * @return
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i : A) {
            list.add(i);
        }
        int k = K;

        //进位
        int carry = 0;

        for (int i = list.size() - 1; i >= 0; i--) {
            int last = list.get(i);

            int val = k % 10;
            k = k / 10;


            int newVal = last + val + carry;
            carry = newVal / 10;

            list.set(i, newVal % 10);
        }
        Iterator<Integer> iterator = list.iterator();
        if (iterator.next() == 0) {
            iterator.remove();
        }

        return list;
    }

    public List<Integer> addToArrayForm0(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        int carry = 0;
        int n = A.length;
        while (--n >= 0 || K > 0) {
            int val = 0;
            if (n >= 0) {
                val = A[n];
            }
            int newVal = val + carry + K % 10;

            K /= 10;
            carry = newVal / 10;
            list.add(newVal % 10);
        }

        Collections.reverse(list);
        return list;
    }

}
