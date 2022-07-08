package leetcode.array;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by lijianhua04 on 2020/3/22.
 */
public class DegreeOfanArray_697 {


    /**
     * 出现频率最高的，不止一个数
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray0(int[] nums) {

        int maxFrequency = -1;
        int maxFrequencyNum = -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int v = map.getOrDefault(num, 0) + 1;
            if (v > maxFrequency) {
                maxFrequency = v;
                maxFrequencyNum = num;
            }
            map.put(num, v);
        }

        int first = -1;
        int last = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == maxFrequencyNum) {
                first = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == maxFrequencyNum) {
                last = i;
                break;
            }
        }
        return last - first + 1;
    }


    class Node implements Comparable<Node> {
        int firstIndex;
        int lastIndex;
        int frequency;

        public Node(int firstIndex, int lastIndex, int frequency) {
            this.firstIndex = firstIndex;
            this.lastIndex = lastIndex;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node o) {
            return this.frequency - o.frequency;
        }
    }

    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Node node = map.get(nums[i]);
            if (node == null) {
                map.put(nums[i], new Node(i, i, 1));
            } else {
                node.lastIndex = i;
                node.frequency = node.frequency + 1;
            }
        }


        List<Map.Entry<Integer, Node>> collect = map.entrySet().stream()
                .sorted(Map.Entry.<Integer, Node>comparingByValue().reversed())
                .collect(Collectors.toList());

        Node value = collect.get(0).getValue();
        int maxFrequency = value.frequency;
        int shorest = value.lastIndex - value.firstIndex + 1;
        for (int i = 1; i < collect.size(); i++) {
            value = collect.get(1).getValue();
            if (value.frequency != maxFrequency) {
                break;
            }
            shorest = Math.min(shorest, value.lastIndex - value.firstIndex + 1);
        }

        return shorest;
    }


    /**
     * 三个map的解法 https://leetcode-cn.com/problems/degree-of-an-array/solution/shu-zu-de-du-by-leetcode/
     * 记录频率、firstIndex、lastIndex
     */

}