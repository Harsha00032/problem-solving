package data_structures.string;

/*
You are given an array of characters chars where some characters may appear consecutively.
Your task is to compress this array using the following rules:

1. Replace consecutive repeating characters with the character followed by its count.
2. If a character occurs only once, keep it as is.
3. Return a new array containing the compressed characters.

Input: chars = ['a', 'a', 'b', 'b', 'c', 'c', 'c']
Output: ['a', '2', 'b', '2', 'c', '3']
 */
public class StringCompression {

    private static char[] compress(char[] chars) {

        // base condition and null check
        if (chars == null || chars.length <= 1)
            return chars;

        int len = chars.length;
        StringBuilder compressedChars = new StringBuilder(len);

        for (int i =0; i < len; i++) {

            char ch = chars[i];
            int count = 0;

            // increase the count when the next element is same as the current element
            while (i < len && ch == chars[i]){
                count++;
                i++;
            }

            // decrease i to consider the element as current element  to counter outer loop increment
            i--;

            compressedChars.append(ch);
            if (count > 1)
                compressedChars.append(count);

        }

        return compressedChars.toString().toCharArray();
    }

    public static void main(String[] args) {
        char[] chars1 = {'a', 'a', 'a', 'a', 'a','a', 'a', 'a','a', 'a', 'a','a', 'a', 'a','a', 'a', 'a','a', 'a', 'a','a', 'a', 'a','b', 'c', 'c', 'c'};
        System.out.println(new String(compress(chars1))); // Output: a2b2c3

        char[] chars2 = {'a'};
        System.out.println(new String(compress(chars2))); // Output: a

        char[] chars3 = {'a', 'b', 'c'};
        System.out.println(new String(compress(chars3))); // Output: abc

        char[] chars4 = {'a', 'a', 'a', 'b', 'b', 'a', 'a'};
        System.out.println(new String(compress(chars4))); // Output: a3b2a2
    }
}
