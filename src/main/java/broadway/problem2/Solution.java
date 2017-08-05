package broadway.problem2;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;


public class Solution
{
    private static FileInputStream readFromFile()
    {
        try
        {
            URL resource = Solution.class.getResource( "testcase3" );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );
        }
        catch ( IOException e )
        {
            return null;
        }
    }


    static public class DoubleLinkedListNode {
        public int val;
        public DoubleLinkedListNode prev, next;

        DoubleLinkedListNode(int node_value) {
            val = node_value;
            prev = next = null;
        }
    };


    static DoubleLinkedListNode Insert(DoubleLinkedListNode node, int value) {
        if (node == null)
        {
            DoubleLinkedListNode current = new DoubleLinkedListNode( value );
            current.next = current;
            current.prev = current;
            return current;
        }

        DoubleLinkedListNode current = node;
        DoubleLinkedListNode newNode = new DoubleLinkedListNode( value );

        if (value < current.val)
        {
            while (value < current.val && current.prev.val < current.val)
            {
                current = current.prev;

                if (current == node)
                {
                    current = current.next;
                    break;
                }
            }
        }
        else
        {
            while (value > current.val && current.next.val > current.val)
            {
                current = current.next;

                if (current == node)
                {
                    current = current.prev;
                    break;
                }
            }
        }

        DoubleLinkedListNode previous = current;
        DoubleLinkedListNode next = current.next;

        newNode.prev = previous;
        newNode.next = next;

        previous.next = newNode;
        next.prev = newNode;

        return newNode;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner( readFromFile( ) );

        int elements = in.nextInt( );

        int[] array = new int[ elements ];

        for ( int i = 0; i < elements; i++ )
        {
            array[i] = in.nextInt( );
        }

        Arrays.sort(array);

        int insertValue = in.nextInt();

        DoubleLinkedListNode head = new DoubleLinkedListNode( array[ 0 ] );
        DoubleLinkedListNode previous = head;
        DoubleLinkedListNode current = head;

        DoubleLinkedListNode insertNode = head;

        for ( int i = 1; i < elements; i++ )
        {
            current = new DoubleLinkedListNode( array[ i ] );
            previous.next = current;
            current.prev = previous;
            previous = current;

            if (current.val == 1 || current.val == 7 || current.val == 9)
            {
                insertNode = current;
            }
        }

        current.next = head;
        head.prev = current;

//        Insert( insertNode, 5 );
//        Insert( insertNode, 11 );
        Insert( insertNode, insertValue );

        current = head;

        do
        {
            System.out.println( current.val );
            current = current.next;
        }
        while ( current != head );

    }
}
