- This Java program implements a command-line Tic-Tac-Toe game where a human player competes against an AI opponent. The code initializes a 3×3 board and uses a continuous game loop to accept the player's moves, update the board, and then trigger the AI's turn. The AI uses the minimax algorithm to evaluate potential moves and choose the optimal move, while helper methods are provided to check for wins, draws, and remaining moves. The game concludes by evaluating the final board state and announcing the winner or a draw.
- Here's a sample output for this tic-tac-toe game:
  - Welcome to Tic-Tac-Toe!
  ---------
  | _ _ _ |
  | _ _ _ |
  | _ _ _ |
  ---------
  Enter your move (row and column: 1 1 for top-left): 1 1
  ---------
  | X _ _ |
  | _ _ _ |
  | _ _ _ |
  ---------
  AI is making a move...
  ---------
  | X _ _ |
  | _ O _ |
  | _ _ _ |
  ---------
  Enter your move (row and column: 1 1 for top-left): 2 1
  ---------
  | X _ _ |
  | X O _ |
  | _ _ _ |
  ---------
  AI is making a move...
  ---------
  | X _ _ |
  | X O _ |
  | _ _ O |
  ---------
  Enter your move (row and column: 1 1 for top-left): 1 2
  ---------
  | X X _ |
  | X O _ |
  | _ _ O |
  ---------
  AI is making a move...
  ---------
  | X X O |
  | X O _ |
  | _ _ O |
  ---------
  Enter your move (row and column: 1 1 for top-left): 3 1
  ---------
  | X X O |
  | X O _ |
  | X _ O |
  ---------
  You win!
  
  
