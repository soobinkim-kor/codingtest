package Leetcode;

public class FindMinimuminRotatedSortedArray153 {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int answer = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = (start + end) / 2;
            if(nums[mid] <= nums[end]){
                end = mid - 1;
                answer = Math.min(nums[mid], answer);
            }else{
                start = mid + 1;

            }
        }
        return answer;
    }
}
