package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode.com/problems/distant-barcodes/submissions/
 */
public class DistantBarcodes_1054 {


    /**
     * 不能直接排序后 按奇偶位放置
     *
     * @param barcodes
     * @return
     */
    public int[] rearrangeBarcodes0(int[] barcodes) {

        int ans[] = new int[barcodes.length];

        Arrays.sort(barcodes);
        int idx = 0;
        for (int i = 0; i < ans.length; i += 2) {
            ans[i] = barcodes[idx++];
        }
        for (int i = 1; i < ans.length; i += 2) {
            ans[i] = barcodes[idx++];
        }

        return ans;
    }


    /**
     * [1，2，2，3] 需要按频率排序
     * 再按奇偶位放置
     *
     * @param barcodes
     * @return
     */
    public int[] rearrangeBarcodes(int[] barcodes) {

        int ans[] = new int[barcodes.length];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int c : barcodes) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        AtomicInteger q = new AtomicInteger();

        // 按出现的频率倒序
        map.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .forEachOrdered(e -> {
                            for (int i = 0; i < e.getValue(); i++) {
                                barcodes[q.getAndIncrement()] = e.getKey();
                            }
                        }
                );

        int idx = 0;
        for (int i = 0; i < ans.length; i += 2) {
            ans[i] = barcodes[idx++];
        }
        for (int i = 1; i < ans.length; i += 2) {
            ans[i] = barcodes[idx++];
        }

        return ans;
    }
}
