package techniques.two_pointers;

import java.util.Arrays;

/*
Problem Statement
You are given a 1-indexed array of integers numbers that is already sorted in non-decreasing order.
You need to find two numbers such that they add up to a specific target number. Return the indices of the two numbers (as a 1-based array) as an integer array answer of size 2.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You must solve the problem using only constant extra space.

Example
numbers = [2,7,11,15], target = 9
o/p = {1,2};
 */
public class TwoSumII {

    private static int[] twoSum(int[] numbers, int target) {

        int left =0;
        int right = numbers.length -1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum > target)
                right--;
            else if (sum < target)
                left ++;
            else
                return new int[]{++left, ++ right};
        }

        return new int[]{-1, -1};

    }

    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};

        System.out.println(Arrays.toString(twoSum(numbers, 9)));
    }
}
