package Leetcode;

public class NumberOfIslands200 {
    public static int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    public int numIslands(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int answer = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == '1'){
                    answer++;
                    recursive(i,j,rows,cols, grid);
                }
            }
        }
        return answer;
    }
    public void recursive(int i, int j, int rows, int cols, char[][] grid){
        if(grid[i][j] == '0'){
            return;
        }
        grid[i][j] = '0';

        for(int dir=0; dir<4; dir++){
            int newI = i+directions[dir][0];
            int newJ = j+directions[dir][1];
            if(newI < rows && newJ < cols && newI >= 0 && newJ >= 0){
                recursive(newI, newJ, rows, cols, grid);
            }
        }
    }

}
