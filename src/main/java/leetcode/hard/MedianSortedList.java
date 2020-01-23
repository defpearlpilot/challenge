package leetcode.hard;

import java.util.function.BiFunction;

public class MedianSortedList {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return findMedian(nums2);
        } else if (nums2 == null || nums2.length == 0) {
            return findMedian(nums1);
        }

        return findMedianTwoArrays(nums1, nums2);
    }


    public static void main(String[] args) {
//        int[] nums = new int[]{1, 8, 14, 17};
//        System.out.println(binarySearch(5, nums));
//        System.out.println(binarySearch(5, new int[]{1, 3, 14, 17}));
//        System.out.println(binarySearch(9, nums));
//        System.out.println(binarySearch(14, nums));
//        System.out.println(binarySearch(15, nums));

//        System.out.println(new MedianSortedList().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
//        System.out.println(new MedianSortedList().findMedianSortedArrays(new int[]{1, 3}, new int[0]));
//        System.out.println(new MedianSortedList().findMedianSortedArrays(new int[]{1, 3, 5, 9}, new int[0]));
//        System.out.println(new MedianSortedList().findMedianSortedArrays(new int[0], new int[]{11}));

//        System.out.println(new MedianSortedList().findMedianSortedArrays(new int[]{1, 3}, new int[]{5, 7}));
//        System.out.println(new MedianSortedList().findMedianSortedArrays(new int[]{5, 7}, new int[]{1, 3}));
//        System.out.println(findMedianSortedArrays(new int[]{7, 8, 9, 10}, new int[]{3, 12, 15, 18}));
//        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5, 14, 17}, new int[]{8, 9, 11, 15}));
//        System.out.println(findMedianSortedArrays(new int[]{5, 14, 17}, new int[]{8, 9, 11, 15}));
        // A = 11
        // B = 9
        // 1A, 3B, 8A, 9B, 11B, 14A, 17A
        // 0A, 0B, 1A, 1B, 2B,  2A,  3A
        System.out.println(findMedianSortedArrays(new int[]{1, 8, 14, 17}, new int[]{3, 11}));
        // 1, 3, 8, 11, 14, 17

        // A = 11
        // B = 7
        // 1A, 3B, 8A, 11B, 14A, 17A
        // 0A, 0B, 1A, 1B,  2A,  3A
//        System.out.println(new MedianSortedList().findMedianSortedArrays(new int[]{1, 8, 14, 17}, new int[]{3}));

    }

    private static double findMedianTwoArrays(int[] leftArr, int[] rightArr) {
        int leftIndex = 0;
        int rightIndex = rightArr.length - 1;

        int leftVal = leftArr[leftIndex]; // 14
        int rightVal = rightArr[rightIndex]; // 11

        BiFunction<int[], Integer, Boolean> indexInBounds = (int[] array, Integer pos) -> (pos >= 0 && pos < array.length);

        while (leftIndex <= rightIndex) {
            leftIndex = leftArr.length/2;
            rightIndex = rightArr.length/2;

            if (leftVal < rightVal && indexInBounds.apply(leftArr, leftIndex + 1) &&  rightVal < leftArr[leftIndex + 1] ) {
                leftIndex++;
            } else if(leftVal > rightVal && indexInBounds.apply(leftArr, leftIndex - 1) && rightVal > leftArr[leftIndex - 1]) {
                leftIndex--;
            } else if(rightVal < leftVal && indexInBounds.apply(rightArr, rightIndex + 1) && leftVal < rightArr[rightIndex + 1]) {
                rightIndex++;
            } else if(rightVal > leftVal && indexInBounds.apply(rightArr, rightIndex - 1) && leftVal > rightArr[rightIndex - 1]) {
                rightIndex--;
            }

            leftVal = leftArr[leftIndex]; // 14
            rightVal = rightArr[rightIndex]; // 11
        }

        leftVal = leftArr[leftIndex]; // 14
        rightVal = rightArr[rightIndex]; // 11

        // 5, 8, 9, 11, 14, 15, 17

        return 0.0d;
    }





    private static double findMedian(int[] nums) {
        int length = nums.length;
        int midpoint = length / 2;

        if (length % 2 == 0) {
            int first = nums[midpoint - 1];
            int second = nums[midpoint];

            return (first + second) / 2.0;
        } else {
            return nums[midpoint];
        }
    }
}
