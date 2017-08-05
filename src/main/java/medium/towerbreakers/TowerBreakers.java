package medium.towerbreakers;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class TowerBreakers
{
    private static FileInputStream readFromFile()
    {
        try
        {
            URL resource = TowerBreakers.class.getResource( "testcase1.2" );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );

        }
        catch ( IOException e )
        {
            return null;
        }
    }


    private static boolean LOG_ENABLED = true;


    private static void LOG(String format, Object... args)
    {
        if ( LOG_ENABLED )
        {
            System.out.format(format, args);
        }
    }


    TreeSet<Integer> heights;


    TowerBreakers(TreeSet<Integer> heights)
    {
        this.heights = heights;
    }


//    private int play(Map<Integer, List<Integer>> factors)
//    {
//        int playerTurn = 2;
//
//        while ( !heights.isEmpty( ) )
//        {
//            Integer item = heights.pollLast( );
//            if (item == 1)
//            {
//                break;
//            }
//
//            int reduced = reduceTower( factors, item );
//            if ( reduced == 0)
//            {
//                return playerTurn;
//            }
//
//            playerTurn = playerTurn == 1 ? 2 : 1;
//
//            if (reduced > 1)
//            {
//                heights.add( reduced );
//            }
//        }
//
//        return playerTurn;
//    }


    private int play ( Map< Integer, List< Integer > > factors )
    {
        int playerTurn = 2;

        int allFactors = 0;
        int factorCount = 0;
        for (int height: heights)
        {
//            System.out.println(height);
            List< Integer > integers = factors.get( height );

            LOG( "%10d:\n", height );
            for (Integer factor: integers)
            {
                LOG( "%10d", factor );
            }
            LOG("\n");

            if (integers.size() > 1)
            {
                factorCount += integers.size();
            }
        }
        LOG( "factors: %10d\n", factorCount);

        return factorCount % 2 == 1 ? 1: 2;
    }


    private static List<Integer> newLinkedList(int item)
    {
        List<Integer> items = new LinkedList<>( );
        items.add( item );

        return items;
    }


    private static boolean factorize(Map< Integer, List< Integer > > factors, int max)
    {
        factors.put( 1, newLinkedList( 1 ) );

        for ( int factor = 2 ; factor * factor <= max ; factor++ )
        {
            List< Integer > prevFactors = factors.get( factor );
            if (prevFactors != null)
            {
                continue;
            }

            factors.put( factor, newLinkedList( factor ) );
            int product = factor * factor;

            for ( int multiple = factor; product <= max; multiple++)
            {
                product = factor * multiple;

                List<Integer> theseFactors = factors.get(product);
                if (theseFactors != null)
                {
                    continue;
                }

                theseFactors = newLinkedList( factor );
                factors.put( product, theseFactors );

                List<Integer> multipleFactors = factors.get(multiple);
                if (multipleFactors != null)
                {
                    theseFactors.addAll( multipleFactors );
                }
                else
                {
                    theseFactors.add( multiple );
                }
            }
        }

        return true;
    }


    private static boolean patchFactors(Map< Integer, List< Integer > > factorMap, int max)
    {
        for ( int number = 3 ; number <= max ; number++ )
        {
            List< Integer > factors = factorMap.get( number );
            if ( factors == null )
            {
                factorMap.put( number, newLinkedList( number ) );
            }
        }

        return true;
    }


        private static boolean breakDownFactors(Map< Integer, List< Integer > > factorMap, int max)
    {
        for ( int number = 3 ; number <= max ; number++ )
        {
            List< Integer > factors = factorMap.get( number );
            if (factors == null)
            {
                factorMap.put( number, newLinkedList( number ) );
                continue;
            }

            if (factors.size() == 1)
            {
                continue;
            }

            List< Integer > replacement = new LinkedList<>( );

            for (Integer factor: factors)
            {
                List< Integer > subFactors = factorMap.get( factor );
                if (subFactors.size() == 1)
                {
                    replacement.add(factor);
                }
                else
                {
                    replacement.addAll( subFactors );
                }

                //System.out.format( "%5d", factor );
            }

//            System.out.println( );
//
            factorMap.put( number, replacement );
//
//            for (Integer factor: replacement)
//            {
//                System.out.format( "%5d", factor );
//            }
//
//            System.out.println( );

        }

        return true;
    }


//    private int reduceTower(Map< Integer, Set<Integer> > factors, int height)
//    {
//        if (height <= 3)
//        {
//            return 1;
//        }
//
//        for (int divisor = 2; divisor <= 100000; divisor++)
//        {
//            if (height % divisor == 0)
//            {
//                return divisor;
//            }
//        }
//
//        return 0;
//    }

    public static void main(String[] args)
    {

//        Scanner in = new Scanner( System.in);
        Scanner in = new Scanner( readFromFile() );
        int testCases = in.nextInt();

        Map< Integer, List< Integer > > factors = new HashMap<>( );

        factorize( factors, 1000000 );
        breakDownFactors( factors, 1000000 );

        System.out.println( "HELLO" );
        for (int tcount = 0; tcount < testCases; tcount++)
        {
            int numTowers = in.nextInt();

            TreeSet<Integer> towers = new TreeSet<>( );
            for (int nTower = 0; nTower < numTowers; nTower++)
            {
                towers.add( in.nextInt() );
            }

            TowerBreakers tb = new TowerBreakers( towers );
            System.out.println( tb.play( factors ) );

            //LOG( "\n" );
        }


    }
}
