package Leetcode;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray215{
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> e2 - e1);
        for(int num : nums) pq.add(num);
        int answer = 0;
        for(int i = 0; i < k; i++){
            answer = pq.poll();
        }

        return answer;
    }
}
