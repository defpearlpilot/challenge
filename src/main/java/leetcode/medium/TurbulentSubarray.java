package leetcode.medium;

/*
A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.

Input: [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])

 */

public class TurbulentSubarray {

    public static boolean isSameSign(int f, int s) {
        return ((f >= 0) == (s >= 0));
    }

    public static int diffAtK(int[] A, int k) {
        return A[k] - A[k + 1];
    }

    public int maxTurbulenceSize(int[] A) {
        if (A.length == 0) {
            return 0;
        }

        if (A.length == 1) {
            return 1;
        }

        int maxRun = 1;
        int currRun = 1;

        int prevDiff = diffAtK(A, 0);
        if (prevDiff != 0) {
            maxRun = 2;
            currRun = 2;
        }

        System.out.println("Diff at k: 0 " + prevDiff);

        for (int k = 1; k < A.length - 1; k++) {
            int currDiff = diffAtK(A, k);
            System.out.println("Diff at k: " + k + " is " + currDiff);
            if (currDiff == 0) {
                maxRun = Math.max(currRun, maxRun);
                currRun = 0;
            } else if (isSameSign(prevDiff, currDiff)) {
                System.out.println("Resetting");
                maxRun = Math.max(currRun, maxRun);
                currRun = 2;
            } else {
                currRun++;
                maxRun = Math.max(currRun, maxRun);
            }

            System.out.println("curr run: " + currRun + " max run: " + maxRun);

            prevDiff = currDiff;
        }

        return maxRun;
    }

    public static void main(String[] args) {
        System.out.println("MaxTurbulent " + args + " is " + new TurbulentSubarray().maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}));
    }
}
