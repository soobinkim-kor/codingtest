package 백준;/*

15

6 4
0100
1110
1000
0000
0111
0000

9

6 4
0000
1110
1000
0000
0111
0000


1 1
0

1?

2 2
01
10

3

-1
4 4
0111
1111
1111
1110

2 3
011
110

-1

* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class baek2206 {
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};

    // 벽을 부순 후 가능 경로와 부수지 않고 간 경로를 따로 체크
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");

        int N = Integer.parseInt(data[0]);
        int M = Integer.parseInt(data[1]);

        boolean[][] visited = new boolean[N][M];
        boolean[][] visited2 = new boolean[N][M];
        String[][] map = new String[N][M];
        for(int i=0;i<N;i++){
            String lines = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j]= String.valueOf(lines.charAt(j));
            }
        }

        printArray(map);

        if(N==1 && M==1){
            System.out.println(1);
            return;
        }

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0,0,1,1});
        visited[0][0]=true;
        while(!queue.isEmpty()){
            printQueue(queue);
            int[] loc = queue.poll();
            // loc[0] = x좌표
            // loc[1] = y좌표
            // loc[2] = 남은 벽 부시기 횟수
            // loc[3] = 길이
            //System.out.println("좌표: ("+loc[0]+", "+loc[1]+") 남은 부시기 횟수: "+loc[2]+", 현재까지 길이: " +loc[3]);

            if(loc[0]==N-1 && loc[1] == M-1){
                System.out.println(loc[3]);
                return;
            }

            for(int i=0;i<4;i++){
                int newX=loc[0]+dx[i];
                int newY=loc[1]+dy[i];


                // visited[][] = 뚫지 않고 간 길
                // visited2[][] = 뚫은 후 간 길
                // 방문 가능성 판별
                if(newX>=0 && newX<N && newY>=0 && newY<M){

                    // 방문 안한 곳이면
                    if(!visited[newX][newY] && !visited2[newX][newY]){
                        // 갈 수 있는 곳이면
                        if(map[newX][newY].equals("0")){
                            // 방문 처리
                            visited[newX][newY]=true;

                            // 길이 추가
                            queue.add(new int[]{newX,newY,loc[2],loc[3]+1});
                        }
                        // 막혀있고 부실 수 있으면
                        if(map[newX][newY].equals("1") && loc[2]==1){
                            if(!visited2[newX][newY]){
                                visited2[newX][newY]=true;
                                queue.add(new int[]{newX,newY,0,loc[3]+1});
                            }
                            // 방문 처리

                            // 길이 추가

                        }


                    }
                }
            }


        }

        System.out.println(-1);
    }

    public static void printArray(Object[][] data){
        for (Object[] datum : data) {
            for(Object d : datum){
                System.out.print(d+" ");
            }
            System.out.println();
        }
    }

    public static void printQueue(Queue<int[]> queue){
        for(int[] data : queue){
            System.out.print("["+data[0]+","+data[1]+" :"+data[2]+"] ");

        }
        System.out.println();
    }

    public class Data{
        private String[][] map;
        private int xaxis;
        private int yaxis;
        private int length;

        public void setMap(String[][] map){
            this.map=map;
        }
        public String[][] getMap(){
            return this.map;
        }

        public int getXaxis() {
            return xaxis;
        }

        public void setXaxis(int xaxis) {
            this.xaxis = xaxis;
        }

        public int getYaxis() {
            return yaxis;
        }

        public void setYaxis(int yaxis) {
            this.yaxis = yaxis;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }
}
