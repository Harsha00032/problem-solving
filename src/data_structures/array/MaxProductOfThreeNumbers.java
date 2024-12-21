package data_structures.array;

/*
Given an integer array nums, find three numbers whose product is maximum and return the maximum product.
Example 1:
Input: nums = [-100,-98,-1,2,3,4]
Output: 39200

 */
public class MaxProductOfThreeNumbers {

    private static int maxProductOfThreeNumbers(int[] nums){

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {

            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3)
                max3 = num;
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2)
                min2 = num;
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println("Maximum Product of Three Numbers: " + maxProductOfThreeNumbers(nums)); // Output: 39200
    }
}
