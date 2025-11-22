package algorithms.sort;

import java.util.Arrays;

/*
Sorts by iterating through the elements from the unsorted part and
inserting an element in it's right position in the current sorted path in each iteration
 */
public class InsertionSort {

    // Insertion Sort algorithm
    private static void insertionSort(int[] nums) {

        // handle null or single element array
        if (nums == null || nums.length <= 1)
            return;

        // Insertion Sort logic
        for (int i=1; i<nums.length; i++) {
            // place nums[i] in its correct position among nums[0..i-1]
            for (int j=i; j>0; j--) {
                if (nums[j] < nums[j-1]){
                    int temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] unsortedInts = {11,34,44,55,66,77,22,33,88,99,0};
        System.out.println("The Given unsorted list ==>");
        System.out.println(Arrays.toString(unsortedInts));
        insertionSort(unsortedInts);
        System.out.println("After Sorting ==>");
        System.out.println(Arrays.toString(unsortedInts));


    }
}
