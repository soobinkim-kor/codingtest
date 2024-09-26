package Leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class MergeKSortedLists23 {

    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i=0;i<n;i++){
            ListNode itr = lists[i];
            while(itr != null){
                arrayList.add(itr.val);
                itr = itr.next;
            }
        }
        if(n==0 || arrayList.isEmpty()){
            return null;
        }
        Collections.sort(arrayList);
        ListNode head = new ListNode(arrayList.get(0));
        ListNode it = head;
        for(int i=1;i<arrayList.size();i++){
            it.next = new ListNode(arrayList.get(i));
            it = it.next;
        }
        it.next = null;
        return head;
    }

}
