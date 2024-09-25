package Leetcode;

public class MergeKSortedLists23 {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        while(true){
            int minIndex = 0;
            int minVal = Integer.MIN_VALUE;
            for(int i = 0; i < lists.length; i++){
                // i번째 노드의 최소 value
                int min = Integer.MIN_VALUE;

                if(lists[i] == null || lists[i].next == null){
                    continue;
                }
                // 새로 접근할 노드의 시작
                ListNode cur = lists[i];
                while(null != cur){

                    if(cur.val > min){
                        min = cur.val;
                        minIndex = i;
                    }
                    cur = cur.next;
                }
            }
            dummy.val = minVal;
            dummy.next = lists[minIndex];

            break;
        }
        return dummy.next;
    }

}
