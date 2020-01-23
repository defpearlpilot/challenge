package leetcode.medium;

import java.util.PriorityQueue;

/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 */
public class KthSmallestMatrix {
    public static int kthSmallest(int[][] matrix, int k) {
        int captured = 0;

        int[] indices = new int[matrix.length];
        int matrixIndex = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);

//        for (int[] row: matrix) {
//            queue.add()
//        }
//
//
//        while (captured < k) {
//
//
//            if (matrixIndex == matrix.length) {
//                matrixIndex = 0;
//            }
//        }
//
//
        return 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };

        int smallest = kthSmallest(matrix, 8);
        assert (smallest == 13);
    }
}
