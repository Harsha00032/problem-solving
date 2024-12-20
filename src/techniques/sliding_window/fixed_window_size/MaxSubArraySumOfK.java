package techniques.sliding_window.fixed_window_size;

/*
Find the maximum sum of a contiguous subarray of a fixed size 𝐾
Example:
arr[] = [2, 1, 5, 1, 3, 2]
K = 3
Output = 9
 */
public class MaxSubArraySumOfK {

    private static int maxSubArrayOfSizeK(int[] ints, int k) {

        if (ints == null || ints.length < k)
            return -1;

        int currentSum = 0;

        for (int i = 0; i < k; i++) {
            currentSum += ints[i];
        }

        int maxSum = currentSum;
        int start = 0;
        int end = k;

        while (end < ints.length) {
            currentSum += ints[end];
            currentSum -= ints[start];
            start++;
            end++;

            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int k = 3;
        int arr[] = {2, 1, 5, 1, 3, 2};

        System.out.println("The maximum sum of subarray of size K is " + maxSubArrayOfSizeK(arr, k));
    }
}
