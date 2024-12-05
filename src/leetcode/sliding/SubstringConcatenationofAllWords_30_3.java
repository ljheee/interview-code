package leetcode.sliding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * faster than 43.01%
 * 来源解法一 https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-6/
 * 对每一个字符i ，截取之后的charNum个，进行比较；
 *
 * <p>
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * <p>
 * Created by lijianhua04 on 2020/4/6.
 */
public class SubstringConcatenationofAllWords_30_3 {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        int wordNum = words.length;

        if (wordNum == 0) {
            return list;
        }
        int wordLen = words[0].length();

        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        HashMap<String, Integer> window = new HashMap<>();
        int charNum = wordNum * wordLen;

        for (int i = 0; i < s.length() - charNum + 1; i++) {

            int left = i;
            int match = 0;// 用于比较两个map
            for (int j = 1; j <= wordNum; j++) {
                String substring = s.substring(left + (j - 1) * wordLen, left + j * wordLen);
                if (!map.containsKey(substring)) {
                    window.clear();
                    break;
                }
                window.put(substring, window.getOrDefault(substring, 0) + 1);
                if(window.get(substring).intValue() == map.get(substring).intValue()){
                    match++;
                }
            }

            if (match == map.size()) {
                list.add(i);
            }
            window.clear();
        }


        return list;
    }


    public static void main(String[] args) {
//        System.out.println(new SubstringConcatenationofAllWords_30_3().findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo", "barr", "wing", "ding", "wing"}));
        System.out.println(new SubstringConcatenationofAllWords_30_3().findSubstring("abababab", new String[]{"a", "b", "a"}));

    }
}