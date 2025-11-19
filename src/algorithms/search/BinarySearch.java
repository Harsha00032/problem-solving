package algorithms.search;


/*
Implementation of Binary Search algorithm in Java.
 */
public class BinarySearch {

    private static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length <= 0)
            return -1;
        int len = nums.length;
        int low =0;
        int high = len-1;

        while (low <= high) {
            int mid = (low+high)/2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target) {
                low = mid+1;
            } else
                high = mid-1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40, 50};
        int target = 12;
        int result = binarySearch(arr, target);
        if (result == -1) {
            System.out.println("Element not present in array");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }
}
