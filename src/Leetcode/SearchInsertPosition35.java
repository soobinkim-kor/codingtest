package Leetcode;

public class SearchInsertPosition35 {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        int mid;
        while(start <= end){
            mid = (start + end) / 2;

            if(nums[mid] > target){
                end = mid - 1;
            }else if(nums[mid] < target){
                start = mid + 1;
            }else{
                return mid;
            }
        }
        return start;
    }
}
