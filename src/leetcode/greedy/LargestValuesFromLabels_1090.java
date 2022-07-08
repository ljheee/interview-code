package leetcode.greedy;

import java.util.*;

/**
 * 输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * values[i]对应labels[i]
 * 选出num_wanted个当作子集，且子集中同一个label不能超过use_limit个；
 * 要求：返回结果和最大；
 * <p>
 * value倒序排序后，尽可能选择更大的value，且满足use_limit条件
 */
public class LargestValuesFromLabels_1090 {

    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {

        List<Node> list = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            list.add(new Node(values[i], labels[i]));
        }
        // 按value 倒序排序
        Collections.sort(list);

        Iterator<Node> iterator = list.iterator();
        int prevLabel = -1;
        int count = 1;
        HashMap<Integer, Integer> valueUsedFrequencyMap = new HashMap<>();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.label == prevLabel) {
                count++;
                if (count > use_limit || valueUsedFrequencyMap.get(next.label) == use_limit) {
                    iterator.remove();
                    count--;
                } else {
                    valueUsedFrequencyMap.put(next.label, valueUsedFrequencyMap.get(next.label) + 1);
                }
            } else {

                if (valueUsedFrequencyMap.get(next.label) == null) {
                    valueUsedFrequencyMap.put(next.label, 1);
                    prevLabel = next.label;
                    count = 1;
                } else if (valueUsedFrequencyMap.get(next.label) + 1 <= use_limit) {
                    valueUsedFrequencyMap.put(next.label, valueUsedFrequencyMap.get(next.label) + 1);
                    prevLabel = next.label;
                    count = 1;
                } else {
                    iterator.remove();
                    count = 1;
                }
            }
        }


        // 输出结果
        int res = 0;
        for (int i = 0; i < list.size() && num_wanted > 0; i++) {
            Node node = list.get(i);
            res += node.value;
            num_wanted--;
        }

        return res;
    }

    public int largestValsFromLabels1(int[] values, int[] labels, int num_wanted, int use_limit) {

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            list.add(new int[]{values[i], labels[i]});
        }
        // 按value 倒序排序
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        HashMap<Integer, Integer> valueUsedFrequencyMap = new HashMap<>();


        // 输出结果
        int res = 0;
        for (int i = 0; i < list.size() && num_wanted > 0; i++) {
            int[] pair = list.get(i);

            int usedCount = valueUsedFrequencyMap.getOrDefault(pair[0], 0);
            if (usedCount + 1 > use_limit) {
                continue;
            }
            res += pair[1];
            num_wanted--;
        }

        return res;
    }

    class Node implements Comparable<Node> {
        int value;
        int label;

        @Override
        public int compareTo(Node o) {
            return o.value - this.value;
        }

        public Node(int value, int label) {
            this.value = value;
            this.label = label;
        }
    }

    public static void main(String[] args) {
//        int i = new LargestValuesFromLabels_1090().largestValsFromLabels(new int[]{2, 6, 3, 6, 5}, new int[]{1, 1, 2, 1, 1}, 3, 1);
//        int i = new LargestValuesFromLabels_1090().largestValsFromLabels(new int[]{4, 9, 1, 1, 2}, new int[]{2, 2, 1, 2, 2}, 4, 2);
        int i = new LargestValuesFromLabels_1090().largestValsFromLabels(new int[]{50, 85, 4, 18, 29, 72, 65, 1, 54, 22, 31, 12, 85}, new int[]{5, 5, 1, 0, 2, 0, 5, 2, 5, 2, 5, 5, 4}, 5, 2);
        System.out.println(i);

    }
}
