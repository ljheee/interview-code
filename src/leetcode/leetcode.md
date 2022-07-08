

###### add-two-numbers 两个链表，逆向数字相加
https://leetcode.com/problems/add-two-numbers/
重点：
1、单向链表，向后添加元素
2、考虑case要全面，只剩下一个链表时 考虑进位，最终一个进位；




###### next-greater-elements 环形数组 输出下一个greater元素
https://leetcode.com/problems/next-greater-element-ii/



###### Maximum XOR of Two Numbers 一个数组找出两个元素异或运算后的最大值
https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/

https://kingsfish.github.io/2017/12/15/Leetcode-421-Maximum-XOR-of-Two-Numbers-in-an-Array/
题解 https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/solution/li-yong-yi-huo-yun-suan-de-xing-zhi-tan-xin-suan-f/

考虑使用字典树，前缀最不相同的，最后二进制对应的结果最大。思想参考：
https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/solution/qian-zhui-shu-shen-du-you-xian-bian-li-c-by-bartho/



###### 归并排序-借助counter数组
https://leetcode-cn.com/problems/sort-list/solution/zi-di-xiang-shang-de-gui-bing-pai-xu-java-dai-ma-b/


###### BFPRT算法
KthLargestElement.java
top-k问题 

最小的K个数
https://www.kancloud.cn/kancloud/the-art-of-programming/41579

1.排序 时间复杂度O(n*logN)
2.建立容量为k的大顶堆； O（n*logk）
3.快速选择：基于快排的改进，每次递归一边、舍弃一半数据。
