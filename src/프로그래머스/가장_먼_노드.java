package 프로그래머스;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 가장_먼_노드 {
    public static void main(String[] args) {
        int n=6;
        int[][] vertex = new int[][]{{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
        System.out.println(solution(n,vertex));
    }
    public static int solution(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>();
        for(int i=0;i<=n;i++){
            connections.add(new ArrayList<>());
        }



        for(int[] edges : edge){
            connections.get(edges[0]).add(edges[1]);
            connections.get(edges[1]).add(edges[0]);
        }
        //System.out.println(connections);
        int max = 0;
        boolean[] visited = new boolean[n+1];
        visited[1]=true;
        int[] distances = new int[n+1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1,0});
        while(!queue.isEmpty()){
            //System.out.println(queue);
            int[] current = queue.poll();
            int start = current[0];
            int distance =current[1];
            ArrayList<Integer> conn = connections.get(start);
            for(int con : conn){
                if(!visited[con]){
                    visited[con]=true;
                    distances[con]=distance+1;
                    max=Math.max(max,distances[con]);
                    queue.add(new int[]{con,distance+1});
                }
            }
        }
        int answer = 0;
        for(int i=0;i<distances.length;i++){
            if(distances[i]==max){
                answer++;
            }
        }

        return answer;
    }
}
