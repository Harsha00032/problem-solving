package techniques.two_pointers;

import java.util.Arrays;

/*
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

You must do this in-place without making a copy of the array and minimize the total number of operations.

Example:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
 */
public class MoveZerosInPlace {

    private static void moveZeros(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] != 0)
                left ++;
            else if (arr[right] == 0)
                right --;
            else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left ++;
                right --;
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {0,0,0,2,0,0,0,0,0,-1,90,3,0,1,2,0,5,0,0};
        System.out.println("Given => "+Arrays.toString(arr));
        moveZeros(arr);
        System.out.println("Zeros Moved => "+Arrays.toString(arr));
    }
}
