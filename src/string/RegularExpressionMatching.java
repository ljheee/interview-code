package string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配
 * https://leetcode.com/problems/regular-expression-matching/
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {

        Matcher matcher = Pattern.compile(p).matcher(s);
        boolean matches = matcher.matches();

        return matches;
    }
}
