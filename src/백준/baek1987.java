package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baek1987 {
    private static final int[] dx = {1,-1,0,0};
    private static final int[] dy = {0,0,1,-1};
    static int r, c, answer;

    static boolean[] visitedChar;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        r = Integer.parseInt(size[0]);
        c = Integer.parseInt(size[1]);

        char[][] board = new char[r][c];
        for (int i = 0; i < r; i++) {

            String data = br.readLine();

            for(int j=0; j<c; j++){

                board[i][j]=data.charAt(j);
            }
        }

        visitedChar = new boolean[26];

        visitedChar[board[0][0]-'A']=true;
        answer=0;
        dfs(0,0,1,board);

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int count, char[][] board){
        answer = Math.max(answer,count);
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX < r && newY < c && newX >= 0 && newY >= 0) {

                if (!visitedChar[board[newX][newY]-'A']){
                    visitedChar[board[newX][newY]-'A']=true;
                    dfs(newX,newY,count+1, board);
                    visitedChar[board[newX][newY]-'A']=false;
                }
            }
        }
    }
}
