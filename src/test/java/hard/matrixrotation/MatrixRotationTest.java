package hard.matrixrotation;


import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by andrew on 12/27/16.
 */
class MatrixRotationTest
{

    @Test
    void testCalculateModulo()
    {
//        assertEquals( 14, new MatrixRotation( ).calculateModulo( 5, 4 ) );
//        assertEquals( 16, new MatrixRotation( ).calculateModulo( 4, 6 ) );
//        assertEquals( 4, new MatrixRotation( ).calculateModulo( 2, 2 ) );
//        assertEquals( 2, new MatrixRotation( ).calculateModulo( 2, 1 ) );
    }

    @Test
    void testTwoByTwoTranslation()
    {
        MatrixRotation rotator = new MatrixRotation( );

        Point origin = new Point( 0, 0 );
//        assertEquals( new Point(0,0), rotator.calculatePosition( origin, 2, 2, 0,0,0 ) );
//        assertEquals( new Point(0,1), rotator.calculatePosition( origin, 2, 2, 0,0, 1 ) );
//        assertEquals( new Point(1,1), rotator.calculatePosition( origin, 2, 2, 0,0, 2 ) );
//        assertEquals( new Point(1,0), rotator.calculatePosition( origin, 2, 2, 0,0, 3 ) );
//        assertEquals( new Point(0,0), rotator.calculatePosition( origin, 2, 2, 0,0, 4 ) );
//
//
//        assertEquals( new Point(0,0), rotator.calculatePosition( origin, 5, 4, 0,0, 0 ) );
//        assertEquals( new Point(0,1), rotator.calculatePosition( origin, 5, 4, 0,0, 1 ) );
//        assertEquals( new Point(0,2), rotator.calculatePosition( origin, 5, 4, 0,0, 2 ) );
//        assertEquals( new Point(0,3), rotator.calculatePosition( origin, 5, 4, 0,0, 3 ) );
//        assertEquals( new Point(1,3), rotator.calculatePosition( origin, 5, 4, 0,0, 4 ) );
//        assertEquals( new Point(2,3), rotator.calculatePosition( origin, 5, 4, 0,0, 5 ) );
//        assertEquals( new Point(3,3), rotator.calculatePosition( origin, 5, 4, 0,0, 6 ) );
//        assertEquals( new Point(4,3), rotator.calculatePosition( origin, 5, 4, 0,0, 7 ) );
//        assertEquals( new Point(4,2), rotator.calculatePosition( origin, 5, 4, 0,0, 8 ) );
//        assertEquals( new Point(4,1), rotator.calculatePosition( origin, 5, 4, 0,0, 9 ) );
//        assertEquals( new Point(4,0), rotator.calculatePosition( origin, 5, 4, 0,0, 10 ) );
//        assertEquals( new Point(3,0), rotator.calculatePosition( origin, 5, 4, 0,0, 11 ) );
//        assertEquals( new Point(2,0), rotator.calculatePosition( origin, 5, 4, 0,0, 12 ) );
//        assertEquals( new Point(1,0), rotator.calculatePosition( origin, 5, 4, 0,0, 13 ) );
//
//
//        Point origin1x1 = new Point( 1, 1 );
//        assertEquals( new Point(1,1), rotator.calculatePosition( origin1x1, 5, 4, 1,1, 0 ) );
//        assertEquals( new Point(1,2), rotator.calculatePosition( origin1x1, 5, 4, 1,1, 1 ) );
//        assertEquals( new Point(2,2), rotator.calculatePosition( origin1x1, 5, 4, 1,1, 2 ) );
//        assertEquals( new Point(2,2), rotator.calculatePosition( origin1x1, 5, 4, 1,1, 2 ) );
    }

/*
    @Pips
    void testCalculateXOffset()
    {
        assertEquals( 0, new MatrixRotation( ).calculateXOffset( 0,0, 5, 4, 1 ) );
        assertEquals( 3, new MatrixRotation( ).calculateXOffset( 0,0, 5, 4, 6 ) );
        assertEquals( 4, new MatrixRotation( ).calculateXOffset( 0,0, 5, 4, 9 ) );
        assertEquals( 2, new MatrixRotation( ).calculateXOffset( 0,0, 5, 4, 12 ) );

        assertEquals( 0, new MatrixRotation( ).calculateXOffset( 0,1, 5, 4, 1 ) );
        assertEquals( 3, new MatrixRotation( ).calculateXOffset( 0, 1, 5, 4, 6 ) );
        assertEquals( 4, new MatrixRotation( ).calculateXOffset( 0, 1, 5, 4, 9 ) );
        assertEquals( 1, new MatrixRotation( ).calculateXOffset( 0, 1, 5, 4, 12 ) );

    }


    @Pips
    void testCalculateYOffset()
    {
        assertEquals( 0, new MatrixRotation( ).calculateYOffset( 0, 5, 4, 1 ) );
        assertEquals( 3, new MatrixRotation( ).calculateYOffset( 0, 5, 4, 6 ) );
        assertEquals( 4, new MatrixRotation( ).calculateYOffset( 0, 5, 4, 9 ) );
        assertEquals( 2, new MatrixRotation( ).calculateYOffset( 0, 5, 4, 12 ) );
    }
*/

}