package leetcode.hard;


import java.util.Arrays;


public class MaxEnvelopes
{
    private static int WIDTH = 0;
    private static int LENGTH = 1;

    public static int compareEnvelopes(int[] env1, int[] env2) {
        if (env1[WIDTH] == env2[WIDTH]) {
            return env1[LENGTH] - env2[LENGTH];
        }

        return env1[WIDTH] - env2[WIDTH];
    }

    public static boolean envelopeContainedIn(int[] envelope, int[] potentialContainer) {
        return potentialContainer[WIDTH] > envelope[WIDTH] && potentialContainer[LENGTH] > envelope[LENGTH];
    }

    public static int maxEnvelopesDP(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        Arrays.sort( envelopes, (env1, env2) -> {
            if (env1[WIDTH] == env2[WIDTH]) {
                return env1[LENGTH] - env2[LENGTH];
            }

            return env1[WIDTH] - env2[WIDTH];
        });

        int[] dp = new int[envelopes.length];

//        int len = 0;
//
//        for (int[] envelope: envelopes) {
//            int index = Arrays.binarySearch(dp, 0, len, envelope[WIDTH]);
//            System.out.println("Index for " + envelope[WIDTH] + " is " + index);
//
//            if (index < 0) {
//                index = -(index + 1);
//            }
//
//            dp[index] = envelope[WIDTH];
//            if (index == len) {
//                len++;
//            }
//        }
//      return len;

        Arrays.fill(dp, 1);
        int max = dp[0];
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopeContainedIn(envelopes[j], envelopes[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
//        int result1 = maxEnvelopesDP(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}});
//        assert(result1 == 3);
        int result2 = maxEnvelopesDP(new int[][]{{1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}});
        assert (result2 == 3);
    }
}
