package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给出会议时间间隔，determine if a person could attend all meetings
 * <p>
 * https://www.cnblogs.com/grandyang/p/5240774.html
 * <p>
 * 相当于 检测所有区间是否存在重叠，存在重叠，就不能attend all meetings.
 * <p>
 * 检测所有区间是否存在重叠：最大不重叠区间个数==intervals.length
 */
public class MeetingRooms_252 {

    public boolean canAttendMeetings(int[][] intervals) {

        if (intervals.length == 0) {
            return true;
        }
        // 按区间 结束时间排序，每次找最先结束的
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int count = 0;// 最大不重叠区间个数
        int x_end = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start >= x_end) {
                count++;
                x_end = interval[1];
            }
        }
        return count == intervals.length;
    }
}
