package citadel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchable {

    public static void binarySearchCandidates(List<Integer> results,
                                              int[] array,
                                              int start,
                                              int end,
                                              int lowerBound,
                                              int upperBound) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        int value = array[mid];

        binarySearchCandidates(results, array, start, mid - 1, lowerBound, value);
        if (value >= lowerBound && value <= upperBound) {
            results.add(value);
        }
        binarySearchCandidates(results, array, mid + 1, end, value, upperBound);
    }

    public static List<Integer> binarySearchable(int[] array) {
        int mid = array.length / 2;

        List<Integer> searchable = new LinkedList<>();

        int value = array[mid];

        binarySearchCandidates(searchable, array, 0, mid - 1, array[0], value);
        searchable.add(value);
        binarySearchCandidates(searchable, array, mid + 1, array.length - 1, value, array[array.length - 1]);

        return searchable;
    }

    public static void main(String[] args) {
        System.out.println(binarySearchable(new int[]{3,5,11,8,3,9,10}));
        System.out.println("DONE");

    }
}

