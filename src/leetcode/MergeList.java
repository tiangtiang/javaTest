package leetcode;

/**
 * Created by tiang on 2017/10/30.
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode result = null, top = null, bot = null;
        if(l1.val>l2.val){
            result = l2;
            top = l1;
            bot = l2.next;
        }else{
            result = l1;
            top = l1.next;
            bot = l2;
        }
        ListNode pre = result;
        while(top!=null && bot != null){
            if(top.val<bot.val){
                pre.next = top;
                top = top.next;
            }else{
                pre.next = bot;
                bot = bot.next;
            }
            pre = pre.next;
        }
        if(top == null)
            pre.next = bot;
        else
            pre.next = top;
        return result;
    }
}