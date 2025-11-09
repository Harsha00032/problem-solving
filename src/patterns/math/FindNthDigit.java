package patterns.math;

/*
Given an integer n, return the nth digit of the infinite integer sequence
 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] - Medium
Example 1:
Input: n = 3
Output: 3
Example 2:

Input: n = 11
Output: 0
Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */
public class FindNthDigit {

    private static int findNthDigit(int n) {

        long number = 1; // start number of particular no of digits
        long digits = 1; // no of digits
        long digitsSoFar = 9; // total no of digits possible for the given no of digits


        while (n > digitsSoFar * digits) {
            n -= (int) (digitsSoFar * digits);
            digitsSoFar *= 10;
            number *= 10;
            digits++;
        }

        number = number + (n-1)/digits;
       int digitIndex = (int) ((n-1) % digits);

       return Long.toString(number).charAt(digitIndex) - '0';
    }
    public static void main(String[] args) {
        int n = 3600;
        System.out.println("The "+ n +"th digit in the infinite sequence is => "+ findNthDigit(n));
    }
}
