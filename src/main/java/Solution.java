import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static String generatePoundString( int length, int depth )
    {
        StringBuffer buffer = new StringBuffer();


        for (int i = length; i > 0; i--)
        {
            if (i > depth)
            {
                buffer.append(' ');
            }
            else
            {
                buffer.append('#');
            }
        }

        return buffer.toString();
    }

    static void StairCase(int length) {
        int depth = 0;

        while( depth < length ) {
//            if (depth - length == 0)
//            {
//                depth++;
//                continue;
//            }
            System.out.println(generatePoundString( length, ++depth ));
        }

    }


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int _n;
        _n = Integer.parseInt(in.nextLine().trim());

        String x = "hi";

        StairCase(_n);

    }
}
