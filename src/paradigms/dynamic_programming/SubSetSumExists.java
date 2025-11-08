package paradigms.dynamic_programming;

/**
 * Utility class demonstrating several DP approaches to the Subset Sum problem.
 * Contains: 2D DP (tabulation), 1D DP using two arrays, and a fully space-optimized 1D DP.
 */
public class SubSetSumExists {

    /**
     * Checks if there exists a subset of 'arr' that sums up to 'target'
     * using Dynamic Programming (2D tabulation).
     *
     * @param arr    input array of positive integers
     * @param target target sum
     * @return true if subset exists, otherwise false
     */
    public static boolean subsetSumExists2D(int[] arr, int target) {
        int n = arr.length;
        // 2-dimensional DP table: dp[i][s] = is it possible to get sum 's' using first i elements
        boolean[][] dp = new boolean[n+1][target+1];
        // base condition - zero-sum is always possible (empty subset)
        for (int index =0; index <= n; index++) {
            dp[index][0] = true;
         }
        // fill the table using the recurrence:
        // dp[i][sum] = dp[i-1][sum] (not take) || dp[i-1][sum-arr[i-1]] (take)
        for (int index=1; index <=n; index++) {
            for (int sum =1; sum <= target; sum++) {
                // not taking current element
                boolean notTaken = dp[index-1][sum];
                // taking current element (only if it does not exceed current sum)
                boolean taken = false;
                if (sum - arr[index-1] >= 0) {
                    taken = dp[index-1][sum-arr[index-1]];
                }
                dp[index][sum] = taken || notTaken;
            }
        }

        return dp[n][target];
    }

    // This will reduce the space complexity to O(2*target)
    /**
     * 1D DP using two arrays: previous and current row simulation.
     * prev[s] stores whether sum s is possible using processed elements so far.
     */
    public static boolean subsetSumExistsWith2Arrays(int[] nums, int target) {
        boolean[] prev = new boolean[target+1];
        // base condition: sum 0 is always achievable
        prev[0] = true;

        for (int num : nums) {
            // curr represents dp for the current element considered
            boolean[] curr = new boolean[target+1];
            for (int sum = 0; sum <= target; sum++) {
                // if we don't take current number, inherit previous result
                boolean notTaken = prev[sum];
                boolean taken = false;
                // if taking current number is possible, check previous state for (sum-num)
                if (sum - num >= 0) {
                    taken = prev[sum - num];
                }
                curr[sum] = taken || notTaken;
            }
            // move to next iteration
            prev = curr;
        }
        return prev[target];
    }

    /**
     * Space-optimized 1D DP: uses a single array and iterates sums backwards
     * so that each number is only used once per iteration (prevents reuse).
     */
    private static boolean subsetSumExistsSpaceOptimized(int[] nums, int target) {
        boolean[] prev = new boolean[target+1];
        prev[0] = true; // zero-sum always possible

        for (int num : nums) {
            // iterate sums from target down to num to avoid using updated states in the same iteration
            for (int sum = target; sum >= num; sum--) {
                prev[sum] = prev[sum] || prev[sum-num];
            }
        }
        return prev[target];
    }




    public static void main(String[] args) {
        System.out.println("execution starts.......");
        int[] arr1 = {3, 34, 4, 12, 5, 2};
        int target1 = 9;
        System.out.println("Array: {3, 34, 4, 12, 5, 2}, Target = 9 → " +
                subsetSumExists2D(arr1, target1));

        int[] arr2 = {1, 2, 3, 7};
        int target2 = 6;
        System.out.println("Array: {1, 2, 3, 7}, Target = 6 → " +
                subsetSumExists2D(arr2, target2));

        int[] arr3 = {2, 4, 6, 8};
        int target3 = 5;
        System.out.println("Array: {2, 4, 6, 8}, Target = 5 → " +
                subsetSumExists2D(arr3, target3));

        System.out.println("execution starts.......");
        int[] arr11 = {3, 34, 4, 12, 5, 2};
        int target11 = 9;
        System.out.println("Array: {3, 34, 4, 12, 5, 2}, Target = 9 → " +
                subsetSumExistsWith2Arrays(arr11, target11));

        int[] arr21 = {1, 2, 3, 7};
        int target21 = 6;
        System.out.println("Array: {1, 2, 3, 7}, Target = 6 → " +
                subsetSumExistsWith2Arrays(arr21, target21));

        int[] arr31 = {2, 4, 6, 8};
        int target31 = 5;
        System.out.println("Array: {2, 4, 6, 8}, Target = 5 → " +
                subsetSumExistsWith2Arrays(arr31, target31));

        System.out.println("execution starts.......");
        int[] arr111 = {3, 34, 4, 12, 5, 2};
        int target111 = 9;
        System.out.println("Array: {3, 34, 4, 12, 5, 2}, Target = 9 → " +
                subsetSumExistsSpaceOptimized(arr111, target111));

        int[] arr211 = {1, 2, 3, 7};
        int target211 = 6;
        System.out.println("Array: {1, 2, 3, 7}, Target = 6 → " +
                subsetSumExistsSpaceOptimized(arr211, target211));

        int[] arr311 = {2, 4, 6, 8};
        int target311 = 5;
        System.out.println("Array: {2, 4, 6, 8}, Target = 5 → " +
                subsetSumExistsSpaceOptimized(arr311, target311));
    }
}
