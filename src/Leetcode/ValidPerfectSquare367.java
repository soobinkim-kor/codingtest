package Leetcode;

public class ValidPerfectSquare367 {
    public boolean isPerfectSquare(int num) {
        int start = 1;
        int end = num;

        long target;
        while(start <= end){
            target = (start + end)/2;
            if((long) target * target < (long) num){
                start = (int) (target + 1);
            }else if((long) target * target > (long) num){
                end = (int) (target - 1);
            }else{
                return true;
            }
        }
        return false;
    }
}
