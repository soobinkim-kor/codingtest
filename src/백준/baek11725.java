package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class baek11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] answer = new int[n+1];

        // [[0번 노드와 연결된 애들], [1번 노드와 연결된 애들], [2번 노드와 연결된 애들], ... [n번 노드와 연결된 애들] ]
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        boolean[] visited = new boolean[n+1]; // 0부터 n 까지 방문
        for(int i=0;i<n+1;i++){
            arrayList.add(new ArrayList<Integer>());
        }
        for(int i=0;i<n-1;i++){
            String[] info = br.readLine().split(" ");
            int start = Integer.parseInt(info[0]);
            int end = Integer.parseInt(info[1]);
            arrayList.get(start).add(end);
            arrayList.get(end).add(start);
        }
        visited[1]=true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while(!queue.isEmpty()){
            int num = queue.poll();
            ArrayList<Integer> connections = arrayList.get(num);
            for(int connection : connections){
                if(!visited[connection]){
                    visited[connection]=true;
                    answer[connection]=num;
                    queue.add(connection);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=2;i<answer.length;i++){
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);


    }
}
