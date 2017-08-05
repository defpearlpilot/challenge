package gridsearch;


import java.io.*;
import java.net.URL;
import java.util.Scanner;



public class Solution
{

    private static void readGrids ( File is )
    throws
    FileNotFoundException
    {
        Scanner in = new Scanner( is );
        int t = in.nextInt( );
        for ( int a0 = 0 ; a0 < t ; a0++ )
        {
            int R = in.nextInt( );
            int C = in.nextInt( );
            String G[] = new String[ R ];
            for ( int G_i = 0 ; G_i < R ; G_i++ )
            {
                G[ G_i ] = in.next( );
            }
            int r = in.nextInt( );
            int c = in.nextInt( );
            String P[] = new String[ r ];
            for ( int P_i = 0 ; P_i < r ; P_i++ )
            {
                P[ P_i ] = in.next( );
            }

            findSubGrid( P, G );
        }

    }


    public static void main ( String[] args )
    {
        URL resource = Solution.class.getResource( "input3.text" );
        File fis = new File( resource.getFile( ) );

        try
        {
            readGrids( fis );
        }
        catch ( IOException e )
        {
            System.exit( -1 );
        }

    }


    private static boolean findAtPos ( int univIndex, final int pos, String[] searchGrid, String[] universe )
    {
        int searchIndex = 1;
        while (searchIndex < searchGrid.length)
        {
            String search = searchGrid[ searchIndex ];
            String univString = universe[ univIndex ];
            int foundPos = univString.indexOf( search, pos );
            if (foundPos != pos)
            {
                return false;
            }

            searchIndex++;
            univIndex++;
        }

        return true;
    }


    private static int subSearch(int univIndex, int start, String[] searchGrid, String[] universe )
    {
        int searchIndex = 0;

        while (searchIndex < searchGrid.length)
        {
            String search = searchGrid[ searchIndex ];
            String univString = universe[ univIndex ];
            int pos = univString.indexOf( search, start );

            while (pos > -1)
            {
                if (findAtPos( univIndex + 1, pos, searchGrid, universe ))
                {
                    return pos;
                }

                pos = universe[ univIndex ].indexOf( search, pos + 1 );
            }

            if ( pos == -1 )
            {
                break;
            }

            searchIndex++;
            univIndex++;
        }


        return -1;
    }

    private static void findSubGrid( String[] searchGrid, String[] universe )
    {
        int foundIndex;
        int start = 0;
        for (int univIndex = 0; univIndex < universe.length; univIndex++)
        {
            foundIndex = subSearch( univIndex, start, searchGrid, universe );

            if (foundIndex >= 0) {
                System.out.println( "YES" );
                return;
            }
        }

        System.out.println( "NO" );
    }


}
