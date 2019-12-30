package leetcode.medium;


import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class MergeIntervals
{
    public int[][] merge(int[][] intervals) {
        int BEGIN = 0;
        int END = 1;

        if (intervals == null || intervals.length == 0 || intervals.length == 1) {
            return intervals;
        }

        PriorityQueue<int[]> sorted = new PriorityQueue<>( Comparator.comparingInt( arr -> arr[ BEGIN ] ) );

        for (int[] interval: intervals) {
            sorted.add( interval );
        }

        Queue<int[]> merged = new ArrayDeque<>( intervals.length );

        Stack<int[]> stack = new Stack<>();
        stack.push( sorted.remove() );

        while (!sorted.isEmpty()) {
            int[] some = stack.pop();
            int[] other = sorted.remove();

            if (some[BEGIN] <= other[BEGIN] && some[END] >= other[END]) {
                int[] combined = new int[]{ some[ BEGIN ], some[ END ] };
                stack.push( combined );
            } else if (some[END] >= other[BEGIN]) {
                int[] combined = new int[]{ some[ BEGIN ], other[ END ] };
                stack.push( combined );
            } else {
                merged.add( some );
                stack.push( other );
            }
        }

        if (!stack.empty()) {
            merged.add( stack.pop( ) );
        }

        return merged.toArray( new int[merged.size()][] );
    }

    public static void main(String[] args) {
        MergeIntervals merge = new MergeIntervals();
//        int[][] merged = merge.merge( new int[][]{{1,4}, {2, 3}});
//        System.out.println("DONE");

        int[][] merged = merge.merge( new int[][]{{1,3}, {2, 6}, {8, 10}, {15, 18}} );
        System.out.println("DONE");
    }
}
