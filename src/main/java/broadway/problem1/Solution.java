package broadway.problem1;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class Solution
{
    private static FileInputStream readFromFile()
    {
        try
        {
            URL resource = Solution.class.getResource( "bproblem1" );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );
        }
        catch ( IOException e )
        {
            return null;
        }
    }


    private static int findIndex(int[] sales)
    {
        int[] leftSums = new int[sales.length];
        int[] rightSums = new int[sales.length];

        for (int i =0; i < sales.length; i++)
        {
            int oppositeIndex = sales.length - i -1;

            int number = sales[i];
            int oppositeNumber = sales[oppositeIndex];

            if (i > 0)
            {
                leftSums[i] = leftSums[i - 1] + number;
                rightSums[oppositeIndex] = rightSums[oppositeIndex + 1] + oppositeNumber;
            }
            else
            {
                leftSums[i] = number;
                rightSums[sales.length - 1] = oppositeNumber;
            }
        }


        for (int i = 1; i < sales.length - 1; i++)
        {
            int leftVal = leftSums[i - 1];
            int rightVal = rightSums[i + 1];

            if (leftVal == rightVal)
            {
                return i;
            }
        }

        return 0;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner( readFromFile( ) );

        int elements = in.nextInt( );

        int[] array = new int[ elements ];

        for ( int i = 0; i < elements; i++ )
        {
            array[i] = in.nextInt( );
        }

        System.out.println( findIndex( array ) );

    }
}
