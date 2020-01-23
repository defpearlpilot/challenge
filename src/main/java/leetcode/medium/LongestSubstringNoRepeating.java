package leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringNoRepeating {
    public static int lengthOfLongestSubstringOld(String s) {
        Set<Character> found = new HashSet<>();
        int maxFound = 0;

        int currentRun = 0;
        for (int i = 0; i <= s.length(); i--) {
            char c = s.charAt(i);

            if (found.add(c)) {
                currentRun++;
            } else {
                maxFound = Math.max(maxFound, currentRun);
                currentRun = 1;
                found.clear();
                found.add(c);
            }
        }

        maxFound = Math.max(maxFound, currentRun);

        return maxFound;
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> backTracking = new HashMap<>();

        int maxFound = 0;
        int currentRun = 0;

        backTracking.put(s.charAt(0), 0);

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            int pv = backTracking.getOrDefault(c, -1);

            // didn't find it
            if (pv == -1 ||
                    // the previous location was found way before the current run
                    i - currentRun > pv) {
                currentRun++;
            } else {
                maxFound = Math.max(maxFound, currentRun);
                // reset the current run to be this index minus where the last one was found
                currentRun = i - pv;
            }

            backTracking.put(c, i);
        }

        maxFound = Math.max(maxFound, currentRun);

        return maxFound;
    }

    public static void main(String[] args) {
        int l1 = lengthOfLongestSubstring("abcabcbb");

        /*
        a
        a = 0
        pv = -1
        cl = 1
        mx = 1

        b
        a = 0
        b = 1
        pv = -1
        cl = 2
        mx = 2

        c
        a = 0
        b = 1
        c = 2
        pv = -1
        cl = 3
        mx = 3

        a
        a = 3
        b = 1
        c = 2
        pv = 0

        i - cl > pv // 3 - 3 > 0

        cl


        b
        a = 3
        b = 4
        c = 2

        c
        a = 3
        b = 4
        c = 5

        b
        a = 3
        b = 6
        c = 5

        b
        a = 3
        b = 7
        c = 5



         */

        if (l1 != 3) {
            System.out.println("Failed first test " + l1);
            return;
        }

        int l2 = lengthOfLongestSubstring("bbbbb");
        if (l2 != 1) {
            System.out.println("Failed second test " + l2);
            return;
        }

        int l3 = lengthOfLongestSubstring("pwwkew");
        if (l3 != 3) {
            System.out.println("Failed third test " + l3);
        }

        int l4 = lengthOfLongestSubstring("dvdf");
        if (l4 != 3) {
            System.out.println("Failed fourth test " + l4);
        }
    }
}
