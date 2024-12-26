package techniques.sliding_window.dynamic_window_size;

import java.util.HashSet;

/*
Given a string s, find the length of the longest substring without repeating characters. - Medium
Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
 */
public class LengthOfLongestSubstring {

    private static int lengthOfLongestSubstring(String s) {

        int start = 0;
        int end = 0;
        int maxLen = 0;
        HashSet<Character> characterHashSet = new HashSet<>();

        while (end < s.length()) {

            char ch = s.charAt(end);

            if (characterHashSet.contains(ch)) {
                characterHashSet.remove(s.charAt(start));
                start++;
            } else {
                characterHashSet.add(ch);
                maxLen = Math.max(maxLen, end - start +1);
                end++;
            }

        }
        return maxLen;
    }
    public static void main(String[] args) {
        String str = "bbaa";
        System.out.println("Length of the longest sub string => "+ lengthOfLongestSubstring(str));
    }
}
