package patterns.sliding_window.dynamic_window_size;

/*
Given an array of positive integers nums and a positive integer target, return the minimal length of a
subarray, whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

Example 1:
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinSubArrayOfSumK {

    private static int minSubArrayOfSumK(int[] nums, int target) {
        if (nums == null)
            return -1;

        int start = 0;
        int minLen = Integer.MAX_VALUE;
        int currentSum = 0;
        int end = 0;
        while (end < nums.length) {

            currentSum += nums[end];

            while (target <= currentSum) {
                minLen = Math.min(minLen, end-start+1);
                currentSum -= nums[start];
                start++;
            }
            end++;
        }

        if (minLen == Integer.MAX_VALUE)
            return 0;

        return minLen;
    }

    public static void main(String[] args) {
        int[] nums = {1,1};
        int target = 2;
        System.out.println("The minimum length of the subarray that sum to the given target => "+ minSubArrayOfSumK(nums, target));
    }
}
