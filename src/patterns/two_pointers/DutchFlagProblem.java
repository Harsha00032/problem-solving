package patterns.two_pointers;

/*
The Dutch National Flag problem, proposed by Edsger Dijkstra, involves sorting an array of 0s, 1s, and 2s
such that all 0s come first, followed by all 1s, and then all 2s. The goal is to do this in a single pass through
 the array and with constant space.
 */
public class DutchFlagProblem {

    private static void sortColors(int[] nums) {

        int mid =0; // tracks current element
        int low =0; // tracks 0's
        int high = nums.length -1; // tracks 2's

        while (mid <= high) {
            if (nums [mid] == 0) {
                nums[mid] = nums[low];
                nums[low] = 0;
                low++;
                mid++;
            } else if (nums[mid] == 1){
                mid ++;
            } else {
                nums[mid] = nums[high];
                nums[high] = 2;
                high--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 1, 0, 2, 2, 1, 0, 0, 2};
        System.out.println("Before sorting: ");
        printArray(nums);

        sortColors(nums);

        System.out.println("After sorting: ");
        printArray(nums);
    }

    // Function to print the array
    public static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
