动态规划一般思路：
1） 确定递推量： 确定递推过程中要保留的历史信息数量和具体含义， 同时也会定下动态规划的维度； 
2） 推导递推式：根据确定的递推量， 得到如何利用存储的历史信息在有效时间（通常是常量或者线性时间）内得到当前的信息结果； 
3） 计算初始条件：有了递推式之后， 只需要计算初始条件， 就可以根据递推式得到我们想要的结果了。 通常初始条件都是比较简单的情况， 一般来说直接赋值即可；  



题目见https://leetcode-cn.com/problemset/all/




#### 同分异构词
判断文本段相等
https://leetcode-cn.com/problems/valid-anagram/solution/pythonyi-xing-asciima-pan-duan-lun-wen-cha-zhong-y/
之前看数学之美有一章用这种方法判断文章内容重复度，还是蛮有用的

即用两个文本对应数字加起来判断是否一样，后面的<1e-5是判断两个浮点数是否相等，因为浮点数存在尾数问题

不过文本太短，非常容易出现两个文本恰好加起来等于一个数字，所以可以设计一种函数，将字母对应的ASCII变换一下，可以使用**2（不过貌似被出题人写了对应的测试案例过不了），好的函数是可以使两对文本相加恰好相等的概率降至极低
```
    public boolean isAnagram(String s, String t) {
        return Math.abs(sumChar(s) - sumChar(t)) < 0.0001;
    }
    
    //查看数字加起来起来是否一样 抄别人的。
    public double sumChar(String s){
        double sum = 0;
        char[] cs = s.toCharArray();
        for(char c : cs){
            sum += Math.pow((int)c,0.5);
        }
        return sum;
    }
```


#### 斐波那契
非递归的解法
https://leetcode-cn.com/problems/fibonacci-number/solution/fei-bo-na-qi-shu-by-leetcode/


递归算法，时间复杂度
https://blog.csdn.net/so_geili/article/details/53444816




#### tips条件
1、数组递增、有序，考虑用二分
2、涉及数组连续i~j的和，考虑前缀和
3、子数组的和，考虑前缀和、双指针；



bfs
https://leetcode.com/problems/trapping-rain-water-ii/