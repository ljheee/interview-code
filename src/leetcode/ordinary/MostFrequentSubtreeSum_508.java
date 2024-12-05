package leetcode.ordinary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/most-frequent-subtree-sum/
 * 找出 频率 最多的 子树和
 * Created by lijianhua04 on 2020/2/21.
 */
public class MostFrequentSubtreeSum_508 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 主要是递归
     * <p>
     * 一个优化点：用 private int maxFrequency = 0;记录最大频率，最后过滤map中最大频率的即可
     *
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap();
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return new int[0];
        }
        cal(map, root);


        List<Map.Entry<Integer, Integer>> collect = map.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).collect(Collectors.toList());

        Map.Entry<Integer, Integer> prev = null;
        for (int i = 0; i < collect.size(); i++) {
            Map.Entry<Integer, Integer> entry = collect.get(i);
            if (prev != null && !prev.getValue().equals(entry.getValue())) {
                break;
            }
            list.add(entry.getKey());
            prev = entry;
        }

        list.toArray(new Integer[list.size()]);// 数组的包装类型，不能转成数组的基本类型；
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private int cal(HashMap<Integer, Integer> map, TreeNode node) {
        int left = 0;
        int right = 0;
        if (node.left != null) {
            left = cal(map, node.left);
        }
        if (node.right != null) {
            right = cal(map, node.right);
        }
        int sum = node.val + left + right;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }


}
