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
        //ArrayList<int[]> arrayList = new ArrayList<>();
        int[] answer = new int[n+1];
        //Queue<int[]> queue = new LinkedList<>();

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
            //arrayList.add(new int[]{start,end});
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
        /* 이렇게 하면 안되고 ArrayList<ArrayList<Integer>> 로 해야함
            1번째 = 1과 연결된 모든 노드(숫자) 들
        *
        * */


        //boolean[] visited = new boolean[n-1];


        /*
        queue.add(new int[]{0,1});

        while(!queue.isEmpty()){
            //printQueue(queue);
            int[] data = queue.poll();
            int end = data[1];

            for(int i=0;i<arrayList.size();i++){
                // 각 연걸내용 마다 실행

                // 방문했던 노드 이면
                if(visited[i]){
                    continue;
                }

                int[] info = arrayList.get(i);
                //System.out.println("이번 꺼: "+info[0]+" "+info[1]);
                if(end==info[0]){
                    visited[i]=true;
                    answer[info[1]-1]=end;
                    queue.add(new int[]{end,info[1]});
                }
                else if(end==info[1]){
                    visited[i]=true;
                    answer[info[0]-1]=end;
                    queue.add(new int[]{end,info[0]});
                }
            }
        }

        */
        StringBuilder sb = new StringBuilder();
        for(int i=2;i<answer.length;i++){
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);


    }

    static void printQueue(Queue<int[]> queue){
        for(int[] data : queue){
            System.out.print(data[0]+" "+data[1]+", ");
        }
        System.out.println();
    }

}
