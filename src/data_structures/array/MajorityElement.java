package data_structures.array;

/*
You are given an array nums of size n. A majority element in this array is an element that appears more than n / 2 times, where n is the size of the array.
Your task is to find the majority element. It is guaranteed that the array always contains a majority element.
Example 1:
Input:
nums = [3, 2, 3]
Output:
3
 */

/**
 * Finds the majority element (appears > n/2 times) using Moore's Voting Algorithm.
 * The algorithm first finds a candidate by canceling out pairs of different elements,
 * then verifies the candidate by counting its occurrences.
 */
public class MajorityElement {

    // Applying Moore's Voting Algorithm - Each element carries a vote and vote itself as a possible candidate
    /**
     * Returns the majority element if present; otherwise -1.
     * @param nums input array (may contain a guaranteed majority element)
     */
    private static int majorityElement(int[] nums) {

        // defensive null check
        if (nums == null)
            return -1;

        int count = 0;      // vote counter
        int candidate = 0;  // current candidate for majority

        // Phase 1: find a candidate by pairing different elements (Moore's voting)
        for (int num : nums) {
            if (count == 0) {
                candidate = num; // pick new candidate when counter drops to zero
                count = 1;
            } else if (candidate == num)
                count++;         // same as candidate -> increment votes
            else
                count--;         // different -> cancel one vote
        }

        // Phase 2: verify the candidate actually appears > n/2 times
        count = 0;
        for (int num : nums) {
            if (num == candidate)
                count++;
        }

        if (count > nums.length / 2)
            return candidate;

        return -1;

    }

    public static void main(String[] args) {
        int[] inputArray = {1, 1, 2};
        System.out.println("The majority element is => " + majorityElement(inputArray));
    }
}
