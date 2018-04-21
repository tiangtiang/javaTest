package leetcode;

/**
 * Created by lianglab on 2018/4/10.
 */
public class PartitionList {
    /**
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null)
            return head;
        ListNode pre = null, last = null,
                preTemp = null, lastTemp = null;
        ListNode pointer = head;
        while(pointer!=null){
            if(pointer.val >= x){
                if(last == null){
                    last = pointer;
                    lastTemp = last;
                }else{
                    lastTemp.next = pointer;
                    lastTemp = lastTemp.next;
                }
            }else{
                if(pre == null){
                    pre = pointer;
                    preTemp = pre;
                }else{
                    preTemp.next = pointer;
                    preTemp = preTemp.next;
                }
            }
            pointer = pointer.next;
        }
        if(pre == null)
            return last;
        else{
            preTemp.next = last;
            if(lastTemp!=null)
                lastTemp.next = null;
            return pre;
        }
    }
}
