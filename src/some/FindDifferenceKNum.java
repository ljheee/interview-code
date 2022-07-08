package some;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 头条笔试
 * 在n个元素的数组中，找到值为k的数字对去重后的个数
 * https://www.jianshu.com/p/00d3fd1d9e23
 */
public class FindDifferenceKNum {

    /**
     * 差值
     * @param arr
     * @param k
     * @return
     */
    public static int solve(int arr[], int k) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            linkedList.addLast(arr[i]);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        while (linkedList.size() > 1) {
            Integer first = linkedList.removeFirst();

            for (Integer i : linkedList) {
                if (first - i == k || i - first == k) {
                    if (first >= i) {
                        map.put(first, i);
                    } else {
                        map.put(i, first);
                    }
                    linkedList.remove(i);
                    break;
                }
            }
        }
        return map.size();
    }


    public static void main(String[] args) {

        System.out.println(solve(new int[]{1, 3, 2, 4, 1}, 2));//2
        System.out.println(solve(new int[]{1, 3, 2, 4, 1, 3}, 2));//2
        System.out.println(solve(new int[]{1, 5, 3, 4, 2}, 2));//2
        System.out.println(solve(new int[]{1, 5, 3, 3, 4, 2}, 2));//3
        System.out.println(solve(new int[]{1, 1, 1, 1}, 0));//0
    }


}
