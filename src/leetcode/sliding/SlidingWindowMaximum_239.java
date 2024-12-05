package leetcode.sliding;

import leetcode.ArrayGenerator;

import java.util.*;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum_239 {

    /**
     * 长度为k的划窗
     * 利用 PriorityQueue 找出划窗内的最值；
     * 利用 LinkedList 控制划窗元素
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k == 0) return nums;

        int[] ans = new int[nums.length - k + 1];
        int i = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int num : nums) {
            if (priorityQueue.size() == k) {
                ans[i++] = priorityQueue.peek();
                priorityQueue.remove(linkedList.removeLast());
            }
            priorityQueue.add(num);
            linkedList.addFirst(num);
        }
        while (i < ans.length) ans[i++] = priorityQueue.peek();
        return ans;
    }

    /**
     * 简化仅使用一个PriorityQueue
     * <p>
     * 最后一个case超时，因为priorityQueue.remove();// 遍历一遍queue，O(k)
     * 最终O(n*k)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k == 0) return nums;

        int[] ans = new int[nums.length - k + 1];
        int i = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int j = 0; j < nums.length; j++) {
            if (priorityQueue.size() == k) {
                ans[i++] = priorityQueue.peek();
                priorityQueue.remove(nums[j - k]);// 遍历一遍queue，O(k)
            }
            priorityQueue.add(nums[j]);
        }


        while (i < ans.length) ans[i++] = priorityQueue.peek();
        return ans;
    }


    /**
     * 双端队列法
     * deque遇到更大元素，清调队列中较小的元素 —— 保证队首永远是当前区间最大元素；
     * 最终实现了 元素顺序倒序的 队列
     * <p>
     * <p>
     * abbott评论
     * https://leetcode-cn.com/problems/sliding-window-maximum/comments/
     * <p>
     * vector<int> maxSlidingWindow(vector<int>& nums, int k) {
     * vector<int> res;
     * deque<int> de;
     * for(int i = 0;i<nums.size();i++){
     * //保证de头最大
     * while(!de.empty() && de.back() < nums[i]){
     * de.pop_back();
     * }
     * de.push_back(nums[i]);
     * if(i >= k-1){
     * res.push_back(de.front());
     * if(!de.empty() && de.front() == nums[i-k+1]) de.pop_front();
     * }
     * <p>
     * }
     * return res;
     * }
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow_deque(int[] nums, int k) {

        if (nums == null || nums.length == 0 || k == 0) return nums;

        int[] ans = new int[nums.length - k + 1];
        int i = 0;

        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int j = 0; j < nums.length; j++) {
            while (!linkedList.isEmpty() && nums[j] > linkedList.peekLast()) linkedList.pollLast();
            linkedList.addLast(nums[j]);
            if (j >= k - 1) {
                ans[i++] = linkedList.peek();
                if (!linkedList.isEmpty() && nums[j - k + 1] == linkedList.peek()) linkedList.removeFirst();
            }
        }
        return ans;
    }


    public static int[] maxSlidingWindow_zuo(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return nums;
        int[] ans = new int[nums.length - k + 1];
        int j = 0;

        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && queue.peekLast()[0] <= nums[i]) queue.removeLast();
            queue.addLast(new int[]{nums[i], i});
        }
        ans[j++] = queue.peekFirst()[0];

        int L = 0;
        for (int R = k; R < nums.length; R++) {
            while (!queue.isEmpty() && queue.peekLast()[0] <= nums[R]) queue.removeLast();
            queue.addLast(new int[]{nums[R], R});

            if (queue.peekFirst()[1] == L) {
                queue.removeFirst();
            }
            ans[j++] = queue.peekFirst()[0];
            L++;
        }

        return ans;
    }
    public static int[] maxSlidingWindow_zuo2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return nums;
        int[] ans = new int[nums.length - k + 1];
        int j = 0;

        LinkedList<int[]> queue = new LinkedList<>();

        for (int R = 0; R < nums.length; R++) {
            while (!queue.isEmpty() && queue.peekLast()[0] <= nums[R]) queue.removeLast();
            queue.addLast(new int[]{nums[R], R});


            if (!queue.isEmpty() && queue.peekFirst()[1] == R-k) {
                queue.removeFirst();
            }


            if(R >=k-1){
                ans[j++] = queue.peekFirst()[0];
            }
        }

        return ans;
    }

    public static void main(String[] args) {


        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.add(1);
        priorityQueue.remove(1);
        //System.out.println(priorityQueue);// [1, 1] 重复元素只删除一个


//        System.out.println(Arrays.toString(maxSlidingWindow_deque(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
//        System.out.println(Arrays.toString(maxSlidingWindow_deque(new int[]{-1, -3, -1, -3, 5, 3, 6, 7}, 3)));
//        System.out.println(Arrays.toString(maxSlidingWindow_deque(new int[]{1, 2, -6, -5, -8, -3, 7, -1}, 3)));

        // 比对通过
        for (int i = 0; i < 100; i++) {
            int[] array = ArrayGenerator.generateRandomArray(8, -10, 9);
            int k = new Random().nextInt(5);
            if (!Arrays.equals(maxSlidingWindow_deque(array, k), maxSlidingWindow_zuo(array, k))) {
                System.out.println(String.format("%s,%s,%s,%d",
                        Arrays.toString(maxSlidingWindow_deque(array, k)),
                        Arrays.toString(maxSlidingWindow2(array, k)),
                        Arrays.toString(array),
                        k));
            }
        }


    }
}
