/*
We scan the board

When we find an empty cell ('.'), we try placing digits '1' to '9'

Before placing, we call isValid(...) (similar to your isValidSudoku logic)

If valid, we place the digit and recurse deeper

If we reach a dead-end, we undo the move (backtracking)

When the board is completely filled, recursion unwinds with true

*/

class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;

                            // Recurse
                            if (backtrack(board)) {
                                return true; // solved
                            }

                            // Undo choice (backtrack)
                            board[i][j] = '.';
                        }
                    }
                    return false; // no valid digit works here
                }
            }
        }
        return true; // all cells filled
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        // check row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false;
        }

        // check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false;
        }

        // check 3x3 sub-box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == c) return false;
            }
        }

        return true;
    }
}
