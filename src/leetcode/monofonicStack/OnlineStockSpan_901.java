package leetcode.monofonicStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/online-stock-span/
 * https://blog.csdn.net/lw_power/article/details/103957702
 *
 */
public class OnlineStockSpan_901 {


    class StockSpanner {

        Stack<Integer> stack = null;
        List<Integer> priceList = null;
        int index;

        public StockSpanner() {
            stack = new Stack<>();
            priceList = new ArrayList<>();
            index = 0;


            priceList.add(10_0000 + 1);
            stack.push(0);
        }

        public int next(int price) {
            index++;

            while (!stack.isEmpty() && priceList.get(stack.peek()) <= price) {
                stack.pop();
            }

            int count = index - stack.peek();

            priceList.add(price);
            stack.push(index);
            return count;
        }
    }
}
