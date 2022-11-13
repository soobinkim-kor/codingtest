package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class baek2178 {
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        int n = Integer.parseInt(size[0]);
        int m = Integer.parseInt(size[1]);
        int[][] map = new int[n][m];

        for(int i=0;i<n;i++){
            String data = br.readLine();
            for(int j=0;j<m;j++){
                map[i][j]= data.charAt(j)-48;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,1});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];
//            if(row==n-1 && col==m-1){
//                //answer=distance;
//                break;
//            }
            for(int i=0;i<4;i++){
                int newRow = row+dx[i];
                int newCol = col+dy[i];

                if(newRow>=0 && newRow<n && newCol>=0 && newCol<m){
                    if(map[newRow][newCol]==1){
                        map[newRow][newCol]=distance+1;
                        queue.add(new int[]{newRow,newCol,distance+1});
                    }
                }
            }
        }
        System.out.println(map[n-1][m-1]);

    }
}
