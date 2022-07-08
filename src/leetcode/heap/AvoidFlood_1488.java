package leetcode.heap;

import java.util.*;

/**
 * @author lijianhua.
 */
public class AvoidFlood_1488 {


    /**
     * rains[i]=j>0代表第i天，j湖泊会下雨
     *
     * @param rains
     * @return
     */
    public int[] avoidFlood(int[] rains) {

        HashMap<Integer, LinkedList<Integer>> lakeOfRainDayMap = new HashMap<>();
        for (int i = 0; i < rains.length; i++) {
            lakeOfRainDayMap.computeIfAbsent(rains[i], s -> new LinkedList<>()).addLast(i);
        }

        PriorityQueue<LakeAndNextRainDay> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.nextRainDay));

        int[] ans = new int[rains.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] > 0) {

                LinkedList<Integer> rainDays = lakeOfRainDayMap.get(rains[i]);
                rainDays.removeFirst();

                // priorityQueue已下雨湖泊，又下雨，无法避免
                if(!priorityQueue.isEmpty()){
                    if (priorityQueue.peek().nextRainDay==i) return new int[]{};
                }
                if (!rainDays.isEmpty()) priorityQueue.add(new LakeAndNextRainDay(rains[i], rainDays.peekFirst()));

            } else {
                if (!priorityQueue.isEmpty()) {
                    LakeAndNextRainDay lakeAndNextRainDay = priorityQueue.poll();
                    ans[i] = lakeAndNextRainDay.lake;
                } else {
                    ans[i] = 1;
                }
            }
        }

        return ans;
    }


    static class LakeAndNextRainDay {
        int lake;
        int nextRainDay;

        public LakeAndNextRainDay(int lake, int nextRainDay) {
            this.lake = lake;
            this.nextRainDay = nextRainDay;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LakeAndNextRainDay that = (LakeAndNextRainDay) o;

            if (lake != that.lake) return false;
            return nextRainDay == that.nextRainDay;
        }

        @Override
        public int hashCode() {
            int result = lake;
            result = 31 * result + nextRainDay;
            return result;
        }
    }

}
