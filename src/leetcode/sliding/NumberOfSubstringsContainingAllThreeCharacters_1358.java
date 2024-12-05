package leetcode.sliding;

/**
 * Created by lijianhua04 on 2020/5/10.
 */
public class NumberOfSubstringsContainingAllThreeCharacters_1358 {


    /**
     * 当某个单元满足时，其后的所有元素 加上也满足
     * 但每次收缩时，ans += 1;不对
     * 不过的case：aaacbaaacb
     *
     * @param s
     * @return
     */
    public int numberOfSubstrings0(String s) {

        int ans = 0;
        int[] count = new int[3];
        int left = 0;
        char[] chars = s.toCharArray();
        for (int right = 0; right < chars.length; right++) {
            count[chars[right] - 'a']++;

            if (count[0] > 0 && count[1] > 0 && count[2] > 0) ans += (chars.length - right);
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                count[chars[left++] - 'a']--;
                if (count[0] > 0 && count[1] > 0 && count[2] > 0) ans += 1;
            }
        }
        return ans;
    }

    /**
     * 如果s[left,right] 满足条件，则加上right~length的后缀都满足；因此是length-right个
     *
     * @param s
     * @return
     */
    public static int numberOfSubstrings(String s) {

        int ans = 0;
        int[] count = new int[3];
        int left = 0;
        char[] chars = s.toCharArray();
        for (int right = 0; right < chars.length; right++) {
            count[chars[right] - 'a']++;

            if (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                ans += (chars.length - right);

                while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                    count[chars[left++] - 'a']--;
                    //if (count[0] > 0 && count[1] > 0 && count[2] > 0) ans += 1; why not +1 ?
                    if (count[0] > 0 && count[1] > 0 && count[2] > 0) ans += (chars.length - right);
                }
            }
        }
        return ans;
    }

    /**
     * refer: https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/discuss/577338/Easy-Java-Solution
     *
     * @param s
     * @return
     */
    public static int numberOfSubstrings2(String s) {

        int l = s.length();
        int i, j, count = 0;
        if (l >= 50000) // for last test case number 54
            return 49998;

        for (i = 0; i < l; i++) {
            for (j = i + 3; j <= l; j++) {
                String h = s.substring(i, j);
                if (h.contains("a") && h.contains("b") && h.contains("c")) {
                    count += (l - j + 1);// length-right
                    break;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("aaacbaaacbaaacb"));
        System.out.println(numberOfSubstrings2("aaacbaaacb"));

    }
}
