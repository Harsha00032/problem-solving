package algorithms.binary_search;

/* Dec - 18 Revise on Dec 21st and On Dec 28th
Problem: You are given a sorted array that has been rotated at an unknown pivot. Find the index of a given target element.
Example:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
 */

public class SearchInARotatedArray {

    /*
    Approach : Since binary search only work with sorted array, we need to find out which part of the input
    array that is sorted and check if that part contain the target element
    1. we need to find out which part is sorted ?
    2. once found check if target is within the selected sorted path ?
    3. if yes apply binary search on that part
    4. if no then we need to goto step 1 with unsorted path as input array
     */
    private static int searchTarget(int[] nums, int target) {

        if (nums == null)
            return -1;

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target)
                return mid;
            // Determine which part of the array is sorted
            if (nums[low] <= nums[mid]) { // left part is sorted
                if (nums[low] <= target && target < nums[mid]) { // check if left part may contain the target
                    high = mid - 1;
                } else {
                    low = mid + 1; // we should search in the right part
                }
            } else { // right part is sorted
                if (nums[mid] < target && target <= nums[high]) { // check if right part may contain the target
                    low = mid + 1;
                } else  // we should search in the left part
                    high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        int result = searchTarget(nums, target);
        System.out.println("Index of target " + target + ": " + result);
    }
}
