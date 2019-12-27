package leetcode.easy;

public class MaxSubArray {

    // [-2,1,-3,4,-1,2,1,-5,4]

    /*
        carry over the best max

        current run can be:
            1) the current number
            2) the current number + the current run

        []
        -Inf | -2

        [-2]
        1 => -2 | 1 | -2 + 1

        [-2, -1]
        -3 ? -3 | -1 | -3 + -1

        [-2, -1, -1]
        4 ? 4 | -1 | -1 + 4

        [-2, -1. -1, 4]
        -1 ? -1 :


     */

    public int maxSubArrayN2(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int bestMax = Integer.MIN_VALUE/2;
        int currentRun = Integer.MIN_VALUE/2;

        for (int start = 0; start < nums.length; start++) {
            for (int end = 0; end < nums.length; end++) {
                int currentNum = nums[end];

                currentRun = Math.max(currentNum, currentNum + currentRun);
                bestMax = Math.max(bestMax, currentRun);
            }

            currentRun = Integer.MIN_VALUE/2;
        }

        return bestMax;
    }

    /*
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

current | current + previous_run | previous_run


     */
    public int maxSubArrayKadane(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Long bestMax = null;
        long currentRun = 0L;

        for (int current: nums) {
            if (bestMax == null) {
                bestMax = Long.valueOf(current);
            }

            currentRun = Math.max(current, current + currentRun);
            bestMax = Math.max(bestMax, currentRun);
        }

        return Long.valueOf(bestMax).intValue();
    }

    public static void main(String[] args) {
        var array = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        var subArrayN = new MaxSubArray().maxSubArrayN2(array);
        System.out.println("Subarray N2 is: " + subArrayN);
        var subArrayK = new MaxSubArray().maxSubArrayKadane(array);
        System.out.println("Subarray Kadane is: " + subArrayK);

        var array2 = new int[]{2};
        var subArray2 = new MaxSubArray().maxSubArrayKadane(array2);
        System.out.println("Subarray Kadane 2 is: " + subArray2);

    }
}
