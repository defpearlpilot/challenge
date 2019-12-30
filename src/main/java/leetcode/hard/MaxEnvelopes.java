package leetcode.hard;


import java.util.Arrays;


public class MaxEnvelopes
{
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        int WIDTH = 0;
        int LENGTH = 1;

        Arrays.sort( envelopes, (arr1, arr2) -> {
            if (arr1[WIDTH] == arr2[WIDTH]) {
                return arr1[LENGTH] - arr2[LENGTH];
            }

            return arr1[WIDTH] - arr2[WIDTH];
        });

        int maxEnvelopes = 1;
        int[] lastEnvelope = envelopes[0];

        for (int i = 1; i < envelopes.length; i++) {
            int[] current = envelopes[i];

            if (current[WIDTH] > lastEnvelope[WIDTH] && current[LENGTH] > lastEnvelope[LENGTH]) {
                maxEnvelopes++;
                lastEnvelope = current;
            }
        }

        return maxEnvelopes;
    }

    public int maxEnvelopesDP(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        int WIDTH = 0;
        int LENGTH = 1;

        Arrays.sort( envelopes, (arr1, arr2) -> {
            if (arr1[WIDTH] == arr2[WIDTH]) {
                return arr1[LENGTH] - arr2[LENGTH];
            }

            return arr1[WIDTH] - arr2[WIDTH];
        });

        int maxEnvelopes = 1;
        int[] lastEnvelope = envelopes[0];

        int[] dp = new int[envelopes.length];

        for (int i = 1; i < envelopes.length; i++) {
            int[] current = envelopes[i];

            if (current[WIDTH] > lastEnvelope[WIDTH] && current[LENGTH] > lastEnvelope[LENGTH]) {
                maxEnvelopes++;
                lastEnvelope = current;
            }
        }

        return maxEnvelopes;
    }

    public static void main(String[] args) {
        MaxEnvelopes me = new MaxEnvelopes();

//        me.maxEnvelopes( new int[][] {{5,4},{6,4},{6,7},{2,3}} );

        me.maxEnvelopes( new int[][]{ { 1, 3 }, { 3, 5 }, { 6, 7 }, { 6, 8 }, { 8, 4 }, { 9, 5 } } );
    }
}
