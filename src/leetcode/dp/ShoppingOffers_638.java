package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/shopping-offers/
 * 示例题目，礼包数量是2个，实际并非为2
 *
 * <p>
 * Created by lijianhua04 on 2020/8/13.
 */
public class ShoppingOffers_638 {

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int ans = 0;
        for (int i = 0; i < needs.size(); i++) {
            ans += price.get(i) * needs.get(i);
        }
        List<Integer> special0 = special.get(0);
        List<Integer> special1 = special.get(1);

        return determineSpecial(price, special0, special1, needs, ans);
    }

    /**
     * 当前购买needs数量的物品，花费ans;
     * 尝试使用 礼包，用更少的钱获取needs
     * <p>
     * 使用礼包的条件：礼包物品数量，不能超过所需数量。
     *
     * @return
     */
    private int determineSpecial(List<Integer> price, List<Integer> special0, List<Integer> special1, List<Integer> needs, int ans) {

        //if (ans == 0) return ans;
        int money0 = 0;
        for (int i = 0; i < special0.size() - 1; i++) {
            if (special0.get(i) > needs.get(i)) {
                //不能使用礼包，只能优惠0元
                money0 = special0.get(special0.size() - 1);
                break;
            }
            money0 += price.get(i) * special0.get(i);
        }
        money0 -= special0.get(special0.size() - 1);

        int money1 = 0;
        for (int i = 0; i < special1.size() - 1; i++) {
            if (special1.get(i) > needs.get(i)) {
                money1 = special1.get(special1.size() - 1);
                break;
            }
            money1 += price.get(i) * special1.get(i);
        }
        money1 -= special1.get(special1.size() - 1);

        if (money0 <= 0 && money1 <= 0) {
            return ans;
        }

        if (money0 <= 0) {
            return determineSpecial(price, special0, special1, useSpecial(special1, needs), ans - money1);
        }
        if (money1 <= 0) {
            //useSpecial(special0, needs);
            return determineSpecial(price, special0, special1, useSpecial(special0, needs), ans - money0);
        }

        return Math.min(determineSpecial(price, special0, special1, useSpecial(special0, needs), ans - money0),
                determineSpecial(price, special0, special1, useSpecial(special1, needs), ans - money1));
    }

    private List<Integer> useSpecial(List<Integer> special, List<Integer> needs) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < special.size() - 1; i++) {
            result.add(needs.get(i) - special.get(i));
        }
        return result;
    }



    public static void main(String[] args) {
        ShoppingOffers_638 shoppingOffers638 = new ShoppingOffers_638();

        //Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
        //Output: 14
        List<Integer> price = new ArrayList<>();
        List<Integer> special0 = new ArrayList<>();
        List<Integer> special1 = new ArrayList<>();
        List<List<Integer>> special = new ArrayList<>();
        special.add(special0);
        special.add(special1);
        List<Integer> needs = new ArrayList<>();

        price.add(2);
        price.add(5);
        special0.add(3);
        special0.add(0);
        special0.add(5);
        special1.add(1);
        special1.add(2);
        special1.add(10);
        needs.add(3);
        needs.add(2);
//        System.out.println(shoppingOffers638.shoppingOffers(price, special, needs));
        price.clear();
        special0.clear();
        special1.clear();
        needs.clear();


        //Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
        // 11
        price.add(2);
        price.add(3);
        price.add(4);
        special0.add(1);
        special0.add(1);
        special0.add(0);
        special0.add(4);

        special1.add(2);
        special1.add(2);
        special1.add(1);
        special1.add(9);
        needs.add(1);
        needs.add(2);
        needs.add(1);
//        System.out.println(shoppingOffers638.shoppingOffers(price, special, needs));
        price.clear();
        special0.clear();
        special1.clear();
        needs.clear();

        //[0,0,0]
        //[[1,1,0,4],[2,2,1,9]]
        //[1,1,1]
        price.add(0);
        price.add(0);
        price.add(0);
        special0.add(1);
        special0.add(1);
        special0.add(0);
        special0.add(4);

        special1.add(2);
        special1.add(2);
        special1.add(1);
        special1.add(9);
        needs.add(1);
        needs.add(1);
        needs.add(1);
        System.out.println(shoppingOffers638.shoppingOffers(price, special, needs));


    }
}
