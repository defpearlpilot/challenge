package careercup.gaps;


import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StringConcatenator
{
    private static int[] stringToCharMask(String s)
    {
        return charArrayToMask( s.toCharArray( ) );
    }


    private static int[] charArrayToMask( char[] a )
    {
        int[] occurrences = new int[ 26 ];
        for (char c: a)
        {
            Character ch = Character.toLowerCase( c );
            int letterIndex = Integer.valueOf( ch ) - 97;
            occurrences[ letterIndex ] = occurrences[ letterIndex ] + 1;
        }

        return occurrences;
    }


    private static int[] diffMasks(int[] base, int[] sub)
    {
        if (base == null || sub == null)
        {
            return null;
        }

        int[] maskDiff = new int[ base.length ];

        for (int i = 0; i < base.length; i++)
        {
            int diff = base[i] - sub[i];
            if (diff < 0)
            {
                return null;
            }

            maskDiff[ i ] = diff;
        }

        return maskDiff;
    }


    private static String max(String some, String other)
    {
        return some.length( ) > other.length( ) ? some : other;
    }


    private static String findMax(int[] charPoolMask,
                                  String[] strings,
                                  Map<String, int[]> masks,
                                  String currentAgg,
                                  String currentMax,
                                  int start)
    {
        String localMax = currentMax;

        for (int i = start; i < strings.length; i++)
        {
            String s1 = strings[ i ];
            int[] mask = masks.get( s1 );

            int[] diff = diffMasks( charPoolMask, mask );
            if (diff != null)
            {
                String thisAgg = currentAgg + s1;
                localMax = max( localMax, thisAgg );

                String potentialMax = findMax( diff, strings, masks, thisAgg, localMax, i + 1 );
                localMax = max( localMax, potentialMax );
            }
        }

        return localMax;
    }


    public static void main(String[] args)
    {
        char[] workingChars = new char[]{ 'a', 'a', 'b', 'c', 'd', 'd', 'e', 'c' };
        String[] strings = new String[]{ "abba", "aabc", "de", "cde" };

        int[] charMask = charArrayToMask( workingChars );

        Map< String, int[] > stringMaskMap = Stream.of( strings )
                                                   .collect( Collectors.toMap( Function.identity( ),
                                                                               StringConcatenator::stringToCharMask ) );

        String maxString = "";

        findMax( charMask, strings, stringMaskMap, "", maxString, 0 );
    }
}
