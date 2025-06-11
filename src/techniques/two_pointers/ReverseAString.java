package techniques.two_pointers;

import java.util.Arrays;

/* Write a function that reverses a string.
The input string is given as an array of characters s.
You must do this by modifying the input array in place with O(1) extra memory.

Example:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]

 */
public class ReverseAString {

    private static String reverse(String string) {

        char[] chars = string.toCharArray();

        int left = 0;
        int right = chars.length -1;

        while (left < right) {
            char temp  = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
         }

        return Arrays.toString(chars);
    }

    public static void main(String[] args) {
        String str = "hello";
        System.out.println("After reverse => "+ reverse(str));
    }
}
