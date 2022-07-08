package leetcode.sliding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * <p>
 * Created by lijianhua04 on 2020/4/6.
 */
public class SubstringConcatenationofAllWords_30 {

    /**
     * 不足：出现位置 不按word整数倍时，不通过
     * 遍历 s 步长不能是word，而应该是1
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();


        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int length = words.length;
        int wordLen = words[0].length();


        HashMap<String, Integer> window = new HashMap<>();
        int charNum = length * wordLen;
        int left = 0;
        int ll = 0;
        int step = 0;
        for (int right = wordLen; right <= s.length(); right += wordLen) {

            String substring = s.substring(ll, right);
            if (!map.containsKey(substring)) {
                left = right;
                ll = left;
                window.clear();
                step = 0;
                continue;
            }
            window.put(substring, window.getOrDefault(substring, 0) + 1);
            step += wordLen;
            ll += wordLen;
            if (step == charNum) {
                // map equal

                if (mapEqual(window, map)) {
                    list.add(left);
                    left += wordLen;
                    right = left;
                    ll = left;
                    window.clear();
                    step = 0;
                } else {
                    left += wordLen;
                    right = left;
                    ll = left;
                    window.clear();
                    step = 0;
                }
            }
        }
        return list;

    }

    private boolean mapEqual(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {

        if (map1.keySet().size() != map2.keySet().size()) {
            return false;
        }
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            Integer integer = map2.get(entry.getKey());
            if (integer == null || integer != entry.getValue().intValue()) {
                return false;
            }

        }
        return true;
    }


    public static void main(String[] args) {
//        System.out.println(new SubstringConcatenationofAllWords_30().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
//        System.out.println(new SubstringConcatenationofAllWords_30().findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
//        System.out.println(new SubstringConcatenationofAllWords_30().findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
//        System.out.println(new SubstringConcatenationofAllWords_30().findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));
        System.out.println(new SubstringConcatenationofAllWords_30().findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo", "barr", "wing", "ding", "wing"}));
    }
}