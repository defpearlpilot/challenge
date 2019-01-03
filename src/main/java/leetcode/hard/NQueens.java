package leetcode.hard;

import java.util.*;

/*
[Q] => [1]

// all twos are invalid
[Q,0] => [1,2]
[0,Q]

// all threes are invalid
[Q,0,0] => [1,3,2]
[0,0,Q]
[0,Q,0]

// Four
[0,0,Q,0] => [3,1,4,2]
[Q,0,0,0]
[0,0,0,Q]
[0,Q,0,0]

[0,Q,0,0] => [2,4,1,3]
[0,0,0,Q]
[Q,0,0,0]
[0,0,Q,0]

// illegal = > [1,
[0,Q,0,0] => [2,4,3,1]
[0,0,0,Q]
[0,0,Q,0]
[Q,0,0,0]

// Five
3,5,2,4,1
[0,0,Q,0,0] [0,0,Q,0,0]
[0,0,0,0,Q] [Q,0,0,0,0]
[0,Q,0,0,0] [0,0,0,Q,0]
[0,0,0,Q,0] [0,Q,0,0,0]
[Q,0,0,0,0] [Q,0,0,0,0]

[0,0,0,0,Q] [Q,0,0,0,0]
[0,Q,0,0,0] [0,0,0,Q,0]
[0,0,0,Q,0] [0,Q,0,0,0]
[Q,0,0,0,0] [0,0,0,0,Q]
[0,0,Q,0,0] [0,0,Q,0,0]

[0,Q,0,0,0]
[0,0,0,Q,0]
[Q,0,0,0,0]
[0,0,Q,0,0]
[0,0,0,0,Q]

[0,0,0,Q,0]
[Q,0,0,0,0]
[0,0,Q,0,0]
[0,0,0,0,Q]
[0,Q,0,0,0]

[Q,0,0,0,0]
[0,0,Q,0,0]
[0,0,0,0,Q]
[0,Q,0,0,0]
[0,0,0,Q,0]

 */

    /*
24153
X__X_
[.,Q,.,.,.]
[.,.,.,Q,.]
[Q,.,.,.,.]
[.,.,.,.,Q]
[.,.,Q,.,.]

31524
_X__X
[.,.,Q,.,.]
[Q,.,.,.,.]
[.,.,.,.,Q]
[.,Q,.,.,.]
[.,.,.,Q,.]

     */

public class NQueens {

    private void printArr(int[] board) {
        for (int i1 : board) {
            System.out.print(i1);
        }
        System.out.println();
    }

    private void printLine(String line) {
        System.out.print(line.charAt(0));
        for (int i = 1; i < line.length(); i++) {
            System.out.print(",");
            System.out.print(line.charAt(i));
        }
    }

    private void printBoard(List<String> board) {
        for (String b : board) {
            System.out.print("[");
            printLine(b);
            System.out.println("]");
        }
    }

    private boolean validateAll(int[] board) {
        int pos = board.length - 1;
        while (pos >= 0) {
            if (!validSequences(board, pos--)) {
                return false;
            }
        }

        return true;
    }

    private boolean validSequences(int[] board, int index) {
        int pos = index - 1;

        while (pos >= 0) {
            if (!validSequence(board, pos--, index)) {
                return false;
            }
        }

        return true;
    }

    private boolean validSequence(int[] board, int prevIndex, int currIndex) {
        int prev = board[prevIndex];
        int curr = board[currIndex];

        if (curr == 0 || prev == 0) {
            return false;
        }

        return Math.abs(prev - curr) != (currIndex - prevIndex);
    }

    private int[] createBoard(int n) {
        int[] board = new int[n];

        for (int i = 0; i < n; i++) {
            board[i] = i + 1;
        }

        return board;
    }

    private void swapPositions(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private String queenAtPosition(int length, int pos) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i <= length; i++) {
            if (pos == i) {
                builder.append("Q");
            } else {
                builder.append(".");
            }
        }

        return builder.toString();
    }

    private List<String> boardToList(Map<Integer, String> cache, int[] arr) {
        List<String> l = new LinkedList<>();

        for (int i1 : arr) {
            l.add(cache.get(i1));
        }

        return l;
    }

    private Map<Integer, String> generateBoardCache(int n) {
        Map<Integer, String> cache = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            cache.put(i, queenAtPosition(n, i));
        }

        return cache;
    }

    private List<int[]> generateBoards(int n) {
        List<int[]> boards = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int[] board = createBoard(n);
            swapPositions(board, 0, i);

            List<int[]> g = generateBoards(board, n, 1);
            boards.addAll(g);
        }

        return boards;
    }

    private List<int[]> generateBoards(int[] board, int n, int pos) {
        List<int[]> boards = new LinkedList<>();

        if (n == pos && validateAll(board)) {
            boards.add(board);
        } else {
            for (int i = pos; i < n; i++) {
                int[] copy = Arrays.copyOf(board, board.length);
                swapPositions(copy, pos, i);

                if (!validSequence(copy, pos - 1, pos)) {
                    continue;
                }

                boards.addAll(generateBoards(copy, n, pos + 1));
            }
        }

        return boards;
    }

    public List<List<String>> solveNQueens(int n) {
        Map<Integer, String> boardCache = generateBoardCache(n);
        List<int[]> boards = generateBoards(n);

        List<List<String>> strings = new LinkedList<>();
        for (int[] board : boards) {
            List<String> boardAsString = boardToList(boardCache, board);
//            printArr(board);
//            printBoard(boardAsString);
//            System.out.println();
            strings.add(boardAsString);
        }

        return strings;
    }


    public static void main(String[] args) {
        NQueens queens = new NQueens();
//        System.out.println(queens.solveNQueens(1));
//        System.out.println(queens.solveNQueens(4));
        queens.solveNQueens(5);
    }
}

