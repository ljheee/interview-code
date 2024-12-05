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
public class SubstringConcatenationofAllWords_30_2 {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();


        int startIdx = s.length();

        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
            int index = s.indexOf(word);
            if (index == -1) {
                return list;
            } else {
                startIdx = Math.min(startIdx, index);
            }
        }

        int length = words.length;
        int wordLen = words[0].length();


        HashMap<String, Integer> window = new HashMap<>();
        int charNum = length * wordLen;
        int left = startIdx;
        int ll = left;
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
                    left += 1;
                    right = left;
                    ll = left;
                    window.clear();
                    step = 0;
                } else {
                    left += 1;
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
        System.out.println(new SubstringConcatenationofAllWords_30_2().findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo", "barr", "wing", "ding", "wing"}));
    }
}