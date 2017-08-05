package fibmodified;


import java.math.BigDecimal;
import java.util.Scanner;


/**
 * Created by andrew on 2/7/16.
 */
public class FibonacciModified
{


    public static void main(String args[])
    {
        calculationFibMod(0, 1, 5);
        calculationFibMod(0, 1, 11);

    }


    public static void scanInput()
    {
        Scanner in = new Scanner( System.in);
        int first = in.nextInt();
        int second = in.nextInt();
        int n = in.nextInt( );

        calculationFibMod( first, second, n );
    }

    private static void calculationFibMod ( int first, int second, int nth )
    {
        BigDecimal[] calcs = new BigDecimal[nth];

        calcs[ 0 ] = new BigDecimal( first );
        calcs[ 1 ] = new BigDecimal( second );

        for (int i = 2; i < nth; i++)
        {
            System.out.println( (i-1) + " = " + calcs[i-1] );
            System.out.println( (i-2) + " = " + calcs[i-2] );
            BigDecimal im1 = calcs[ i - 1 ].multiply( calcs[ i - 1] );
            calcs[ i ] = im1.add( calcs[ i - 2 ] );
            System.out.println( "I = " + i + " value: " + calcs[i] );
        }
    }
}
