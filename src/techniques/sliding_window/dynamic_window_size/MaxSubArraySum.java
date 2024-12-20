package techniques.sliding_window.dynamic_window_size;


/*
Problem Statement
Given an integer array nums, find the contiguous subArray (containing at least one number) that has the largest sum and return its sum.

Example 1:
makefile
Copy code
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subArray [4,-1,2,1] has the largest sum = 6.
 */
public class MaxSubArraySum {

    // Kadane's Algorithm
    private static int maxSubArray(int[] ints) {

        if (ints == null)
            return -1;

        int currentSum = ints[0];
        int maxSum = currentSum;
        int end = 1;

        while (end < ints.length) {
            currentSum = Math.max(currentSum + ints[end], ints[end]);
            maxSum = Math.max(currentSum, maxSum);
            end++;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum: " + maxSubArray(nums)); // Output: 6
    }
}
