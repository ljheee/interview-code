package leetcode.ordinary;

/**
 * https://leetcode-cn.com/problems/statistics-from-a-large-sample/
 */
public class StatisticsLargeSamle_1093 {

    public double[] sampleStats(int[] count) {

        double result[] = new double[5];
        int firstNo0 = -1;
        int lastNo0 = -1;
        int maxFrequency = 0;
        int maxFrequencyNum = 0;
        long no0Cnt = 0;
        long sum = 0;
        for (int i = 0; i < count.length; i++) {
            int frequency = count[i];
            if (frequency != 0 && firstNo0 == -1) {
                firstNo0 = i;
            }
            if (frequency != 0) {
                no0Cnt += frequency;
                sum += i * frequency;
                lastNo0 = i;
            }
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                maxFrequencyNum = i;
            }
        }
        result[0] = firstNo0;
        result[1] = lastNo0;
        result[2] = sum * 1.0 / no0Cnt;
        result[4] = maxFrequencyNum;

        int curCount = 0;
        int a = -1, b = -1;
        for (int i = 0; i < count.length; i++) {
            int cnt = count[i];
            curCount += cnt;
            if (a == -1 && curCount >= no0Cnt / 2) {
                a = i;
            }
            if (b == -1 && curCount >= no0Cnt / 2 + 1) {
                b = i;
            }
        }
        if ((no0Cnt & 1) == 1) {
            result[3] = b;
        } else {
            result[3] = (a + b) / 2.0;
        }
        return result;
    }


    /**
     * 立方体表面积
     * 892 https://blog.csdn.net/lyp867491149/article/details/93307760
     *
     * @param grid
     * @return
     */
    public int surfaceArea(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] > 0)
                    res += grid[i][j] * 4 + 2;
                if (i < grid.length - 1)
                    res -= Math.min(grid[i][j], grid[i + 1][j]) * 2;
                if (j < grid.length - 1)
                    res -= Math.min(grid[i][j], grid[i][j + 1]) * 2;
            }
        }
        return res;
    }




}
