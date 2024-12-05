package leetcode.tree;

/**
 */
public class DecodeString_394 {

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Decode String.
     * Memory Usage: 37.1 MB, less than 63.85% of Java online submissions for Decode String.
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {

        if (s == null || s.length() == 0) return s;
        return process(s, 0, s.length() - 1);
    }

    private String process(String s, int L, int R) {
        if (L > R) return "";
        if (L == R) return String.valueOf(s.charAt(L));

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = L; i <= R; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int digitIdxStart = i;

                // find the end of digit
                for (i = i + 1; Character.isDigit(s.charAt(i)) && i <= R; i++) ;
                int num = Integer.parseInt(s.substring(digitIdxStart, i));

                int ll = i + 1;
                int left = 1;
                int right = 0;
                for (i = i + 1; i <= R; i++) {
                    if (s.charAt(i) == '[') {
                        left++;
                    } else if (s.charAt(i) == ']') {
                        right++;
                    }
                    if (left == right) {
                        String sub = process(s, ll, i - 1);
                        for (int j = 0; j < num; j++) {
                            stringBuilder.append(sub);
                        }
                        break;
                    }
                }
                continue;
            }
            stringBuilder.append(c);

        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        DecodeString_394 decodeString394 = new DecodeString_394();

        decodeString394.decodeString("100[leetcode]");
    }
}
