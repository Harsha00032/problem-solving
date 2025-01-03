package data_structures.array;

import java.util.HashSet;

/*
Given an unsorted array of integers nums, find the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time complexity.
Example 1
Input:
nums = [100, 4, 200, 1, 3, 2]
Output:
4
Explanation:
The longest consecutive sequence is [1, 2, 3, 4], which has a length of 4.
 */
public class LongestConsecutiveSequence {

    private static int longestConsecutiveSequence(int[] nums) {

        if (nums == null)
            return -1;

        HashSet<Integer> integerHashSet = new HashSet<>();

        for (int num : nums) {
            integerHashSet.add(num);
        }

        int maxLen = 0;


        for (int num : integerHashSet) {

            //check if the current number is a start of a sequence
            if (!integerHashSet.contains(num - 1)) {

               int next = num;

                // count the length of the sequence
                while (integerHashSet.contains(next)) {
                    next++;
                }

                maxLen = Math.max(maxLen, next - num);
            }

        }

        return maxLen;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println("Longest Consecutive Sequence is => " + longestConsecutiveSequence(nums));
        long endTime = System.nanoTime();
        System.out.println("The total execution time is "+ (endTime - startTime)/1_000_000+ " ms");
    }
}
