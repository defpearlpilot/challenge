package leetcode.hard;

public class MedianSortedList {
    private static int firstOf(int[] nums) {
        return nums[0];
    }

    private static int lastOf(int[] nums) {
        return nums[nums.length - 1];
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return findMedian(nums2);
        } else if (nums2 == null || nums2.length == 0) {
            return findMedian(nums1);
        }

        return findMedian(nums1, nums2);
    }

    private static double findMedian(int[] nums1, int[] nums2) {
        double median1 = findMedian(nums1);
        double median2 = findMedian(nums2);

        if (median1 == median2) {
            return median1;
        }

        // non-overlapping sets, first strictly less than second
        if (lastOf(nums1) < firstOf(nums2)) {
            return findMedianNonOverlapping(nums1, nums2);
        } else if (lastOf(nums2) < firstOf(nums1)) {
            return findMedianNonOverlapping(nums2, nums1);
        }

        return medianSearch(nums1, nums2);
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
        System.out.println(findMedianSortedArrays(new int[]{5, 14, 17}, new int[]{8, 9, 11, 15}));
        // A = 11
        // B = 9
        // 1A, 3B, 8A, 9B, 11B, 14A, 17A
        // 0A, 0B, 1A, 1B, 2B,  2A,  3A
//        System.out.println(findMedianSortedArrays(new int[]{1, 8, 14, 17}, new int[]{3, 11}));
        // A = 11
        // B = 7
        // 1A, 3B, 8A, 11B, 14A, 17A
        // 0A, 0B, 1A, 1B,  2A,  3A
//        System.out.println(new MedianSortedList().findMedianSortedArrays(new int[]{1, 8, 14, 17}, new int[]{3}));

    }

    private static double medianSearch(int[] nums1, int[] nums2) {
        MedianData median1 = findMedianData(nums1);
        MedianData median2 = findMedianData(nums2);

        double median = (median1.median + median2.median) / 2.0;

        int total = nums1.length + nums2.length;

        if (total % 2 == 1) {
            int expectedIndex = (total - 1) / 2;

            if (median1.median > median2.median) {
                MedianData temp = median1;
                median1 = median2;
                median2 = temp;
            }

            int index1 = median1.index;
            int index2 = median2.index;

            while (true) {
                int currentIndex = index1 + index2 + 1;

                if (currentIndex == expectedIndex) {
                    double val = median2.nums[index2];
                    if (val > median1.median && val < median2.median) {
                        return val;
                    }
                }

                if (median1.nums[index1] < median2.median) {
                    if (index1 < median1.nums.length - 1 && median1.nums[index1 + 1] < median2.median) {
                        index1++;
                    }
                }

                if (median2.nums[index2] > median1.median) {
                    if (index2 > 0 && median2.nums[index2 - 1] > median1.median) {
                        index2--;
                    }
                }
            }
        } else {
            MedianData localMedian1 = localMedianSearch(median, median1.median, median1.index, nums1);
            MedianData localMedian2 = localMedianSearch(median, median2.median, median1.index, nums2);

            return (localMedian1.median + localMedian2.median) / 2.0;
        }
    }

    private static MedianData localMedianSearch(double globalMedian, double localMedian, int index, int[] nums)
    {
        if (globalMedian < localMedian) {
            return backwardSearch(globalMedian, index, nums);
        } else {
            return forwardSearch(globalMedian, index, nums);
        }
    }

    private static MedianData forwardSearch(double median, int index, int[] nums)
    {
        int last = 0;

        while (index <= nums.length - 1 && nums[index] < median) {
            last = nums[index];
            index++;
        }

        index--;
        if (nums[index] > median) {
            return new MedianData(index + 1, last, nums);
        }

        return new MedianData(index, nums[index], nums);
    }

    private static MedianData backwardSearch(double median, int index, int[] nums)
    {
        int last = 0;

        while (index >= 0 && nums[index] > median) {
            last = nums[index];
            index--;
        }

        index++;
        if (nums[index] < median) {
            return new MedianData(index - 1, last, nums);
        }

        return new MedianData(index, nums[index], nums);
    }

    private static double findMedianNonOverlapping(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int midpoint = totalLength / 2;

        if (totalLength % 2 == 0) {
            double first = getAtIndex(midpoint - 1, nums1, nums2);
            double second = getAtIndex(midpoint, nums1, nums2);

            return (first + second) / 2.0;
        } else {
            return getAtIndex(midpoint, nums1, nums2);
        }
    }

    private static double getAtIndex(int index, int[] nums1, int[] nums2) {
        if (index < nums1.length) {
            return nums1[index];
        } else {
            return nums2[index - nums1.length];
        }
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

    static class MedianData {
        int index;
        double median;
        int[] nums;

        MedianData(int index, double median, int[] nums) {
            this.index = index;
            this.median = median;
            this.nums = nums;
        }

        @Override
        public String toString() {
            return "MedianData(" + this.index + ", " + this.median + ")";
        }
    }

    private static MedianData findMedianData(int[] nums) {
        int length = nums.length;
        int midpoint = length / 2;

        if (length % 2 == 0) {
            int first = nums[midpoint - 1];
            int second = nums[midpoint];

            double median = (first + second) / 2.0;

            return new MedianData(midpoint, median, nums);
        } else {
            double median = nums[midpoint];
            return new MedianData(midpoint, median, nums);
        }
    }
}
