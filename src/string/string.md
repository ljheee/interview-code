

###### java字符串逆转
https://github.com/TheAlgorithms/Java/blob/master/Others/ReverseString.java
```
    public static String reverse(String str) {
        if (str == null || str.isEmpty()) return str;

        char[] arr = str.toCharArray();
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return new String(arr);
    }

```


###### RemoveDuplicateFromString
https://github.com/TheAlgorithms/Java/blob/master/Others/RemoveDuplicateFromString.java
```
    public static String removeDuplicate(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (sb.toString().indexOf(s.charAt(i)) == -1) {
                sb.append(String.valueOf(s.charAt(i)));
            }
        }

        return sb.toString();
    }
```

###### 字符串-回文判断
1.基于数组下标
2.利用栈 判断回文 
    http://wiki.jikexueyuan.com/project/easy-learn-algorithm/stack.html
    栈的特点：存进去是正序，输出来是逆序。
    将0~mid存入栈，再一一出栈和mid~len比较，全部相等且栈空就是回文。


技巧
回文存在 轴枢 是奇数或偶数的情况，加一个特殊字符，强行隔开。


###### Parse a string to integer
https://github.com/TheAlgorithms/Java/blob/master/Maths/ParseInteger.java




环形buffer
https://github.com/TheAlgorithms/Java/blob/master/DataStructures/Buffers/CircularBuffer.java


#### KMP
ShortestPalindrome_214
https://leetcode-cn.com/problems/shortest-palindrome/solution/zui-duan-hui-wen-chuan-by-leetcode/


ShortestPalindrome_214.shortestPalindrome2 双指针+递归
来源（解法二）https://leetcode-cn.com/problems/shortest-palindrome/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--44/

解法三：滚动hash，


