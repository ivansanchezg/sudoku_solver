package com.ivansanchezg.sudokusolver;

public class SudokuValidator {
    
    public static boolean checkColumn(int[][] matrix, int value, int length, int row, int column) {
        //We iterate through the current column and compare. If the value repeats, it is not valid.
        for (int i = 0; i < length; i++) {
            if (matrix[i][column] == value && i != row) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkRow(int[][] matrix, int value, int length, int row, int column) {
        // Iterate through the current row and compare. If the value repeats, it is not valid.
        for (int i = 0; i < length; i++) {
            if (matrix[row][i] == value && i != column) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSquare(int[][] matrix, int value, int length, int row, int column) {
        // Get the size of the squares with the square root. 9x9 Sudoku has 3x3 squares, 16x16 Sudoku has 4x4 squares.
        int squareSize = (int) Math.sqrt(length);

        // Calculate the initial position of the square by dividing the row and column with the size of the square.
        // Example: In a 9x9 sudoku, if row is 4 and column 8, then the squareStartRow will be 1 and the squareStartColumn will be 2.
        // Then we multiple that by the size of the square, so we get 3 for squareStartRow and 6 for the SquareStartColumn.
        // The iteration is done by adding the square size to the multiplication. So in this example the square goes from
        // row 3 to 5 and from column 6 to 8.
        int squareStartRow = row / squareSize;
        int squareStartColumn = column / squareSize;

        for (int i = squareStartRow * squareSize; i < (squareStartRow * squareSize) + squareSize; i++) {
            for (int j = squareStartColumn * squareSize; j < (squareStartColumn * squareSize) + squareSize; j++) {
                if (matrix[i][j] == value && i != row && j != column) {
                    return false;
                }
            }
        }

        return true;
    }

}