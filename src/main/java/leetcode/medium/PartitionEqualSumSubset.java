package leetcode.medium;

import java.util.Arrays;

public class PartitionEqualSumSubset {
    /*
    Given a non-empty array containing only positive integers, find if the array can be partitioned into two
    subsets such that the sum of elements in both subsets is equal.

    Note:

    Each of the array element will not exceed 100.
    The array size will not exceed 200.
     */

    public static boolean gatherTotal(int total, int[] nums) {
        for (int index = nums.length - 1; index > 0; index--) {
            int candidate = nums[index];

            if (candidate == total) {
                return true;
            }

            if (gatherSub(total - candidate, index - 1, nums)) {
                return true;
            }
        }

        return false;
    }

    public static boolean gatherSub(int total, int end, int[] nums) {
        if (total < 0) {
            return false;
        }

        if (total == 0) {
            return true;
        }

        if (Arrays.binarySearch(nums, 0, end, total) >= 0) {
            return true;
        }

        for (int index = end; index > 0; index--) {
            int candidate = nums[index];

            if (candidate == total) {
                return true;
            }

            if (gatherSub(total - candidate, index - 1, nums)) {
                return true;
            }
        }

        return false;
    }

    public static boolean canPartition(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }

        Arrays.sort(nums);

        int sum = Arrays.stream(nums).sum();

        if ((sum & 1) == 1) {
            return false;
        }

        return gatherTotal(sum/2, nums);
    }


    private static void testOne() {
        /*
        Input: [1, 5, 11, 5]

        Output: true

        Explanation: The array can be partitioned as [1, 5, 5] and [11].
         */

        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }

    private static void testTwo() {
        /*
        Input: [1, 2, 3, 5]

        Output: false

        Explanation: The array cannot be partitioned into equal sum subsets.
         */

        System.out.println(canPartition(new int[]{1, 2, 3, 5}));
    }

    private static void testThree() {
        /*
        7,4,3
        6,5,2,1

        7,6,1
        5,4,3,2
         */

        System.out.println(canPartition(new int[]{1,2,3,4,5,6,7}));
    }

    private static void testFour() {
        /*
        1,2,5
         */

        System.out.println(canPartition(new int[]{1,2,5}));
    }

    public static void main(String[] args) {
//        testOne();
//        testTwo();
//        testThree();
        testFour();
    }
}
