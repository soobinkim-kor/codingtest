import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class baek14500 {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] visited;
    static int r,c,answer;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        r = Integer.parseInt(data[0]);
        c = Integer.parseInt(data[1]);
        answer=0;
        board = new int[r][c];

        for(int i=0;i<r;i++){
            String[] d = br.readLine().split(" ");
            for(int j=0;j<c;j++){
                board[i][j]=Integer.parseInt(d[j]);
            }
        }

        // 한개의 점을 기준으로 4칸짜리 dfs 하면 됨

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                visited = new boolean[r][c];
                //dfs(i,j,0,0);
                bfs(i,j);
            }
        }

        // ㅁ 모양
        for(int i=0;i<r-1;i++){
            for(int j=0;j<c-1;j++){
                answer=Math.max(answer,board[i][j]+board[i+1][j]+board[i][j+1]+board[i+1][j+1]);
            }
        }

        // ㅗ 모양

        // 가로
        for(int i=0;i<r;i++){
            for(int j=0;j<c-2;j++){
                if(i==0){   // 첫줄일 때 아래만 보면 됨
                    answer=Math.max(answer,board[i][j]+board[i][j+1]+board[i][j+2]+board[i+1][j+1]);
                }else if(i==r-1){       // 마지막 줄일 때 위만 보면 됨
                    answer=Math.max(answer,board[i][j]+board[i][j+1]+board[i][j+2]+board[i-1][j+1]);
                }else{                  // 사이일 땐 위아래 둘다 확인
                    answer=Math.max(answer,board[i][j]+board[i][j+1]+board[i][j+2]+Math.max(board[i+1][j+1],board[i-1][j+1]));
                }
            }
        }

        // 세로
        for(int i=0;i<r-2;i++){
            for(int j=0;j<c;j++){
                if(j==0){       // 맨 왼쪽에 붙은 길이 3 작대기
                    answer=Math.max(answer,board[i][j]+board[i+1][j]+board[i+2][j]+board[i+1][j+1]);
                }else if(j==c-1){ // 맨 오른쪽에 붙은 길이 3 작대기
                    answer=Math.max(answer,board[i][j]+board[i+1][j]+board[i+2][j]+board[i+1][j-1]);
                }else{ // 가운데 (양옆확인)
                    answer=Math.max(answer,board[i][j]+board[i+1][j]+board[i+2][j]+Math.max(board[i+1][j+1],board[i+1][j-1]));
                }
            }
        }

        System.out.println(answer);
    }
    public static void dfs(int x, int y, int length, int sum){
        System.out.println(x+", "+y);
        if(length==4){
            answer=Math.max(answer,sum);
        }
        for(int i=0;i<4;i++){
            int newX = x+dx[i];
            int newY = y+dy[i];
            if(newX>0 && newY>0 && newX<r && newY<c){
                if(!visited[newX][newY]){
                    visited[newX][newY]=true;
                    dfs(newX,newY,length+1,sum+board[newX][newY]);
                    visited[newX][newY]=false;
                }
            }
        }
        System.out.println("끝");
    }

    public static void bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y,1,board[x][y]});
        while(!queue.isEmpty()){

            int[] data = queue.poll();
            x=data[0];
            y=data[1];
            int length = data[2];
            int sum = data[3];

            if(length==4){
                answer=Math.max(answer,sum);
            }
            for(int i=0;i<4;i++){
                int newX = x+dx[i];
                int newY = y+dy[i];
                if(newX>0 && newY>0 && newX<r && newY<c){
                    if(!visited[newX][newY]){
                        visited[newX][newY]=true;
                        queue.add(new int[]{newX,newY,length+1,sum+board[newX][newY]});
                    }
                }
            }
        }
    }
}
