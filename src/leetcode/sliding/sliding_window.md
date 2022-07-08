滑动窗口；
核心是 用left和right指针，夹住一个窗口；
窗口内的数据，某一项指标是固定的；
right指针不断右移，在扩大窗口，直到窗口内的数据不满足条件时，让left左移。

https://juejin.im/post/5cccc9d1f265da0384129e5f
我们要控制前后指针的移动来控制窗口，这样的移动是有条件的，也就是要想清楚在什么情况下移动，在什么情况下保持不变，我在这里讲讲我的想法，我的思路是保证右指针每次往前移动一格，每次移动都会有新的一个元素进入窗口，这时条件可能就会发生改变，然后根据当前条件来决定左指针是否移动，以及移动多少格。我写来一个模版在这里，可以参考：






#### SubarraysK_DifferentIntegers_992
两个划窗
引入已存在的数；
新增j-i+1个子数组
https://www.jianshu.com/p/194a34ca427f

https://codechina.org/2019/05/leetcode-992-subarrays-with-k-different-integers-java/

a[k]=f(k)-f(k-1)解法
https://brianyi.top/posts/28058be4/


