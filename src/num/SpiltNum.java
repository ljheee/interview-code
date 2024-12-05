package num;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijianhua04 on 2019/11/5.
 */
public class SpiltNum {

    public static void main(String[] args) {

        int n = 123;
        List<Integer> list = new ArrayList<>();

        // 把数字 n 拆解得到每一位
        int mutNum = 10;
        while (n / mutNum >= 1) {
            list.add(n % mutNum);
            n = n / mutNum;

            // 最后一个：数字首位 直接添加到list
            if (n <= 9) {
                list.add(n);
            }
        }
        System.out.println(list);
    }
}
