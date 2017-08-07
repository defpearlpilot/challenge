package sorts;


import java.util.Arrays;


/**
 * Created by andrew on 12/31/16.
 */
public class MergeSort
{
    public void checkRemainder(int[] values, int start, int end, int other)
    {

    }


    private void mergeInPlace(int[] values, int start, int pivot, int end)
    {
        if (start >= end)
        {
            return;
        }

        int leftIndex = start;
        int leftEnd = pivot;
        int rightIndex = pivot + 1;

        while (leftIndex <= leftEnd && rightIndex <= end)
        {
            if (values[leftIndex] < values[rightIndex])
            {
                ++leftIndex;
            }
            else
            {
                int temp = values[ rightIndex ];
                System.arraycopy( values, leftIndex, values, leftIndex + 1, rightIndex - leftIndex );
                values[ leftIndex ] = temp;
                ++leftIndex;
                ++leftEnd;
                ++rightIndex;
            }
        }
    }


    public void sortInPlace(int[] values, int start, int end)
    {
        if (start >= end)
        {
            return;
        }

        int pivot = (int) Math.floor((start + end) / 2);
        sortInPlace(values, start, pivot);
        sortInPlace(values, pivot + 1, end);
        mergeInPlace(values, start, pivot, end);
    }


    public static void main(String[] args)
    {
        MergeSort sorter = new MergeSort();

        int array[] = {5,4,3,2,1};
        sorter.sortInPlace( array, 0, array.length - 1 );
        System.out.println( Arrays.toString( array ) );
        System.out.println( "DONE" );
    }
}
