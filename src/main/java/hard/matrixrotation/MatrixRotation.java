package hard.matrixrotation;


import javafx.beans.WeakInvalidationListener;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class MatrixRotation
{
    private static boolean logEnabled = true;

    private static FileInputStream readFromFile()
    {
        try
        {
            URL resource = MatrixRotation.class.getResource( "testcase6" );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );

        }
        catch ( IOException e )
        {
            return null;
        }
    }


    private int calculateXIncrementClockWise(int width, int height, int x, int y, int xOff, int yOff)
    {
        //topmost
        if (y == yOff)
        {
            if ((x - xOff) > 0)
            {
                return -1;
            }
        }
        //bottom most
        else if (y == (height - yOff -1 )) // at bottom of the array
        {
            if (x < (width - xOff - 1))
            {
                return 1;
            }
        }

        return 0;
    }


    private int calculateYIncrementCounter(int width, int height, int x, int y, int xOff, int yOff)
    {
        //leftmost
        if (x == xOff)
        {
            if (y < (height - yOff - 1)) // at position 0 in loop
            {
                return 1;
            }
        }
        //rightmost
        else if (x == (width - xOff - 1 )) // at bottom of the array
        {
            if ((y - yOff) > 0)
            {
                return -1;
            }
        }

        return 0;
    }


    public int calculateModulo(int colOff, int width, int rowOff, int height)
    {
        int high = (height - (rowOff*2) - 1) * 2;
        int wid = (width - (colOff*2) -1) * 2;

        return high + wid;
    }


    public Point calculatePosition( Point p, int rowInc, int colInc, int height, int width, int rowOff, int colOff, int rotation)
    {
        int modulo = calculateModulo( colOff, width, rowOff, height );
        int translation = rotation % modulo;

        LOG("Moving %s", p);
        if (translation == 0)
        {
            LOG(" to %s\n", p);
            return p;
        }

        int row = p.x;
        int column = p.y;

        do
        {
            row += rowInc;
            column += colInc;
            --translation;

            rowInc = calculateRowIncrement( width, height, row, column, rowOff, colOff );
            colInc = calculateColumnIncrement( width, height, row, column, rowOff, colOff );

            LOG(" to [%d, %d]", row, column);
        }
        while (translation > 0);

        LOG("\n");
        return new Point( row, column );
    }


    private static void printMatrix(int m, int n, int[][] matrix)
    {
        for ( int mi = 0 ; mi < m ; mi++ )
        {
            for ( int ni = 0 ; ni < n ; ni++ )
            {
                System.out.format( "%12d", matrix[ mi ][ ni ]);
            }
            System.out.println( "" );
        }
        System.out.println( "" );
    }


    private int calculateColumnIncrement(int width, int height, int row, int col, int rowOff, int colOff)
    {
        //top
        if (row == rowOff)
        {
//            if (col != colOff)
//            {
//                return -1;
//            }
//            else if (height % 2 == 1 && height/2 == rowOff)
//            {
//                return width - (2 * colOff) - 1;
//            }

            if (height % 2 == 1 && height/2 == rowOff)
            {
                if (col < (width - colOff -1 )) // at bottom of the array
                {
                    return 1;
                }
                else
                {
                    return -(width - (2 * colOff) - 1);
                }
            }
            else if (col != colOff)
            {
                return -1;
            }


        }
        //bottom most
        else if (row == (height - rowOff - 1))
        {
            if (col < (width - colOff -1 )) // at bottom of the array
            {
                return 1;
            }
        }

        return 0;
    }


    private int calculateRowIncrement(int width, int height, int row, int col, int rowOff, int colOff)
    {
        //leftmost
        if (col == colOff)
        {
            if (width % 2 == 1 && width/2 == colOff)
            {
//                if (row == rowOff)
//                {
//                    return height - (2 * rowOff) - 1;
//                }
//                else
//                {
//                    return - 1;
//                }
                if (row < ( height - rowOff - 1 ))
                {
                    return 1;
                }
                else
                {
                    return - (height - (2 * rowOff) - 1);
                }

            }
            else if ( row < ( height - rowOff - 1 ) )
            {
                return 1;
            }
        }
        //rightmost
        else if (col == (width - colOff - 1))
        {
            if (row != rowOff) // at position 0 in loop
            {
                return -1;
            }
        }

        return 0;
    }


    private void LOG(String format, Object... args)
    {
        if (logEnabled)
        {
            System.out.format(format, args);
        }
    }

    public int[][] rotateMatrix(int rotations, int height, int width, int[][] matrix)
    {
        int rowDepth = height/2;
        int columnDepth = width/2;

        int[][] retMatrix = new int[height][width];

        int rowInc;
        int colInc;

        int xOff;
        int yOff;


        for (int col = 0; col < width; col++)
        {
            if (col < columnDepth)
            {
                yOff = col;
            }
            else
            {
                yOff = width - col - 1;
            }

            for (int row = 0; row < height; row++)
            {

                if (row < rowDepth)
                {
                    xOff = row;
                }
                else
                {
                    xOff = height - row - 1;
                }

                int xOffset = Math.min(xOff,yOff);
                int yOffset = Math.min(xOff,yOff);

                Point origin = new Point( row, col );

                int value = matrix[ origin.x ][ origin.y ];
                if (value == 71783860)
                {
                    System.out.println( value );
                }

                rowInc = calculateRowIncrement( width, height, row, col, xOffset, yOffset );
                colInc = calculateColumnIncrement( width, height, row, col, xOffset, yOffset );

                Point translation = calculatePosition( origin, rowInc, colInc, height, width, xOffset, yOffset, rotations );

                retMatrix[translation.x][translation.y] = matrix[origin.x][origin.y];
            }
        }

        return retMatrix;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner( readFromFile( ) );
        int rows = in.nextInt( );
        int columns = in.nextInt( );
        int rotations = in.nextInt( );

        int[][] matrix = new int[ rows ][ columns ];

        for ( int mi = 0 ; mi < rows ; mi++ )
        {
            for ( int ni = 0 ; ni < columns ; ni++ )
            {
                matrix[ mi ][ ni ] = in.nextInt( );
            }
        }

        int[][] result = new MatrixRotation().rotateMatrix(rotations, rows, columns, matrix);

        printMatrix( rows, columns, result );
    }

}
