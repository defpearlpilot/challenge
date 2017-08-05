package aqrtest;


import hard.matrixrotation.MatrixRotation;
import scala.Char;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Solution
{
    private static FileInputStream readFromFile()
    {
        try
        {
            URL resource = Solution.class.getResource( "testcase" );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );
        }
        catch ( IOException e )
        {
            return null;
        }
    }


    static long power(int p, int q)
    {
        double multiple = Math.log( q / 2.0 );
        int remainder = q % 2;

        int sum = p;
        while (multiple > 0)
        {
            sum *= sum;
            multiple -= 1.0;
        }

        sum *= sum;

        if (remainder == 1)
        {
            sum *= p;
        }

        return sum;
    }


    static void printArray(int[] array)
    {
        for(int i=0; i < array.length; i++)
        {
            System.out.println( array[i] );
        }
        System.out.println( "" );
    }


    static String sortString(String s)
    {
        char[] chars = s.toCharArray( );
        Arrays.sort( chars );
        return new String( chars );
    }


    static String sortSegments(String s)
    {
        List<String> segments = new LinkedList<String>();

        String current = "";
        char lastChar = 'a';
        for (int i=0; i < s.length(); i++)
        {

            char at = s.charAt( i );
            if ( Character.isAlphabetic( at ) && !Character.isAlphabetic( lastChar ) ||
                 !Character.isAlphabetic( at ) && Character.isAlphabetic( lastChar ))
            {
                segments.add( sortString( current) );
                current = "" + at;
            } else {
                current += at;
            }

            lastChar = at;
        }

        if (current.length() > 0)
        {
            segments.add( sortString( current) );
        }

        StringBuilder result = new StringBuilder( );
        for (String segment: segments)
        {
            result.append( segment );
        }

        return result.toString();
    }


    static int[] sort(int[] n)
    {
        printArray( n );

        for (int index = 0; index < n.length; index++)
        {
            for (int j = index + 1; j < n.length; j++)
            {
                if (n[j] <= n[index])
                {
                    int temp = n[index];
                    n[index] = n[j];
                    n[j] = temp;
                }
            }
        }

        printArray( n );

        return n;
    }

    public static void main(String[] args)
    {
//        Scanner in = new Scanner( readFromFile( ) );
//        int rows = in.nextInt( );
//        int columns = in.nextInt( );
//
//        int[][] matrix = new int[ rows ][ columns ];
//
//        for ( int mi = 0 ; mi < rows ; mi++ )
//        {
//            for ( int ni = 0 ; ni < columns ; ni++ )
//            {
//                matrix[ mi ][ ni ] = in.nextInt( );
//            }
//        }

//        System.out.println( power( 2, 3 ) );
//        System.out.println( power( 2, 4 ) );

        System.out.println( power( 3, 5 ) );

        System.out.println( sortSegments( "AZFQ013452BAB" ) );

    }


}
