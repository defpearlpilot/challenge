package leetcode.easy;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class RottingOranges {
    /*
    In a given grid, each cell can have one of three values:

    the value 0 representing an empty cell;
    the value 1 representing a fresh orange;
    the value 2 representing a rotten orange.
    Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

    Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
     */

    private static int NONE = 0;
    private static int FRESH = 1;
    private static int ROTTING = 2;

    private static boolean mutate(int r, int c, int[][] source, int[][] dest) {
        boolean modified = false;

        if (source[r][c] == 2) {
            if (r - 1 > 0) {
                dest[r - 1][c] = 2;
                modified = true;
            }
            if (r + 1 < dest.length) {
                dest[r + 1][c] = 2;
                modified = true;
            }
            if (c -1 > 0) {
                dest[r][c-1] = 2;
                modified = true;
            }
            if (c + 1 > source[r].length) {
                dest[r][c+1] = 2;
                modified = true;
            }
        }

        return modified;
    }


    private static boolean rotOranges(int[][] grid) {
        int[][] copy = new int[grid.length][];

        boolean modified = false;
        for (int r = 0; r < grid.length; r++) {
            copy[r] = new int[grid[r].length];
            for (int c = 0; c < grid[r].length; c++) {
                modified = mutate(r, c, grid, copy);
            }
        }

        if (modified) {
            rotOranges(copy);
        }

        return modified;
    }

    static class Orange {
        int row;
        int col;
        int minute;

        Orange(int row, int col, int minute) {
            this.row = row;
            this.col = col;
            this.minute = minute;
        }
    }

    private static int bfsRotOranges(int[][] grid) {
        Queue<Orange> queue = new ArrayDeque<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == ROTTING) {
                    queue.add(new Orange(row, col, 0));
                }
            }
        }

        int minutes = -1;

        while(!queue.isEmpty()) {
            Orange head = queue.remove();
            minutes = Math.max(minutes, head.minute);
            grid[head.row][head.col] = ROTTING;

            if (head.row - 1 > 0 && grid[head.row - 1][head.col] == FRESH) {
                queue.add(new Orange(head.row - 1, head.col, head.minute + 1));
            }
            if (head.row + 1 < grid.length && grid[head.row + 1][head.col] == FRESH) {
                queue.add(new Orange(head.row + 1, head.col, head.minute + 1));
            }
            if (head.col - 1 > 0 && grid[head.row][head.col - 1] == FRESH) {
                queue.add(new Orange(head.row, head.col - 1, head.minute + 1));
            }
            if (head.col + 1 < grid[head.row].length && grid[head.row][head.col + 1] == FRESH) {
                queue.add(new Orange(head.row, head.col + 1, head.minute + 1));
            }
        }

        return minutes;
    }

    public static int orangesRotting(int[][] grid) {

        return bfsRotOranges(grid);
    }

    private static void testOne() {
        /*
        Input:
          2,1,1 0,0,0 2
          1,1,0 0,0,0
          0,1,1 0,0,0

          2,2,1
          2,1,0
          0,1,1

          2,2,2
          2,2,0
          0,1,1

          2,2,2
          2,2,0
          0,2,1

          2,2,2
          2,2,0
          0,2,2

        Output: 4
         */
        int data[][] = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        if (orangesRotting(data) != 4) {
            System.out.println("Result is not 4");
        }
        ;
    }

    private static void testTwo() {
        /*
        Input:
          2,1,1
          0,1,1
          1,0,1
        Output: -1
        Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
         */
    }

    public static void main(String[] args) {
        testOne();
    }

}
