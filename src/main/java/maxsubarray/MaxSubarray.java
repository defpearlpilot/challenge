package maxsubarray;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by andrew on 2/7/16.
 */
public class MaxSubarray
{
    private static void calculateMaxSubarrays(int[] array)
    {
        List<Integer> allPositives = new LinkedList<>( );

        List< List< Integer > > positiveLists = new LinkedList<>( );
        List< Integer > currentMax = new LinkedList<>( );

        int last = -1;
        for (int i = 0; i < array.length; i++)
        {
            int current = array[ i ];
            if (current > 0)
            {
                allPositives.add( current );

                if ( i == 0 )
                {
                    currentMax.add( current );
                }
                else if ( last > 0 )
                {
                    currentMax.add( current );
                }
                else
                {
                    positiveLists.add( currentMax );
                    currentMax = new LinkedList<>( );
                }
            }
            else
            {
                positiveLists.add( currentMax );
                currentMax = new LinkedList<>( );
            }
        }

        if (currentMax.size() > 0)
        {
            positiveLists.add( currentMax );
        }

        int sum = allPositives.stream( )
                              .mapToInt( Integer::intValue )
                              .sum( );

        int currentMaxInt = 0;
        for (List<Integer> sub: positiveLists)
        {
            int subSum = sub.stream( )
                            .mapToInt( Integer::intValue )
                            .sum( );

            if (subSum > currentMaxInt)
            {
                currentMaxInt = subSum;
            }
        }

        System.out.println( sum );
        System.out.println( currentMaxInt );
    }

    public static void main(String args[])
    {
        int[] array = new int[]{ 1, 2, 3, 4 };

        calculateMaxSubarrays( array );
    }
}
