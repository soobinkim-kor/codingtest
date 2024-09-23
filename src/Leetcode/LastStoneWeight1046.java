package Leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight1046 {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((e1, e2) -> e2 - e1);
        for(int stone : stones){
            minHeap.add(stone);
        }
        while(minHeap.size() > 1){
            int stoneX = minHeap.poll();
            if(minHeap.peek() != null){
                int stoneY = minHeap.poll();
                if(stoneX != stoneY){
                    int newStone = Math.abs(stoneY - stoneX);
                    minHeap.add(newStone);
                }
            }
        }
        return minHeap.isEmpty() ? 0 : minHeap.peek();
    }
}
