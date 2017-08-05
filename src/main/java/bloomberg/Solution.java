package bloomberg;


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
  I spent a bit of time trying to optimize this problem.  Similarly to a recursive fib problem, we end up evaluating
  the same results several times.  In order to prevent that, I keep a separate matrix for visited paths and how
  many paths there are from that location.

  For example, in the original problem:

  1 1 1 1
  1 1 1 1
  1 1 1 1

  it will do something like

  0 0 0 0
  0 0 0 1
  0 0 0 0

  0 0 0 1
  0 0 0 1
  0 0 0 0

  0 0 3 1
  0 0 2 1
  0 0 1 0

  0 6 3 1
  0 3 2 1
  0 1 1 0

  10 6 3 1
   4 3 2 1
   1 1 1 0

  I suspect that I may still have some problems with the high count problems and the mod.  I would also think that there may be some problems with
  puzzles that may have odd distributions of zeros in the matrix.

  The app also hung on me again so I wasn't really able to test any further and I didn't want to risk losing the work.

  I have to get back to a presentation for senior management.  Yay! /s

  Thanks for your time!!

*/


public class Solution {
    /*
 * Complete the function below.
 */
    static final int MOD = 1000000007;

    // checks if we can move
    static boolean canMove(int row, int col, int[][]a)
    {
        return (a[row][col] == 1);
    }

    // checks if we can move down (bounds), from the current point to the down point
    static boolean canMoveDown(int row, int col, int[][] a, int height)
    {
        if (!canMove(row, col, a))
        {
            return false;
        }

        if (row + 1 == height)
        {
            return false;
        }

        return canMove(row + 1, col, a);
    }

    // checks if we can move right (bounds), from the current point to the right point
    static boolean canMoveRight(int row, int col, int[][] a, int width)
    {
        if (!canMove(row, col, a))
        {
            return false;
        }

        if (col + 1 == width)
        {
            return false;
        }

        return canMove(row, col + 1, a);
    }


    static int countPaths(int row, int col, int h, int w, int[][]a, int[][] p)
    {
        // termination of recursion
        if (row == h -1 && col == w -1)
        {
            return 1;
        }

        int validPaths = 0;

        // valid to recurse right
        if (canMoveRight(row, col, a, w))
        {
            // check if we've already gone to the right
            int visitedCount = p[row][col+1];
            if (visitedCount == -1)
            {
                // visit if not
                visitedCount = countPaths(row, col+1, h, w, a, p);
                p[row][col+1] = visitedCount;
            }

            validPaths += visitedCount;
        }

        // valid to recurse down
        if (canMoveDown(row, col, a, h))
        {
            // check if we've already gone down
            int visitedCount = p[row +1][col];
            if (visitedCount == -1)
            {
                // visit if not
                visitedCount = countPaths(row+1, col, h, w, a, p);
                p[row+1][col] = visitedCount;
            }

            validPaths += visitedCount;
        }

        return validPaths;
    }

    static long numberOfPaths(int[][] a) {
        int height = a.length;
        if (height == 0)
        {
            return 0;
        }

        int width = a[0].length;
        if (width == 0)
        {
            return 0;
        }

        int[][] p = new int[height][width];

        // matrix for memoizing visited paths
        for (int i=0; i<p.length; i++)
        {
            for (int j=0; j<p[i].length; j++)
            {
                p[i][j] = -1;
            }
        }

        int paths = countPaths(0, 0, height, width, a, p);

        return paths % MOD;
    }


    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        long res;

        int _a_rows = 0;
        int _a_cols = 0;
        _a_rows = Integer.parseInt(in.nextLine().trim());
        _a_cols = Integer.parseInt(in.nextLine().trim());

        int[][] _a = new int[_a_rows][_a_cols];
        for(int _a_i=0; _a_i<_a_rows; _a_i++) {
            for(int _a_j=0; _a_j<_a_cols; _a_j++) {
                _a[_a_i][_a_j] = in.nextInt();

            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }

        res = numberOfPaths(_a);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }
}