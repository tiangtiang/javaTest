package leetcode;

/**
 * Created by lianglab on 2018/4/2.
 */
public class DuplicatedLinkedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode newHead = null;
        ListNode newPre = null;
        ListNode aft = head.next, last = head;
        int count = 1;
        while(aft!=null){
            if(last.val == aft.val)
                count++;
            else{
                if(count == 1){
                    if(newHead == null) {
                        newHead = last;
                        newPre = last;
                    }
                    else{
                        newPre.next = last;
                        newPre = last;
                    }
                    last.next = null;
                }
                last = aft;
                count = 1;
            }
            aft = aft.next;
        }
        if(count == 1){
            if(newHead == null)
                newHead = last;
            else
                newPre.next = last;
            last.next = null;
        }
        return newHead;
    }
}
