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
public class MajorityElement {

    // Applying Moore's Voting Algorithm - Each element carries a vote and vote itself as a possible candidate
    private static int majorityElement(int[] nums) {

        if (nums == null)
            return -1;

        int count = 0;
        int candidate = 0;

        // Identify the possible candidate
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (candidate == num)
                count++;
            else
                count--;
        }

        // Verify the candidate
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
