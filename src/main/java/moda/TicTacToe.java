package moda;

/*
tic tac toe where we accept a board in a particular format. the function should return
if someone one won and who did if so.

arbitary n-dimenion board

winning conditions:
player has a full row across
player has a full row down
and player has a full row diagonally TL->BR or BL->TR
*/

public class TicTacToe {

    enum Winner {
        X,
        O,
        None
    }

    private static char X_CHAR = 'X';
    private static char BLANK = ' ';

    /**
     * Generic method to test if a particular row, column or diagonal is completely filled
     * by a particular character.
     *
     * @param board
     * @param startRow
     * @param startCol
     * @param rowIncrement
     * @param colIncrement
     * @return
     */
    private static Winner checkRCD(char[][] board,
                                   int startRow,
                                   int startCol,
                                   int rowIncrement,
                                   int colIncrement) {
        char first = board[startRow][startCol];

        if (first == TicTacToe.BLANK) {
            return Winner.None;
        }

        int rowIndex = startRow + rowIncrement;
        int colIndex = startCol + colIncrement;

        for (int i = 1; i < board.length; i++) {
            char compare = board[rowIndex][colIndex];
            if (compare != first) {
                return Winner.None;
            }

            rowIndex += rowIncrement;
            colIndex += colIncrement;
        }

        if (first == TicTacToe.X_CHAR) {
            return Winner.X;
        }

        return Winner.O;
    }

    private static Winner checkDiagonalTop(char[][] board) {
        return checkRCD(board, 0, 0, 1, 1);
    }

    private static Winner checkDiagonalBottom(char[][] board) {
        return checkRCD(board, board.length - 1, 0, -1, 1);
    }

    private static Winner determineWinner(char[][] board) {
        // check rows
        for (int row = 0; row < board.length; row++) {
            Winner result = checkRCD(board, row, 0, 0, 1);
            if (result != Winner.None) {
                return result;
            }
        }

        // check columns
        for (int col = 0; col < board[0].length; col++) {
            Winner columnWinner = checkRCD(board, 0, col, 1, 0);
            if (columnWinner != Winner.None) {
                return columnWinner;
            }
        }

        // check diagonals
        Winner diagonalWinnerTop = checkDiagonalTop(board);
        if (diagonalWinnerTop != Winner.None) {
            return diagonalWinnerTop;
        }

        return checkDiagonalBottom(board);
    }

    public static void main(String[] args) {
        testFirstColumn();
        testSecondColumn();
        testThirdColumn();
        testFirstRow();
        testSecondRow();
        testThirdRow();
        testDiagonalTopDown();
        testDiagonalBottomUp();

        test4DiagonalBottomUp();


        System.out.println("Done");
    }

    private static void testFirstColumn() {
        char[][] board = {
                {'X', ' ', ' '},
                {'X', ' ', ' '},
                {'X', ' ', ' '}
        };

        assertTrue(determineWinner(board) == Winner.X, "Test first column Expected X to win");
    }

    private static void testSecondColumn() {
        char[][] board = {
                {' ', 'O', ' '},
                {' ', 'O', ' '},
                {' ', 'O', ' '}
        };

        assertTrue(determineWinner(board) == Winner.O, "Test second column Expected O to win");
    }

    private static void testThirdColumn() {
        char[][] board = {
                {' ', ' ', 'X'},
                {' ', ' ', 'X'},
                {' ', ' ', 'X'}
        };

        assertTrue(determineWinner(board) == Winner.X, "Test third column Expected X to win");
    }

    private static void testFirstRow() {
        char[][] board = {
                {'O', 'O', 'O'},
                {'X', ' ', 'O'},
                {'X', 'O', 'X'}
        };

        assertTrue(determineWinner(board) == Winner.O, "Test first row Expected O to win");
    }

    private static void testSecondRow() {
        char[][] board = {
                {' ', ' ', 'O'},
                {'O', 'O', 'O'},
                {'X', 'O', 'X'}
        };

        assertTrue(determineWinner(board) == Winner.O, "Test second row Expected O to win");
    }

    private static void testThirdRow() {
        char[][] board = {
                {'X', ' ', 'O'},
                {'X', 'O', 'X'},
                {'O', 'O', 'O'}
        };

        assertTrue(determineWinner(board) == Winner.O, "Test third row Expected O to win");
    }

    private static void testDiagonalTopDown() {
        char[][] board = {
                {'X', ' ', 'O'},
                {'O', 'X', 'X'},
                {'O', 'O', 'X'}
        };

        assertTrue(determineWinner(board) == Winner.X, "Test diagonal down expected X to win");
    }

    private static void testDiagonalBottomUp() {
        char[][] board = {
                {'X', ' ', 'O'},
                {'O', 'O', 'X'},
                {'O', 'O', 'X'}
        };

        assertTrue(determineWinner(board) == Winner.O, "Test diagonal up expected O to win");
    }

    private static void test4DiagonalBottomUp() {
        char[][] board = {
                {'X', ' ', 'O', 'O'},
                {'X', ' ', 'O', ' '},
                {'O', 'O', 'X', ' '},
                {'O', 'O', 'X', ' '}
        };

        assertTrue(determineWinner(board) == Winner.O, "Test diagonal up expected O to win");
    }


    private static void assertTrue(boolean result, String message) {
        if (!result) {
            System.err.println(message);
        }
    }
}







