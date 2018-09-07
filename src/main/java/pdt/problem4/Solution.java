package pdt.problem4;


import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

import static common.TestCaseSupport.readFromFile;


public class Solution
{


    private static void localMain()
    {
        FileInputStream fileInputStream = readFromFile( "/pdt/problem4/problem3.txt" );
        Scanner in = new Scanner( fileInputStream );

        int arraySize = in.nextInt();

        if (arraySize == 0)
        {
            System.out.println( -1 );
            return;
        }

        int[] array = new int[arraySize];

        int applications = in.nextInt();

        int max = -1;
        for (int i = 0; i < applications; i++)
        {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int k = in.nextInt();
//            System.out.println(String.format("%d %d %d", a, b, k));

            for (int sub = a; sub <= b; sub++)
            {
                array[sub] = array[sub] + k;

                if (array[sub] > max)
                {
                    max = array[ sub ];
                }
//                System.out.println( Arrays.toString( array ) );
            }

        }

        System.out.println( max );
    }

    public static void main(String[] args)
    {
        localMain();
    }
}
