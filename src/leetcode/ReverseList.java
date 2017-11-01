package leetcode;

/**
 * 翻转list
 * Created by tiang on 2017/10/31.
 */
public class ReverseList {
    /**
     * Given a linked list, swap every two adjacent nodes and return its head.
     For example,
     Given 1->2->3->4, you should return the list as 2->1->4->3.
     Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode root = new ListNode(0);
        root.next = head;
        swapPair(root);
        return root.next;
    }
    private void swapPair(ListNode current){
        ListNode pre = current.next;
        if(pre == null)
            return;
        ListNode tail = pre.next;
        if(tail == null)
            return;
        current.next = tail;
        pre.next = tail.next;
        tail.next = pre;
        if(pre.next == null)
            return;
        swapPair(pre);
    }

    /**
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes
     is not a multiple of k then left-out nodes in the end should remain as it is.
     You may not alter the values in the nodes, only nodes itself may be changed.
     Only constant memory is allowed.
     For example,
     Given this linked list: 1->2->3->4->5
     For k = 2, you should return: 2->1->4->3->5
     For k = 3, you should return: 3->2->1->4->5
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k<=1)
            return head;
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode pre = head;
        if(pre == null)
            return head;
        ListNode tail = pre.next, tempRoot = root;
        int count = 1;
        while(tail!=null) {
            int leftLength = 1;
            ListNode pointer = tail;
            while(leftLength<k && pointer != null){
                pointer = pointer.next;
                leftLength++;
            }
            if(leftLength<k)
                break;
            while (tail != null && count < k) {
                pre.next = tail.next;
                tail.next = tempRoot.next;
                tempRoot.next = tail;
                tail = pre.next;
                count++;
            }
            if(tail == null)
                break;
            tempRoot = pre;
            pre = tail;
            tail = pre.next;
            count = 1;
        }
        return root.next;
    }
}
