package patterns.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Given an array of intervals where intervals[i] = [starti, endi],
merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 */
public class MergeIntervals {
    private static int[][] mergeIntervals(int[][] intervals){

        //base condition check
        if (intervals == null || intervals.length < 2)
            return intervals;

        // sort the arrays based on start of the interval
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> resultList = new ArrayList<>();

        int start = intervals[0][0];
        int end = intervals[0][1];

        for(int i=1; i<intervals.length; i++) {
            // check if the interval is overlapping
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                // no overlap, add to the result list and update start and end
                resultList.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }

        resultList.add(new int[]{start, end});

        return resultList.toArray(new int[resultList.size()][2]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {3, 6}, {8, 10}, {1, 7}};
        int[][] result = mergeIntervals(intervals);

        // Print the result
        System.out.println("Merged intervals:");
        for (int[] interval : result) {
            System.out.print(Arrays.toString(interval));
        }
    }
}
