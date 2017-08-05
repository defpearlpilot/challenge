package substring;


import java.io.*;
import java.net.URL;
import java.util.*;


/**
 * Created by andrew on 2/7/16.
 */
public class Solution
{
    static int countSub(int length)
    {
        return length / 2;
    }


    static char opposite(char c)
    {
        if (c == '0')
        {
            return '1';
        }
        else
        {
            return '0';
        }
    }


    static int counting(String s)
    {
        int totalSequences = 0;

        Map<Character, Integer> sequenceCount = new HashMap<>();

        sequenceCount.put('0', 0);
        sequenceCount.put('1', 0);

        char previous = s.charAt( 0 );

        sequenceCount.put(previous, 1);


        for (int i = 1; i < s.length(); i++)
        {
            char current = s.charAt( i );


            System.out.println("CURRENT " +current);

            if (current == previous) {
                int count = sequenceCount.get(previous);
                ++count;
                sequenceCount.put(current,  count);

                int otherCount = sequenceCount.get(opposite(current));
                if (count <= otherCount)
                {
                    ++totalSequences;
                }
            }
            else
            {
                sequenceCount.put( current, 1 );
                ++totalSequences;
            }

            previous = current;

            int count = sequenceCount.get(current);
            int otherCount = sequenceCount.get(opposite(current));
            if (count == otherCount)
            {
                sequenceCount.put( current, 1 );
                sequenceCount.put(opposite(current), 0);
            }

        }

        return totalSequences;
    }


    public static void main(String[] args) throws IOException
    {
        System.out.println( counting( "00110" ) );
        System.out.println( counting( "10101" ) );
    }
}
