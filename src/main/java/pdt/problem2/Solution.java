package pdt.problem2;


import java.io.FileInputStream;
import java.util.Scanner;

import static common.TestCaseSupport.readFromFile;


public class Solution
{
    static int fourthBit(int num)
    {
        if ((num & 16) > 0)
            return 1;

        return 0;
    }


    private static void localMain()
    {
        FileInputStream fileInputStream = readFromFile( "/pdt/problem2/problem.txt" );
        Scanner in = new Scanner( fileInputStream );

        String elements = in.next( );

        System.out.println( elements );
    }

    public static void main(String[] args)
    {
        System.out.println(fourthBit( 32 ));
        System.out.println(fourthBit( 77 ));
    }
}
