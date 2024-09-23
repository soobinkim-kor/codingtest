package Leetcode;

public class FindMinimuminRotatedSortedArray153 {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        if(nums[start] < nums[end] || end == 0){
            return nums[start];
        }
        int mid = 0;
        while (start < end) {
            mid = (start + end) / 2;
            if(nums[mid - 1] < nums[mid]){
                start = mid + 1;
            }else{
                return nums[mid];
            }
        }
        return nums[mid-1];
    }
}
