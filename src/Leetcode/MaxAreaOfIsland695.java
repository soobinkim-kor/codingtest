package Leetcode;

public class MaxAreaOfIsland695 {
    public static int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    public int maxAreaOfIsland(char[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int answer = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == '1'){
//                    answer++;
                    answer = Math.max(answer,recursive(i,j,rows,cols, grid));
//                    recursive(0, i,j,rows,cols, grid);
                }
            }
        }
        return answer;
    }
    public int recursive(int i, int j, int rows, int cols, char[][] grid){
        // 물인 경우 (재귀 종료)
        if(i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] == '0') {
            return 0; // 현재 면적 0 반환
        }

        grid[i][j] = '0';
        int area = 1;
        for(int dir=0; dir<4; dir++){
            int newI = i+directions[dir][0];
            int newJ = j+directions[dir][1];
            area += recursive(newI, newJ, rows, cols, grid);

        }
        return area;
    }
}
