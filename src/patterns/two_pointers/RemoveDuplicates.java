package patterns.two_pointers;

/*
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.

Since it is not allowed to allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

After removing the duplicates, return the number of unique elements in nums.

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]

 */
public class RemoveDuplicates {

    private static int uniqueElementsLen(int[] nums){


        // Base condition
        if (nums.length <= 1)
            return nums.length;

        int left = 1;
        int len = 1;

        while (left < nums.length){

            // skip duplicates
            while (left < nums.length && nums[left] == nums[left-1]) {
                left++;
            }

            // increase len only when a unique element is found
            if(left < nums.length)
                len++;
            left++;
        }

        return len;

    }

    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(uniqueElementsLen(nums));
    }
}
