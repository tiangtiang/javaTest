package leetcode;

/**
 * Created by lianglab on 2018/3/6.
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;
        ListNode fast = head, slow = head;
        int i=1;
        for(;fast.next!=null;i++)
            fast = fast.next;
        for(int j=0;j<(i-k%i)-1;j++)
            slow = slow.next;
        fast.next = head;
        ListNode newRoot = slow.next;
        slow.next = null;
        return newRoot;
    }
}
