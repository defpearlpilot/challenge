package pdt.problem1;


import java.awt.*;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

import static common.TestCaseSupport.readFromFile;


public class Solution
{
    private static int[] fibonacci(int n)
    {
        // specified bounds of the problem
        if (n == 0 || n > 10)
        {
            return new int[0];
        }

        // at this point, it's safe to allocate
        int[] retval = new int[n];
        retval[0] = 0;

        if (n == 1)
        {
            return retval;
        }

        retval[1] = 1;

        if (n == 2)
        {
            return retval;
        }

        retval[2] = 1;

        if (n == 3)
        {
            return retval;
        }

        for (int index = 3; index < n; index++)
        {
            retval[index] = retval[index - 1] + retval[index -2];
        }

        return retval;
    }


    private static void localMain()
    {
        FileInputStream fileInputStream = readFromFile( "/pdt/problem1/problem.txt" );
        Scanner in = new Scanner( fileInputStream );

        String elements = in.next( );

        System.out.println( elements );
    }

    public static void main(String[] args)
    {
        System.out.println( Arrays.toString( fibonacci( 0 ) ) );
        System.out.println( Arrays.toString( fibonacci( 1 ) ) );
        System.out.println( Arrays.toString( fibonacci( 2 ) ) );
        System.out.println( Arrays.toString( fibonacci( 3 ) ) );
        System.out.println( Arrays.toString( fibonacci( 4 ) ) );
        System.out.println( Arrays.toString( fibonacci( 5 ) ) );
        System.out.println( Arrays.toString( fibonacci( 10 ) ) );
        System.out.println( Arrays.toString( fibonacci( 11 ) ) );

//        localMain();
    }
}
