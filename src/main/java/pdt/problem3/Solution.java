package pdt.problem3;


import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

import static common.TestCaseSupport.readFromFile;


public class Solution
{

    static int maxDifference( int[] a ) {
        System.out.println( Arrays.toString( a ) );

        if (a.length == 0)
        {
            return 0;
        }

        if (a.length == 1)
        {
            // nothing to diff
            return 0;
        }

        int[] diffMaxArray = new int[a.length];
        diffMaxArray[0] = 0;

        int index = 2;

        int maxDiff = -1;

        while (index < a.length)
        {
            int current = a[index];

            int backTrack = index - 1;
            if (a[backTrack] < current)
            {
                diffMaxArray[index] = current - a[backTrack] + diffMaxArray[backTrack];
                if (diffMaxArray[index] > maxDiff)
                {
                    maxDiff = diffMaxArray[index];
                }
            }
            else
            {
                diffMaxArray[index] = 0;
            }

            System.out.println( Arrays.toString( diffMaxArray ) );
            index ++;

        }

        return maxDiff;
    }

    private static void localMain()
    {
        FileInputStream fileInputStream = readFromFile( "/pdt/problem3/problem.txt" );
        Scanner in = new Scanner( fileInputStream );

        String elements = in.next( );

        System.out.println( elements );
    }

    public static void main(String[] args)
    {
//        maxDifference( new int[]{1, 2, 6, 4} );
        System.out.println( maxDifference( new int[]{ 7, 2, 3, 10, 2, 4, 8, 1 } ) );
        System.out.println( maxDifference( new int[]{ 6,7,9,5,6,3,2 } ) );
    }
}
