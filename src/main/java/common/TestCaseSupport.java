package common;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;


public class TestCaseSupport
{
    public static <TC> List< TC > readTestCases( InputStream is, Function<Scanner, TC> supplier)
    {
        Scanner scan = new Scanner( is );
        int numCases = scan.nextInt( );
        List< TC > testCases = new ArrayList<>( numCases );
        while ( numCases-- > 0 )
        {
            testCases.add( supplier.apply( scan ) );
        }

        return testCases;
    }


    public static FileInputStream readFromFile( String testFile )
    {
        try
        {
            URL resource = TestCaseSupport.class.getResource( testFile );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );
        }
        catch ( IOException e )
        {
            return null;
        }
    }

}
