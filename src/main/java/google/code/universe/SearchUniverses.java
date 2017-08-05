package google.code.universe;


import scala.Int;

import java.io.*;
import java.net.URL;
import java.util.*;


public class SearchUniverses
{
    static class TestCase
    {
        Map< String, Integer > engines;
        List<String>           searches;


        TestCase ( Map< String, Integer > engines, List<String> searches )
        {
            this.engines = engines;
            this.searches = searches;
        }


        static TestCase fromScanner ( Scanner scanner )
        {
            int numEngines = scanner.nextInt( );
            Map< String, Integer > engines = new HashMap<>( );
            while ( numEngines-- > 0 )
            {
                engines.put( readLineSafe( scanner ), numEngines );
            }

            int numCases = scanner.nextInt( );
            List<String> cases = new ArrayList<>( numCases );

            int index = 0;
            while ( index < numCases )
            {
                cases.add( readLineSafe( scanner ) );
                ++index;
            }

            return new TestCase( engines, cases );
        }
    }


    private static String readLineSafe( Scanner scanner )
    {
        String input = scanner.nextLine();
        while ( input.isEmpty( ) )
        {
            input = scanner.nextLine( );
        }
        return input;
    }

    private static FileInputStream readFromFile ( )
    {
        try
        {
            URL resource = SearchUniverses.class.getResource( "testcase.txt" );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );
        }
        catch ( IOException e )
        {
            return null;
        }
    }


    private static List< TestCase > readTestCases ( InputStream is )
    {
        Scanner scan = new Scanner( is );
        int numCases = scan.nextInt( );
        List< TestCase > testCases = new ArrayList<>( numCases );
        while ( numCases-- > 0 )
        {
            testCases.add( TestCase.fromScanner( scan ) );
        }

        return testCases;
    }


    private static void resetEngineSearches ( int[] allEngineSearches )
    {
        Arrays.fill( allEngineSearches, 0 );
    }


    private static boolean updateReference ( int index, int[] allEngineSearches )
    {
        int engineSearches = allEngineSearches[ index ];
        if ( engineSearches != 0 )
        {
            // Don't care about how many times we used this engine, just that we used it
            return false;
        }

        int numEngines = allEngineSearches.length - 1;
        int enginesReferenced = allEngineSearches[ numEngines ] + 1;
        allEngineSearches[ numEngines ] = enginesReferenced;
        allEngineSearches[ index ] = 1;

        return enginesReferenced == numEngines;
    }


    private static int solveTestCase( TestCase testCase )
    {
        Map< String, Integer > engines = testCase.engines;
        int size = engines.size();
        int[] allEngineSearches = new int[ size + 1 ];

        return testCase.searches.stream( )
                                .reduce( 0,
                                         ( count, search ) -> {
                                             Integer engineIndex = engines.get( search );
                                             boolean shouldSwitch = updateReference( engineIndex,
                                                                                     allEngineSearches );
                                             if ( shouldSwitch )
                                             {
                                                 resetEngineSearches( allEngineSearches );
                                                 updateReference( engineIndex, allEngineSearches );
                                                 return count + 1;
                                             }

                                             return count;
                                         },
                                         ( some, other ) -> some + other );

    }

    public static void main ( String[] args )
    {
        FileInputStream fileInputStream = readFromFile( );

        List< TestCase > testCases = readTestCases( fileInputStream );

        testCases.forEach( testCase -> {
            int switches = solveTestCase( testCase );
            System.out.println( "Case " + switches );
        } );
        System.out.println( "DONE" );
    }
}
