import java.util.Scanner;

public class TicTacToe {
    // Constants for players and the empty cell
    static final char PLAYER = 'X'; // human player
    static final char AI = 'O';     // AI opponent
    static final char EMPTY = '_';

    // Create a board representation as a 3x3 matrix
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();

        System.out.println("Welcome to Tic-Tac-Toe!");
        printBoard();

        // Game loop; player always goes first.
        while (true) {
            // Human player's move
            playerMove(scanner);
            printBoard();
            if (hasWinner() || isBoardFull()) {
                break;
            }

            // AI's move
            System.out.println("AI is making a move...");
            Move bestMove = findBestMove();
            board[bestMove.row][bestMove.col] = AI;
            printBoard();
            if (hasWinner() || isBoardFull()) {
                break;
            }
        }

        // Evaluate final outcome
        int score = evaluate();
        if (score == +10) {
            System.out.println("AI wins!");
        } else if (score == -10) {
            System.out.println("You win!");
        } else {
            System.out.println("It is a draw!");
        }
        scanner.close();
    }

    // Initialize board with empty cells
    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Print the current state of the board
    static void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    // Check if there are moves left on the board
    static boolean isMovesLeft() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == EMPTY)
                    return true;
        return false;
    }

    // Evaluate the board:
    // +10 if AI wins, -10 if player wins, 0 otherwise.
    static int evaluate() {
        // Check rows for victory.
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] &&
                board[row][1] == board[row][2]) {
                if (board[row][0] == AI)
                    return +10;
                else if (board[row][0] == PLAYER)
                    return -10;
            }
        }

        // Check columns for victory.
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] &&
                board[1][col] == board[2][col]) {
                if (board[0][col] == AI)
                    return +10;
                else if (board[0][col] == PLAYER)
                    return -10;
            }
        }

        // Check diagonals for victory.
        if (board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            if (board[0][0] == AI)
                return +10;
            else if (board[0][0] == PLAYER)
                return -10;
        }
        if (board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            if (board[0][2] == AI)
                return +10;
            else if (board[0][2] == PLAYER)
                return -10;
        }
        // No winner: return 0.
        return 0;
    }

    // Minimax algorithm:
    // depth is current depth in game tree
    // isMax is true if the current move is of AI, false for human player's turn.
    static int minimax(int depth, boolean isMax) {
        int score = evaluate();

        // If the game has ended, return the score.
        if (score == 10 || score == -10)
            return score;

        // If there are no more moves, it's a draw.
        if (!isMovesLeft())
            return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == EMPTY) {
                        board[i][j] = AI;  // Make the move
                        best = Math.max(best, minimax(depth + 1, !isMax));
                        board[i][j] = EMPTY; // Undo the move
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == EMPTY) {
                        board[i][j] = PLAYER; // Make the move
                        best = Math.min(best, minimax(depth + 1, !isMax));
                        board[i][j] = EMPTY;  // Undo the move
                    }
                }
            }
            return best;
        }
    }

    // Find the best move for the AI player
    static Move findBestMove() {
        int bestVal = Integer.MIN_VALUE;
        Move bestMove = new Move(-1, -1);

        // Evaluate all possible moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == EMPTY) {
                    board[i][j] = AI; // Make the move
                    int moveVal = minimax(0, false);
                    board[i][j] = EMPTY; // Undo the move

                    // If the value of the current move is better than the best, update best
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    // Human player's move
    static void playerMove(Scanner scanner) {
        int row, col;
        while (true) {
            System.out.print("Enter your move (row and column: 1 1 for top-left): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume the newline
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid coordinates. Please try again.");
            } else if (board[row][col] != EMPTY) {
                System.out.println("Cell already occupied. Please choose another cell.");
            } else {
                board[row][col] = PLAYER;
                break;
            }
        }
    }

    // Check if there is a winner
    static boolean hasWinner() {
        int score = evaluate();
        return score == 10 || score == -10;
    }
}
