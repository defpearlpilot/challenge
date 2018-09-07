package sorts;


import java.util.Arrays;


/**
 * Created by andrew on 12/31/16.
 */
public class MergeSort
{
    private static void printF() {
        System.out.println( String.format( "%s", "hi" ) );
    }

    private static void printArr(String name, int[] arr) {
        System.out.print( name );
        System.out.println( Arrays.toString( arr ) );
    }

    void merge(int array[], int leftIndex, int middleIndex, int rightIndex)
    {
        int leftSize = middleIndex - leftIndex + 1;
        int rightSize = rightIndex - middleIndex;

        int leftSub[] = new int [leftSize];
        int rightSub[] = new int [rightSize];

        System.arraycopy( array, leftIndex, leftSub, 0, leftSize );
        System.arraycopy( array, middleIndex+1, rightSub, 0, rightSize );

        int leftSubIndex = 0, rightSubIndex = 0;

        int arrayIndex = leftIndex;
        while (leftSubIndex < leftSize && rightSubIndex < rightSize)
        {
            if (leftSub[leftSubIndex] <= rightSub[rightSubIndex])
            {
                array[arrayIndex++] = leftSub[leftSubIndex++];
            }
            else
            {
                array[arrayIndex++] = rightSub[rightSubIndex++];
            }
        }

        while (leftSubIndex < leftSize)
        {
            array[arrayIndex++] = leftSub[leftSubIndex++];
        }

        while (rightSubIndex < rightSize)
        {
            array[arrayIndex++] = rightSub[rightSubIndex++];
        }
    }

    private void sort(int[] values, int start, int end)
    {
        if (start < end)
        {
            int pivot = (start + end) / 2;
            sort(values, start, pivot);
            sort(values, pivot + 1, end);
            merge(values, start, pivot, end);
        }
    }


    public static void main(String[] args)
    {
        MergeSort sorter = new MergeSort();

        int array[] = {38, 27, 43, 3, 9, 82, 10};
        sorter.sort( array, 0, array.length - 1 );
        System.out.println( Arrays.toString( array ) );
        System.out.println( "DONE" );
    }
}
