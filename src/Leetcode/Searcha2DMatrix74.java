package Leetcode;

public class Searcha2DMatrix74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int rowIndex = -1;

        for (int i = 0; i < rows; i++) {
            if(target >= matrix[i][0] && target <= matrix[i][cols - 1]){
                rowIndex = i;
                break;
            }
        }
        if(rowIndex == -1){
            return false;
        }

        int[] searchRow = matrix[rowIndex];

        int start = 0;
        int end = cols - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if(target < searchRow[mid]){
                end = mid - 1;
            }else if(target > searchRow[mid]){
                start = mid + 1;
            }else{
                return true;
            }
        }

        return false;
    }
}
