package leetcode;

/**
 * Created by tiang on 2017/10/30.
 * Given a linked list, remove the nth node from the end of list and return its head.

 For example,

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tail = head;
        int i=0;
        while(i<n && tail!=null){
            tail = tail.next;
            i++;
        }
        if(i<n){
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        while(tail!=null){
            pre = pre.next;
            tail = tail.next;
        }
        if(pre.next == head){
            head = head.next;
        }else{
            ListNode temp = pre.next;
            pre.next = temp.next;
            temp.next = null;
        }
        return head;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
