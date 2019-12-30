package leetcode.medium;


public class LongestCommonSubsequence
{
    public static int lcsRecursive ( Integer[][] memo, char[] some, char[] other, int someIndex, int otherIndex )
    {
        Integer memoValue = memo[ someIndex ][ otherIndex ];
        if ( memoValue != null )
        {
            return memoValue;
        }

        if ( someIndex == 0 || otherIndex == 0 )
        {
//            System.out.println( "End comparing " + someIndex + " => " + some[someIndex] + " to " + otherIndex + " => "  + other[otherIndex] );
            memo[ someIndex ][ otherIndex ] = 0;
            return memo[ someIndex ][ otherIndex ];
        }

//        System.out.println( "Comparing " + someIndex + " => " + some[someIndex] + " to " + otherIndex + " => "  + other[otherIndex] );
        else if ( some[ someIndex ] == other[ otherIndex ] )
        {
            System.out.println( "Taking " + some[someIndex] + " => "  + other[otherIndex] );

            memo[ someIndex ][ otherIndex ] = 1 + lcsRecursive( memo, some, other, someIndex - 1, otherIndex - 1 );
        }
        else
        {
            int someMax = lcsRecursive( memo, some, other, someIndex - 1, otherIndex );
            int otherMax = lcsRecursive( memo, some, other, someIndex, otherIndex - 1 );

            memo[ someIndex ][ otherIndex ] = Math.max( someMax, otherMax );
        }

        return memo[ someIndex ][ otherIndex ];
    }


    public static void main ( String[] args )
    {
//        String some = "DFJSIEIS";
//        String other = "AEDDSEIX";

        // "h
        /*
        some = "hofmr";
        other = "hofmr";

         */
        String some = "hofubmnylkra";
        String other = "pqhgxgdofcvmr";

        char[] someChars = some.toCharArray( );
        char[] otherChars = other.toCharArray( );

        Integer[][] memo = new Integer[ someChars.length ][ otherChars.length ];

        int subsequence = lcsRecursive( memo, someChars, otherChars, someChars.length - 1, otherChars.length - 1 );
        System.out.println( "Max is " + subsequence );
    }
}
