package techniques.two_pointers;

/*
Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.
Example 1:
Input: [1, 2, 5, 3, 7, 10, 9, 12]
Output: 5
Explanation: We need to sort only the subarray [5, 3, 7, 10, 9] to make the whole array sorted
 */
public class MinimumWindowSort {

    private static int minimumWindowSort(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = nums.length - 1;

        // find the first out of order element form the beginning
        while (left < n - 1 && nums[left] <= nums[left + 1]) {
            left++;
        }

        // array is already in sorted order
        if (left == n - 1)
            return 0;

        // find the first out of order element from the end
        while (right > 0 && nums[right] >= nums[right - 1]) {
            right--;
        }

        int subArrayMin = nums[left];
        int subArrayMax = nums[right];

        for (int i = left; i <= right; i++) {
            subArrayMin = Math.min(nums[i], subArrayMin);
            subArrayMax = Math.max(nums[i], subArrayMax);
        }

        while (left >= 0 && nums[left] > subArrayMin) {
            left--;
        }

        while (right <= n-1 && nums[right] < subArrayMax) {
            right++;
        }

        return right - (left + 1);

    }

    public static void main(String[] args) {
        int[] nums = {2,6,4,0,10,9,15};
        System.out.println("The minimumWindowSort is " + minimumWindowSort(nums));
    }
}
