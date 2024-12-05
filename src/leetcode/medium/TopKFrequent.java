package leetcode.medium;

import java.util.*;

/**
 * Created by lijianhua04 on 2019/11/19.
 */
public class TopKFrequent {

    /**
     * 返回数组中 出现频率最高的k个数
     * https://leetcode.com/problems/top-k-frequent-elements/
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();

        // use counterMap to mark each element's frequence
        HashMap<Integer, Integer> counterMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer frequence = counterMap.get(nums[i]);
            frequence = frequence == null ? 1 : frequence + 1;
            counterMap.put(nums[i], frequence);
        }

        // comparingByValue for hashmap
        counterMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .forEachOrdered(e -> list.add(e.getKey()));

        return list.subList(0, k);
    }


    /**
     * 使用桶排序[思想]，进行计数
     * <p>
     * https://leetcode-cn.com/problems/top-k-frequent-elements/solution/li-yong-hashmaptong-ji-mei-ge-shu-chu-xian-ci-shu-/
     * <p>
     * 使用桶下标，表示出现的次数，桶内元素为出现该次数的elements；
     * 能使用桶计数的关键： k ≤ number of unique elements.
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent0(int[] nums, int k) {
        List<Integer> res = new ArrayList();
        // key为值 value为出现次数
        HashMap<Integer, Integer> map = new HashMap();
        // 统计每个数出现的次数
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        // list数组中每一个位置为这个数出现的次数 值为出现次数这么多的数
        List<Integer>[] list = new List[nums.length + 1];
        for (int key : map.keySet()) {
            // 出现的次数
            int i = map.get(key);
            if (list[i] == null) list[i] = new ArrayList();
            // 当前次数中 包含那些数
            list[i].add(key);
        }
        for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
            if (list[i] == null) continue;
            // 该题没有出现频率相同的数字 不需要考虑其他情况
            res.addAll(list[i]);
        }
        return res;
    }


    /**
     * 出现频率最高的K个字符串
     * https://leetcode.com/problems/top-k-frequent-words/submissions/
     *
     * @param words
     * @param k
     * @return
     */
    public static List<String> topKFrequent(String[] words, int k) {
        List<String> list = new ArrayList<>();
        // key为值 value为出现次数
        HashMap<String, Integer> map = new LinkedHashMap();
        // 统计每个数出现的次数
        for (String i : words) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<String>[] bucket = new List[words.length + 1];

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (bucket[entry.getValue()] == null) {
                bucket[entry.getValue()] = new ArrayList<>();
            }
            bucket[entry.getValue()].add(entry.getKey());
        }

        for (int i = bucket.length - 1; i > 0 && list.size() < k; i--) {
            if (bucket[i] != null) {
                Collections.sort(bucket[i], String::compareTo);
                for (String s : bucket[i]) {
                    if (list.size() < k) {
                        list.add(s);
                    } else {
                        break;
                    }
                }
            }
        }

        return list;
    }

    /**
     * 离原点 最近的k个点
     * https://leetcode.com/problems/k-closest-points-to-origin/
     * <p>
     * 解法二：分治法，类似于快速选择，每次丢弃一半数据，返回的k个最小的也不需要有序；
     * https://leetcode-cn.com/problems/k-closest-points-to-origin/solution/zui-jie-jin-yuan-dian-de-k-ge-dian-by-leetcode/
     * <p>
     * https://www.jianshu.com/p/94186c129ee6
     * https://juejin.im/post/5dd25ce4f265da0bc6421b9e
     *
     * @param points
     * @param K
     * @return
     */
    public static int[][] kClosest(int[][] points, int K) {

        int[][] kcloest = new int[K][2];

        int row = points.length;

        // 维护容量为k的最大堆 (最后输出顺序从大到小，留下的就是最小的K个)
        PriorityQueue<Integer[]> queue = new PriorityQueue<>(K, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                int val1 = o1[0] * o1[0] + o1[1] * o1[1];
                int val2 = o2[0] * o2[0] + o2[1] * o2[1];
                return val2 - val1;
            }
        });

        for (int i = 0; i < row; i++) {
            int[] xy = points[i];
            int val = xy[0] * xy[0] + xy[1] * xy[1];
            System.out.println(val);

            if (queue.size() == K) {
                queue.add(new Integer[]{xy[0], xy[1]});
                queue.poll();
            } else {
                queue.add(new Integer[]{xy[0], xy[1]});
            }
        }

        int j = 0;
        while (!queue.isEmpty()) {
            Integer[] integers = queue.poll();
            kcloest[j++] = new int[]{integers[0], integers[1]};
        }
        return kcloest;
    }


    /**
     * 按 字符频率 排序
     * 标准的桶排序
     * https://leetcode.com/problems/sort-characters-by-frequency/
     * <p>
     * https://leetcode-cn.com/problems/sort-characters-by-frequency/solution/fu-za-du-wei-onde-tong-pai-xu-de-javashi-xian-by-l/
     *
     * @param s
     * @return
     */
    public static String frequencySort(String s) {
        char[] chars = s.toCharArray();

        HashMap<Character, Integer> countMap = new HashMap<>();
        for (Character c : chars) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        // 存放到各自频率的桶中
        List<Character>[] bucket = new List[chars.length + 1];

        for (char c : countMap.keySet()) {
            Integer frequence = countMap.get(c);
            if (bucket[frequence] == null) {
                bucket[frequence] = new ArrayList<>();
            }
            bucket[frequence].add(c);
        }

        // 倒着遍历桶
        int p = 0;
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                // bucket[i]里的元素，都是出现i次的，每个元素追加i个
                for (char c : bucket[i]) {
                    for (int j = 0; j < i; j++) {
                        chars[p++] = c;
                    }
                }
            }
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        int[][] ints = kClosest(new int[][]{{1, 2}, {2, 2}}, 1);
        System.out.println(Arrays.toString(ints));

        String[] ss = new String[]{"i", "love", "leetcode", "i", "love", "coding"};

        topKFrequent(ss, 3);
        frequencySort("tree");
    }

}
