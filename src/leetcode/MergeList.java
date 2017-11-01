package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     * 层层遍历
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(new ArrayList<>(Arrays.asList(lists)), null);
    }

    private ListNode mergeKLists(List<ListNode> lists, ListNode root){
        //lists = lists.stream().filter(node->node != null).collect(Collectors.toList());
        while(lists.contains(null))
            lists.remove(null);
        int minNum = Integer.MAX_VALUE, index = 0;
        for(int i=0;i<lists.size();i++){
            if(lists.get(i).val <minNum){
                minNum = lists.get(i).val;
                index = i;
            }
        }
        int listLength = lists.size();
        if(listLength == 1){
            if(root == null)
                root = lists.get(index);
            else
                root.next = lists.get(index);
            return root;
        }else if(listLength <= 0){
            return root;
        }else{
            ListNode temp = lists.get(index);
            if(root == null) {
                root = temp;
                lists.set(index, lists.get(index).next);
                mergeKLists(lists, root);
            }else {
                root.next = temp;
                lists.set(index, lists.get(index).next);
                mergeKLists(lists, root.next);
            }
        }
        return root;
    }

    /**
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     * 用分治法
     * @param lists
     * @return
     */
    public ListNode mergeKListsDivide(ListNode[] lists) {
        return mergeKListsDivide(lists, 0, lists.length);
    }
    private ListNode mergeKListsDivide(ListNode[] lists, int start ,int end){
        if(end<=start){
            return null;
        }else if(end-start == 2){
            return mergeTwoLists(lists[start], lists[start+1]);
        }else if(end-start == 1){
            return lists[start];
        }else{
            int middle = (end+start)/2;
            ListNode left = mergeKListsDivide(lists, start, middle),
                    right = mergeKListsDivide(lists, middle, end);
            return mergeTwoLists(left, right);
        }
    }
}