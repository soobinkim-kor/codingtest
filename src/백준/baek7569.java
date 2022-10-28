package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class baek7569 {
    static int[] dz = { 0, 0, 0, 0, -1, 1 };
    static int[] dy = { 0, 0, -1, 1, 0, 0 };
    static int[] dx = { -1, 1, 0, 0, 0, 0 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int m = Integer.parseInt(data[0]);
        int n = Integer.parseInt(data[1]);
        int h = Integer.parseInt(data[2]);
        int[][][] boxes = new int[h][n][m];

        int count=0;

        Queue<int[]> queue = new LinkedList<>();

        for(int i=0;i<h;i++){
            for(int j=0;j<n;j++){
                String[] d = br.readLine().split(" ");
                for(int k=0;k<m;k++){
                    int tomato = Integer.parseInt(d[k]);

                    if(tomato==0){ // 안익은 토마토 갯수 새기
                        count++;
                    }
                    else if(tomato==1){ // 익은 토마토의 위치 큐에 추가
                        queue.add(new int[]{i,j,k});
                    }

                    boxes[i][j][k]= tomato;
                }
            }
        }

        if(count==0){
            System.out.println(0);
            return;
        }

        // queue 생성 후, 초기 익은 토마토의 위치들을 넣음
        // 이후 큐에 있는 값들을 빼가면서 영향을 주게 바꾸고, 새로 익은 토마토 위치를 큐에 추가
        int answer=0;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] location = queue.poll();
                int z = location[0];
                int y = location[1];
                int x = location[2];
                for (int j = 0; j < 6; j++) {
                    int nz = z + dz[j];
                    int ny = y + dy[j];
                    int nx = x + dx[j];

                    if (nz < 0 || ny < 0 || nx < 0 || nz >= h || ny >= n || nx >= m)
                        continue;
                    else if (boxes[nz][ny][nx] != 0)
                        continue;

                    count--;
                    boxes[nz][ny][nx] = 1;
                    queue.add(new int[]{nz, ny, nx});
                }
            }
            answer++;

        }
        if(count>0){
            System.out.println(-1);
            return;
        }
        System.out.println(answer-1);
    }

    public static void printArray(int[][][] array){
        for(int[][] data : array){
            for(int[] dat : data){
                for(Integer da : dat){
                    System.out.print(da+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
