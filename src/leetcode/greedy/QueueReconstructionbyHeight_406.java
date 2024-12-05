package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 根据身高重建队列
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 * <p>
 * https://www.jianshu.com/p/e2612ff668fe
 * 1.排序：按照身高从高到低排，升高相同的按k从小到大排
 * 2.插入：按照排序好的顺序逐个插入新数组，插入的位置按照k来插
 * 每次resize队列
 * <p>
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 * <p>
 * <p>
 * Created by lijianhua04 on 2019/12/13.
 */
public class QueueReconstructionbyHeight_406 {

    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        LinkedList<int[]> linkedList = new LinkedList();
        for (int[] p : people) {
            linkedList.add(p[1], p);
        }

//        return linkedList.toArray(new int[people.length][]);
        return linkedList.toArray(people);// 可以直接转成数组
    }

    public static void main(String[] args) {

        System.out.println(reconstructQueue(new int[][]{new int[]{7, 0}, new int[]{4, 1}}));
    }
}
