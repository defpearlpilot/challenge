package pdt.problem5;


import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static common.TestCaseSupport.readFromFile;


public class Solution
{
    private static interface Operation
    {
        int apply(int v);
    }

    private static class IncOperation implements Operation
    {
        private final int addend;

        public IncOperation(int k)
        {
            this.addend = k;
        }

        @Override
        public int apply ( int v )
        {
            return v + addend;
        }
    }

    private static class SuperStack
    {
        private final TreeMap< Integer, List< Operation > > operations;

        private final int[] stack    = new int[ 10000 ];
        private int   stackTop = -1;

        public SuperStack()
        {
            operations = new TreeMap<>( );
        }

        void push ( int i )
        {
            stack[++stackTop] = i;
//            System.out.println( Arrays.toString( stack) );
        }


        int pop( )
        {
            int val = stack[stackTop];

            List< Operation > operations = this.operations.get( stackTop );

            --stackTop;

            // move ops down one level
            if ( operations != null )
            {
                List< Operation > nextOps = this.operations.get( stackTop );
                if ( nextOps == null )
                {
                    this.operations.put( stackTop, operations );
                }
                else
                {
                    nextOps.addAll( operations );
                }
            }

            return val;
        }

        boolean isEmpty()
        {
            return stackTop < 0;
        }

        int peek()
        {
            System.out.print( "KEYS " );
            System.out.println( operations.keySet( ) );

            int v =  stack[stackTop];

            for (Map.Entry<Integer, List<Operation>> entry: this.operations.entrySet())
            {
                if (stackTop < entry.getKey())
                {
                    for (Operation op: entry.getValue())
                    {
                        v = op.apply( v );
                    }
                }
            }

            return v;
        }

        void addOperation ( int height, Operation op )
        {
            List< Operation > operationsForHeight = this.operations.get( height );
            if (operationsForHeight == null)
            {
                operationsForHeight = new LinkedList<>( );
                operations.put( height, operationsForHeight );
            }

            operationsForHeight.add( op );
        }
    }


    private static void localMain()
    {
        FileInputStream fileInputStream = readFromFile( "/pdt/problem5/problem.txt" );
        Scanner in = new Scanner( fileInputStream );

        int operations = in.nextInt( );

        SuperStack stack = new SuperStack();

        for ( int i = 0 ; i < operations ; i++ )
        {
            String op = in.nextLine( );
            System.out.println( op );

            String[] parts = op.split( " " );

            if ( parts.length == 2 && parts[0].equals( "push" ))
            {
                int val = Integer.parseInt( parts[1] );
                stack.push( val );
            }
            else if ( op.equals( "pop" ) )
            {
                stack.pop( );
            }
            else if ( parts.length == 3 && parts[0].equals( "inc" ) )
            {
                int h = Integer.parseInt( parts[1] );
                int k = Integer.parseInt( parts[2] );
                stack.addOperation( h, new IncOperation( k ) );
            }

            if ( stack.isEmpty( ) )
            {
                System.out.println( "EMPTY" );
            }
            else
            {
                System.out.println( stack.peek( ) );
            }
        }
    }

    public static void main(String[] args)
    {
        localMain();
    }
}
