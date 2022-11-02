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
        ArrayList<int[]> arrayList = new ArrayList<>();
        int[] answer = new int[n];
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<n-1;i++){
            String[] info = br.readLine().split(" ");
            int start = Integer.parseInt(info[0]);
            int end = Integer.parseInt(info[1]);
            arrayList.add(new int[]{start,end});
        }
        boolean[] visited = new boolean[n];

        queue.add(new int[]{0,1});
        while(!queue.isEmpty()){
            //printQueue(queue);
            int[] data = queue.poll();
            int start = data[0];
            int end = data[1];

            for(int i=0;i<arrayList.size();i++){
                int[] info = arrayList.get(i);
                if(end==info[0] && !visited[info[1]-1]){
                    visited[info[1]-1]=true;
                    answer[info[1]-1]=end;
                    queue.add(new int[]{end,info[1]});
                }
                else if(end==info[1] && !visited[info[0]-1]){
                    visited[info[1]-1]=true;
                    answer[info[0]-1]=end;
                    queue.add(new int[]{end,info[0]});
                }
            }
        }

        for(int d : answer){
            System.out.println(d);
        }

    }

    static void printQueue(Queue<int[]> queue){
        for(int[] data : queue){
            System.out.print(data[0]+" "+data[1]+", ");
        }
        System.out.println();
    }

}
