package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class baek11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<int[]> arrayList = new ArrayList<>();
        int[] answer = new int[n-1];
        for(int i=0;i<n-1;i++){
            String[] info = br.readLine().split(" ");
            int start = Integer.parseInt(info[0]);
            int end = Integer.parseInt(info[1]);
            arrayList.add(new int[]{start,end});
        }

        boolean[] visited = new boolean[n-1];


    }

}
