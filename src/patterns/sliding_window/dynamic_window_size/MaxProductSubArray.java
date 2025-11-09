package patterns.sliding_window.dynamic_window_size;

/*
Given an integer array nums, find the contiguous subArray within the array (containing at least one number) which has the largest product and return its product.

Example 1:
makefile
Copy code
Input: nums = [2,3,-2,4,5]
Output: 6
Explanation: The subArray [2,3] has the largest product = 6.
 */

public class MaxProductSubArray {

    private static int maxProductSubArray(int[] nums) {

        if (nums == null)
            return -1;

        int currentMax = nums[0];
        int currentMin = nums[0];

        int maxProduct = currentMax;

        int end = 1;

        while (end < nums.length) {
            int currentValue = nums[end];
            end++;

            if (currentValue < 0) {
                int temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }

            currentMax = Math.max(currentValue, currentMax * currentValue);
            currentMin = Math.min(currentValue, currentMin* currentValue);

            maxProduct = Math.max(currentMax, maxProduct);
        }


        return maxProduct;

    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        System.out.println("Maximum Product Subarray: " + maxProductSubArray(nums)); // Output: 6

        nums = new int[]{-2, 3, -4};
        System.out.println("Maximum Product Subarray: " + maxProductSubArray(nums)); // Output: 24
    }
}
