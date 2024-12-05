

###### RemoveDuplicatesfromSortedArray
返回去重后的 new length，同时需要将原数组 0~newLength 按去重后的元素出现
巧妙点：不开辟新数组；用数组后面的元素替换前面的。


###### 无序数组，至少存在一个重复元素，找到该重复元素
1、排序后，按RemoveDuplicatesfromSortedArray的方法，找到重复元素；
2、弗洛伊德的乌龟和兔子（循环检测）
    https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode/
    RemoveDuplicatesfromSortedArray.findDuplicate
    
鸽子洞原理(鸽巢原理)
https://blog.csdn.net/lcj_cjfykx/article/details/41695111



#### 众数
找出数组中的众数：出现频率最高，有时众数在一组数中有好几个；

例如：1，2，3，3，4的众数是3。
例如：1，2，2，3，3，4的众数是2和3。
例如：1，2，3，4，5没有众数。


- 出现频率超出n/2的数
摩尔投票法 https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
https://leetcode.com/problems/majority-element/submissions/

> 往年一道408算法题的答案就是投票法

超过半数的，才能用投票法(用超过半数的票，压倒其他竞争者)
https://blog.csdn.net/qq_39652552/article/details/82082950








