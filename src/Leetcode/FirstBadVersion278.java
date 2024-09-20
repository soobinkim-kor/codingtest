package Leetcode;

public class FirstBadVersion278 {
    public int firstBadVersion(int n) {
        if(n == 1){
            return isBadVersion(1) ? 1: 0;
        }
        int start = 1;
        int end = n;
        int mid;
        while(start <= end){
            mid = start+(end-start)/2;
            if(!isBadVersion(mid)){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return start;
    }
    private boolean isBadVersion(int version){
        return version >= 3;
    }
}
