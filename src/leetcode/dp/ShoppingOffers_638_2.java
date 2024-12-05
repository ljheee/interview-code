package leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/shopping-offers/
 * 示例题目，礼包数量是2个，实际并非为2
 * <p>
 * Created by lijianhua04 on 2020/8/13.
 */
public class ShoppingOffers_638_2 {

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int ans = 0;
        for (int i = 0; i < needs.size(); i++) {
            ans += price.get(i) * needs.get(i);
        }

        return determineSpecial(price, special, needs, ans);
    }

    /**
     * 当前购买needs数量的物品，花费ans;
     * 尝试使用 礼包，用更少的钱获取needs
     * <p>
     * 使用礼包的条件：礼包物品数量，不能超过所需数量。
     * https://leetcode.com/problems/shopping-offers/discuss/105212/Very-Easy-to-understand-JAVA-Solution-beats-95-with-explanation
     *
     * @return
     */
    private int determineSpecial(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int ans) {
        int result = ans;
        for (int i = 0; i < special.size(); i++) {
            int money0 = 0;
            List<Integer> special0 = special.get(i);
            for (int j = 0; j < special0.size() - 1; j++) {
                if (special0.get(j) > needs.get(j)) {
                    //不能使用礼包，只能优惠0元
                    money0 = 0;
                    break;
                }
                money0 += price.get(j) * special0.get(j);
            }
            money0 -= special0.get(special0.size() - 1);

            if (money0 > 0) {
                result = Math.min(result, special0.get(special0.size() - 1) + determineSpecial(price, special, useSpecial(special0, needs), ans - money0-special0.get(special0.size() - 1)));
            }
        }

        return result;
    }

    private List<Integer> useSpecial(List<Integer> special, List<Integer> needs) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < special.size() - 1; i++) {
            result.add(needs.get(i) - special.get(i));
        }
        return result;
    }


    public static void main(String[] args) {
        ShoppingOffers_638_2 shoppingOffers638 = new ShoppingOffers_638_2();

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
        System.out.println(shoppingOffers638.shoppingOffers(price, special, needs));
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
        System.out.println(shoppingOffers638.shoppingOffers(price, special, needs));
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
        price.clear();
        special0.clear();
        special1.clear();
        needs.clear();
        //[1,1,1]
        //[[1,1,0,0],[2,2,1,9]]
        //[1,1,0]
        price.add(1);
        price.add(1);
        price.add(1);
        special0.add(1);
        special0.add(1);
        special0.add(0);
        special0.add(0);

        special1.add(2);
        special1.add(2);
        special1.add(1);
        special1.add(9);
        needs.add(1);
        needs.add(1);
        needs.add(0);
        System.out.println(shoppingOffers638.shoppingOffers(price, special, needs));
        price.clear();
        special0.clear();
        special1.clear();
        needs.clear();

    }
}
