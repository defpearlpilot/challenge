package leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinWindowSubstring {

    // a => [1, 11]
    // b => [4, 10]
    // c => [6, 13]

    private int[] findBounds(Map<Character, Integer> positions) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (Integer position: positions.values()) {
            min = Math.min(min, position);
            max = Math.max(max, position);
        }

        return new int[]{min, max};
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> positions = new HashMap<>();
        Set<Character> tSet = new HashSet<>();

        for (int i = 0; i < t.length(); i++) {
            tSet.add(t.charAt(i));
        }

        int minSpan = s.length() + 1;
        int start = -1;
        int end = -1;

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (!tSet.contains(c)) {
                continue;
            }

            positions.put(c, i);

            if (positions.size() == t.length()) {
                int[] bounds = findBounds(positions);
                int distance = bounds[1] - bounds[0];
                minSpan = Math.min(minSpan, distance);

                if (minSpan == distance) {
                    start = bounds[0];
                    end = bounds[1];
                }
            }
        }

        if (positions.size() == t.length()) {
            return s.substring(start, end + 1);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        MinWindowSubstring mws = new MinWindowSubstring();
        System.out.println(mws.minWindow("ADOBECODEBANC", "ABC"));
    }
}
