package leetcode.greedy;

import java.util.*;
import java.util.stream.Stream;

/**
 * 贪心思想：无论能否找到全局最优解，先找到 当前最优的。
 * <p>
 * <p>
 * 切分子序列，并不需要实实在在的容器去存储
 * https://www.jianshu.com/p/b910737af4dc
 */
public class SplitArrayIntoConsecutiveSubsequences_659 {

    public boolean isPossible(int[] nums) {
        List<List<Integer>> listList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            ListIterator<List<Integer>> listIterator = listList.listIterator();
            boolean needAddNewChain = true;
            while (listIterator.hasNext()) {
                List<Integer> next = listIterator.next();
                if (nums[i] - next.get(next.size() - 1) == 1) {
                    next.add(nums[i]);
                    needAddNewChain = false;
                    break;
                } else {
                    continue;
                }
            }

            //add one
            if (needAddNewChain) {
                List<Integer> enrty = new ArrayList<>();
                enrty.add(nums[i]);
                listIterator.add(enrty);
            }
        }

        for (List<Integer> entry : listList) {
            if (entry.size() < 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * 贪心思想：无论能否找到全局最优解，先找到 当前最优的。
     * <p>
     * <p>
     * 此题当前最优的：
     * 1.当前chain 长度小于3，且满足，直接放当前chain；
     * 2.当前chain 长度>3，且满足自己，后继有人优先考虑后继 ——可能后继不满足递增，用idx记录当前索引，用于补救；
     * 若后继无人，直接放自己；
     * 3.当前chain和后继 都不满足递增，就开启新chain
     * <p>
     * ==再进一步抽象：
     * 当前最优=把当前num[i] 尽可能追加到最短的链上，如果已有的所有链都不满足，再去开辟新chain；
     * 该提炼源于：https://www.jianshu.com/p/8b2d0e2f1931
     *
     * @param nums
     * @return
     */
    public boolean isPossible0(int[] nums) {
        List<List<Integer>> listList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            boolean needAddNewChain = true;
            int idx = -1;
            ListIterator<List<Integer>> listIterator = listList.listIterator();
            for (int j = 0; listIterator.hasNext(); j++) {
                List<Integer> next = listIterator.next();
                if (next.size() < 3 && nums[i] - next.get(next.size() - 1) == 1) {
                    next.add(nums[i]);
                    needAddNewChain = false;
                    break;
                } else if (listIterator.hasNext() && nums[i] - next.get(next.size() - 1) == 1) {
                    // 当前chain.size>=3,且后继有人，优先给后者
                    idx = j;
                    continue;
                } else if (nums[i] - next.get(next.size() - 1) == 1) {
                    // 当前chain.size>=3,且后继wu人，还是优先放到当前链
                    next.add(nums[i]);
                    needAddNewChain = false;
                    break;
                } else {
                    continue;
                }
            }

            // nums[i]没能被 后继者接受
            if (idx != -1 && needAddNewChain) {
                listList.get(idx).add(nums[i]);
                continue;
            }

            //add one
            if (needAddNewChain) {
                List<Integer> enrty = new ArrayList<>();
                enrty.add(nums[i]);
                listIterator.add(enrty);
            }
        }

        for (List<Integer> entry : listList) {
            if (entry.size() < 3) {
                return false;
            }
        }
        return true;
    }


    /**
     * 切分成k段，每段都是连续子数组
     * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/submissions/
     * <p>
     * <p>
     * 1296.切分成若干段，每段都是连续递增的k个数
     * https://leetcode-cn.com/problems/divide-array-in-sets-of-k-consecutive-numbers/solution/bao-bao-ye-neng-kan-dong-de-leetcode-ti-jie-greedy/
     * <p>
     * 判断nums[start] 后续的k-1个连续数的 出现次数，是否小于nums[start]的
     * <p>
     * <p>
     * 使用优先级队列，找出连续地址的k个数
     * https://leetcode-cn.com/problems/divide-array-in-sets-of-k-consecutive-numbers/solution/you-xian-dui-lie-by-liweiwei1419-2/
     * <p>
     * 相似题 https://leetcode-cn.com/problems/hand-of-straights/
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean isPossibleDivide(int[] nums, int k) {

        if (nums.length % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            int start = num;
            int count = map.get(num);
            if (count == 0) continue;

            for (int i = 1; i < k; ++i) {
                Integer c = map.get(start + i);
                if (c == null || c < count) {
                    return false;
                }
                map.put(start + i, c - count);
            }
            map.put(start, 0);
        }
        return true;
    }

    public static void main(String[] args) {

        // ArrayList实现遍历时添加元素
        ArrayList<String> list = new ArrayList();
        list.add("java01");
        list.add("java02");
        list.add("java03");

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String next = listIterator.next();
            // do others
        }
        listIterator.add("java04");
        System.out.println(list);//[java01, java02, java03, java04]


        boolean possible = new SplitArrayIntoConsecutiveSubsequences_659().isPossible0(new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10, 11});
        System.out.println(possible);


        boolean possibleDivide = new SplitArrayIntoConsecutiveSubsequences_659().isPossibleDivide(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4);
        System.out.println(possibleDivide);
    }


}
