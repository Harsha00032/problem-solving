package techniques.sliding_window.dynamic_window_size;

import java.util.HashMap;
import java.util.HashSet;

/*
Given a string, find the length of the longest substring in it with no more than K distinct characters.
You can assume that K is less than or equal to the length of the given string.
Example 1:
Input: String="araaci", K=2
Output: 4
Explanation: The longest substring with no more than '2' distinct characters is "araa".
 */
public class LengthOfTheLongestSubstringWithKDistinctCharacters {

    private static int lengthOfTheLongestSubstringWithKDistinctCharacters(String string, int k) {

        // map to track character and their frequencies
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();

        int start = 0;
        int maxlen = 0;

        for (int end = 0; end < string.length(); end++) {

            // expand the window
            char currentChar = string.charAt(end);
            characterIntegerHashMap
                    .put(string.charAt(end), characterIntegerHashMap.getOrDefault(currentChar, 0) + 1);

            // shrink the window till the distinct elements in the current window are less than or equal to K
            while (characterIntegerHashMap.size() > k) {
                currentChar = string.charAt(start);
                int freq = characterIntegerHashMap.get(currentChar);
                freq--;
                if (freq == 0) {
                    characterIntegerHashMap.remove(currentChar);
                } else
                    characterIntegerHashMap.put(currentChar, freq);

                start++;
            }

            maxlen = Math.max(maxlen, end - start + 1); // check if the current window could be the result
        }
        return maxlen;
    }

    public static void main(String[] args) {
        String string = "aabbaccddeeeff";
        System.out.println("The result is => " + lengthOfTheLongestSubstringWithKDistinctCharacters(string, 3));
    }
}
