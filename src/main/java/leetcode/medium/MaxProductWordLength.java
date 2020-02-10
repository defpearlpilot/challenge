package leetcode.medium;

//https://leetcode.com/problems/maximum-product-of-word-lengths/

import java.util.HashSet;
import java.util.Set;

public class MaxProductWordLength {
    /*
    Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the
    two words do not share common letters. You may assume that each word will contain only lower case
    letters. If no such two words exist, return 0.
     */

    private static int calculateMask(String some) {
        int mask = 0;

        for (Character c: some.toCharArray()) {
            int a = c - 'a';
            mask |= 1 << a;
        }

        return mask;
    }


    private static int maxProduct(String[] words) {
        int max = 0;

        int[] masks = new int[words.length];

        for (int i = 0; i < words.length; i++ ) {
            masks[i] = calculateMask(words[i]);
        }

        for (int i = 0; i < words.length; i++ ) {
            for (int j = i + 1; j < words.length; j++) {
                String s = words[i];
                String o = words[j];

                if ((masks[i] & masks[j]) == 0)  {
                    int product = s.length() * o.length();
                    max = Math.max(max, product);
                }
            }
        }

        return max;
    }

    private static void testOne() {
//        Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
//        Output: 16
//        Explanation: The two words can be "abcw", "xtfn".

        String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};

        int product = maxProduct(words);
        System.out.println(product);
    }

    public static void main(String[] args) {
        testOne();

//        calculateMask("ac");
    }
}
