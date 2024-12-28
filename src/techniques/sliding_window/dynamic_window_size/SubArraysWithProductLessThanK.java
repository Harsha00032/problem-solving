package techniques.sliding_window.dynamic_window_size;

/*
Given an array with positive numbers and a positive target number, find all of its contiguous subarrays whose product is less than the target number.
Example 1:
Input: [2, 5, 3, 10], target=30
Output: [2], [5], [2, 5], [3], [5, 3], [10]
Explanation: There are six contiguous subarrays whose product is less than the target.
 */
public class SubArraysWithProductLessThanK {

    private static int subArraysWithProductLessThanK(int[] nums, int target) {

        int start = 0;
        int end = 0;
        int product = 1;
        int count = 0;

        while (end < nums.length) {
            product *= nums[end];

            //shrink the window till your product is less than the target
            while (product >= target && start <= end) {
                product /= nums[start];
                start++;
            }

            // formula is based on the fact that add a new element in the subarray would potentially
            // add eligible subArrays of it's length
            count += end - start + 1;
            end++;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 3, 10};
        System.out.println("The number of sub arrays with count less than target => " + subArraysWithProductLessThanK(nums, 30));

    }

}
