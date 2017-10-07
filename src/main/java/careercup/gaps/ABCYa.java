package careercup.gaps;


import java.util.Arrays;


public class ABCYa
{
    public static void print(int[] arr)
    {
        for (int i: arr)
        {
            System.out.print( i + " ");
        }
        System.out.println( );
    }

    public static void main(String[] args)
    {
        int[] nums = new int[]{4, 6, 9, 10, 2, 5, 25, 12, 99, 56, 34, 3, 16};

        Arrays.sort( nums );

        print( nums );
        for (int index = 2; index < nums.length; index++)
        {
            int minuend = nums[ index ];
            for (int subIndex = index -1; subIndex > 0; subIndex--)
            {
                int subtrahend = nums[ subIndex ];
                int difference = minuend - subtrahend;
                if ( Arrays.binarySearch( nums, 0, subIndex - 1, difference ) > 0 )
                {
                    System.out.println( subtrahend + " " + difference + " = " + minuend );
                }
            }
        }
    }
}
