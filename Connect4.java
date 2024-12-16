import java.util.Scanner;

public class Connect4 {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER1 = 'X';
    private static final char PLAYER2 = 'O';

    public static void main(String[] args) {
        char[][] board = new char[ROWS][COLS];
        initializeBoard(board);
        
        boolean gameWon = false;
        char activePlayer = PLAYER1;

        while (!gameWon && !isBoardFull(board)) {
            printBoard(board);
            int column = getPlayerMove(activePlayer);
            
            if (makeMove(board, column, activePlayer)) {
                if (isWinningMove(board, activePlayer)) {
                    gameWon = true;
                    printBoard(board);
                    System.out.println("Player " + activePlayer + " wins!");
                } else {
                    
                    if (activePlayer == PLAYER1) {
                        activePlayer = PLAYER2;
                    } else {
                        activePlayer = PLAYER1;
                    }
                       
                }
            } else {
                System.out.println("Column is full! Try again.");
            }
        }

        if (!gameWon) {
            printBoard(board);
            System.out.println("It's a tie!");
        }
    }

    private static void initializeBoard(char[][] board) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    private static void printBoard(char[][] board) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        for (int col = 0; col < COLS; col++) {
            System.out.print(col + 1 + " ");
        }
        System.out.println();
    }

    private static int getPlayerMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int column;
        while (true) {
            System.out.print("Player " + player + ", choose a column (1-7): ");
            try {
                column = scanner.nextInt() - 1;
                if (column >= 0 && column < COLS) {
                    return column;
                }
            } catch (Exception e) {
                scanner.next(); // 
            }
            System.out.println("Invalid input. Please try again.");
        }
    }

    private static boolean makeMove(char[][] board, int column, char player) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == EMPTY) {
                board[row][column] = player;
                return true;
            }
        }
        return false;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private static boolean isWinningMove(char[][] board, char player) {
        // Check horizontal
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLS - 4; col++) {
                if (board[row][col] == player &&
                    board[row][col + 1] == player &&
                    board[row][col + 2] == player &&
                    board[row][col + 3] == player) {
                    return true;
                }
            }
        }

       
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == player &&
                    board[row + 1][col] == player &&
                    board[row + 2][col] == player &&
                    board[row + 3][col] == player) {
                    return true;
                }
            }
        }

        
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col <= COLS - 4; col++) {
                if (board[row][col] == player &&
                    board[row - 1][col + 1] == player &&
                    board[row - 2][col + 2] == player &&
                    board[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }

        
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLS - 4; col++) {
                if (board[row][col] == player &&
                    board[row + 1][col + 1] == player &&
                    board[row + 2][col + 2] == player &&
                    board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }
}    