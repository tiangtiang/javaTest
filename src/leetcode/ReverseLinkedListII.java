package leetcode;

/**
 * Created by lianglab on 2018/4/10.
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode temp = new ListNode(0);
        temp.next = head;
        head = temp;
        int count = 0;
        ListNode pre = head;
        while(count<m-1){
            pre = pre.next;
            count++;
        }
        ListNode tail = pre.next;
        ListNode newList = tail;
        ListNode pointer = tail.next, ahead = pointer;
        count++;
        while(count<n){
            pointer = pointer.next;
            ahead.next = newList;
            newList = ahead;
            ahead = pointer;
            count++;
        }
        pre.next = newList;
        tail.next = pointer;
        return head.next;
    }
}
