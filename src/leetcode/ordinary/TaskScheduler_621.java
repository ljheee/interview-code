package leetcode.ordinary;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 任务调度
 * 任务数组，通过重排后，使相同任务之间间隔n个时间片，任务不够的就用 idle 空闲时间
 * 最终任务执行时间：执行任务消耗的时间片+idle时间片
 * <p>
 * https://leetcode.com/problems/task-scheduler/submissions/
 */
public class TaskScheduler_621 {

    /**
     * 自己的思路，
     * 按频率排序，先处理出现次数最多的，
     * 初始化列表，把相同的任务依次插入间隔n的位置
     * 难点：不是每个元素都从新的idx 开始。
     *
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval0(char[] tasks, int n) {

        if (n == 0) {
            return tasks.length;
        }
        List<Character> list = new ArrayList<>(Collections.nCopies((tasks.length - 1) * (n + 1) + 1, new Character('r')));

        // use counterMap to mark each element's frequence
        HashMap<Character, Integer> counterMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            counterMap.put(tasks[i], counterMap.getOrDefault(tasks[i], 0) + 1);
        }

        AtomicInteger q = new AtomicInteger();
        // comparingByValue for hashmap
        counterMap.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEachOrdered(e -> tasks[q.getAndIncrement()] = e.getKey());

        int idx = -1;
        for (Map.Entry<Character, Integer> entry : counterMap.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();

            idx += 1;
            int tempIdx = idx;
            list.set(tempIdx, key);
            for (int i = 1; i < value; i++) {
                list.set(tempIdx += (n + 1), key);
            }

        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) != 'r') {
                return i + 1;
            }
        }

        return 0;
    }

    /**
     * 通过推断发现：最终任务序列，被分成mx-1 块，每块都是n+1个槽位。
     * https://www.cnblogs.com/grandyang/p/7098764.html
     * <p>
     * <p>
     * 最终的推导公式：（最多任务数-1）*（n + 1） + （相同最多任务的任务个数）
     * https://blog.csdn.net/Koala_Tree/article/details/78498586
     *
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval(char[] tasks, int n) {

        if (n == 0) {
            return tasks.length;
        }

        // use counterMap to mark each element's frequence
        HashMap<Character, Integer> counterMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            counterMap.put(tasks[i], counterMap.getOrDefault(tasks[i], 0) + 1);
        }

        AtomicInteger q = new AtomicInteger();
        // comparingByValue for hashmap
        counterMap.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEachOrdered(e -> tasks[q.getAndIncrement()] = e.getKey());

        int valueEqualCount = 0;
        Integer maxFrequency = counterMap.get(tasks[0]);
        for (Map.Entry<Character, Integer> entry : counterMap.entrySet()) {
            Integer value = entry.getValue();
            if (maxFrequency.intValue() == value.intValue()) {
                valueEqualCount++;
            }
        }
        return Math.max(tasks.length, (maxFrequency - 1) * (n + 1) + valueEqualCount);
    }

    public static void main(String[] args) {
        leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2);
    }
}
