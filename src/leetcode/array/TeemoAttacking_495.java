package leetcode.array;

/**
 * 间隔攻击
 * https://leetcode.com/problems/teemo-attacking/
 *
 * 区间合并类的题目
 * Created by lijianhua04 on 2020/3/23.
 */
public class TeemoAttacking_495 {

    public int findPoisonedDuration(int[] timeSeries, int duration) {

        if(timeSeries.length==0){
            return 0;
        }
        int ans = duration;
        int x_end = timeSeries[0]+duration;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i]>=x_end) {
                ans+=duration;
                x_end+=duration;
            }else{
                ans-=(x_end-timeSeries[i]);
                ans+=duration;
                x_end=timeSeries[i]+duration;
            }
        }
        return ans;
    }

    public int findPoisonedDuration1(int[] timeSeries, int duration) {

        int total = 0;
        for (int i = 0; i < timeSeries.length-1; i++) {
            total+=Math.min(timeSeries[i+1]-timeSeries[i],duration);
        }
        return total+duration;
    }



    public static void main(String[] args) {
        new TeemoAttacking_495().findPoisonedDuration(new int[]{1,2,3,4,5,6,7,8,9},1);
        new TeemoAttacking_495().findPoisonedDuration(new int[]{1,2,3,4,5},5);
    }
}
