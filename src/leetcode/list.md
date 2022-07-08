
##### 单链表
单链表移除元素，借助prev current指针；
```
ListNode prev = null;
ListNode current = head;
```

单链表 添加元素
```
    /**
     * 给 单向链表 向后追加node
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode point = head; // point 代表活动节点，可以看作是tail，永远是最后一个有效node

        for (int i = 2; i < 5; i++) {
            point.next = new ListNode(i);
            point = point.next;
        }

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
```