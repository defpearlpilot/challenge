package medium.flipmatrix;


import stringchain.StringChain;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class MatrixFlip
{
    private abstract static class Orientation
    {
        int index;
        int mag;

        Orientation(int index, int magnitude)
        {
            this.index = index;
            this.mag = magnitude;
        }

        abstract void flip(int maxWidth, int[][] matrix);

        public boolean equals(Object other)
        {
            if (this == other)
            {
                return true;
            }

            if (!(other instanceof Orientation))
            {
                return false;
            }

            if (!this.getClass().isAssignableFrom( other.getClass() ))
            {
                return false;
            }

            Orientation otherO = (Orientation) other;

            return this.index == otherO.index;
        }
    }

    private static class Row extends Orientation
    {
        Row(int index, int mag)
        {
            super(index, mag);
        }

        void flip(int maxWidth, int[][] matrix)
        {
            for (int j = 0; j <= maxWidth/ 2; j++)
            {
                int temp = matrix[this.index][maxWidth-j];
                matrix[this.index][maxWidth-j] = matrix[this.index][j];
                matrix[this.index][j] = temp;
            }
        }

        public String toString()
        {
            return "Row("+index+","+mag+")";
        }
    }

    private static class Column extends Orientation
    {
        Column(int index, int mag)
        {
            super( index, mag );
        }

        void flip(int maxWidth, int[][] matrix)
        {
            for (int i = 0; i <= maxWidth / 2; i++)
            {
                int temp = matrix[maxWidth - i][this.index];
                matrix[maxWidth - i][this.index] = matrix[i][this.index];
                matrix[i][this.index] = temp;
            }
        }

        public String toString()
        {
            return "Col("+index+","+mag+")";
        }
    }


    private static int calculateQuad(int n, int[][] matrix)
    {
        int sum = 0;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                sum += matrix[i][j];
            }
        }

        return sum;
    }


    private static void printMatrix(int n, int[][] matrix)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.printf( matrix[ i ][ j ] + " ");
            }
            System.out.println( "" );
        }
        System.out.println( "" );
    }


    private static List<Orientation> calculateEntropy( int width, int halfWidth, int [][] matrix)
    {
        int rows[] = new int[ width];
        int columns[] = new int[ width];

        int maxIndex = width - 1;

        for (int i = 0; i <= maxIndex; i++)
        {
            for (int j = 0; j <= maxIndex; j++)
            {
                int item = matrix[i][j];

                if (j < halfWidth) {
                    rows[i] += item;
                } else {
                    rows[i] -= item;
                }

                if (i < halfWidth) {
                    columns[j] += item;
                } else {
                    columns[j] -= item;
                }
            }
        }

        List<Orientation> orientations = new LinkedList<>( );


        for (int i = 0; i < width; i++)
        {
            orientations.add( new Row( i, rows[ i ] ) );
            orientations.add( new Column( i, columns[ i ] ) );
        }


        orientations.sort( (some, other) -> {
            if (some.mag == other.mag)
            {
                return 0;
            }
            else if (some.mag < other.mag)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        } );

        //orientations.forEach( System.out::println );

        return orientations;
    }


    private static int maximizeMatrix(int width, int halfWidth, int[][] matrix)
    {
        int sum = 0;
        for (int row = 0; row < halfWidth; row++)
        {
            for (int col = 0; col < halfWidth; col++)
            {
                int rightCol = width - col;
                int lowerRow = width - row;

                int max = matrix[ row ][ col ];
                int upperRightQuad = matrix[ row ][ rightCol ];
                int lowerLeftQuad = matrix[ lowerRow ][ col ];
                int lowerRightQuad = matrix[ lowerRow ][ rightCol ];

                if (upperRightQuad > max)
                {
                    max = upperRightQuad;
                }
                if (lowerLeftQuad > max)
                {
                    max = lowerLeftQuad;
                }
                if (lowerRightQuad > max)
                {
                    max = lowerRightQuad;
                }

                sum += max;
            }
        }

        return sum;
    }

    private static int maximizeMatrixFlip(int width, int halfWidth, int [][] matrix)
    {
        //printMatrix( width, matrix );
        List<Orientation> orientations = calculateEntropy( width, halfWidth, matrix );
        Orientation previous;
        Orientation orientation = orientations.get( 0 );


        int max = calculateQuad( halfWidth, matrix );

        while (true)
        {
            orientation.flip( width - 1, matrix );
            previous = orientation;

            int sum = calculateQuad( halfWidth, matrix );

            if (sum > max)
            {
                max = sum;
            }

            orientations = calculateEntropy( width, halfWidth, matrix );
            orientation = orientations.remove( 0 );
            if (orientation.mag > 0)
            {
                break;
            }

            if ( orientation.equals( previous ) )
            {
                orientation = orientations.remove( 0 );
            }
        }

        return max;
    }


    private static FileInputStream readFromFile()
    {
        try
        {
            URL resource = MatrixFlip.class.getResource( "bluemountain1" );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );

        }
        catch ( IOException e )
        {
            return null;
        }

    };


    public static void main(String[] args)
    {

//        Scanner in = new Scanner( System.in);
        Scanner in = new Scanner( readFromFile() );
        int queries = in.nextInt();

        for (int qcount = 0; qcount < queries; qcount++)
        {
            int n = in.nextInt();
            int count = n * 2;

            int[][] matrix = new int[count][count];

            for (int i = 0; i < count; i++)
            {
                for (int j = 0; j < count; j++)
                {
                    matrix[i][j] = in.nextInt();
                    //System.out.println( matrix[i][j] );
                }
            }

            System.out.println( maximizeMatrix( (n * 2) -1, n, matrix ) );

        }



/*
        int n = 2;

        int[][] matrix = new int[][] {
            {112, 42, 83, 119},
            {56, 125, 56, 49},
            {15, 78, 101, 43},
            {62, 98, 114, 108}
        };

        Orientation row = new Row( 0, 0 );
        row.flip( 3, matrix );

        Orientation col = new Column( 0, 0 );
        col.flip( 3, matrix );
*/

//        System.out.println( "DONE" );
//        maximizeMatrix(n * 2, n, matrix );
    }
}
