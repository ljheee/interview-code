package frequency;

import java.util.Random;

/**
 * Created by lijianhua04 on 2020/11/25.
 */
public class EqualProbabilityRandom {

    public final static int random() {
        return Math.random() > 0.88 ? 1 : 0;
    }

    /**
     * 调用两次f，如果是01 返回0
     * 调用两次f，如果是10 返回1
     * 其他情况重做
     *
     * @return
     */
    public static int equalProbabilityRandom() {
        int first;
        do {
            first = random();
        } while (first == random());
        return first;
    }
}
