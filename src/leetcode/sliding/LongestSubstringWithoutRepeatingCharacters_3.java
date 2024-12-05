package leetcode.sliding;

import java.util.HashSet;

/**
 * 元素不重复的 最长子数组
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
 * <p>
 * Created by lijianhua04 on 2020/4/4.
 */
public class LongestSubstringWithoutRepeatingCharacters_3 {

    public int lengthOfLongestSubstring(String s) {


        if (s.length() == 0) {
            return 0;
        }
        HashSet<Character> characters = new HashSet<>();
        int left = 0;
        int max = 1;
        for (int right = 0; right < s.length(); right++) {
            if (characters.add(s.charAt(right))) {
                int len = right - left + 1;
                if (len > max) {
                    max = len;
                }
                continue;
            }

            while (characters.add(s.charAt(right)) == false) {
                characters.remove(s.charAt(left));
                left++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters_3().lengthOfLongestSubstring("abcabcbb"));
    }


}
