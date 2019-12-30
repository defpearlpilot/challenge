package leetcode.medium;

/*
Given an unsorted array of integers, find the length of longest increasing subsequence.
Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 */

import java.util.Arrays;


public class LongestIncreasingSubsequence
{
/*
    10, 101
    9, 101
    2, 3, 7, 101
    2, 5, 7, 101
    5, 7, 101
    3, 7, 101
    7, 101
    101
    18

    A[i] + LIS[

* */



    static class Entry {
        int value;
        int index;
    }

    public int lengthOfLISR(Integer[] memo, int[] nums, int start) {
        if (memo[start] != null) {
            return memo[start];
        }

        if (start > nums.length - 1) {
            memo[start] = 0;
            return memo[start];
        }

        int max = 0;
        for (int begin = start; begin < nums.length; begin++) {
            int startNum = nums[begin];

            for (int next = start + 1; next < nums.length; next++) {
                int nextNum = nums[next];
                if (startNum > nextNum) {
                    continue;
                }

                int total = 1 + lengthOfLISR( memo, nums, next );
                max = Math.max(total, max);
            }
        }

        memo[start] = max;
        return memo[start];
    }

    public int lengthOfLISRecursive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill( dp, 1 );

        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max( dp[i], dp[j] + 1 );
                }
            }

            max = Math.max( max, dp[i] );
        }

        return max;
    }


    public int lengthOfLISBroken(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Entry[] entries = new Entry[nums.length];

        for (int index = 0; index < nums.length; index++) {
            Entry e = new Entry();
            e.index = index;
            e.value = nums[index];

            entries[index] = e;
        }

        Arrays.sort( entries, (e1, e2) -> e1.value - e2.value );

        Entry first = entries[0];
        int startIndex = first.index;
        int sequence = 1;

        for (int index = 1; index < entries.length; index++) {
            Entry compare = entries[index];
            if (compare.index > startIndex && compare.value > entries[index - 1].value) {
                System.out.println( "Adding " + compare.value );
                startIndex = compare.index;
                sequence++;
            }
        }

        return sequence;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        System.out.println(lis.lengthOfLISRecursive( new int[]{1,3,6,7,9,4,10,5,6} ));
        System.out.println(lis.lengthOfLISRecursive( new int[]{10,9,2,5,3,7,101,18} ));
    }
}
