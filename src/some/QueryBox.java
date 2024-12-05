package some;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 范围查询
 * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)。
 * 意思是在数组里下标0~3这个范围上，有几个2? 返回2。
 */
public class QueryBox {


    public static int queryBox(int[] arr, int from, int to, int num) {
        int cnt = 0;

        for (int i = from; i <= to; i++) {
            if (arr[i] == num) cnt++;
        }
        return cnt;
    }


    public static int queryBox1(int[] arr, int from, int to, int num) {

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> indexList = map.getOrDefault(arr[i], new ArrayList<>());
            indexList.add(i);
        }

        ArrayList<Integer> indexList = map.get(num);
        if (indexList == null) return 0;

        int a = countLess(indexList, from);
        int b = countLess(indexList, to + 1);
        return b - a;
    }

    /**
     * 有序数组，找出小于target的个数
     * 二分
     *
     * @param indexList
     * @param target
     * @return
     */
    private static int countLess(ArrayList<Integer> indexList, int target) {

        int left = 0;
        int right = indexList.size() - 1;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;//计算mid要加1

            if (indexList.get(mid) < target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();


        arrayList.add(0);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);

        System.out.println(countLess(arrayList, 2));
        System.out.println(countLess(arrayList, 7));
    }


}
