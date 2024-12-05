package leetcode.array;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/corporate-flight-bookings
 */
public class CorporateFlightBookings_1109 {


    /**
     * O(n^2)
     *
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {

        int[] ans = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            int[] booking = bookings[i];
            for (int j = booking[0] - 1; j < booking[1]; j++) {
                ans[j] += booking[2];
            }
        }
        return ans;
    }


    public int[] corpFlightBookings1(int[][] bookings, int n) {
        int[] ans = new int[n];

        HashMap<Integer, Integer> startNumMap = new HashMap<>();

        int[] prev = bookings[0];
        startNumMap.put(prev[0], prev[2]);

        int x_end = prev[1];
        for (int i = 1; i < bookings.length; i++) {
            int[] booking = bookings[i];

            if (booking[0] > x_end) {
                startNumMap.put(booking[0], booking[2]);
                prev = booking;
                x_end = booking[1];

            }

        }
        return null;
    }

    /**
     * https://leetcode-cn.com/problems/corporate-flight-bookings/solution/5118_hang-ban-yu-ding-tong-ji-by-user9081a/
     * 定义 counter[] 数组记录每站的人数变化
     * 每站的人数为前一站人数加上当前人数变化 counters[i] += counters[i - 1]
     *
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] ans = new int[n];

        for (int i = 0; i < bookings.length; i++) {
            int[] booking = bookings[i];
            ans[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                ans[booking[1]] -= booking[2];
            }
        }

        for (int i = 1; i < n; i++) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }


}
