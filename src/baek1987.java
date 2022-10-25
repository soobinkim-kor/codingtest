import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class baek1987 {
    private static final int[] dx = {1,-1,0,0};
    private static final int[] dy = {0,0,1,-1};
    static int answer, r, c;
    static Character[][] board;
    static ArrayList<String> arr;

    static boolean[] visitedChar;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rAndC = br.readLine().split(" ");
        r = Integer.parseInt(rAndC[0]);
        c = Integer.parseInt(rAndC[1]);

        board = new Character[r][c];
        for (int i = 0; i < r; i++) {
            String[] data = br.readLine().split("");
            for(int j   =   0   ;   j   <   c   ;   j++){
                board[i][j]=data[i].charAt(j);
            }
        }

        visitedChar = new boolean[26];



        dfs(0,0,1);

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int count){

        if(answer<count){
            answer=count;
        }

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX < r && newY < c && newX >= 0 && newY >= 0) {

                if ( !visitedChar[board[newX][newY]-'A']){
                    visitedChar[board[newX][newY]-'A']=true;

                    dfs(newX,newY,count+1);

                    visitedChar[board[newX][newY]-'A']=false;
                }

            }

        }
    }
}
