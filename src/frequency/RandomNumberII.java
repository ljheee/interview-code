package frequency;

import java.util.Random;

/**
 * 给你一个随机函数f，等概率返回a~b中的一个数字，这是你唯一可以使用的随机机制，如何实现等概率返回c~d中的一个数字。
 */
public class RandomNumberII {

    public final static int a = 0;
    public final static int b = 0;

    /**
     * 等概率返回a~b
     * 统一减掉a,变成0~b-a
     *
     * @return
     */
    public final static int random15(int a, int b) {
        return new Random().nextInt();
    }

    /**
     * 等概率返回0，1
     *
     * @return
     */
    public static int random01() {
        if (random15(1,15) == 3) {
            return random01();
        } else if (random15(1, 2) < 3) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 等概率返回0~6
     * 0~6 需要3个二进制位;
     * <p>
     * 返回值统一+1,得到0~7
     *
     * @return
     */
    public static int random06() {
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            ans += random01() << i;
        }
        if (ans == 7) return random06();
        return ans;
    }

}
