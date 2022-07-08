package string;

/**
 * Created by lijianhua04 on 2020/4/22.
 */
public class StringCompression_443 {

    /**
     * 只返回长度
     * @param chars
     * @return
     */
    public int compress1(char[] chars) {

        if (chars == null || chars.length == 0) {
            return 0;
        }
        int ans = 0;
        int left = 0;
        int right = chars.length - 1;
        int count = 1;
        while (left <= right) {

            left++;
            while (left <= right && chars[left] == chars[left - 1]) {
                count++;
                left++;
            }

            ans += (1 + (count == 1 ? 0 : String.valueOf(count).length()));
            count = 1;
        }
        return ans;
    }

    /**
     * 修改原数组
     * 同时返回长度
     * @param chars
     * @return
     */
    public int compress(char[] chars) {

        if (chars == null || chars.length == 0) {
            return 0;
        }
        if(chars.length==1) return 1;
        int ans = 0;
        int left = 0;
        int right = chars.length - 1;
        int count = 1;
        while (left <= right) {

            left++;

            while (left <= right && chars[left] == chars[left - 1]) {
                count++;
                left++;
            }

            chars[ans++] = chars[left-count];
            if(count ==1){
            }else {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[ans++] = c;
                }
            }

            count = 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new StringCompression_443().compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        System.out.println(new StringCompression_443().compress(new char[]{'a'}));
        System.out.println(new StringCompression_443().compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
        System.out.println(new StringCompression_443().compress(new char[]{'a', 'b', 'b', 'b', 'b', 'c', 'c', 'c'}));
    }

}
