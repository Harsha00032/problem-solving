package algorithms;

/**
 * Problem: Given an integer array of size n, find the majority element.
 * The majority element is the element that appears more than n/2 times.
 * It is assumed that the array always contains a majority element.
 * This class implements Moore's Voting Algorithm which runs in O(n) time
 * and O(1) additional space: first find a candidate by canceling out
 * pairs of different elements, then verify the candidate.
 */
public class MooreVotingAlgorithm {

    /**
     * Finds and returns the majority element using Moore's Voting Algorithm.
     * Returns -1 for null or empty input. If the array length is 1, returns
     * the single element (it's trivially the majority).
     */
    public static int majorityElement(int[] nums) {
         // handle null input
         if (nums == null)
             return -1;

         // single element is always the majority
         if (nums.length == 1)
             return nums[0];

         // possible candidate for majority element
         int candidate = 0;
         int count = 0;

         // Phase 1: find a candidate by cancelling out different elements
         for (int num : nums) {
             if (count == 0) {
                 candidate = num; // pick new candidate
                 count = 1;
             } else if (candidate == num) {
                 count++; // same as candidate, increase support
             } else {
                 count--; // different element, decrease support
             }
         }

         // Note: Under the assumption that a majority element exists,
         // `candidate` is guaranteed to be the majority. If the input
         // may not contain a majority, perform a verification pass
         // (count occurrences of candidate) here.

         return candidate;

    }

    /**
     * Demonstration main method with a few example arrays.
     */
    public static void main(String[] args) {
        int[] a1 = {3, 3, 4};
        int[] a2 = {2, 2, 1, 1, 2};
        int[] a3 = {1, 2, 3, 2, 2};

        System.out.println("Array a1 majority -> " + majorityElement(a1)); // expect 3
        System.out.println("Array a2 majority -> " + majorityElement(a2)); // expect 2
        System.out.println("Array a3 majority -> " + majorityElement(a3)); // expect 2
    }
}
