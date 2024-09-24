package Leetcode;

import java.util.*;

public class KClosestPointsToOrigin973 {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((e1,e2) -> calcDist(e1) - calcDist(e2));
        pq.addAll(Arrays.asList(points));
        ArrayList<int[]> ans = new ArrayList<>();
        int[][] answer = new int[k][2];
        for(int i = 0; i < k; i++){
            answer[i] = pq.poll();
        }
        return answer;
    }
    // sqrt 까지 계산할 필요 없음
    public static int calcDist(int[] point){
        int x = point[0];
        int y = point[1];
        return x * x + y * y;
    }
}
