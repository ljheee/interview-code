package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2N个人，分别去A，B两地，各去N人，要求路费最小
 * https://leetcode.com/problems/two-city-scheduling/submissions/
 * <p>
 * 思路：必须选出N人去A地，
 * costs[i]去AB的选择点就是 去哪个的路费更小。
 * 特殊case，所有人去B的费用都较小。只能挑出去B费用 针对最小的N个人。
 *
 *
 * <p>
 * <p>
 * Created by lijianhua04 on 2020/2/10.
 */
public class TwoCityScheduling_1029 {


    public int twoCitySchedCost0(int[][] costs) {

        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] - o1[1]) - (o2[0] - o2[1]);
            }
        });

        int ans = 0;
        for (int i = 0; i < costs.length / 2; i++) {
            ans += costs[i][0];
        }
        for (int i = costs.length / 2; i < costs.length; i++) {
            ans += costs[i][1];
        }

        return ans;
    }


    public static void main(String[] args) {
        // 特殊case，所有人去B的费用都较小。只能挑出去B费用 针对最小的N个人。
        new TwoCityScheduling_1029().twoCitySchedCost0(new int[][]{{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}});
    }
}

