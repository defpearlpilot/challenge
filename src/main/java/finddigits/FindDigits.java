package finddigits;


import java.util.*;


public class FindDigits
{
    private static int countDigits(int number)
    {
        String nString = String.valueOf( number );
        Set< Integer > visited = new HashSet<>( );

        int found = 0;

        for (int index = 0; index < nString.length(); index++)
        {
            char ch = nString.charAt( index );
            int digit = Character.getNumericValue( ch );

            if (digit == 0)
            {
                continue;
            }

            if (digit == 1)
            {
                found++;
                continue;
            }

            if (visited.contains( digit ))
            {
                found++;
                continue;
            }

            if (number % digit ==  0)
            {
                found++;
                visited.add( digit );
            }
        }

        return found;
    };


    public static void main(String[] args)
    {
/*
        Scanner in = new Scanner( System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
        }
*/

        System.out.println( countDigits( 1012 ) );

    }
}
