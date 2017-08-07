package google.code.scalar;


import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static common.TestCaseSupport.readFromFile;
import static common.TestCaseSupport.readTestCases;


public class MinimumScalarProduct
{
    static class Vector
    {
        int[] coordinates;


        Vector ( int[] coordinates )
        {
            this.coordinates = coordinates;
        }
    }

    static class TestCase
    {
        Vector some;
        Vector other;


        TestCase ( Vector some, Vector other )
        {
            this.some = some;
            this.other = other;
        }


        static Vector fromScanner ( int numDimensions, Scanner scanner )
        {
            int[] coordinates = new int[ numDimensions ];

            int currentDim = 0;
            while ( currentDim < numDimensions )
            {
                coordinates[ currentDim++ ] = scanner.nextInt( );
            }

            return new Vector( coordinates );
        }


        static TestCase fromScanner ( Scanner scanner )
        {
            int numDimensions = scanner.nextInt( );

            Vector some = fromScanner( numDimensions, scanner );
            Vector other = fromScanner( numDimensions, scanner );

            return new TestCase( some, other );
        }
    }


    private static int solveTestCase ( TestCase testCase )
    {
        Arrays.sort( testCase.some.coordinates );
        Arrays.sort( testCase.other.coordinates );

        int product = 0;
        int length = testCase.some.coordinates.length;

        for ( int i = 0 ; i < length ; i++ )
        {
            int first = testCase.some.coordinates[ i ];
            int second = testCase.other.coordinates[ length - i - 1 ];

            product += first * second;
        }

        return product;
    }


    public static void main ( String[] args )
    {
        FileInputStream fileInputStream = readFromFile( "/google/code/scalar/testcase.txt" );
        List< TestCase > testCases = readTestCases( fileInputStream, TestCase::fromScanner );

        testCases.forEach( testCase -> {
            int product = solveTestCase( testCase );
            System.out.println( "product " + product );
        } );
        System.out.println( "DONE" );

    }
}
