package easy.kaprekar;


import medium.flipmatrix.MatrixFlip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by andrew on 12/31/16.
 */
public class Kaprekar
{
    private static FileInputStream readFromFile()
    {
        try
        {
            URL resource = Kaprekar.class.getResource( "bluemountain1" );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );

        }
        catch ( IOException e )
        {
            return null;
        }

    }


    private static boolean isKaprekar(long number)
    {
        String val = String.valueOf( number );
        String squareVal = String.valueOf( number * number );

        int valLen = val.length();
        int doubleValLen = val.length() * 2;
        int squareValLen = squareVal.length( );

        if (squareValLen == doubleValLen || squareValLen == (doubleValLen - 1))
        {
            int midPoint = squareValLen - valLen;

            if (midPoint < 0)
            {
                return false;
            }

            String firstHalf = squareVal.substring( 0, midPoint );
            String secondHalf = squareVal.substring( midPoint, squareVal.length( ) );

            if (firstHalf.length() == 0)
            {
                firstHalf = "0";
            }

            if (secondHalf.length() == 0)
            {
                secondHalf = "0";
            }

            long sum = Integer.valueOf( firstHalf ) + Integer.valueOf( secondHalf );

            return number == sum;
        }

        return false;
    }


    public static void main(String[] args)
    {
        Scanner in = new Scanner( readFromFile() );
        int lower = in.nextInt();
        int upper = in.nextInt();

        List< String > kaprekars = new LinkedList<>();

        for (long number = lower; number <= upper; number++)
        {
            if ( isKaprekar( number ) )
            {
                kaprekars.add( String.valueOf( number ) );
            }
        }

        if (kaprekars.isEmpty())
        {
            System.out.printf( "INVALID RANGE" );
        }
        else
        {
            System.out.println( String.join(" ", kaprekars) );
        }
    }
}
