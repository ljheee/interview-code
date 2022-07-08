package some;

/**
 * 三步翻转法，需要交换N次
 *
 * @author lijianhua
 */
public class FilpString {


    public static void main(String[] args) {
        String newStr = filpString2("abcdefgh", 5);

        System.out.println(newStr);

    }

    //递归
    private static String filpString(String s, int leftSize) {

        char[] chars = s.toCharArray();

        int rightSize = s.length() - leftSize;

        int rest = leftSize - rightSize;
        if (rest == 0) {
            //abcd 对半交换
            int j = leftSize;
            for (int i = 0; i < leftSize; i++) {
                char tmp = chars[j];
                chars[j] = chars[i];
                chars[i] = tmp;
                j++;
            }
            return String.valueOf(chars);
        } else if (rest > 0) {

            // 左边长：
            return s.substring(leftSize) + filpString(s.substring(rightSize, leftSize) + s.substring(0, rightSize), rest);
        } else {
            // TODO: 2020/8/8
            return "";
        }
    }

    /**
     * 对半交换
     * abcdefgh 对半abcd和efgh 从头开始交换
     *
     * @param s
     * @param leftSize
     * @return
     */
    private static String filpString2(String s, int leftSize) {

        char[] chars = s.toCharArray();
        int L = 0;
        int R = s.length() - 1;

        int lpart = leftSize;
        int rpart = s.length() - lpart;
        int same = Math.min(lpart, rpart);
        int diff = lpart - rpart;
        exchange(chars, L, R, same);
        while (diff != 0) {
            if (diff > 0) {
                L = L + same;
                lpart = diff;
            } else {
                R = R - same;
                rpart = -diff;
            }
            same = Math.min(lpart, rpart);
            diff = lpart - rpart;
            exchange(chars, L, R, same);
        }
        return String.valueOf(chars);

    }

    private static void exchange(char[] chars, int L, int R, int same) {
        int c = same;
        int j = R + 1 - same;

        while (c > 0) {
            char tmp = chars[L];
            chars[L] = chars[j];
            chars[j] = tmp;
            c--;
            L++;
            j++;
        }
    }
}
