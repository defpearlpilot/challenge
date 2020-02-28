package leetcode.hard;

import java.util.*;

public class AlienLanguage {
    /*
    https://leetcode.com/problems/alien-dictionary/

    There is a new alien language which uses the latin alphabet. However, the order among letters
    are unknown to you. You receive a list of non-empty words from the dictionary, where words are
    sorted lexicographically by the rules of this new language. Derive the order of letters in
    this language.
     */

    enum Status {
        Unvisited,
        Visiting,
        Visited
    }

    private static void mapWords(Map<Character, Set<Character>> adjacency, String pred, String succ) {
        int length = Math.min(pred.length(), succ.length());
        for (int i = 0; i < length; i++) {
            Character parent = pred.charAt(i);
            Character child = succ.charAt(i);

            Set<Character> characters = adjacency.computeIfAbsent(parent, x -> new HashSet<>());

            if (parent.equals(child)) {
                continue;
            }

            characters.add(child);
            break;
        }
    }


    private static Map<Character, Set<Character>> computeGraph(String[] words) {
        Map<Character, Set<Character>> adjacency = new HashMap<>();

        for (int w = 1; w < words.length; w++) {
            String prevWord = words[w-1];
            String word = words[w];

            mapWords(adjacency, prevWord, word);
        }

        return adjacency;
    }

    private static Map<Character, Status> computeVisited(String[] words) {
        Map<Character, Status> status = new HashMap<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                status.put(word.charAt(i), Status.Unvisited);
            }
        }

        return status;
    }


    private static boolean sort(Map<Character, Set<Character>> adjacency,
                                Map<Character, Status> visitedStatus,
                                Stack<Character> order,
                                Character node) {
        if (visitedStatus.get(node) == Status.Unvisited) {
            visitedStatus.put(node, Status.Visiting);

            Set<Character> children = adjacency.get(node);
            if (children != null && !children.isEmpty()) {
                for (Character child : children) {
                    if (visitedStatus.get(child) == Status.Visiting) {
                        return false;
                    }

                    if (!sort(adjacency, visitedStatus, order, child)) {
                        return false;
                    }
                }
            }

            visitedStatus.put(node, Status.Visited);
            order.push(node);
        }

        return true;
    }

    public static String alienOrder(String[] words) {
        Map<Character, Set<Character>> adjacency = computeGraph(words);

        Stack<Character> order = new Stack<>();

        Map<Character, Status> visitedStatus = computeVisited(words);

        Queue<Character> remaining = new ArrayDeque<>(adjacency.keySet());

        while (!remaining.isEmpty()) {
            Character n = remaining.remove();

            if (!sort(adjacency, visitedStatus, order, n)) {
                return "";
            }
        }

        StringBuilder buf = new StringBuilder();
        while (!order.isEmpty()) {
            buf.append(order.pop());
        }

        return buf.toString();
    }

    public static void testOne() {
    /*
    Input:
    [
      "wrt",
      "wrf",
      "er",
      "ett",
      "rftt"
    ]

    Output: "wertf"
     */

        String result = alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"});
        if (!result.equals("wertf")) {
            System.out.println("FAILED testOne: '" + result + "'");
        }
    }

    public static void testTwo() {
    /*
    Input:
    [
      "z",
      "x",
    ]

    Output: "zx"
     */

        String result = alienOrder(new String[]{"z", "x"});
        if (!result.equals("zx")) {
            System.out.println("FAILED testTwo: '" + result + "'");
        }

    }

    public static void testThree() {
    /*
    Input:
    [
      "z",
      "x",
      "z"
    ]

    Output: ""
     */

        String result = alienOrder(new String[]{"z", "x", "z"});
        if (!result.equals("")) {
            System.out.println("FAILED testThree: '" + result + "'");
        }

    }

    public static void testFour() {
    /*
    Input:
    [
      "z",
      "x",
      "z"
    ]

    Output: ""
     */

        String result = alienOrder(new String[]{"z", "z"});
        if (!result.equals("z")) {
            System.out.println("FAILED testFour: '" + result + "'");
        }

    }

    public static void testFive() {
        /*
        ["ab","adc"]

        Output:
        "abcd"
         */
    }

    public static void main(String[] args) {
        testOne();
        testTwo();
        testThree();
        testFour();
    }
}
